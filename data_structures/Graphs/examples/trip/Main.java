package trip;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.FileNotFoundException;
import java.io.File;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import graph.Graph;
import graph.Graphs;
import graph.DirectedGraph;
import graph.Distancer;

/** Initial class for the 'trip' program.
 *  @author Ron Desmond
 */
public final class Main {

    /** Entry point for the CS61B trip program.  ARGS may contain options
     *  and targets:
     *      [ -m MAP ] [ -o OUT ] [ REQUEST ]
     *  where MAP (default Map) contains the map data, OUT (default standard
     *  output) takes the result, and REQUEST (default standard input) contains
     *  the locations along the requested trip.
     */
    public static void main(String... args) {
        String mapFileName;
        String outFileName;
        String requestFileName;

        mapFileName = "Map";
        outFileName = requestFileName = null;

        int a;
        for (a = 0; a < args.length; a += 1) {
            if (args[a].equals("-m")) {
                a += 1;
                if (a == args.length) {
                    usage();
                } else {
                    mapFileName = args[a];
                }
            } else if (args[a].equals("-o")) {
                a += 1;
                if (a == args.length) {
                    usage();
                } else {
                    outFileName = args[a];
                }
            } else if (args[a].startsWith("-")) {
                usage();
            } else {
                break;
            }
        }

        if (a == args.length - 1) {
            requestFileName = args[a];
        } else if (a > args.length) {
            usage();
        }

        if (requestFileName != null) {
            try {
                System.setIn(new FileInputStream(requestFileName));
            } catch  (FileNotFoundException e) {
                System.err.printf("Could not open %s.%n", requestFileName);
                System.exit(1);
            }
        }

        if (outFileName != null) {
            try {
                System.setOut(new PrintStream(new FileOutputStream(outFileName),
                                              true));
            } catch  (FileNotFoundException e) {
                System.err.printf("Could not open %s for writing.%n",
                                  outFileName);
                System.exit(1);
            }
        }
        trip(mapFileName);
    }

    /** Print a trip for the request on the standard input to the stsndard
     *  output, using the map data in MAPFILENAME.
     */
    private static void trip(String mapFileName) {
        Graph<Location, Road> map = createMap(mapFileName);
        List<Graph<Location, Road>.Vertex>
            dest = getDestinations();
        int steps = 1;
        if (dest.size() == 0) {
            return;
        }
        System.out.println("From " + dest.get(0) + ":\n");
        int iter = 0;
        Distancer<Location> h = new Distancer<Location>() {
            public double
            dist(Location l1, Location l2) {
                double y1 = l1.getY();
                return Math.sqrt(
                                 Math.pow(l1.getX()
                                          - l2.getX(), 2.0)
                                 + Math.pow(
                                            y1 - l2.getY(), 2.0));
            }
        };
        while (dest.size() - 1 > iter) {
            List<Graph<Location, Road>.Edge> directions
                = Graphs.shortestPath(map, dest.get(iter),
                                      dest.get(iter + 1), h);
            Road road =
                directions.get(0).getLabel();
            Location start =
                directions.get(0).getV0().getLabel();
            Location end =
                directions.get(0).getV1().getLabel();
            double dist = 0.0;
            steps = print(road, start, end, dist, map, directions, steps);
            iter += 1;
        }
    }

    /** Prints the current trip with starting road ROAD,
     *  starting location START, ending location END,
     *  a distance DIST, a list of DIRECTIONS, the MAP,
     *  and the number of STEPS which is updated and will
     *  return as an int. */
    private static int print(Road road, Location start,
                      Location end, double dist,
                      Graph<Location, Road> map,
                     List<Graph<Location, Road>.Edge> directions,
                     int steps) {
        for (int i = 0; i < directions.size(); i += 1) {
            if (!directions.get(i).getLabel().toString()
                .equals(road.toString())
                || !directions.get(i).getLabel().getDir()
                .equals(road.getDir())) {
                end = directions.get(i - 1).getV1().getLabel();
                System.out.printf(steps + ". Take " + road
                                  + " " + compassDir(road, start, end)
                                  + " for %.1f miles.\n", dist);
                dist = directions.get(i).getLabel().getDist();
                road = directions.get(i).getLabel();
                start = directions.get(i).getV0().getLabel();
                steps += 1;
            } else {
                dist += directions.get(i).getLabel().getDist();
            }
            if (i == directions.size() - 1) {
                end = directions.get(i).getV1().getLabel();
                System.out.printf(steps + ". Take " + road
                                  + " " + compassDir(road, start, end)
                                  + " for %.1f miles"
                                  + " to "
                                  + directions.get(i).getV1().getLabel()
                                  + ".\n", dist);
                road = directions.get(i).getLabel();
                dist = 0.0;
                steps += 1;
            }
        }
        return steps;
    }

    /** Takes in ROAD, the START and END location and returns the string
     *  which gives its direction NSEW. */
    private static String compassDir(Road road, Location start,
                                     Location end) {
        if (road.getDir().equals("S")) {
            return "south";
        } else if (road.getDir().equals("N")) {
            return "north";
        } else if (road.getDir().equals("W")) {
            return "west";
        } else {
            return "east";
        }
    }


    /** Generates and returns a list of trip locations
     *  from the standard input. */
    private static List
        <Graph<Location, Road>.Vertex> getDestinations() {
        ArrayList<Graph<Location, Road>.Vertex> ret = new ArrayList
            <Graph<Location, Road>.Vertex>();
        Scanner getLocs = new Scanner(System.in);
        while (getLocs.hasNextLine()) {
            String[] locs = getLocs.nextLine().trim().split(",?\\s+");
            if (locs.length < 2) {
                System.out.println("Less than 2 locations.");
                System.exit(1);
            }
            for (int i = 0; i < locs.length; i += 1) {
                if (_verts.containsKey(locs[i])) {
                    ret.add(_verts.get(locs[i]));
                } else {
                    System.out.println(locs[i] + " does not exist "
                                       + "in the current map.");
                    System.exit(1);
                }
            }
        }
        return ret;
    }
    /** Creates the map which it returns as a graph from file
     *  MAPFILENAME. */
    private static Graph<Location, Road> createMap(String mapFileName) {
        Graph<Location, Road> ret =
                new DirectedGraph<Location, Road>();
        try {
            Scanner mapScanner = new Scanner(new File(mapFileName));
            while (mapScanner.hasNextLine()) {
                String line = mapScanner.nextLine();
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 4 && parts[0].equals("L")) {
                    String name = parts[1];
                    Double x = 0.0;
                    Double y = 0.0;
                    try {
                        x = Double.parseDouble(parts[2]);
                        y = Double.parseDouble(parts[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid Location entry.");
                        System.exit(1);
                    }
                    _verts.put(name, ret.add(new Location(name, x, y)));
                } else if (parts.length == 6 && parts[0].equals("R")) {
                    String roadName = parts[2];
                    Location from = _verts.get(parts[1]).getLabel();
                    Location to = _verts.get(parts[5]).getLabel();
                    String dir = "";
                    if (parts[4].matches("(NS)|(EW)|(WE)|(SN)")) {
                        dir = parts[4];
                    } else {
                        System.out.println("Direction for road "
                                           + roadName
                                           + " formatted incorrectly.");
                    }
                    double dis = 0.0;
                    try {
                        dis = Double.parseDouble(parts[3]);
                    } catch (NumberFormatException e) {
                        System.out.println("Distance for road "
                                           + roadName
                                           + " formatted incorrectly.");
                    }
                    ret.add(_verts.get(from.toString()),
                            _verts.get(to.toString()),
                            new Road(roadName, dis, "" + dir.charAt(1),
                                     from, to));
                    ret.add(_verts.get(to.toString()),
                            _verts.get(from.toString()),
                            new Road(roadName, dis,
                                     "" + dir.charAt(0), from, to));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        return ret;
    }

    /** Print a brief usage message and exit program abnormally. */
    private static void usage() {
        System.err.println("Error.");
        System.exit(1);
    }

    /** Hashmap that maps locations to vertices. */
    private static HashMap<String, Graph<Location, Road>.Vertex> _verts
        = new HashMap<String, Graph<Location, Road>.Vertex>();
}


