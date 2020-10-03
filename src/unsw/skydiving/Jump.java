package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Jump {

    protected String id;
    protected LocalDateTime start;

    public Jump(String id, LocalDateTime start) {
        this.id = id;
        this.start = start;
    }

    public String getID() {
        return this.id;
    }

    public LocalDateTime getStart() {
        return this.start;
    }

    public void jump() {
    }
}
