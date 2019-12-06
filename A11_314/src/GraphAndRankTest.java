import java.util.Arrays;
import java.util.List;

public class GraphAndRankTest
{
    /**
     * @author Kael Kirk
     */
    
    public static void main(String [] args)
    {
        dijkstraTests();
    }
    private static void dijkstraTests() {

      int testNumber = 0;
      Graph g;
      List<String> result, expected;

      test("Dijkstra on small, undirected, and \"unweighted\" graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "A", "C", "1" }, //
          { "B", "C", "1" }, //
          { "D", "C", "1" }, //
      }, false);

      g.dijkstra("A");

      result = g.findPath("A");
      expected = Arrays.asList(new String[] { "A" });
      assertEqual(g.getNumEdgesFromStart("A"), 0, ++testNumber, "A is 0 edges away");
      assertEqual(result, expected, ++testNumber, "A to A");
      assertEqual(g.getWeightedCostFromStart("A"), 0d, ++testNumber, "A to A costs nothing");

      result = g.findPath("B");
      expected = Arrays.asList(new String[] { "A", "B" });
      assertEqual(g.getNumEdgesFromStart("B"), 1, ++testNumber, "B is 1 edge away");
      assertEqual(result, expected, ++testNumber, "A to neighbor");
      assertEqual(g.getWeightedCostFromStart("B"), 1d, ++testNumber, "A to neighbor cost");

      result = g.findPath("C");
      expected = Arrays.asList(new String[] { "A", "C" });
      assertEqual(g.getNumEdgesFromStart("C"), 1, ++testNumber, "C is 1 edge away");
      assertEqual(result, expected, ++testNumber, "A to neighbor");
      assertEqual(g.getWeightedCostFromStart("C"), 1d, ++testNumber, "A to neighbor cost");

      result = g.findPath("D");
      expected = Arrays.asList(new String[] { "A", "C", "D" });
      assertEqual(g.getNumEdgesFromStart("D"), 2, ++testNumber, "D is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to D neighbor");
      assertEqual(g.getWeightedCostFromStart("D"), 2d, ++testNumber, "A to not near neighbor cost");

      test("Dijkstra on small, undirected, and weighted graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "B", "C", "1" }, //
          { "C", "D", "1" }, //
          { "D", "E", "1" }, //
          { "E", "F", "1" }, //
          { "F", "G", "1" }, //
          { "A", "C", "100" }, //
          { "A", "D", "100" }, //
          { "A", "E", "100" }, //
          { "A", "F", "3" }, //
          { "A", "G", "100" }, //
      }, false);

      g.dijkstra("A");

      result = g.findPath("B");
      expected = Arrays.asList(new String[] { "A", "B" });
      assertEqual(g.getNumEdgesFromStart("B"), 1, ++testNumber, "B is 1 edge away");
      assertEqual(result, expected, ++testNumber, "A to B");
      assertEqual(g.getWeightedCostFromStart("B"), 1d, ++testNumber, "A to B small cost");

      result = g.findPath("C");
      expected = Arrays.asList(new String[] { "A", "B", "C" });
      assertEqual(g.getNumEdgesFromStart("C"), 2, ++testNumber, "C is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to C takes longer route, less cost");
      assertEqual(g.getWeightedCostFromStart("C"), 2d, ++testNumber, "A to C small cost");

      result = g.findPath("D");
      expected = Arrays.asList(new String[] { "A", "B", "C", "D" });
      assertEqual(g.getNumEdgesFromStart("D"), 3, ++testNumber, "D is 3 edges away");
      assertEqual(result, expected, ++testNumber, "A to D takes longer route, less cost");
      assertEqual(g.getWeightedCostFromStart("D"), 3d, ++testNumber, "A to D small cost");

      result = g.findPath("E");
      expected = Arrays.asList(new String[] { "A", "F", "E" });
      assertEqual(g.getNumEdgesFromStart("E"), 2, ++testNumber, "E is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to E has equal cost route, selects fewest nodes");
      assertEqual(g.getWeightedCostFromStart("E"), 4d, ++testNumber, "A to E small cost");

      result = g.findPath("F");
      expected = Arrays.asList(new String[] { "A", "F" });
      assertEqual(g.getNumEdgesFromStart("F"), 1, ++testNumber, "F is 1 edge away");
      assertEqual(result, expected, ++testNumber, "A to F takes direct route");
      assertEqual(g.getWeightedCostFromStart("F"), 3d, ++testNumber, "A to F small cost");

      result = g.findPath("G");
      expected = Arrays.asList(new String[] { "A", "F", "G" });
      assertEqual(g.getNumEdgesFromStart("G"), 2, ++testNumber, "G is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to G uses shortcut");
      assertEqual(g.getWeightedCostFromStart("G"), 4d, ++testNumber, "A to G small cost");

      test("Dijkstra on a disconnected, undirected, and weighted graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "A", "C", "4" }, //
          { "B", "C", "3" }, //
          { "C", "D", "10" }, //
          { "Z", "Y", "1" }, //
          { "Z", "W", "2" }, //
          { "Y", "X", "4" }, //
          { "X", "W", "3" }, //
      }, false);

      g.dijkstra("A");

      result = g.findPath("D");
      expected = Arrays.asList(new String[] { "A", "C", "D" });
      assertEqual(g.getNumEdgesFromStart("D"), 2, ++testNumber, "D is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to D has paths of equal cost, chooses fewest edges");
      assertEqual(g.getWeightedCostFromStart("D"), 14d, ++testNumber, "cost is accurate");

      result = g.findPath("Z");
      expected = Arrays.asList(new String[] {});
      assertEqual(g.getNumEdgesFromStart("Z"), -1, ++testNumber, "No edges to Z exist");
      assertEqual(result, expected, ++testNumber, "No path exists");
      assertEqual(g.getWeightedCostFromStart("Z"), -1d, ++testNumber, "cost for non-existent path");

      result = g.findPath("X");
      expected = Arrays.asList(new String[] {});
      assertEqual(g.getNumEdgesFromStart("X"), -1, ++testNumber, "No edges to X exist");
      assertEqual(result, expected, ++testNumber, "No path exists");
      assertEqual(g.getWeightedCostFromStart("X"), -1d, ++testNumber, "cost for non-existent path");

      g.dijkstra("X");

      result = g.findPath("Z");
      assertEqual(g.getNumEdgesFromStart("Z"), 2, ++testNumber, "Z is 2 edges away");
      assertEqual(g.getWeightedCostFromStart("Z"), 5d, ++testNumber, "cost is accurate");

      result = g.findPath("A");
      expected = Arrays.asList(new String[] {});
      assertEqual(g.getNumEdgesFromStart("A"), -1, ++testNumber, "No edges to A exist");
      assertEqual(result, expected, ++testNumber, "No path exists");
      assertEqual(g.getWeightedCostFromStart("A"), -1d, ++testNumber, "cost for non-existent path");

      result = g.findPath("D");
      expected = Arrays.asList(new String[] {});
      assertEqual(g.getNumEdgesFromStart("D"), -1, ++testNumber, "No edges to D exist");
      assertEqual(result, expected, ++testNumber, "No path exists");
      assertEqual(g.getWeightedCostFromStart("D"), -1d, ++testNumber, "cost for non-existent path");

      test("Dijkstra on small, directed, and weighted graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "B", "C", "1" }, //
          { "C", "D", "1" }, //
          { "D", "E", "1" }, //
          { "E", "F", "1" }, //
          { "F", "G", "1" }, //
          { "A", "C", "100" }, //
          { "A", "D", "100" }, //
          { "A", "E", "100" }, //
          { "A", "F", "3" }, //
          { "A", "G", "100" }, //
      }, true);

      g.dijkstra("A");

      result = g.findPath("B");
      expected = Arrays.asList(new String[] { "A", "B" });
      assertEqual(g.getNumEdgesFromStart("B"), 1, ++testNumber, "B is 1 edge away");
      assertEqual(result, expected, ++testNumber, "A to B");
      assertEqual(g.getWeightedCostFromStart("B"), 1d, ++testNumber, "A to B small cost");

      result = g.findPath("C");
      expected = Arrays.asList(new String[] { "A", "B", "C" });
      assertEqual(g.getNumEdgesFromStart("C"), 2, ++testNumber, "C is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to C takes longer route, less cost");
      assertEqual(g.getWeightedCostFromStart("C"), 2d, ++testNumber, "A to C small cost");

      result = g.findPath("D");
      expected = Arrays.asList(new String[] { "A", "B", "C", "D" });
      assertEqual(g.getNumEdgesFromStart("D"), 3, ++testNumber, "D is 3 edges away");
      assertEqual(result, expected, ++testNumber, "A to D takes longer route, less cost");
      assertEqual(g.getWeightedCostFromStart("D"), 3d, ++testNumber, "A to D small cost");

      result = g.findPath("E");
      expected = Arrays.asList(new String[] { "A", "B", "C", "D", "E" });
      assertEqual(g.getNumEdgesFromStart("E"), 4, ++testNumber, "E is 4 edges away");
      assertEqual(result, expected, ++testNumber, "A to E with fewer nodes is not possible");
      assertEqual(g.getWeightedCostFromStart("E"), 4d, ++testNumber, "A to E small cost");

      result = g.findPath("F");
      expected = Arrays.asList(new String[] { "A", "F" });
      assertEqual(g.getNumEdgesFromStart("F"), 1, ++testNumber, "F is 1 edge away");
      assertEqual(result, expected, ++testNumber, "A to F takes direct route");
      assertEqual(g.getWeightedCostFromStart("F"), 3d, ++testNumber, "A to F small cost");

      result = g.findPath("G");
      expected = Arrays.asList(new String[] { "A", "F", "G" });
      assertEqual(g.getNumEdgesFromStart("G"), 2, ++testNumber, "G is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to G uses shortcut");
      assertEqual(g.getWeightedCostFromStart("G"), 4d, ++testNumber, "A to G small cost");

      g.dijkstra("G");

      result = g.findPath("G");
      expected = Arrays.asList(new String[] { "G" });
      assertEqual(g.getNumEdgesFromStart("G"), 0, ++testNumber, "G is 0 edges away");
      assertEqual(result, expected, ++testNumber, "G to G");
      assertEqual(g.getWeightedCostFromStart("G"), 0d, ++testNumber, "G to itself costs nothing");

      result = g.findPath("F");
      expected = Arrays.asList(new String[] {});
      assertEqual(g.getNumEdgesFromStart("F"), -1, ++testNumber, "No edges to F exist");
      assertEqual(result, expected, ++testNumber, "No path is possible");
      assertEqual(g.getWeightedCostFromStart("F"), -1d, ++testNumber, "G can't go anywhere, no cost");

      result = g.findPath("A");
      expected = Arrays.asList(new String[] {});
      assertEqual(g.getNumEdgesFromStart("A"), -1, ++testNumber, "No edges to A exist");
      assertEqual(result, expected, ++testNumber, "No path is possible");
      assertEqual(g.getWeightedCostFromStart("A"), -1d, ++testNumber, "G can't go anywhere, no cost");

      test("Dijkstra on circular, undirected, and \"unweighted\" graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "B", "C", "1" }, //
          { "C", "D", "1" }, //
          { "D", "E", "1" }, //
          { "E", "F", "1" }, //
          { "F", "G", "1" }, //
          { "G", "H", "1" }, //
          { "H", "A", "1" }, //
      }, false);

      g.dijkstra("A");

      result = g.findPath("H");
      expected = Arrays.asList(new String[] { "A", "H" });
      assertEqual(g.getNumEdgesFromStart("H"), 1, ++testNumber, "H is 1 edge away");
      assertEqual(result, expected, ++testNumber, "A to H");
      assertEqual(g.getWeightedCostFromStart("H"), 1d, ++testNumber, "small cost");

      result = g.findPath("E");
      assertEqual(g.getNumEdgesFromStart("E"), 4, ++testNumber, "E is 4 edges away");
      assertEqual(g.getWeightedCostFromStart("E"), 4d, ++testNumber, "A to E has routes of equal cost and length");

      test("Dijkstra on circular, directed, and \"unweighted\" graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "B", "C", "1" }, //
          { "C", "D", "1" }, //
          { "D", "E", "1" }, //
          { "E", "F", "1" }, //
          { "F", "G", "1" }, //
          { "G", "H", "1" }, //
          { "H", "A", "1" }, //
      }, true);

      g.dijkstra("A");

      result = g.findPath("H");
      expected = Arrays.asList(new String[] { "A", "B", "C", "D", "E", "F", "G", "H" });
      assertEqual(g.getNumEdgesFromStart("H"), 7, ++testNumber, "H is 7 edges away");
      assertEqual(result, expected, ++testNumber, "A to H goes the long way around");
      assertEqual(g.getWeightedCostFromStart("H"), 7d, ++testNumber, "Long way around costs a lot");

      test("Dijkstra on circular, directed, and weighted graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "B", "C", "2" }, //
          { "C", "D", "4" }, //
          { "D", "E", "8" }, //
          { "E", "F", "16" }, //
          { "F", "G", "32" }, //
          { "G", "H", "64" }, //
          { "H", "A", "128" }, //
      }, true);

      g.dijkstra("A");

      result = g.findPath("E");
      expected = Arrays.asList(new String[] { "A", "B", "C", "D", "E" });
      assertEqual(g.getNumEdgesFromStart("E"), 4, ++testNumber, "E is 4 edges away");
      assertEqual(result, expected, ++testNumber, "A to E has a single route option");
      assertEqual(g.getWeightedCostFromStart("E"), 15d, ++testNumber, "Single route cost is reasonable");

      result = g.findPath("H");
      expected = Arrays.asList(new String[] { "A", "B", "C", "D", "E", "F", "G", "H" });
      assertEqual(g.getNumEdgesFromStart("H"), 7, ++testNumber, "H is 7 edges away");
      assertEqual(result, expected, ++testNumber, "A to H goes the long way around");
      assertEqual(g.getWeightedCostFromStart("H"), 127d, ++testNumber, "Long way around costs a lot");
      test("Dijkstra on an almost disconnected, directed, and weighted graph");

      g = getGraph(new String[][] { //
          { "A", "B", "1" }, //
          { "A", "C", "4" }, //
          { "B", "C", "3" }, //
          { "C", "D", "10" }, //
          { "Z", "Y", "1" }, //
          { "Z", "W", "2" }, //
          { "Y", "X", "4" }, //
          { "X", "W", "3" }, //
          { "C", "Y", "100" }, //
      }, true);

      g.dijkstra("A");

      result = g.findPath("D");
      expected = Arrays.asList(new String[] { "A", "C", "D" });
      assertEqual(g.getNumEdgesFromStart("D"), 2, ++testNumber, "D is 2 edges away");
      assertEqual(result, expected, ++testNumber, "A to D is straightforward");
      assertEqual(g.getWeightedCostFromStart("D"), 14d, ++testNumber, "cost is accurate");

      result = g.findPath("X");
      expected = Arrays.asList(new String[] { "A", "C", "Y", "X" });
      assertEqual(g.getNumEdgesFromStart("X"), 3, ++testNumber, "X is 3 edges away");
      assertEqual(result, expected, ++testNumber, "A to X uses the only directed edge");
      assertEqual(g.getWeightedCostFromStart("X"), 108d, ++testNumber, "A to X costs a lot because it's in Farawayville");

      g.dijkstra("Z");

      result = g.findPath("Y");
      expected = Arrays.asList(new String[] { "Z", "Y" });
      assertEqual(g.getNumEdgesFromStart("Y"), 1, ++testNumber, "Y is 1 edge away");
      assertEqual(result, expected, ++testNumber, "Z to Y uses the only directed edge");
      assertEqual(g.getWeightedCostFromStart("Y"), 1d, ++testNumber, "Z to Y costs very little");

      result = g.findPath("D");
      expected = Arrays.asList(new String[] {});
      assertEqual(g.getNumEdgesFromStart("D"), -1, ++testNumber, "Z to D doesn't exist");
      assertEqual(result, expected, ++testNumber, "since the edge between C and Y is one-way, no path exists");
      assertEqual(g.getWeightedCostFromStart("D"), -1d, ++testNumber, "invalid cost reflects non-existent path");

    }

    /**
     * tests equivalence between two instances of Object and prints result to
     * System.out
     */
    private static void assertEqual(Object given, Object expected, int testNumber, String message) {
      boolean passed = given.equals(expected);
      String passFailOutput = passed ? "PASSED" : "FAILED";

      System.out.printf("%s Test %d: %s%n", passFailOutput, testNumber, message);
      if (!passed)
        System.out.printf("\tEXPECTED %s; RECIEVED %s%n", expected, given);
    }

    /**
     * print testing header
     */
    private static void test(String what) {
      System.out.printf("\nNOW TESTING %s:\n\n", what);
    }
    
    
    private static Graph getGraph(String[][] edges, boolean directed) {
        Graph result = new Graph();
        for (String[] edge : edges) {
            result.addEdge(edge[0], edge[1], Double.parseDouble(edge[2]));
            // If edges are for an undirected graph add edge in other direction too.
            if (!directed) {
                result.addEdge(edge[1], edge[0], Double.parseDouble(edge[2]));
            }
        }
        return result;
    }

    // Tests the all paths method. Run each set of tests twice to ensure the Graph
    // is correctly reseting each time
    private static void doAllPathsTests(String graphNumber, Graph g, boolean weighted,
                    int expectedDiameter, double expectedCostOfLongestShortestPath,
                    String[] expectedPaths) {

        System.out.println("\nTESTING ALL PATHS INFO ON " + graphNumber + ". ");
        for (int i = 0; i < 2; i++) {
            System.out.println("Test run = " + (i + 1));
            System.out.println("Find all paths weighted = " + weighted);
            g.findAllPaths(weighted);
            int actualDiameter = g.getDiameter();
            double actualCostOfLongestShortesPath = g.costOfLongestShortestPath();
            if (actualDiameter == expectedDiameter) {
                System.out.println("Passed diameter test.");
            } else {
                System.out.println("FAILED diameter test. "
                                + "Expected = "  + expectedDiameter +
                                " Actual = " + actualDiameter);
            }
            if (actualCostOfLongestShortesPath == expectedCostOfLongestShortestPath) {
                System.out.println("Passed cost of longest shortest path. test.");
            } else {
                System.out.println("FAILED cost of longest shortest path. "
                                + "Expected = "  + expectedCostOfLongestShortestPath +
                                " Actual = " + actualCostOfLongestShortesPath);
            }
            testAllPathsInfo(g, expectedPaths);
            System.out.println();
        }

    }

    // Compare results of all paths info on Graph to expected results.
    private static void testAllPathsInfo(Graph g, String[] expectedPaths) {
        int index = 0;

        for (AllPathsInfo api : g.getAllPaths()) {
            if (expectedPaths[index].equals(api.toString())) {
                System.out.println(expectedPaths[index] + " is correct!!");
            } else {
                System.out.println("ERROR IN ALL PATHS INFO: ");
                System.out.println("index: " + index);
                System.out.println("EXPECTED: " + expectedPaths[index]);
                System.out.println("ACTUAL: " + api.toString());
                System.out.println();
            }
            index++;
        }
        System.out.println();
    }

    // Test the FootballRanker on the given file.
    private static void doRankTests(FootballRanker ranker) {
        System.out.println("\nTESTS ON FOOTBALL TEAM GRAPH WITH FootBallRanker CLASS: \n");
        double actualError = ranker.doUnweighted(false);
        if (actualError == 13.7) {
            System.out.println("Passed unweighted test");
        } else {
            System.out.println("FAILED UNWEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 13.7, actual: " + actualError);
        }

        actualError = ranker.doWeighted(false);
        if (actualError == 12.6) {
            System.out.println("Passed weigthed test");
        } else {
            System.out.println("FAILED WEIGHTED ROOT MEAN SQUARE ERROR TEST. Expected 12.6, actual: " + actualError);
        }


        actualError = ranker.doWeightedAndWinPercentAdjusted(false);
        if (actualError == 6.3) {
            System.out.println("Passed unweighted win percent test");
        } else {
            System.out.println("FAILED WEIGHTED  AND WIN PERCENT ROOT MEAN SQUARE ERROR TEST. Expected 6.3, actual: " + actualError);
        }
    }
}
