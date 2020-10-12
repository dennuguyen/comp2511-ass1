/**
 * Core of skydive booking system.
 */

package unsw.skydiving;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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

    /**
     * SkydiveBookingSystem Constructor
     * 
     * @param outputFile Handle to output file
     */
    public SkydiveBookingSystem(FileWriter outputFile) {
        Resources resources = new Resources();
        this.flight = new Flight(resources);
        this.register = new Register(resources);
        this.request = new Request(outputFile, resources);
        this.cancel = new Cancel(resources);
        this.change = new Change(outputFile, resources, this.request, this.cancel);
        this.jumprun = new JumpRun(outputFile, resources);
    }

    /**
     * JSON file is scanned and broken into their JSON objects
     * 
     * @param file Name of file
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
            processCommand(new JSONObject(line));
        }

        scan.close();
    }

    /**
     * Commands are passed onto their respective command handlers
     * 
     * @param json Command information stored in json
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
     * @param args Input arguments
     */
    public static void main(String[] args) {

        FileWriter outputFile = null;
        // String outputFileName = null;
        String inputFileName = null;

        // Get file names
        switch (args.length) {
            case 0:
                inputFileName = new Scanner(System.in).next();
                // outputFileName = new Scanner(System.in).next();
                break;
            case 1:
                inputFileName = args[0];
                // outputFileName = "../output.json";
                break;
            case 2:
                inputFileName = args[0];
                // outputFileName = args[1];
                break;
            default:
                System.err.println("Invalid arguments");
        }

        // Open output file
        // try {
        outputFile = new FileWriter(FileDescriptor.out);
        // } catch (IOException e) {
        // e.printStackTrace();
        // }

        // Init the skydive booking system
        SkydiveBookingSystem sys = new SkydiveBookingSystem(outputFile);
        sys.parseJSON(inputFileName);

        // Close output file
        try {
            outputFile.flush();
            outputFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
