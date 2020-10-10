/**
 * Encapsulate request commands
 */

package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Request {

    /**
     * Requests training
     * 
     * @param json
     */
    public void requestTraining(JSONObject json) {
        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String traineeStr = json.getString(SkydiveBookingSystem.TRAINEE);

        // Get jumpers
        Skydiver trainee = Resources.getSkydiver(traineeStr);

        // Find a suitable flight
        for (Plane plane : Resources.getFlights()) {

            LocalDateTime flightStart = plane.getTimeSlot().start;
            LocalDateTime flightEnd = plane.getTimeSlot().end;

            // Jump start must be after plane start
            if (start.isAfter(flightStart)) {

                // Check maxload requirement
                if (2 + plane.getCurrentLoad() <= plane.getMaxload()) {

                    // Find a suitable instructor for flight
                    for (Instructor trainer : Resources.getInstructors()) {

                        // Instructor is available
                        if (!plane.getTimeSlot().isInTimeSlot(trainer.getSchedule())) {

                            Training jump = new Training(id, start, trainer, trainee);

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

    public void requestFunJump(JSONObject json) {
        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        JSONArray jumperArray = json.getJSONArray(SkydiveBookingSystem.SKYDIVERS);

        // Get jumpers
        ArrayList<Skydiver> jumpers = new ArrayList<>();
        for (int i = 0; i < jumperArray.length(); i++) {
            String name = jumperArray.getString(i);
            jumpers.add(Resources.getSkydiver(name));
        }

        // Find a suitable flight
        for (Plane plane : Resources.getFlights()) {

            LocalDateTime flightStart = plane.getTimeSlot().start;
            LocalDateTime flightEnd = plane.getTimeSlot().end;

            // Jump start must be after plane start
            if (start.isAfter(flightStart)) {

                // Check maxload requirement
                if (jumpers.size() + plane.getCurrentLoad() <= plane.getMaxload()) {

                    FunJump jump = new FunJump(id, start, jumpers);

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

    public void requestTandemJump(JSONObject json) {
        // Unpack the json
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String passengerStr = json.getString(SkydiveBookingSystem.PASSENGER);

        // Get jumpers
        Skydiver passenger = Resources.getSkydiver(passengerStr);

        // Find a suitable flight
        for (Plane plane : Resources.getFlights()) {

            LocalDateTime flightStart = plane.getTimeSlot().start;
            LocalDateTime flightEnd = plane.getTimeSlot().end;

            // Jump start must be after plane start
            if (start.plusMinutes(Jump.BRIEF_TIME).isAfter(flightStart)) {

                // Check maxload requirement
                if (2 + plane.getCurrentLoad() <= plane.getMaxload()) {

                    // Find a suitable Master for flight
                    for (Master master : Resources.getTandemMasters()) {

                        // Master is available
                        if (!plane.getTimeSlot().isInTimeSlot(master.getSchedule())) {

                            TandemJump jump = new TandemJump(id, start, master, passenger);

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
