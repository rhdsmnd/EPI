package trip;

import graph.Weighted;

/** Each road has a name, distance, and direction,
 *  used in the implementation of trip.
 *  @author Ron Desmond
 */
public class Road implements Weighted {
    /** Constructs a Road object with specified
     *  NAME, DISTANCE, and DIRECTION with orientation
     *  FROM and TO for a compass reference. */
    public Road(String name, Double distance,
                String direction, Location from,
                Location to) {
        _name = name;
        _distance = distance;
        _direction = direction;
        _from = from;
        _to = to;
    }
    /** returns the distance, represented as
     *  as a double. */
    public Double getDist() {
        return _distance;
    }
    /** returns the string that
     *  represents the direction. */
    public String getDir() {
        return _direction;
    }
    /** returns the name as a String. */
    public String toString() {
        return _name;
    }
    /** returns the double that represents
     *  the weight. */
    public double weight() {
        return _distance;
    }
    /** Returns the initial location for this road.
     */
    public Location getFrom() {
        return _from;
    }
    /**  Returns the ending location for this road.
     */
    public Location getTo() {
        return _to;
    }

    /** The starting point of this road. */
    private Location _from;
    /** The ending point of this road. */
    private Location _to;
    /** The name of this road. */
    private String _name;
    /** The distance of the road. */
    private Double _distance;
    /** The direction of this road. */
    private String _direction;
}
