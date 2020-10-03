package unsw.skydiving;

import java.time.LocalDateTime;

public class Flight {

    public static void createDropZone(String id, String dropzone, LocalDateTime start,
            LocalDateTime end, int maxload) {
        Resources.addDropzone(new Dropzone(id, dropzone, start, end, maxload));
    }
}
