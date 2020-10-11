/**
 * Flight commands handler
 */

package unsw.skydiving;

import java.time.LocalDateTime;
import org.json.JSONObject;

public class Flight {

    private Resources resources;

    public Flight(Resources resources) {
        this.resources = resources;
    }

    /**
     * Extracts the json fields and creates a plane object to be passed to the resource handler
     * 
     * @param json
     */
    public void createFlight(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        int maxload = json.getInt(SkydiveBookingSystem.MAXLOAD);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        LocalDateTime end = LocalDateTime.parse(json.getString(SkydiveBookingSystem.ENDTIME));
        String dropzone = json.getString(SkydiveBookingSystem.DROPZONE);
        resources.addFlight(new Plane(id, dropzone, start, end, maxload));
        resources.sortFlights();
    }
}
