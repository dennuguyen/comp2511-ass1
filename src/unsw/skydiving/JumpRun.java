/**
 * jump-run command handler
 */

package unsw.skydiving;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class JumpRun {

    private FileWriter outputFile;
    private Resources resources;

    public JumpRun(FileWriter outputFile, Resources resources) {
        this.outputFile = outputFile;
        this.resources = resources;
    }

    /**
     * Sorts a list of jumps into the required order and only returns the skydiver entries as a
     * JSONArray
     * 
     * @param jumps List of jumps
     * @return Sorted list of jumps
     */
    private JSONArray sortJumps(ArrayList<Jump> jumps) {
        ArrayList<FunJump> funJumps = new ArrayList<FunJump>();
        ArrayList<Training> trainings = new ArrayList<Training>();
        ArrayList<TandemJump> tandemJumps = new ArrayList<TandemJump>();

        // Split jumps into array lists by their jump types
        for (Jump jump : jumps) {
            if (jump instanceof FunJump)
                funJumps.add((FunJump) jump);
            else if (jump instanceof Training)
                trainings.add((Training) jump);
            else if (jump instanceof TandemJump)
                tandemJumps.add((TandemJump) jump);
            else
                System.err.println("Invalid jump type");
        }

        // Sort fun jumps by size
        funJumps.sort((a, b) -> Integer.compare(b.getSkydivers().size(), a.getSkydivers().size()));

        // Recombine jumps into a sorted list
        ArrayList<Jump> sorted = new ArrayList<Jump>();
        sorted.addAll(funJumps);
        sorted.addAll(trainings);
        sorted.addAll(tandemJumps);

        // Get jump runs
        JSONArray sortedJSON = new JSONArray();
        for (Jump jump : sorted)
            sortedJSON.put(jump.getJumpRun());

        return sortedJSON;
    }

    /**
     * Outputs a list of jumps to a json output file
     * 
     * @param json JumpRun command in JSON format
     */
    public void generate(JSONObject json) {

        // Extract from json
        String id = json.getString(SkydiveBookingSystem.ID);

        // Get jump runs in required order
        Plane flight = resources.getFlight(id);
        ArrayList<Jump> jumps = flight.getJumps();

        // Output to json file
        try {
            this.outputFile.write(sortJumps(jumps).toString(4) + "\n");
            this.outputFile.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
