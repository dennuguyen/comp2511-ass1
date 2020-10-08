package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class Change {

    public void changeTraining(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        // new Training(id, start, trainer, trainee);
    }

    public void changeFunJump(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        LocalDateTime start = LocalDateTime.parse(json.getString(SkydiveBookingSystem.STARTTIME));
        JSONArray jsonArray = json.getJSONArray(SkydiveBookingSystem.SKYDIVERS);
        ArrayList<Skydiver> jumpers = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            String name = (jsonArray.getString(i));
            jumpers.add(new Skydiver(id, name));
        }
        new FunJump(id, start, jumpers);
    }

    public void changeTandemJump(JSONObject json) {
        // new TandemJump(id, start, master, passenger);
    }
}
