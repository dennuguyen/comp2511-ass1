/**
 * Encapsulates the handling of objects.
 */

package unsw.skydiving;

import java.util.Objects;
import java.util.ArrayList;
import java.util.LinkedHashSet;

public final class Resources {

    private static LinkedHashSet<Jump> jumps; // List of jumps
    private static LinkedHashSet<Skydiver> skydivers; // Register list of skydivers
    private static ArrayList<Plane> flights; // List of flights

    static {
        jumps = new LinkedHashSet<Jump>();
        skydivers = new LinkedHashSet<Skydiver>();
        flights = new ArrayList<Plane>();
    }

    public static Jump getJump(String id) {
        Jump retval = null;

        try {
            for (Jump jump : jumps)
                if (Objects.equals(jump.getID(), id))
                    return retval;

            if (retval == null)
                throw new NullPointerException();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public static void addJump(Jump jump) {
        try {
            if (jumps.add(jump) == false)
                throw new IllegalArgumentException("Error: jump id already exists");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void removeJump(String id) {
        try {
            if (jumps.removeIf(jump -> Objects.equals(jump.getID(), id)) == false)
                throw new IllegalArgumentException("Error: jump id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static Skydiver getSkydiver(String id) {
        Skydiver retval = null;

        try {
            for (Skydiver skydiver : skydivers)
                if (Objects.equals(skydiver.getID(), id))
                    return skydiver;

            if (retval == null)
                throw new NullPointerException();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public static void addSkydiver(Skydiver skydiver) {
        try {
            if (skydivers.add(skydiver) == false)
                throw new IllegalArgumentException("Error: skydiver id already exists");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void removeSkydiver(String id) {
        try {
            if (skydivers.removeIf(skydiver -> Objects.equals(skydiver.getID(), id)) == false)
                throw new IllegalArgumentException("Error: skydiver id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static Plane getFlight(String id) {
        Plane retval = null;

        try {
            for (Plane plane : flights)
                if (Objects.equals(plane.getID(), id))
                    return plane;

            if (retval == null)
                throw new NullPointerException();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public static void addFlight(Plane plane) {
        try {
            if (flights.add(plane) == false)
                throw new IllegalArgumentException("Error: plane id already exists");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void removeFlight(String id) {
        try {
            if (flights.removeIf(plane -> Objects.equals(plane.getID(), id)) == false)
                throw new IllegalArgumentException("Error: plane id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
