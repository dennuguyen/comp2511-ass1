/**
 * Resource manager to handle flights and skydivers
 */

package unsw.skydiving;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public final class Resources {

    private static LinkedHashSet<Skydiver> skydivers; // Register of skydivers
    private static ArrayList<Plane> flights; // List of flights

    static {
        skydivers = new LinkedHashSet<>();
        flights = new ArrayList<>();
    }

    public static LinkedHashSet<Skydiver> getSkydivers() {
        return skydivers;
    }

    /**
     * Get skydiver from resources
     * 
     * @param id
     * @return Skydiver
     */
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

    /**
     * Add skydiver to resources
     * 
     * @param skydiver
     */
    public static void addSkydiver(Skydiver skydiver) {
        try {
            if (skydivers.add(skydiver) == false)
                throw new IllegalArgumentException("Error: skydiver id already exists");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove skydiver from resources
     * 
     * @param id
     */
    public static void removeSkydiver(String id) {
        try {
            if (skydivers.removeIf(skydiver -> Objects.equals(skydiver.getID(), id)) == false)
                throw new IllegalArgumentException("Error: skydiver id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets an available instructor
     * 
     * @return
     */
    public static ArrayList<Instructor> getInstructors() {
        ArrayList<Instructor> instructors = new ArrayList<Instructor>();
        for (Skydiver skydiver : skydivers)
            if (skydiver instanceof Instructor)
                instructors.add((Instructor) skydiver);
        return instructors;
    }

    /**
     * Gets an available tandem master
     * 
     * @return
     */
    public static Master getTandemMasters() {
        return null;
    }

    /**
     * Get flight from resources
     * 
     * @param id
     * @return Plane
     */
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

    /**
     * Get all flights from resources
     * 
     * @return ArrayList<Plane>
     */
    public static ArrayList<Plane> getFlights() {
        return flights;
    }

    /**
     * Add flight to resources
     * 
     * @param plane
     */
    public static void addFlight(Plane plane) {
        try {
            if (flights.add(plane) == false)
                throw new IllegalArgumentException("Error: plane id already exists");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Remove flight from resources
     * 
     * @param id
     */
    public static void removeFlight(String id) {
        try {
            if (flights.removeIf(plane -> Objects.equals(plane.getID(), id)) == false)
                throw new IllegalArgumentException("Error: plane id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort the flights in chronological order
     */
    public static void sortFlights() {
        Collections.sort(flights, (a, b) -> a.getTimeSlot().start.compareTo(b.getTimeSlot().start));
    }
}
