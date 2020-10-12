/**
 * Cancel command handler
 */

package unsw.skydiving;

import java.util.Objects;
import org.json.JSONObject;

public class Cancel {

    private Resources resources;

    /**
     * Constructor
     * 
     * @param resources Handle to resource manager
     */
    public Cancel(Resources resources) {
        this.resources = resources;
    }

    /**
     * Finds the jump by its id from all the flights managed by the resource handler
     * 
     * @param json Cancel command in JSON format
     * @return boolean on success or rejected status
     */
    public boolean cancel(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        for (Plane flight : resources.getFlights())
            for (Jump jump : flight.getJumps())
                if (Objects.equals(jump.getID(), id)) {
                    flight.removeJump(id);
                    return true;
                }
        return false;
    }
}
