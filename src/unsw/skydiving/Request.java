package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Request {

    public void requestTraining(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String trainee = json.getString(SkydiveBookingSystem.TRAINEE);
        // Resources.addJump(new Training(id, start, Resources.assignInstructor(), trainee));
    }

    public void requestFunJump(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        JSONArray jsonArray = json.getJSONArray(SkydiveBookingSystem.SKYDIVERS);
        ArrayList<Skydiver> jumpers = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String name = (jsonArray.getString(i));
            jumpers.add(new Skydiver(id, name));
        }
        Resources.addJump(new FunJump(id, start, jumpers));
    }

    public void requestTandemJump(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        String passenger = json.getString(SkydiveBookingSystem.PASSENGER);
        // Resources.addJump(new TandemJump(id, start, Resources.assignMaster(), passenger));
    }
}
