package unsw.skydiving;

public class LicensedJumper extends Student {
    public LicensedJumper(String id) {
        super(id);
    }

    public void pack() {
        this.parachute.pack();
    }
}
