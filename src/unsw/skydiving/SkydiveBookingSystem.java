/**
 * Core of skydive booking system.
 */

package unsw.skydiving;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Skydive Booking System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a skydive booking system. Input and output is in
 * JSON format.
 */
public class SkydiveBookingSystem {

    /* Constant strings to extract JSON fields */
    public static final String COMMAND = "command";
    public static final String FLIGHT = "flight";
    public static final String SKYDIVER = "skydiver";
    public static final String REQUEST = "request";
    public static final String CHANGE = "change";
    public static final String CANCEL = "cancel";
    public static final String JUMP_RUN = "jump-run";
    public static final String TYPE = "type";
    public static final String FUN = "fun";
    public static final String TANDEM = "tandem";
    public static final String TRAINING = "training";
    public static final String ID = "id";
    public static final String STARTTIME = "starttime";
    public static final String ENDTIME = "endtime";
    public static final String SKYDIVERS = "skydivers";
    public static final String PASSENGER = "passenger";
    public static final String TRAINEE = "trainee";
    public static final String LICENCE = "licence";
    public static final String STUDENT = "student";
    public static final String LICENCED_JUMPER = "licenced-jumper";
    public static final String INSTRUCTOR = "instructor";
    public static final String TANDEM_MASTER = "tandem-master";
    public static final String MAXLOAD = "maxload";
    public static final String DROPZONE = "dropzone";

    /* Command handlers */
    private Flight flight;
    private Register register;
    private Request request;
    private Change change;
    private Cancel cancel;
    private JumpRun jumprun;

    public SkydiveBookingSystem() {
        Resources resources = new Resources();
        this.flight = new Flight(resources);
        this.register = new Register(resources);
        this.request = new Request(resources);
        this.change = new Change(resources);
        this.cancel = new Cancel(resources);
        this.jumprun = new JumpRun(resources);
    }

    /**
     * JSON file is scanned and broken into their JSON objects
     * 
     * @param file
     */
    private void parseJSON(String file) {
        Scanner scan = null;

        try {
            scan = new Scanner(new File(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            // System.out.println(line);
            processCommand(new JSONObject(line));
        }

        scan.close();
    }

    /**
     * Commands are passed onto their respective command handlers
     * 
     * @param json
     */
    private void processCommand(JSONObject json) {
        switch (json.getString(COMMAND)) {
            case FLIGHT:
                flight.createFlight(json);
                break;
            case SKYDIVER:
                register.add(json);
                break;
            case REQUEST:
                switch (json.getString(TYPE)) {
                    case FUN:
                        request.requestFunJump(json);
                        break;
                    case TANDEM:
                        request.requestTandemJump(json);
                        break;
                    case TRAINING:
                        request.requestTraining(json);
                        break;
                }
                break;
            case CHANGE:
                switch (json.getString(TYPE)) {
                    case FUN:
                        change.changeFunJump(json);
                        break;
                    case TANDEM:
                        change.changeTandemJump(json);
                        break;
                    case TRAINING:
                        change.changeTraining(json);
                        break;
                }
                break;
            case CANCEL:
                cancel.cancel(json);
                break;
            case JUMP_RUN:
                jumprun.generate(json);
                break;
            default:
                System.err.println("Invalid command");
        }
    }

    /**
     * Main function takes a file from command line either through System.in or args
     * 
     * @param args
     */
    public static void main(String[] args) {
        SkydiveBookingSystem sys = new SkydiveBookingSystem();

        // Get the JSON file name
        switch (args.length) {
            case 0:
                sys.parseJSON(new Scanner(System.in).next());
                break;
            case 1:
                sys.parseJSON(args[0]);
                break;
            default:
                System.err.println("Invalid arguments");
        }

        // System.out.println(new JSONArray(Resources.getSkydivers()));
        // System.out.println(new JSONArray(Resources.getFlights()));
    }
}
