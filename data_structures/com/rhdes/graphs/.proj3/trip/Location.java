package trip;

import graph.Weightable;

/** Represents a Location with a name, as well as
 *  X and Y coordinates.
 *  @author Ron Desmond
 */
public class Location implements Weightable {
    /** Creates a location with string NAME, and
     *  coordinates X and Y.
     */
    public Location(String name, Double x, Double y) {
        _name = name;
        _x = x;
        _y = y;
        _weight = 0.0;
    }
    /** Sets the weight to W. */
    public void setWeight(double w) {
        _weight = w;
    }
    /** Returns the weight of this. */
    public double weight() {
        return _weight;
    }
    /** Returns the x-coordinate. */
    public Double getX() {
        return _x;
    }
    /** Returns the y-coordinate. */
    public Double getY() {
        return _y;
    }
    /** Returns this object as a string
     *  according to its name. */
    public String toString() {
        return _name;
    }

    /** The name of the location. */
    private String _name;
    /** The x-coordinate of the location. */
    private Double _x;
    /** The y-coordinate of the location. */
    private Double _y;
    /** The weight of this location, used in trip. */
    private Double _weight;
}
