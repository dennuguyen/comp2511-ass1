/**
 * Resource manager to handle this.flights and this.skydivers
 */

package unsw.skydiving;

import java.util.Objects;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

public final class Resources {

    private ArrayList<Plane> flights; // List of flights
    private LinkedHashSet<Skydiver> skydivers; // Register of skydivers

    private ArrayList<Plane> tempFlights; // Temporary List of flights
    private LinkedHashSet<Skydiver> tempSkydivers; // Temporary register of skydivers

    public Resources() {
        this.flights = new ArrayList<>();
        this.skydivers = new LinkedHashSet<>();
    }

    public LinkedHashSet<Skydiver> getSkydivers() {
        return this.skydivers;
    }

    /**
     * Get skydiver from resources
     * 
     * @param id
     * @return skydiver: Skydiver
     */
    public Skydiver getSkydiver(String id) {
        Skydiver skydiver = null;

        try {
            for (Skydiver jumper : this.skydivers)
                if (Objects.equals(jumper.getID(), id))
                    skydiver = jumper;

            if (skydiver == null)
                throw new NullPointerException();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return skydiver;
    }

    /**
     * Gets all instructors
     * 
     * @return instructors: ArrayList<Instructor>
     */
    public ArrayList<Instructor> getInstructors() {
        ArrayList<Instructor> instructors = new ArrayList<Instructor>();
        for (Skydiver skydiver : this.skydivers)
            if (skydiver instanceof Instructor)
                instructors.add((Instructor) skydiver);
        return instructors;
    }

    /**
     * Gets all tandem masters
     * 
     * @return masters: ArrayList<Master>
     */
    public ArrayList<Master> getTandemMasters() {
        ArrayList<Master> masters = new ArrayList<Master>();
        for (Skydiver skydiver : this.skydivers)
            if (skydiver instanceof Master)
                masters.add((Master) skydiver);
        return masters;
    }

    /**
     * Add skydiver to resources
     * 
     * @param skydiver
     */
    public void addSkydiver(Skydiver skydiver) {
        try {
            if (this.skydivers.add(skydiver) == false)
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
    public void removeSkydiver(String id) {
        try {
            if (this.skydivers.removeIf(skydiver -> Objects.equals(skydiver.getID(), id)) == false)
                throw new IllegalArgumentException("Error: skydiver id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get flight from resources
     * 
     * @param id
     * @return flight: Plane
     */
    public Plane getFlight(String id) {
        Plane flight = null;

        try {
            for (Plane plane : this.flights)
                if (Objects.equals(plane.getID(), id))
                    flight = plane;

            if (flight == null)
                throw new NullPointerException();

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return flight;
    }

    /**
     * Get all this.flights from resources
     * 
     * @return flights: ArrayList<Plane>
     */
    public ArrayList<Plane> getFlights() {
        return this.flights;
    }

    /**
     * Add flight to resources
     * 
     * @param plane
     */
    public void addFlight(Plane plane) {
        try {
            if (this.flights.add(plane) == false)
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
    public void removeFlight(String id) {
        try {
            if (this.flights.removeIf(plane -> Objects.equals(plane.getID(), id)))
                throw new IllegalArgumentException("Error: plane id does not exist");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sort the flights in chronological order
     */
    public void sortFlights() {
        Collections.sort(this.flights,
                (a, b) -> a.getTimeSlot().getStartTime().compareTo(b.getTimeSlot().getStartTime()));
    }

    /**
     * Saves the state of resources through a copy into temporaries
     */
    public void save() {
        this.tempFlights = new ArrayList<Plane>(this.flights);
        this.tempSkydivers = new LinkedHashSet<Skydiver>(this.skydivers);
    }

    /**
     * Restores the state of resources from temporaries
     */
    public void restore() {
        this.flights = new ArrayList<Plane>(this.tempFlights);
        this.skydivers = new LinkedHashSet<Skydiver>(this.tempSkydivers);
        this.tempFlights.clear();
        this.tempSkydivers.clear();
    }
}
