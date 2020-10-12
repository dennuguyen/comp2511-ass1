/**
 * Skydiver has a unique id and schedule to track jumps
 */
package unsw.skydiving;

import java.util.ArrayList;
import java.util.Objects;

public class Skydiver {

    protected String id;
    protected ArrayList<TimeSlot> schedule;

    /**
     * Constructor
     * 
     * @param id Skydiver id
     */
    public Skydiver(String id) {
        this.id = id;
        this.schedule = new ArrayList<TimeSlot>();
    }

    /**
     * Gets the skydiver's id
     * 
     * @return id
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets the skydiver's schedule
     * 
     * @return schedule
     */
    public ArrayList<TimeSlot> getSchedule() {
        return this.schedule;
    }

    /**
     * Adds timeslot to the skydiver's schedule
     * 
     * @param timeslot Timeslot to add to schedule
     * @return Indicate success
     */
    public boolean addTimeSlot(TimeSlot timeslot) {
        return this.schedule.add(timeslot);
    }

    /**
     * Remove timeslot from the skydiver's schedule
     * 
     * @param timeslot Timeslot to remove from schedule
     * @return Indicate success
     */
    public boolean removeTimeSlot(String id) {
        return this.schedule.removeIf(timeSlot -> Objects.equals(timeSlot.getID(), id));
    }
}
