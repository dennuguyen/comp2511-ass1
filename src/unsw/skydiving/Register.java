/**
 * Register commands handler
 */

package unsw.skydiving;

import org.json.JSONObject;

public class Register {

    Resources resources;

    public Register(Resources resources) {
        this.resources = resources;
    }

    /**
     * Extracts the json fields and creates a Skydiver object to be passed to the resource handler
     * 
     * @param json Register command in JSON format
     */
    public void add(JSONObject json) {
        String name = json.getString(SkydiveBookingSystem.SKYDIVER);
        String license = json.getString(SkydiveBookingSystem.LICENCE);
        switch (license) {
            case SkydiveBookingSystem.STUDENT:
                resources.addSkydiver(new Student(name));
                break;
            case SkydiveBookingSystem.LICENCED_JUMPER:
                resources.addSkydiver(new LicensedJumper(name));
                break;
            case SkydiveBookingSystem.INSTRUCTOR:
                resources.addSkydiver(
                        new Instructor(name, json.getString(SkydiveBookingSystem.DROPZONE)));
                break;
            case SkydiveBookingSystem.TANDEM_MASTER:
                resources.addSkydiver(
                        new Master(name, json.getString(SkydiveBookingSystem.DROPZONE)));
                break;
        }
    }
}
