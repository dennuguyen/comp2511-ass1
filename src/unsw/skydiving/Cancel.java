/**
 * Cancel command handler
 */

package unsw.skydiving;

import org.json.JSONObject;

public class Cancel {

    private Resources resources;

    public Cancel(Resources resources) {
        this.resources = resources;
    }

    /**
     * Finds the jump by its id from all the flights managed by the resource handler
     * 
     * @param json
     */
    public void cancel(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        for (Plane flight : resources.getFlights())
            for (Jump jump : flight.getJumps())
                if (jump.getID() == id) {
                    flight.removeJump(id);
                    return;
                }
    }
}
