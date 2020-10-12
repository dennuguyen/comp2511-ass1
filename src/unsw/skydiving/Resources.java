/**
 * Resource manager to handle this.flights and this.skydivers
 */

package unsw.skydiving;

import java.util.Objects;
import java.time.LocalDateTime;
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

    /**
     * 
     * @return
     */
    public LinkedHashSet<Skydiver> getSkydivers() {
        return this.skydivers;
    }

    /**
     * Get skydiver from resources
     * 
     * @param id skydiver id
     * @return skydiver
     */
    public Skydiver getSkydiver(String id) {
        for (Skydiver jumper : this.skydivers)
            if (Objects.equals(jumper.getID(), id))
                return jumper;
        return null;
    }

    /**
     * 
     * @param timeSlot
     * @return
     */
    public Instructor getAvailableInstructor(TimeSlot timeSlot, String dropzone, Skydiver booked) {
        for (Skydiver skydiver : this.skydivers)
            if (skydiver instanceof Instructor)
                if (Objects.equals(dropzone, ((Instructor) skydiver).getDropzone()))
                    if (!timeSlot.clashes(skydiver.getSchedule()))
                        if (!Objects.equals(skydiver, booked) || booked == null)
                            return (Instructor) skydiver;
        return null;
    }

    /**
     * 
     * @param timeSlot
     * @return
     */
    public Master getAvailableMaster(TimeSlot timeSlot, String dropzone, Skydiver booked) {
        for (Skydiver skydiver : this.skydivers)
            if (skydiver instanceof Master)
                if (Objects.equals(dropzone, ((Master) skydiver).getDropzone()))
                    if (!timeSlot.clashes(skydiver.getSchedule()))
                        if (!Objects.equals(skydiver, booked) || booked == null)
                            return (Master) skydiver;
        return null;
    }

    /**
     * Add skydiver to resources
     * 
     * @param skydiver skydiver to be handled by resources
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
     * @param id skydiver to no longer be handled by resources
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
     * @param id plane id
     * @return plane
     */
    public Plane getFlight(String id) {
        for (Plane plane : this.flights)
            if (Objects.equals(plane.getID(), id))
                return plane;
        return null;
    }

    /**
     * Get all this.flights from resources
     * 
     * @return flights
     */
    public ArrayList<Plane> getFlights() {
        return this.flights;
    }

    /**
     * Gets a list of available flights given a jump start time and jump load
     * 
     * @param start Jump start time
     * @param load  Jump load
     * @return Available flights
     */
    public ArrayList<Plane> getAvailableFlights(LocalDateTime start, int load) {

        ArrayList<Plane> planes = new ArrayList<Plane>();

        for (Plane plane : this.flights) {
            LocalDateTime flightStart = plane.getTimeSlot().getStartTime();

            if (flightStart.toLocalDate().isEqual(start.toLocalDate()))
                if (!start.isAfter(flightStart))
                    if (load + plane.getCurrentLoad() <= plane.getMaxload())
                        planes.add(plane);
        }
        return planes;
    }

    /**
     * Add flight to resources
     * 
     * @param plane plane to be handled by resources
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
     * @param id plane to no longer be handled by resources
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
