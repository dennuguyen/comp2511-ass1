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
        for (Jump jump : jumps)
            if (Objects.equals(jump.getID(), id))
                return jump;
        throw new NullPointerException();
    }

    public static void addJump(Jump jump) {
        if (jumps.add(jump) == false)
            throw new IllegalArgumentException("Error: jump id already exists");
    }

    public static void removeJump(String id) {
        if (jumps.removeIf(jump -> Objects.equals(jump.getID(), id)) == false)
            throw new IllegalArgumentException("Error: jump id does not exist");
    }

    public static Skydiver getSkydiver(String id) {
        for (Skydiver skydiver : skydivers)
            if (Objects.equals(skydiver.getID(), id))
                return skydiver;
        throw new NullPointerException();
    }

    public static void addSkydiver(Skydiver skydiver) {
        if (skydivers.add(skydiver) == false)
            throw new IllegalArgumentException("Error: skydiver id already exists");
    }

    public static void removeSkydiver(String id) {
        if (skydivers.removeIf(skydiver -> Objects.equals(skydiver.getID(), id)) == false)
            throw new IllegalArgumentException("Error: skydiver id does not exist");
    }

    public static Skydiver getAvailableInstructor(String id) {
        for (Skydiver instructor : availableInstructors)
            if (Objects.equals(instructor.getID(), id))
                return instructor;
        throw new NullPointerException();
    }

    public static void addInstructor(Instructor instructor) {
        if (availableInstructors.add(instructor) == false)
            throw new IllegalArgumentException("Error: instructor id already exists");
    }

    public static void removeInstructor(String id) {
        if (availableInstructors
                .removeIf(instructor -> Objects.equals(instructor.getID(), id)) == false)
            throw new IllegalArgumentException("Error: instructor id does not exist");
    }

    public static Dropzone getDropzone(String id) {
        for (Dropzone dropzone : dropzones)
            if (Objects.equals(dropzone.getID(), id))
                return dropzone;
        throw new NullPointerException();
    }

    public static void addDropzone(Dropzone dropzone) {
        if (dropzones.add(dropzone) == false)
            throw new IllegalArgumentException("Error: dropzone id already exists");
    }

    public static void removeDropzone(String id) {
        if (dropzones.removeIf(dropzone -> Objects.equals(dropzone.getID(), id)) == false)
            throw new IllegalArgumentException("Error: dropzone id does not exist");
    }
}
