/**
 * FunJump is a type of Jump and has a group of jumpers of type LicensedJumper
 */

package unsw.skydiving;

import java.util.ArrayList;
import java.util.Collections;
import org.json.JSONArray;
import org.json.JSONObject;

public class FunJump extends Jump {

    private ArrayList<LicensedJumper> jumpers;

    public FunJump(String id, ArrayList<LicensedJumper> jumpers) {
        super(id);
        this.jumpers = jumpers;
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>();
        for (LicensedJumper jumper : this.jumpers)
            skydivers.add(jumper);
        return skydivers;
    }

    @Override
    public JSONObject getJumpRun() {

        // Sort jumpers by their lexicographical order
        Collections.sort(this.jumpers, (a, b) -> a.getID().compareTo(b.getID()));

        // Extract names
        JSONArray sortedJumpers = new JSONArray();
        for (Skydiver jumper : jumpers)
            sortedJumpers.put(jumper.getID());

        // Create json entry
        JSONObject jumpRun = new JSONObject();
        jumpRun.put("skydivers", sortedJumpers);

        return jumpRun;
    }
}
