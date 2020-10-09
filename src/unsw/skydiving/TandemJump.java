package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class TandemJump extends Jump {

    private Master master;
    private Skydiver passenger;

    public TandemJump(String id, LocalDateTime start, Master master, Skydiver passenger) {
        super(id, start);
        this.master = master;
        this.passenger = passenger;
    }

    @Override
    public void jump() {
        this.master.jump();
        this.passenger.jump();
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        return new ArrayList<Skydiver>(Arrays.asList(this.master, this.passenger));
    }
}
