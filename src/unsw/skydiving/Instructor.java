package unsw.skydiving;

public class Instructor extends LicensedJumper {

    private String dropzone;

    public Instructor(String id, String dropzone) {
        super(id);
        this.dropzone = dropzone;
    }

    public String getDropzone() {
        return this.dropzone;
    }
}
