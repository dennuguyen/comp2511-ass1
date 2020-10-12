/**
 * Instructor is a type of Skydiver with a home dropzone
 */

package unsw.skydiving;

public class Instructor extends LicensedJumper {

    private String dropzone;

    /**
     * Constructor
     * 
     * @param id       Skydiver id
     * @param dropzone Home dropzone
     */
    public Instructor(String id, String dropzone) {
        super(id);
        this.dropzone = dropzone;
    }

    /**
     * Gets the home dropzone string
     * 
     * @return dropzone
     */
    public String getDropzone() {
        return this.dropzone;
    }

    /**
     * Count the jump count for the specific day
     * 
     * @return jump count
     */
    public int getJumpCount(TimeSlot timeSlot) {
        int count = 0;
        for (TimeSlot tSlot : this.getSchedule())
            if (tSlot.toLocalDate().equals(timeSlot.toLocalDate()))
                count++;
        return count;
    }
}
