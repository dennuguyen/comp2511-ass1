/**
 * Jump class
 */
package unsw.skydiving;

import java.util.ArrayList;

public class Jump {

    public static final int BRIEF_TIME = 5;
    public static final int PACK_TIME = 10;
    public static final int DEBRIEF_TIME = 15;

    protected String id;

    public Jump(String id) {
        this.id = id;
    }

    /**
     * Gets the id of the Jump
     * 
     * @return id: String
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets the skydivers on this Jump
     * 
     * @return ArrayList<Skydiver>
     */
    public ArrayList<Skydiver> getSkydivers() {
        return null;
    }
}
