/**
 * jump-run command handler
 */

package unsw.skydiving;

import java.util.ArrayList;
import org.json.JSONObject;

public class JumpRun {

    private Resources resources;

    public JumpRun(Resources resources) {
        this.resources = resources;
    }

    /**
     * Outputs a list of jumps in the required order
     * 
     * @param json
     */
    public void generate(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        ArrayList<Jump> jumps = resources.getFlight(id).getJumps();
        System.out.println(new JSONObject(resources.getFlight(id)));
        System.out.println();
        ArrayList<Jump> runs = new ArrayList<Jump>();
        for (Jump jump : jumps) {
            // System.out.println(new JSONObject(jump));
        }
    }
}
