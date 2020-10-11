/**
 * Plane has a flight id, dropzone, timeslot for flight, maxload and list of jumps for that flight
 */

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

    /**
     * Gets the flight id
     * 
     * @return id: String
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets the flight dropzone
     * 
     * @return dropzone: String
     */
    public String getDropzone() {
        return this.dropzone;
    }

    /**
     * Gets the flight timeslot
     * 
     * @return timeslot: TimeSlot
     */
    public TimeSlot getTimeSlot() {
        return this.timeslot;
    }

    /**
     * Gets the flight's maxload
     * 
     * @return maxload: int
     */
    public int getMaxload() {
        return this.maxload;
    }

    /**
     * Gets the flight's current load from counting the number of skydivers from each jump
     * 
     * @return load: int
     */
    public int getCurrentLoad() {
        int load = 0;
        for (Jump jump : this.jumps) {
            if (jump instanceof TandemJump)
                load += ((TandemJump) jump).getSkydivers().size();
            else if (jump instanceof FunJump)
                load += ((FunJump) jump).getSkydivers().size();
            else if (jump instanceof Training)
                load += ((Training) jump).getSkydivers().size();
        }
        return load;
    }

    /**
     * Gets all the jumps
     * 
     * @return jumps: ArrayList<Jump>
     */
    public ArrayList<Jump> getJumps() {
        return this.jumps;
    }

    /**
     * Adds jump to jumplist
     * 
     * @param jump
     */
    public void addJump(Jump jump) {
        this.jumps.add(jump);
    }

    /**
     * Removes jump from jumplist
     * 
     * @param id
     */
    public void removeJump(String id) {
        this.jumps.removeIf(jump -> jump.getID() == id);
    }
}
