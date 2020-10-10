package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Training extends Jump {

    private Instructor trainer;
    private Skydiver trainee;

    public Training(String id, LocalDateTime start, Instructor trainer, Skydiver trainee) {
        super(id, start);
        this.trainer = trainer;
        this.trainee = trainee;
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>();
        skydivers.add(this.trainer);
        skydivers.add(this.trainee);
        return skydivers;
    }
}
