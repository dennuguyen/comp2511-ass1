/**
 * Encapsulate request commands
 */

package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Request {

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

            // Find a suitable instructor
            for (Instructor trainer : Resources.getInstructors()) {

                // Instructor is available
                if (trainer.getFreeTimeSlot(new TimeSlot(flightStart, flightEnd)) != null) {

                    // Jump start must be after plane start
                    if (start.isAfter(flightStart)) {

                        // Check maxload requirement
                        if (2 + plane.getCurrentLoad() <= plane.getMaxload()) {

                            Jump jump = new Training(id, start, trainer, trainee);

                            // Add jump to flight
                            plane.addJump(jump);

                            // Add timeslot to skydiver schedules
                            trainer.addTimeSlot(new TimeSlot(flightStart,
                                    flightEnd.plusMinutes(jump.DEBRIEF_TIME + jump.PACK_TIME)));
                            trainee.addTimeSlot(new TimeSlot(flightStart,
                                    flightEnd.plusMinutes(jump.DEBRIEF_TIME)));
                            break;
                        }
                    }
                }
            }
        }
    }

    public void requestFunJump(JSONObject json) {
        // // Unpack the json
        // String id = json.getString(SkydiveBookingSystem.ID);
        // LocalDateTime start =
        // LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        // JSONArray jumperArray = json.getJSONArray(SkydiveBookingSystem.SKYDIVERS);

        // // Get jumpers
        // ArrayList<Skydiver> jumpers = new ArrayList<Skydiver>();
        // for (int i = 0; i < jumperArray.length(); i++) {
        // String name = (jumperArray.getString(i));
        // jumpers.add(Resources.getSkydiver(name));
        // }
        // FunJump jump = new FunJump(id, start, jumpers);

        // // Find a suitable flight
        // for (Plane plane : Resources.getFlights()) {

        // LocalDateTime flightStart = plane.getTimeSlot().start;
        // LocalDateTime flightEnd = plane.getTimeSlot().end;

        // // Jump start must be after plane start
        // if (start.isAfter(plane.getTimeSlot().start))
        // // Check maxload requirement
        // if (jump.getSkydivers().size() + plane.getCurrentLoad() <= plane.getMaxload()) {
        // // Add jump to flight
        // plane.addJump(jump);

        // // Add timeslot to skydiver schedules
        // for (Skydiver jumper : jumpers)
        // jumper.addTimeSlot(
        // new TimeSlot(flightStart, flightEnd.plusMinutes(jump.PACK_TIME)));
        // break;
        // }
        // }
    }

    public void requestTandemJump(JSONObject json) {
        // // Unpack the json
        // String id = json.getString(SkydiveBookingSystem.ID);
        // LocalDateTime start =
        // LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        // String passengerStr = json.getString(SkydiveBookingSystem.PASSENGER);

        // // Get jumpers
        // Skydiver passenger = Resources.getSkydiver(passengerStr);
        // Master master = Resources.getTandemMasters();
        // TandemJump jump = new TandemJump(id, start, master, passenger);

        // // Find a suitable flight
        // for (Plane plane : Resources.getFlights()) {
        // // Jump start must be after plane start
        // if (start.plusMinutes(jump.BRIEF_TIME).isAfter(plane.getTimeSlot().start))
        // // Check maxload requirement
        // if (jump.getSkydivers().size() + plane.getCurrentLoad() <= plane.getMaxload()) {
        // // Add jump to flight
        // plane.addJump(jump);

        // // Add timeslot to skydiver schedules
        // master.addTimeSlot(new TimeSlot(start.plusMinutes(jump.BRIEF_TIME),
        // plane.getTimeSlot().end.plusMinutes(jump.PACK_TIME)));
        // passenger.addTimeSlot(new TimeSlot(start.plusMinutes(jump.BRIEF_TIME),
        // plane.getTimeSlot().end));
        // break;
        // }
        // }
    }
}
