package unsw.skydiving;

import java.time.LocalDateTime;

public class Dropzone {

    private String id;
    private String dropzone;
    private LocalDateTime start;
    private LocalDateTime end;
    private int maxload;

    public Dropzone(String id, String dropzone, LocalDateTime start, LocalDateTime end,
            int maxload) {
        this.id = id;
        this.dropzone = dropzone;
        this.start = start;
        this.end = end;
        this.maxload = maxload;
    }

    public String getID() {
        return this.id;
    }

    public String getDropzone() {
        return this.dropzone;
    }

    public LocalDateTime getStartTime() {
        return this.start;
    }

    public LocalDateTime getEndTime() {
        return this.end;
    }

    public int getMaxload() {
        return this.maxload;
    }
}
