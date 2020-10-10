package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Jump {

    public static final int BRIEF_TIME = 5;
    public static final int PACK_TIME = 10;
    public static final int DEBRIEF_TIME = 15;

    protected String id;
    protected LocalDateTime start;

    public Jump(String id, LocalDateTime start) {
        this.id = id;
        this.start = start;
    }

    public String getID() {
        return this.id;
    }

    public LocalDateTime getStartTime() {
        return this.start;
    }

    public ArrayList<Skydiver> getSkydivers() {
        return null;
    }
}
