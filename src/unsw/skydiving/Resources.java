/**
 * Encapsulates the handling of objects.
 */

package unsw.skydiving;

import java.util.Objects;
import java.util.LinkedHashSet;

public final class Resources {

    private static LinkedHashSet<Jump> jumps;
    private static LinkedHashSet<Skydiver> skydivers;
    private static LinkedHashSet<Instructor> availableInstructors;
    private static LinkedHashSet<Dropzone> dropzones;

    static {
        jumps = new LinkedHashSet<Jump>();
        skydivers = new LinkedHashSet<Skydiver>();
        availableInstructors = new LinkedHashSet<Instructor>();
        dropzones = new LinkedHashSet<Dropzone>();
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

    public static Skydiver getAvailableInstructor(String id) {
        Skydiver retval = null;

        try {
            for (Skydiver instructor : availableInstructors)
                if (Objects.equals(instructor.getID(), id))
                    return instructor;

            if (retval == null)
                throw new NullPointerException();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public static void addInstructor(Instructor instructor) {
        try {
            if (availableInstructors.add(instructor) == false)
                throw new IllegalArgumentException("Error: instructor id already exists");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void removeInstructor(String id) {
        try {
            if (availableInstructors
                    .removeIf(instructor -> Objects.equals(instructor.getID(), id)) == false)
                throw new IllegalArgumentException("Error: instructor id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static Dropzone getDropzone(String id) {
        Dropzone retval = null;

        try {
            for (Dropzone dropzone : dropzones)
                if (Objects.equals(dropzone.getID(), id))
                    return dropzone;

            if (retval == null)
                throw new NullPointerException();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return retval;
    }

    public static void addDropzone(Dropzone dropzone) {
        try {
            if (dropzones.add(dropzone) == false)
                throw new IllegalArgumentException("Error: dropzone id already exists");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void removeDropzone(String id) {
        try {
            if (dropzones.removeIf(dropzone -> Objects.equals(dropzone.getID(), id)) == false)
                throw new IllegalArgumentException("Error: dropzone id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
