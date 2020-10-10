package unsw.skydiving;

import java.util.ArrayList;

public class Skydiver {

    protected String id;
    protected ArrayList<TimeSlot> schedule;

    public Skydiver(String id) {
        this.id = id;
        this.schedule = new ArrayList<TimeSlot>();
    }

    public String getID() {
        return this.id;
    }

    public ArrayList<TimeSlot> getSchedule() {
        return this.schedule;
    }

    public void addTimeSlot(TimeSlot timeslot) {
        this.schedule.add(timeslot);
    }
}
