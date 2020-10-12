/**
 * Jump class
 */
package unsw.skydiving;

import java.util.ArrayList;
import org.json.JSONObject;

public class Jump {

    /* Jump time constants */
    public static final int BRIEF_TIME = 5;
    public static final int PACK_TIME = 10;
    public static final int DEBRIEF_TIME = 15;

    protected String id;

    /**
     * Constructor
     * 
     * @param id Jump id
     */
    public Jump(String id) {
        this.id = id;
    }

    /**
     * Gets the id of the jump
     * 
     * @return id of jump
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets the skydivers on this jump
     * 
     * @return skydivers in jump as ArrayList
     */
    public ArrayList<Skydiver> getSkydivers() {
        return null;
    }

    /**
     * Gets the jump run order for this jump
     * 
     * @return ordered skydivers in jump as JSONObject
     */
    public JSONObject getJumpRun() {
        return null;
    }
}
