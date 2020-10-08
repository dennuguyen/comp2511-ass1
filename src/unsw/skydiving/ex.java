package unsw.skydiving;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;


public class ex {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(new File(args[0]));
            System.out.println("scanning");
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                System.out.print("line: ");
                System.out.println(line);
                // if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                // System.out.println(command);
                // }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
