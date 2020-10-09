package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

public class Training extends Jump {

    private Instructor trainer;
    private Skydiver trainee;

    public Training(String id, LocalDateTime start, Instructor trainer, Skydiver trainee) {
        super(id, start);
        this.trainer = trainer;
        this.trainee = trainee;
    }

    @Override
    public void jump() {
        this.trainer.jump();
        this.trainee.jump();
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        return new ArrayList<Skydiver>(Arrays.asList(this.trainer, this.trainee));
    }
}
