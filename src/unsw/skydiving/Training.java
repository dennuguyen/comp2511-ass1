package unsw.skydiving;

import java.time.LocalDateTime;

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
}
