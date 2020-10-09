package unsw.skydiving;

import org.json.JSONObject;

public class Cancel {

    public void cancel(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        for (Plane flight : Resources.getFlights())
            for (Jump jump : flight.getJumps())
                if (jump.getID() == id) {
                    flight.removeJump(id);
                    return;
                }
    }
}
