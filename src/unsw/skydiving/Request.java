/**
 * Request commands handler
 */

package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Request {

    private Resources resources;

    Request(Resources resources) {
        this.resources = resources;
    }

    /**
     * Extracts the json fields and creates a Training object to be passed to the resource handler.
     * Bookkeeping conditions are checked in this request. Skydivers involved in the jump also have
     * their time schedule updated.
     * 
     * @param json
     */
    public void requestTraining(JSONObject json) {
        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String traineeStr = json.getString(SkydiveBookingSystem.TRAINEE);

        // Get jumpers
        Skydiver trainee = resources.getSkydiver(traineeStr);

        // Find a suitable flight
        for (Plane plane : resources.getFlights()) {

            LocalDateTime flightStart = plane.getTimeSlot().getStartTime();
            LocalDateTime flightEnd = plane.getTimeSlot().getEndTime();

            // Jump start must be after plane start
            if (start.isAfter(flightStart)) {

                // Check maxload requirement
                if (2 + plane.getCurrentLoad() <= plane.getMaxload()) {

                    // Find a suitable instructor for flight
                    for (Instructor trainer : resources.getInstructors()) {

                        // Instructor is available
                        if (!plane.getTimeSlot().clashes(trainer.getSchedule())) {

                            Training jump = new Training(id, trainer, (Student) trainee);

                            // Add jump to flight
                            plane.addJump(jump);

                            // Add timeslot to skydiver schedules
                            trainer.addTimeSlot(new TimeSlot(flightStart,
                                    flightEnd.plusMinutes(Jump.DEBRIEF_TIME + Jump.PACK_TIME)));
                            trainee.addTimeSlot(new TimeSlot(flightStart,
                                    flightEnd.plusMinutes(Jump.DEBRIEF_TIME)));
                            return;
                        }
                    }
                }
            }
        }

        return;
    }

    /**
     * Extracts the json fields and creates a FunJump object to be passed to the resource handler.
     * Bookkeeping conditions are checked in this request. Skydivers involved in the jump also have
     * their time schedule updated.
     * 
     * @param json
     */
    public void requestFunJump(JSONObject json) {
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

        // Find a suitable flight
        for (Plane plane : resources.getFlights()) {

            LocalDateTime flightStart = plane.getTimeSlot().getStartTime();
            LocalDateTime flightEnd = plane.getTimeSlot().getEndTime();

            // Jump start must be after plane start
            if (start.isAfter(flightStart)) {

                // Check maxload requirement
                if (jumpers.size() + plane.getCurrentLoad() <= plane.getMaxload()) {

                    FunJump jump = new FunJump(id, jumpers);

                    // Add jump to flight
                    plane.addJump(jump);

                    // Add timeslot to skydiver schedules
                    for (Skydiver jumper : jumpers)
                        jumper.addTimeSlot(
                                new TimeSlot(flightStart, flightEnd.plusMinutes(Jump.PACK_TIME)));
                    return;
                }
            }
        }

        return;
    }

    /**
     * Extracts the json fields and creates a TandemJump object to be passed to the resource
     * handler. Bookkeeping conditions are checked in this request. Skydivers involved in the jump
     * also have their time schedule updated.
     * 
     * @param json
     */
    public void requestTandemJump(JSONObject json) {
        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String passengerStr = json.getString(SkydiveBookingSystem.PASSENGER);

        // Get jumpers
        Skydiver passenger = resources.getSkydiver(passengerStr);

        // Find a suitable flight
        for (Plane plane : resources.getFlights()) {

            LocalDateTime flightStart = plane.getTimeSlot().getStartTime();
            LocalDateTime flightEnd = plane.getTimeSlot().getEndTime();

            // Jump start must be after plane start
            if (start.plusMinutes(Jump.BRIEF_TIME).isAfter(flightStart)) {

                // Check maxload requirement
                if (2 + plane.getCurrentLoad() <= plane.getMaxload()) {

                    // Find a suitable Master for flight
                    for (Master master : resources.getTandemMasters()) {

                        // Master is available
                        if (!plane.getTimeSlot().clashes(master.getSchedule())) {

                            TandemJump jump = new TandemJump(id, master, passenger);

                            // Add jump to flight
                            plane.addJump(jump);

                            // Add timeslot to skydiver schedules
                            master.addTimeSlot(new TimeSlot(flightStart,
                                    flightEnd.plusMinutes(Jump.DEBRIEF_TIME + Jump.PACK_TIME)));
                            passenger.addTimeSlot(new TimeSlot(flightStart,
                                    flightEnd.plusMinutes(Jump.DEBRIEF_TIME)));
                            return;
                        }
                    }
                }
            }
        }

        return;
    }
}
