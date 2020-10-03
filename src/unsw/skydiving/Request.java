package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public final class Request {

    public static void requestTraining(String id, LocalDateTime start, Instructor trainer,
            Skydiver trainee) {
        try {
            Resources.addJump(new Training(id, start, trainer, trainee));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void requestFunJump(String id, LocalDateTime start, ArrayList<Skydiver> jumpers) {
        try {
            Resources.addJump(new FunJump(id, start, jumpers));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void requestTandemJump(String id, LocalDateTime start, Master master,
            Skydiver passenger) {
        try {
            Resources.addJump(new TandemJump(id, start, master, passenger));
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
