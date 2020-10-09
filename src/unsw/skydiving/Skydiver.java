package unsw.skydiving;

import java.util.ArrayList;

public class Skydiver {

    protected String id;
    protected ArrayList<TimeSlot> schedule;
    protected Parachute parachute;

    public Skydiver(String id) {
        this.id = id;
        this.schedule = new ArrayList<TimeSlot>();
        this.parachute = null;
    }

    public String getID() {
        return this.id;
    }

    public TimeSlot getFreeTimeSlot(TimeSlot timeslot) {
        for (TimeSlot slot : schedule) {
            if (!slot.isInTimeSlot(timeslot)) {
                return slot;
            }
        }
        return null;
    }

    public void addTimeSlot(TimeSlot timeslot) {
        this.schedule.add(timeslot);
    }

    public void equipParachute() {
        this.parachute = new Parachute();
    }

    public void jump() {
        this.parachute.deploy();
    }
}
