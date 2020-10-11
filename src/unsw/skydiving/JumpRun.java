/**
 * jump-run command handler
 */

package unsw.skydiving;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONObject;

public class JumpRun {

    private FileWriter outputFile;
    private Resources resources;

    public JumpRun(FileWriter outputFile, Resources resources) {
        this.outputFile = outputFile;
        this.resources = resources;
    }

    /**
     * Outputs a list of jumps in the required order
     * 
     * @param json
     */
    public void generate(JSONObject json) {
        String id = json.getString(SkydiveBookingSystem.ID);
        ArrayList<Jump> jumps = resources.getFlight(id).getJumps();

        // try {
        // JSONObject output = new JSONObject();
        // output.put("")
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
    }
}
