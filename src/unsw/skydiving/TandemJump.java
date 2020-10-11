/**
 * TandemJump is a type of Jump which has a tandem master and passenger skydiver
 */

package unsw.skydiving;

import java.util.ArrayList;

public class TandemJump extends Jump {

    private Master master;
    private Skydiver passenger;

    public TandemJump(String id, Master master, Skydiver passenger) {
        super(id);
        this.master = master;
        this.passenger = passenger;
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>();
        skydivers.add(this.master);
        skydivers.add(this.passenger);
        return skydivers;
    }
}
