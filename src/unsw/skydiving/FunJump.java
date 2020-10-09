package unsw.skydiving;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FunJump extends Jump {

    private ArrayList<Skydiver> jumpers;

    public FunJump(String id, LocalDateTime start, ArrayList<Skydiver> jumpers) {
        super(id, start);
        this.jumpers = jumpers;
    }

    @Override
    public void jump() {
        for (Skydiver jumper : this.jumpers)
            jumper.jump();
    }

    @Override
    public ArrayList<Skydiver> getSkydivers() {
        return this.jumpers;
    }
}
