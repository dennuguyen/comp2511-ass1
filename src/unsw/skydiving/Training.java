/**
 * Training is a type of Jump with a trainer of type Instructor and trainee of any class
 */
package unsw.skydiving;

import java.util.ArrayList;
import org.json.JSONObject;

public class Training extends Jump {

    private Instructor trainer;
    private Skydiver trainee;

    /**
     * Constructor
     * 
     * @param id      Jump id
     * @param trainer Trainer on jump
     * @param trainee Trainee on jump
     */
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

    @Override
    public JSONObject getJumpRun() {
        JSONObject jumpRun = new JSONObject();
        jumpRun.put("instructor", this.trainer.getID());
        jumpRun.put("trainee", this.trainee.getID());
        return jumpRun;
    }
}
