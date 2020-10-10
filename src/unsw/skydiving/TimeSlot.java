package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TimeSlot {
    LocalDateTime start;
    LocalDateTime end;

    public TimeSlot(LocalDateTime start, LocalDateTime end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Checks if an instance of time is within the timeslot
     * 
     * @param time
     * @return
     */
    public boolean isInTimeSlot(LocalDateTime time) {
        return this.start.isBefore(time) && this.end.isAfter(time);
    }

    /**
     * Checks if a given timeslot overlaps with the timeslot
     * 
     * @param timeslot
     * @return
     */
    public boolean isInTimeSlot(TimeSlot timeslot) {
        return this.start.isBefore(timeslot.end) && timeslot.start.isBefore(this.end);
    }

    /**
     * Checks if an array of timeslots overlaps with the timeslot
     * 
     * @param timeslots
     * @return
     */
    public boolean isInTimeSlot(ArrayList<TimeSlot> timeslots) {
        for (TimeSlot timeslot : timeslots)
            if (isInTimeSlot(timeslot))
                return true;
        return false;
    }
}
