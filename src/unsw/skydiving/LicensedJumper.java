package unsw.skydiving;

public class LicensedJumper extends Student {
    public LicensedJumper(String id, String name) {
        super(id, name);
    }

    public void pack() {
        this.parachute.pack();
    }
}
