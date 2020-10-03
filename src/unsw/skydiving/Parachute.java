package unsw.skydiving;

public class Parachute {

    private boolean deployed;

    public Parachute() {
        this.deployed = false;
    }

    public void deploy() {
        this.deployed = true;
    }

    public void pack() {
        this.deployed = false;
    }
}
