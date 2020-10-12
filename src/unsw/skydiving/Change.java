/**
 * Change commands handler
 */

package unsw.skydiving;

import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Change {

    private FileWriter outputFile;
    private Resources resources;
    private Request request;
    private Cancel cancel;

    /**
     * Constructor
     * 
     * @param outputFile Handle to output file
     * @param resources  Handle to resource manager
     * @param request    Request command handler
     * @param cancel     Cancel command handler
     */
    public Change(FileWriter outputFile, Resources resources, Request request, Cancel cancel) {
        this.outputFile = outputFile;
        this.resources = resources;
        this.request = request;
        this.cancel = cancel;
    }

    /**
     * Helper function to write Change output
     * 
     * @param success Indicate successful output
     */
    private void writeOutput(boolean success) {
        try {
            JSONObject output = new JSONObject();
            if (success == true) {
                output.put("status", "success");
            } else {
                output.put("status", "rejected");
            }

            this.outputFile.write(output.toString(2) + "\n");
            this.outputFile.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cancels and requests a new Training on success, restores old state on failure
     * 
     * @param json Change command in JSON format
     */
    public void changeTraining(JSONObject json) {

        // Save state
        resources.save();

        // Cancel jump
        if (cancel.cancel(json) == false) {
            this.writeOutput(false);
            return;
        }

        // Request new jump. On failure, restore temporary reference
        if (request.requestTraining(json) == false)
            resources.restore();
    }

    /**
     * Cancels and requests a new FunJump on success, restores old state on failure
     * 
     * @param json Change command in JSON format
     */
    public void changeFunJump(JSONObject json) {

        // Save state
        resources.save();

        // Cancel jump
        if (cancel.cancel(json) == false) {
            this.writeOutput(false);
            return;
        }

        // Request new jump. On failure, restore temporary reference
        if (request.requestFunJump(json) == false)
            resources.restore();
    }

    /**
     * Cancels and requests a new TandemJump on success, restores old state on failure
     * 
     * @param json Change command in JSON format
     */
    public void changeTandemJump(JSONObject json) {

        // Save state
        resources.save();

        // Cancel jump
        if (cancel.cancel(json) == false) {
            this.writeOutput(false);
            return;
        }

        // Request new jump. On failure, restore temporary reference
        if (request.requestTandemJump(json) == false)
            resources.restore();
    }
}
