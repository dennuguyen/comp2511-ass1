/**
 * FunJump is a type of Jump and has a group of jumpers of type LicensedJumper
 */

package unsw.skydiving;

import java.util.ArrayList;

public class FunJump extends Jump {

    private ArrayList<LicensedJumper> jumpers;

    public FunJump(String id, ArrayList<LicensedJumper> jumpers) {
        super(id);
        this.jumpers = jumpers;
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        ArrayList<Skydiver> skydivers = new ArrayList<Skydiver>();
        for (LicensedJumper jumper : this.jumpers)
            skydivers.add(jumper);
        return skydivers;
    }
}
