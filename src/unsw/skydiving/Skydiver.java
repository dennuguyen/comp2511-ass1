package unsw.skydiving;

public class Skydiver {

    protected String id;
    protected String name;
    protected Parachute parachute;

    public Skydiver(String id, String name) {
        this.id = id;
        this.name = name;
        this.parachute = null;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void equipParachute() {
        this.parachute = new Parachute();
    }

    public void jump() {
        this.parachute.deploy();
    }
}
