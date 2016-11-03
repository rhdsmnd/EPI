package make;

import java.util.ArrayList;

/** Representation of targets used in MAKEFILE.  Each target mantains
 *  a list of prerequisites, which are themselves target objects.
 *  @author Ron Desmond
 */
public class Target {

    /** Instantiates a target with NAME and WARNING
     *  that is not yet made. */
    public Target(String name, boolean warning) {
        _warning = warning;
        _name = name;
        _time = -1;
        _exists = false;
        _commands = new ArrayList<String>();
    }

    /** Instantiates a target that has been made with time
     *  TIME, and name NAME. */
    public Target(String name, Integer time) {
        _warning = true;
        _name = name;
        _time = time;
        _exists = true;
        _commands = new ArrayList<String>();
    }

    /** Indicates that this target has been created
     *  at time TIME.
     */
    public void setCreated(int time) {
        _exists = true;
        _time = time;
    }

    /** Returns the boolean whether this vertex has been created. */
    public boolean exists() {
        return _exists;
    }

    /** Returns the date as an int, if any,
     *  that this target was last created. */
    public int getTime() {
        return _time;
    }

    /** Adds a single command S to the list of commands. */
    public void addCommand(String s) {
        _warning = false;
        _commands.add(s);
    }

    /** Returns a copy of the commands. */
    public ArrayList<String> getCommands() {
        return _commands;
    }

    /** Returns the string that represents this target's name. */
    public String toString() {
        return _name;
    }
    /** Sets _warning to WARNING. */
    public void setWarning(boolean warning) {
        _warning = warning;
    }
    /** Returns the boolean warning. */
    public boolean getWarning() {
        return _warning;
    }
    /** Indicates if a prerequisite has a target entry. */
    private boolean _warning;
    /** The name of this target. */
    private String _name;
    /** Indicates whether this object has been made. */
    private boolean _exists;
    /** Represents the time that this object was last created. */
    private int _time;
    /** Represents the list of commands to create this target. */
    private ArrayList<String> _commands;
}
