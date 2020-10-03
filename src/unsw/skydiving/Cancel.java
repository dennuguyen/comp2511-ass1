package unsw.skydiving;

public final class Cancel {

    public static void cancel(String id) {
        try {
            Resources.removeJump(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
