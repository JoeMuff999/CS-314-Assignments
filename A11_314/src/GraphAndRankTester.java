import java.util.Arrays;
import java.util.List;

/*  Student information for assignment:
 *
 *  On my honor, <Joey>, this programming assignment is my own work
 *  and I have not provided this code to any other student.
 *
 *  UTEID: jrm7925
 *  email address: jrmuff@utexas.edu
 *  Grader name: Andrew 
 *  Number of slip days I am using: none
 */

/*
 * Question. The assignment presents three ways to rank teams using graphs.
 * The results, especially for the last two methods are reasonable.
 * However if all results from all college football teams are included
 * some unexpected results occur. Explain the unexpected results:
 * 
 * I believe the unexpected results are caused by all wins being given equal weight. More specifically, FBS teams like UT are 
 * considered to be much stronger (more funding = get better players), and as such, a good FCS win rate means a lot less than a good FBS win rate.
 *  
 *  For example, Michigan State's 2008, 9-4 record, yielded a 13 rank lower prediction for the 3rd method of ranking. The same
 *  happened for teams like Oregon State, and Virginia Tech, who all went 9-4 (not including bowl games) while consistently playing
 *  ranked teams. Now, if we look at the algorithm's 11th and 12th rank predictions, Montana and Richmond, who went 14-2 and 13-3 respectively,
 *  we see how their record does not reflect their actual strength as a team. They played against objectively worse teams, but were able to get a better
 *  record than the FBS schools mentioned earlier. The algorithm doesn't know this, and thats why it ranked worse teams higher than better teams on the basis
 *  of their raw win rate.
 * 
 * Suggest another way of method of ranking teams using the results
 * from the graph. Thoroughly explain your method. The method can build
 * on one of the three existing algorithms.
 *
 * I took the weighted algorithm and I modified the non-averaged weighted path length. 
 * What I did:
 *  find the average cost of all adjacent edges to the vertex you are adding a weighted cost to
 *  add that sum to the edge length of the path so far
 *  add the total sum to the total weighted path length for the given vertex
 *  
 * Reasoning: Essentially, I wanted to find a way to inflate the costs of teams with good records but poor opponents.
 * 
 * The average cost of all adjacent edges to a vertex : basically, this value is lower for better teams, since it takes 
 * into account how well they did head on as opposed to the shortest weighted path. Better teams tend to have better scores, and it also helps ignore
 * the case where the shortest weighted path includes a lot of unranked teams (kind of takes rank into account in that way).
 * 
 * Sum of edge length of path so far: The longer the path, the worse the current team did against the other team. Kind of like
 * the measurement above, only that it punishes the team more directly for its poor results head-on. 
 */

public class GraphAndRankTester
{

    /**
     * Runs tests on Graph classes and FootballRanker class.
     * Experiments involve results from college football
     * teams. Central nodes in the graph are compared to
     * human rankings of the teams.
     * @param args None expected.
     */
    public static void main(String[] args)
    {
        myTests();
    }

    private static void myTests()
    {
        Graph g = new Graph();
        for (int i = 1; i < 10; i++)
        {
            g.addVertex("" + i);
        }
        for (int i = 1; i < 5; i++)
        {
            g.addEdge("" + i, "" + i * 2, i + 1);
            g.addEdge("" + i, "" + i + 3, i + 1);
        }
        g.dijkstra("1");

        List<String> result = g.findPath("2");
        List<String> expected = Arrays.asList(new String[] { "1", "2" });
        printTests(result.equals(expected));
        result = g.findPath("3");
        expected = Arrays.asList(new String[] {});
        printTests(result.equals(expected));
        result = g.findPath("9");
        expected = Arrays.asList(new String[] {});
        printTests(result.equals(expected));
        result = g.findPath("4");
        expected = Arrays.asList(new String[] { "1", "2", "4" });
        printTests(result.equals(expected));

        g.findAllPaths(true);
        printTests(g.costOfLongestShortestPath() == 10);
        printTests(g.getDiameter() == 3);
        g.addVertex("outlier");
        g.findAllPaths(true);
        g.dijkstra("outlier");
        printTests(g.edgeExists("outlier", "1") == false);

        String actual = "2014ap_poll.txt";
        String gameResults = "div12014.txt";
        FootballRanker ranker = new FootballRanker(gameResults, actual);

        ranker.doUnweighted(true);
        ranker.doWeighted(true);
        ranker.doWeightedAndWinPercentAdjusted(true);
        //checked results with friends
    }

    private static void printTests(boolean work)
    {
        if (work)
            System.out.println("Yay you passed");
        else
            System.out.println("Nay you failed");
    }


}
