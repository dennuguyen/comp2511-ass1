/**
 * TimeSlot is a collection of a start and end LocalDateTime representing an unavailable timeslot
 * with convenient clashes methods to determine overlapping times
 */
package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TimeSlot {

    private String id;
    private LocalDateTime start;
    private LocalDateTime end;

    /**
     * Constructor
     * 
     * @param id    Same id as jump id, null if not applicable
     * @param start Start time of time slot
     * @param end   End time of time slot
     */
    public TimeSlot(String id, LocalDateTime start, LocalDateTime end) {
        this.id = id;
        this.start = start;
        this.end = end;
    }

    /**
     * Get the id that is the same as the jump id
     * 
     * @return id
     */
    public String getID() {
        return this.id;
    }

    /**
     * Get start time
     * 
     * @return start time
     */
    public LocalDateTime getStartTime() {
        return this.start;
    }

    /**
     * Get end time
     * 
     * @return end time
     */
    public LocalDateTime getEndTime() {
        return this.end;
    }

    /**
     * Checks if an instance of time is within the timeslot
     * 
     * @param time
     * @return boolean on existing clash
     */
    public boolean clashes(LocalDateTime time) {
        return this.start.isBefore(time) && this.end.isAfter(time);
    }

    /**
     * Checks if a given timeslot overlaps with the timeslot
     * 
     * @param timeslot
     * @return boolean on existing clash
     */
    public boolean clashes(TimeSlot timeslot) {
        return this.start.isBefore(timeslot.end) && timeslot.start.isBefore(this.end);
    }

    /**
     * Checks if an array of timeslots overlaps with the timeslot
     * 
     * @param timeslots
     * @return boolean on existing clash
     */
    public boolean clashes(ArrayList<TimeSlot> timeslots) {
        for (TimeSlot timeslot : timeslots)
            if (clashes(timeslot))
                return true;
        return false;
    }
}
