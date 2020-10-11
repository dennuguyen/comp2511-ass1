/**
 * Instructor is a type of Skydiver with a home dropzone
 */

package unsw.skydiving;

public class Instructor extends LicensedJumper {

    private String dropzone;

    public Instructor(String id, String dropzone) {
        super(id);
        this.dropzone = dropzone;
    }

    /**
     * Gets the home dropzone string
     * 
     * @return dropzone: String
     */
    public String getDropzone() {
        return this.dropzone;
    }
}
