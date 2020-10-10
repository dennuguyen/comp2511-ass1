package unsw.skydiving;

import java.util.ArrayList;
import org.json.JSONObject;

public class JumpRun {

    public void generate(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        ArrayList<Jump> jumps = Resources.getFlight(id).getJumps();
        ArrayList<Jump> runs = new ArrayList<Jump>();
        for (Jump jump : jumps) {
            System.out.println(new JSONObject(jump));
        }
    }
}
