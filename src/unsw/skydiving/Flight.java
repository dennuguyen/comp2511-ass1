package unsw.skydiving;

import java.time.LocalDateTime;
import org.json.JSONObject;

public class Flight {

    public void createDropZone(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        int maxload = json.getInt(SkydiveBookingSystem.MAXLOAD);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        LocalDateTime end = LocalDateTime.parse(json.getString(SkydiveBookingSystem.ENDTIME));
        String dropzone = json.getString(SkydiveBookingSystem.DROPZONE);
        Resources.addFlight(new Plane(id, dropzone, start, end, maxload));
    }
}
