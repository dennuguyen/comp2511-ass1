/**
 * Skydiver has a unique id and schedule to track jumps
 */
package unsw.skydiving;

import java.util.ArrayList;

public class Skydiver {

    protected String id;
    protected ArrayList<TimeSlot> schedule;

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
     * @param timeslot timeslot to add to schedule
     */
    public void addTimeSlot(TimeSlot timeslot) {
        this.schedule.add(timeslot);
    }
}
