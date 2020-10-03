package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Change {

    public static Training changeTraining(String id, LocalDateTime start, Instructor trainer,
            Skydiver trainee) {
        return new Training(id, start, trainer, trainee);
    }

    public static FunJump changeFunJump(String id, LocalDateTime start,
            ArrayList<Skydiver> jumpers) {
        return new FunJump(id, start, jumpers);
    }

    public static TandemJump changeTandemJump(Jump jump, String id, LocalDateTime start,
            Master master, Skydiver passenger) {
        return new TandemJump(id, start, master, passenger);
    }
}
