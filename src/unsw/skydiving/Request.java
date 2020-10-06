package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public final class Request {

    public static void requestTraining(String id, LocalDateTime start, Instructor trainer,
            Skydiver trainee) {
        Resources.addJump(new Training(id, start, trainer, trainee));
    }

    public static void requestFunJump(String id, LocalDateTime start, ArrayList<Skydiver> jumpers) {
        Resources.addJump(new FunJump(id, start, jumpers));
    }

    public static void requestTandemJump(String id, LocalDateTime start, Master master,
            Skydiver passenger) {
        Resources.addJump(new TandemJump(id, start, master, passenger));
    }
}
