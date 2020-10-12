/**
 * TimeSlot is a collection of a start and end LocalDateTime with convenient clashes methods to
 * determine overlapping times
 */
package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TimeSlot {

    private LocalDateTime start;
    private LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Get start time
     * 
     * @return start: LocalDateTime
     */
    public LocalDateTime getStartTime() {
        return this.start;
    }

    /**
     * Get end time
     * 
     * @return end: LocalDateTime
     */
    public LocalDateTime getEndTime() {
        return this.end;
    }

    /**
     * Checks if an instance of time is within the timeslot
     * 
     * @param time
     * @return boolean
     */
    public boolean clashes(LocalDateTime time) {
        return this.start.isBefore(time) && this.end.isAfter(time);
    }

    /**
     * Checks if a given timeslot overlaps with the timeslot
     * 
     * @param timeslot
     * @return boolean
     */
    public boolean clashes(TimeSlot timeslot) {
        return this.start.isBefore(timeslot.end) && timeslot.start.isBefore(this.end);
    }

    /**
     * Checks if an array of timeslots overlaps with the timeslot
     * 
     * @param timeslots
     * @return boolean
     */
    public boolean clashes(ArrayList<TimeSlot> timeslots) {
        for (TimeSlot timeslot : timeslots)
            if (clashes(timeslot))
                return true;
        return false;
    }
}