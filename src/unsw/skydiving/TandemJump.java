package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TandemJump extends Jump {

    private Master master;
    private Skydiver passenger;

    public TandemJump(String id, LocalDateTime start, Master master, Skydiver passenger) {
        super(id, start);
        this.master = master;
        this.passenger = passenger;
        // this.master.addTimeSlot(new TimeSlot(start, start.plusMinutes(5)));
        // this.passenger.addTimeSlot(new TimeSlot(start, start.plusMinutes(5)));
    }

    @Override
    public void jump() {
        this.master.jump();
        this.passenger.jump();
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>();
        skydivers.add(this.master);
        skydivers.add(this.passenger);
        return skydivers;
    }
}
