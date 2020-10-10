package unsw.skydiving;

import org.json.JSONObject;

public class JumpRun {

    public void generate(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        for (Jump jump : Resources.getFlight(id).getJumps()) {
            System.out.println(new JSONObject(jump));
        }
    }
}
