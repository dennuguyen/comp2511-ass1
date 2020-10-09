package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Plane {

    private String id;
    private String dropzone;
    private TimeSlot timeslot;
    private int maxload;
    private ArrayList<Jump> jumps;

    public Plane(String id, String dropzone, LocalDateTime start, LocalDateTime end, int maxload) {
        this.id = id;
        this.dropzone = dropzone;
        this.timeslot = new TimeSlot(start, end);
        this.maxload = maxload;
        this.jumps = new ArrayList<Jump>();
    }

    public String getID() {
        return this.id;
    }

    public String getDropzone() {
        return this.dropzone;
    }

    public TimeSlot getTimeSlot() {
        return this.timeslot;
    }

    public int getMaxload() {
        return this.maxload;
    }

    public int getCurrentLoad() {
        int load = 0;
        for (Jump jump : jumps) {
            if (jump instanceof TandemJump)
                load += ((TandemJump) jump).getSkydivers().size();
            else if (jump instanceof FunJump)
                load += ((FunJump) jump).getSkydivers().size();
            else if (jump instanceof Training)
                load += ((Training) jump).getSkydivers().size();
        }
        return load;
    }

    public ArrayList<Jump> getJumps() {
        return this.jumps;
    }

    public void addJump(Jump jump) {
        this.jumps.add(jump);
    }

    public void removeJump(String id) {
        this.jumps.removeIf(jump -> jump.getID() == id);
    }
}
