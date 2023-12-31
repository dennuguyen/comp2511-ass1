/**
 * Request commands handler
 */

package unsw.skydiving;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

public class Request {

    private FileWriter outputFile;
    private Resources resources;

    /**
     * Constructor
     * 
     * @param outputFile Handle to output file
     * @param resources  Handle to resource manager
     */
    Request(FileWriter outputFile, Resources resources) {
        this.outputFile = outputFile;
        this.resources = resources;
    }

    /**
     * Helper function to filter flights
     * 
     * @param flights      List of flights to filter
     * @param skydivers    List of skydivers to consider
     * @param isInstructor 3-state variable
     * @return Flight to add jump to
     */
    private Plane filterFlights(ArrayList<Plane> flights, ArrayList<Skydiver> skydivers) {

        ArrayList<Plane> filteredFlights = new ArrayList<Plane>();

        // Filter flights for the earliest flight time possible
        LocalDateTime min = null;
        for (Iterator<Plane> i = flights.iterator(); i.hasNext();) {
            Plane flight = i.next();

            if (min == null || flight.getTimeSlot().getStartTime().isBefore(min)) {
                filteredFlights.clear();
                filteredFlights.add(flight);
                min = flight.getTimeSlot().getStartTime();
            } else if (flight.getTimeSlot().getStartTime().isEqual(min))
                filteredFlights.add(flight);
        }

        // Filter flights with consideration of skydiver schedules
        for (Iterator<Plane> i = filteredFlights.iterator(); i.hasNext();) {
            Plane flight = i.next();

            // Check if skydiver schedules are okay
            for (Skydiver skydiver : skydivers)
                if (flight.getTimeSlot().clashes(skydiver.getSchedule())) {
                    i.remove();
                    break;
                }
        }

        // Filter flights by dropzone vacancies
        // Get dropzones
        HashMap<String, Integer> dropzones = new HashMap<String, Integer>();
        for (Plane flight : filteredFlights) {
            String name = flight.getDropzone();
            if (!dropzones.containsKey(name))
                dropzones.put(name, 1);
            else {
                int count = dropzones.get(name);
                dropzones.put(name, count + flight.getMaxload() - flight.getCurrentLoad());
            }
        }

        // Find dropzone with max vacancy
        Map.Entry<String, Integer> max = null;
        for (Map.Entry<String, Integer> entry : dropzones.entrySet()) {
            if (max == null || entry.getValue().compareTo(max.getValue()) > 0)
                max = entry;
        }

        // Filter for flights with the dropzone with max vacancy
        for (Iterator<Plane> i = filteredFlights.iterator(); i.hasNext();) {
            Plane flight = i.next();
            if (!Objects.equals(flight.getDropzone(), max.getKey()))
                i.remove();
        }

        // Get earliest flight from filtered list or null otherwise
        return filteredFlights.isEmpty() ? null : filteredFlights.get(0);
    }

    /**
     * Helper function to write Request output
     * 
     * @param success Indicate successful output
     * @param plane   Plane to extract info from
     */
    private void writeOutput(boolean success, Plane plane) {
        try {
            JSONObject output = new JSONObject();
            if (success == true) {
                output.put("flight", plane.getID());
                output.put("dropzone", plane.getDropzone());
                output.put("status", "success");
            } else {
                output.put("status", "rejected");
            }

            this.outputFile.write(output.toString(2) + "\n");
            this.outputFile.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Extracts the json fields and creates a Training object to be passed to the resource handler.
     * Bookkeeping conditions are checked in this request. Skydivers involved in the jump also have
     * their time schedule updated.
     * 
     * @param json Request command in JSON format
     * @return boolean on success or rejected status
     */
    public boolean requestTraining(JSONObject json) {

        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String traineeStr = json.getString(SkydiveBookingSystem.TRAINEE);

        // Get jumpers
        Skydiver trainee = resources.getSkydiver(traineeStr);

        // Get all available flights
        ArrayList<Plane> flights = resources.getAvailableFlights(start, 2);

        // Filter flights
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>();
        skydivers.add(trainee);
        Plane plane = this.filterFlights(flights, skydivers);

        // Check if flight exists
        if (plane == null) {
            this.writeOutput(false, null);
            return false;
        }

        // Get trainer
        Instructor trainer =
                resources.getNextInstructor(plane.getTimeSlot(), plane.getDropzone(), trainee);

        // Check if trainer exists
        if (trainer == null) {
            this.writeOutput(false, null);
            return false;
        }

        // Get flight start and end times
        LocalDateTime flightStart = plane.getTimeSlot().getStartTime();
        LocalDateTime flightEnd = plane.getTimeSlot().getEndTime();

        // Add jump to plane
        Training jump = new Training(id, trainer, trainee);
        plane.addJump(jump);

        // Add time slot to skydiver schedules
        trainer.addTimeSlot(new TimeSlot(id, flightStart,
                flightEnd.plusMinutes(Jump.DEBRIEF_TIME + Jump.PACK_TIME)));
        if (trainee instanceof Student)
            trainee.addTimeSlot(
                    new TimeSlot(id, flightStart, flightEnd.plusMinutes(Jump.DEBRIEF_TIME)));
        else
            trainee.addTimeSlot(new TimeSlot(id, flightStart,
                    flightEnd.plusMinutes(Jump.DEBRIEF_TIME + Jump.PACK_TIME)));

        // Output status
        this.writeOutput(true, plane);
        return true;
    }

    /**
     * Extracts the json fields and creates a FunJump object to be passed to the resource handler.
     * Bookkeeping conditions are checked in this request. Skydivers involved in the jump also have
     * their time schedule updated.
     * 
     * @param json Request command in JSON format
     * @return boolean on success or rejected status
     */
    public boolean requestFunJump(JSONObject json) {
        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        JSONArray jumperArray = json.getJSONArray(SkydiveBookingSystem.SKYDIVERS);

        // Get jumpers
        ArrayList<LicensedJumper> jumpers = new ArrayList<>();
        for (int i = 0; i < jumperArray.length(); i++) {
            String name = jumperArray.getString(i);
            jumpers.add((LicensedJumper) resources.getSkydiver(name));
        }

        // Get all available flights
        ArrayList<Plane> flights = resources.getAvailableFlights(start, jumpers.size());

        // Filter flights
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>(jumpers);
        Plane plane = this.filterFlights(flights, skydivers);

        // Check if flight exists
        if (plane == null) {
            this.writeOutput(false, null);
            return false;
        }

        // Get flight start and end times
        LocalDateTime flightStart = plane.getTimeSlot().getStartTime();
        LocalDateTime flightEnd = plane.getTimeSlot().getEndTime();

        // Add jump to flight
        FunJump jump = new FunJump(id, jumpers);
        plane.addJump(jump);

        // Add time slot to skydiver schedules
        for (Skydiver jumper : jumpers)
            jumper.addTimeSlot(
                    new TimeSlot(id, flightStart, flightEnd.plusMinutes(Jump.PACK_TIME)));

        this.writeOutput(true, plane);
        return true;
    }

    /**
     * Extracts the json fields and creates a TandemJump object to be passed to the resource
     * handler. Bookkeeping conditions are checked in this request. Skydivers involved in the jump
     * also have their time schedule updated.
     * 
     * @param json Request command in JSON format
     * @return boolean on success or rejected status
     */
    public boolean requestTandemJump(JSONObject json) {
        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String passengerStr = json.getString(SkydiveBookingSystem.PASSENGER);

        // Get jumpers
        Skydiver passenger = resources.getSkydiver(passengerStr);

        // Get all available flights
        ArrayList<Plane> flights =
                resources.getAvailableFlights(start.plusMinutes(Jump.BRIEF_TIME), 2);

        // Filter flights
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>();
        skydivers.add(passenger);
        Plane plane = this.filterFlights(flights, skydivers);

        // Check if flight exists
        if (plane == null) {
            this.writeOutput(false, null);
            return false;
        }

        // Get tandem master
        Master master =
                resources.getNextMaster(plane.getTimeSlot(), plane.getDropzone(), passenger);

        // Check if tandem master exists
        if (master == null) {
            this.writeOutput(false, null);
            return false;
        }

        // Get flight start and end times
        LocalDateTime flightStart = plane.getTimeSlot().getStartTime();
        LocalDateTime flightEnd = plane.getTimeSlot().getEndTime();

        // Add jump to plane
        TandemJump jump = new TandemJump(id, master, passenger);
        plane.addJump(jump);

        // Add time slot to skydiver schedules
        master.addTimeSlot(new TimeSlot(id, flightStart.minusMinutes(Jump.BRIEF_TIME),
                flightEnd.plusMinutes(Jump.PACK_TIME)));
        passenger.addTimeSlot(
                new TimeSlot(id, flightStart.minusMinutes(Jump.BRIEF_TIME), flightEnd));

        // Output status
        this.writeOutput(true, plane);
        return true;
    }
}
