package unsw.skydiving;

import org.json.JSONObject;

public class Register {

    public void add(JSONObject json) {
        String name = json.getString(SkydiveBookingSystem.SKYDIVER);
        String license = json.getString(SkydiveBookingSystem.LICENCE);

        switch (license) {
            case SkydiveBookingSystem.STUDENT:
                Resources.addSkydiver(new Student(name, name));
            case SkydiveBookingSystem.LICENSED_JUMPER:
                Resources.addSkydiver(new LicensedJumper(name, name));
            case SkydiveBookingSystem.INSTRUCTOR:
                Resources.addSkydiver(new Instructor(name, name));
            case SkydiveBookingSystem.TANDEM_MASTER:
                Resources.addSkydiver(new Master(name, name));
        }
    }
}
