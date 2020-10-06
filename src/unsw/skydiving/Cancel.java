package unsw.skydiving;

public final class Cancel {

    public static void cancel(String id) {
        Resources.removeJump(id);
    }
}
