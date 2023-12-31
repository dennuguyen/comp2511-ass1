/**
 * Tandem master is a type of skydiver who can take charge of tandem jumps and instruct training
 * jumps
 */

package unsw.skydiving;

public class Master extends Instructor {

    /**
     * Constructor
     * 
     * @param id       Skydiver id
     * @param dropzone Home dropzone
     */
    public Master(String id, String dropzone) {
        super(id, dropzone);
    }
}
