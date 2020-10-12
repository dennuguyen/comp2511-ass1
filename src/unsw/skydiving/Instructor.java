/**
 * Instructor is a type of Skydiver with a home dropzone
 */

package unsw.skydiving;

public class Instructor extends LicensedJumper {

    private String dropzone;
    private int jumpCount;

    /**
     * Constructor
     * 
     * @param id       Skydiver id
     * @param dropzone Home dropzone
     */
    public Instructor(String id, String dropzone) {
        super(id);
        this.dropzone = dropzone;
        this.jumpCount = 0;
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
     * Gets the jump count
     * 
     * @return jump count
     */
    public int getJumpCount() {
        return this.jumpCount;
    }

    /**
     * Increment the jump count by one
     */
    public void incrementJumpCount() {
        this.jumpCount++;
    }
}
