package make;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import graph.Graph;
import graph.Traversal;
import graph.NoLabel;
import graph.DirectedGraph;
import graph.StopException;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/** Initial class for the 'make' program.
 *  @author Ron Desmond
 */
public final class Main {

    /** Entry point for the CS61B make program.  ARGS may contain options
     *  and targets:
     *      [ -f MAKEFILE ] [ -D FILEINFO ] TARGET1 TARGET2 ...
     */
    public static void main(String... args) {
        String makefileName;
        String fileInfoName;

        if (args.length == 0) {
            usage();
        }

        makefileName = "Makefile";
        fileInfoName = "fileinfo";

        int a;
        for (a = 0; a < args.length; a += 1) {
            if (args[a].equals("-f")) {
                a += 1;
                if (a == args.length) {
                    usage();
                } else {
                    makefileName = args[a];
                }
            } else if (args[a].equals("-D")) {
                a += 1;
                if (a == args.length) {
                    usage();
                } else {
                    fileInfoName = args[a];
                }
            } else if (args[a].startsWith("-")) {
                usage();
            } else {
                break;
            }
        }

        ArrayList<String> targets = new ArrayList<String>();

        for (; a < args.length; a += 1) {
            targets.add(args[a]);
        }

        make(makefileName, fileInfoName, targets);
    }

    /** Carry out the make procedure using MAKEFILENAME as the makefile,
     *  taking information on the current file-system state from FILEINFONAME,
     *  and building TARGETS, or the first target in the makefile if TARGETS
     *  is empty.
     */
    private static void make(String makefileName, String fileInfoName,
                             List<String> targets) {
        Graph<Target, NoLabel> makeGraph = new DirectedGraph
            <Target, NoLabel>();
        HashMap<String, Graph<Target, NoLabel>.Vertex> vertices =
            new HashMap<String, Graph<Target, NoLabel>.Vertex>();
        int time = processFile(makeGraph, fileInfoName, vertices);
        processMake(makeGraph, makefileName, vertices);
        if (targets.size() == 0) {
            try {
                Scanner getFirstTarget = new Scanner(new File(makefileName));
                if (getFirstTarget.hasNext()) {
                    try {
                        String target = getFirstTarget.next("\\s*[^\\s:=#\\]+");
                        targets.add(target);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        System.exit(1);
                    }
                } else {
                    System.out.println("Makefile is blank and no targets "
                                       + "are specified.");
                    System.exit(1);
                }
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        makeTargets(targets, makeGraph, vertices, time);
    }

    /** Takes in a list of TARGETS, a graph of objects G that
     *  may be made or not, a hashmap VERTICES that maps a vertex's
     *  name to itself, and an int that signifies the TIME. */
    private static void makeTargets(List<String> targets, Graph
                                    <Target, NoLabel> g,
                                    HashMap<String,
                                    Graph<Target, NoLabel>.Vertex>
                                    vertices, int time) {
        for (int i = 0; i < targets.size(); i += 1) {
            MakeTraversal trav =
                new MakeTraversal(time);
            trav.depthFirstTraverse(g, vertices.get(targets.get(i)));
        }
    }
    /** Returns the time, and takes in a graph G, the name
     *  of the file that contains objects FILEINFONAME,
     *  and a hashmap VERTICES that maps a vertex's name
     *  to itself. */
    private static int processFile(Graph<Target, NoLabel> g,
                            String fileInfoName, HashMap
                            <String, Graph<Target, NoLabel>.Vertex>
                            vertices) {
        try {
            Scanner fileScanner = new Scanner(
                                              new File(fileInfoName));
            int time = -1;
            if (fileScanner.hasNextLine()) {
                String fileTime = fileScanner.nextLine();
                if (fileTime.matches("\\s*\\d+\\s*")) {
                    time = Integer.parseInt(fileTime.trim());
                } else {
                    System.out.println("File time formatted incorrectly.");
                    System.exit(1);
                }
            } else {
                System.out.println("File time formatted incorrectly.");
                System.exit(1);
            }
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.trim().split("\\s+");
                if (parts.length == 2
                    && parts[0].matches("[A-Za-z\\.]+")
                    && parts[1].matches("\\d+")) {
                    vertices.put(parts[0],
                                 g.add(new Target(parts[0],
                                                  Integer.parseInt(parts[1]))));
                } else {
                    System.out.println("File object formatted incorrectly.");
                    System.exit(1);
                }
            }
            return time;
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.out.println("Error");
        System.exit(1);
        return -1;
    }

    /** Uses the graph G, the MAKEFILENAME, and a hashmap
     *  VERTICES that maps a vertex's name to itself. */
    private static void processMake(Graph<Target, NoLabel> g,
                             String makeFileName, HashMap
                             <String, Graph<Target, NoLabel>.Vertex>
                             vertices) {
        try {
            Scanner makeScanner = new Scanner(
                                              new File(makeFileName));
            String makeRegex = "";
            String line = makeScanner.nextLine();
            while (makeScanner.hasNextLine()) {
                if (line.matches("(#.+)|\\s+|()")) {
                    continue;
                } else if (line.matches(
                                        "[^\\s:=#\\\\]+"
                                        + ":(\\s([^\\s:"
                                        + "=#\\\\]+))+")) {
                    String[] parser = line.split(":");
                    String target = parser[0];
                    String[] prereqs = parser[1].trim().split("\\s+");
                    if (!vertices.containsKey(target)) {
                        vertices.put(target, g.add(new Target(target, false)));
                    }
                    for (int i = 0; i < prereqs.length; i += 1) {
                        if (!vertices.containsKey(prereqs[i])) {
                            vertices.put(prereqs[i],
                                         g.add(new Target(prereqs[i], true)));
                        }
                        g.add(vertices.get(target), vertices.get(prereqs[i]),
                              new NoLabel());
                    }
                    boolean canAddCommand =
                        vertices.get(target).getLabel()
                        .getCommands().size() == 0;
                    while (makeScanner.hasNextLine()) {
                        line = makeScanner.nextLine();
                        if (line.matches("\\s+\\S+.*")) {
                            if (canAddCommand) {
                                vertices.get(target)
                                    .getLabel().addCommand(line);
                            } else {
                                System.out.println("Can't have separate "
                                                   + "commands for "
                                                   + "the same target");
                                System.exit(1);
                            }
                        } else if (line.matches("(#.+)|\\s+|()")) {
                            vertices.get(target).getLabel()
                                .setWarning(false);
                            continue;
                        } else {
                            break;
                        }
                    }
                } else {
                    System.out.println("Makefile formatted incorrectly.");
                    System.exit(1);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /** Print a brief usage message and exit program abnormally. */
    private static void usage() {
        System.out.println("Arguments must follow the format:"
                           + " [ -f MAKEFILE ] "
                           + "[ -D FILEINFO ] TARGET1 TARGET2 ...");
        System.exit(1);
    }

    /** A traversal class that overrides postvisit and visit
     *  to make objects as needed. */
    private static class MakeTraversal extends Traversal<
        Target, NoLabel> {
        /** Takes in TIME as the current time when
         *  creating objects. */
        public MakeTraversal(int time) {
            _time = time;
        }
        /** Traverses the graph G starting from
         *  vertex V. */
        public void depthFirstTraverse(Graph<Target, NoLabel> G,
                                       Graph<Target, NoLabel>.Vertex v) {
            _making = new HashMap<
                Graph<Target, NoLabel>.Vertex, Boolean>();
            _finalV = v;
            super.depthFirstTraverse(G, v);
        }
        /** Overrides visit to check for cycles and
         *  tentatively checks if V has to be
         *  remade. */
        protected void visit(Graph<Target, NoLabel>.Vertex v) {
            for (Graph<Target, NoLabel>.Edge e
                     : super.theGraph().outEdges(v)) {
                Graph<Target, NoLabel>.Vertex child =
                    e.getV(v);
                if (_making.get(child) != null
                    && _making.get(child)) {
                    System.out.println("The graph"
                                       + "has a cycle.");
                    System.exit(1);
                }
            }
            _making.put(v, true);
        }
        /** Postvisits V and checks the time its children are
         *  made and if it is made, remaking if necessary. */
        protected void postVisit(Graph<Target, NoLabel>.Vertex v) {
            if (v.getLabel().exists()) {
                _making.put(v, false);
            } else {
                _making.put(v, true);
            }
            for (Graph<Target, NoLabel>.Edge e
                     : super.theGraph().outEdges(v)) {
                if (e.getV(v).getLabel().getTime()
                    > v.getLabel().getTime()
                    && v.getLabel().exists()) {
                    _making.put(v, true);
                }
            }
            if (_making.get(v)) {
                v.getLabel().setCreated(_time);
                _time += 1;
                if (v.getLabel().getWarning()) {
                    System.out.println("Target was never "
                                       + "created in makefile.");
                    System.exit(1);
                }
                for (String command: v.getLabel().getCommands()) {
                    System.out.println(command);
                }
            }
            if (v == _finalV) {
                throw new StopException();
            }
        }
        /** Represents the last vertex, throws a StopException in
         *  postVisit to prevent a resetting of the graph. */
        private Graph<Target, NoLabel>.Vertex _finalV;
        /** Represents the current time. */
        private int _time;
        /** Keeps track of the vertex's level in the tree. */
        private HashMap<Graph<Target, NoLabel>.Vertex, Boolean> _making
            = new HashMap<Graph<Target, NoLabel>.Vertex, Boolean>();
    }
}
