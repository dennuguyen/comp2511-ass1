/**
 * Training is a type of Jump with a trainer of type Instructor and trainee of any class
 */
package unsw.skydiving;

import java.util.ArrayList;

public class Training extends Jump {

    private Instructor trainer;
    private Skydiver trainee;

    public Training(String id, Instructor trainer, Skydiver trainee) {
        super(id);
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
