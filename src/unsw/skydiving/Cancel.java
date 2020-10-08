package unsw.skydiving;

import org.json.JSONObject;

public class Cancel {

    public void cancel(JSONObject json) {
        Resources.removeJump(json.getString(SkydiveBookingSystem.ID));
    }
}
