/**
 * Plane has a flight id, dropzone, time slot for flight, maxload and list of jumps for that flight
 */

package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class Plane {

    private String id;
    private String dropzone;
    private TimeSlot timeSlot;
    private int maxload;
    private ArrayList<Jump> jumps;

    /**
     * Constructor
     * 
     * @param id       Plane id
     * @param dropzone Dropzone name
     * @param start    Start time of flight
     * @param end      End time of flight
     * @param maxload  Max load of plane
     */
    public Plane(String id, String dropzone, LocalDateTime start, LocalDateTime end, int maxload) {
        this.id = id;
        this.dropzone = dropzone;
        this.timeSlot = new TimeSlot(null, start, end);
        this.maxload = maxload;
        this.jumps = new ArrayList<Jump>();
    }

    /**
     * Gets the flight id
     * 
     * @return plane id
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets the flight dropzone
     * 
     * @return dropzone
     */
    public String getDropzone() {
        return this.dropzone;
    }

    /**
     * Gets the flight time slot
     * 
     * @return time slot of flight
     */
    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    /**
     * Gets the flight's maxload
     * 
     * @return maxload
     */
    public int getMaxload() {
        return this.maxload;
    }

    /**
     * Gets the flight's current load from counting the number of skydivers from each jump
     * 
     * @return current load
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
     * @return jumps
     */
    public ArrayList<Jump> getJumps() {
        return this.jumps;
    }

    /**
     * Adds jump to jumplist
     * 
     * @param jump jump to be added to flight
     * @return Indicate success
     */
    public boolean addJump(Jump jump) {
        return this.jumps.add(jump);
    }

    /**
     * Removes jump from jumplist
     * 
     * @param id jump to be removed from flight
     * @return Indicate succcess
     */
    public boolean removeJump(String id) {
        for (Jump jump : this.jumps)
            if (Objects.equals(jump.getID(), id)) {
                for (Skydiver skydiver : jump.getSkydivers())
                    skydiver.removeTimeSlot(id);
                this.jumps.remove(jump);
                return true;
            }
        return false;
    }
}
