package unsw.skydiving;

import org.json.JSONObject;

public class Register {

    public void add(JSONObject json) {
        String name = json.getString(SkydiveBookingSystem.SKYDIVER);
        String license = json.getString(SkydiveBookingSystem.LICENCE);

        switch (license) {
            case SkydiveBookingSystem.STUDENT:
                Resources.addSkydiver(new Student(name));
                break;
            case SkydiveBookingSystem.LICENSED_JUMPER:
                Resources.addSkydiver(new LicensedJumper(name));
                break;
            case SkydiveBookingSystem.INSTRUCTOR:
                Resources.addSkydiver(
                        new Instructor(name, json.getString(SkydiveBookingSystem.DROPZONE)));
                break;
            case SkydiveBookingSystem.TANDEM_MASTER:
                Resources.addSkydiver(
                        new Master(name, json.getString(SkydiveBookingSystem.DROPZONE)));
                break;
        }
    }
}
