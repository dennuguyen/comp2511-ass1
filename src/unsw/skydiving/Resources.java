/**
 * Resource manager to handle this.flights and this.skydivers
 */

package unsw.skydiving;

import java.util.Objects;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;

public final class Resources {

    private ArrayList<Plane> flights; // List of flights
    private LinkedHashSet<Skydiver> skydivers; // Register of skydivers

    private ArrayList<Plane> tempFlights; // Temporary List of flights
    private LinkedHashSet<Skydiver> tempSkydivers; // Temporary register of skydivers

    /**
     * Constructor
     */
    public Resources() {
        this.flights = new ArrayList<>();
        this.skydivers = new LinkedHashSet<>();
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
     * Gets the next instructor
     * 
     * @param timeSlot Time slot of concern
     * @param dropzone Dropzone of concern
     * @param booked   Skydiver to ignore
     * @return instructor or null on failure
     */
    public Instructor getNextInstructor(TimeSlot timeSlot, String dropzone, Skydiver booked) {

        // Get all available instructors
        ArrayList<Instructor> instructors = new ArrayList<Instructor>();
        for (Skydiver skydiver : this.skydivers)
            if (skydiver instanceof Instructor)
                if (Objects.equals(dropzone, ((Instructor) skydiver).getDropzone()))
                    if (!timeSlot.clashes(skydiver.getSchedule()))
                        if (!Objects.equals(skydiver, booked) || booked == null)
                            instructors.add((Instructor) skydiver);

        // Sort instructors by jump count
        Collections.sort(instructors,
                (a, b) -> Integer.compare(a.getJumpCount(timeSlot), b.getJumpCount(timeSlot)));

        // Filter instructors for min jump count
        int min = -1;
        ArrayList<Instructor> tempInstructors = new ArrayList<Instructor>();
        for (Iterator<Instructor> i = instructors.iterator(); i.hasNext();) {
            Instructor instructor = i.next();
            int jumpCount = instructor.getJumpCount(timeSlot);
            if (min == -1 || jumpCount < min) {
                tempInstructors.clear();
                tempInstructors.add(instructor);
                min = jumpCount;
            } else if (jumpCount == min)
                tempInstructors.add(instructor);
        }

        // Get earliest registered skydiver from filtered list or null otherwise
        return tempInstructors.isEmpty() ? null : tempInstructors.get(0);
    }

    /**
     * Gets the next tandem master
     * 
     * @param timeSlot Time slot of concern
     * @param dropzone Dropzone of concern
     * @param booked   Skydiver to ignore
     * @return tandem master or null on failure
     */
    public Master getNextMaster(TimeSlot timeSlot, String dropzone, Skydiver booked) {

        // Get all available instructors
        ArrayList<Master> masters = new ArrayList<Master>();
        for (Skydiver skydiver : this.skydivers)
            if (skydiver instanceof Master)
                if (Objects.equals(dropzone, ((Master) skydiver).getDropzone()))
                    if (!timeSlot.clashes(skydiver.getSchedule()))
                        if (!Objects.equals(skydiver, booked) || booked == null)
                            masters.add((Master) skydiver);

        // Sort tandem masters by jump count
        Collections.sort(masters,
                (a, b) -> Integer.compare(a.getJumpCount(timeSlot), b.getJumpCount(timeSlot)));

        // Filter instructors for min jump count
        int min = -1;
        ArrayList<Master> tempMasters = new ArrayList<Master>();
        for (Iterator<Master> i = masters.iterator(); i.hasNext();) {
            Master master = i.next();
            int jumpCount = master.getJumpCount(timeSlot);
            if (min == -1 || jumpCount < min) {
                tempMasters.clear();
                tempMasters.add(master);
                min = jumpCount;
            } else if (jumpCount == min)
                tempMasters.add(master);
        }

        // Get earliest registered skydiver from filtered list or null otherwise
        return tempMasters.isEmpty() ? null : tempMasters.get(0);
    }

    /**
     * Add skydiver to resources
     * 
     * @param skydiver skydiver to be handled by resources
     * @return Indicate success
     */
    public boolean addSkydiver(Skydiver skydiver) {
        return this.skydivers.add(skydiver);
    }

    /**
     * Remove skydiver from resources
     * 
     * @param id skydiver to no longer be handled by resources
     * @return Indicate success
     */
    public boolean removeSkydiver(String id) {
        return this.skydivers.removeIf(skydiver -> Objects.equals(skydiver.getID(), id));
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
     * @return Indicate success
     */
    public boolean addFlight(Plane plane) {
        return this.flights.add(plane);
    }

    /**
     * Remove flight from resources
     * 
     * @param id plane to no longer be handled by resources
     * @return Indicate success
     */
    public boolean removeFlight(String id) {
        return this.flights.removeIf(plane -> Objects.equals(plane.getID(), id));
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
