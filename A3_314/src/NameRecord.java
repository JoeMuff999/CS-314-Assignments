import java.util.ArrayList;

public class NameRecord
{
    private String name;
    private int baseDecade;

    private ArrayList<Integer> rankByDecade;
    
    public NameRecord()
    {
    }
    /*Construct a new NameRecord object based on the parameters
     * pre: name must not be null, baseDecade >= 0, rankByDecade must not be null */
    public NameRecord(String name, int baseDecade, ArrayList<Integer> rankByDecade)
    {
        if(name == null || baseDecade < 0 || rankByDecade == null)
            throw new IllegalArgumentException("Illegal Argument Exception in method"
                    + "NameRecord(String, int, ArrayList<Integer>), String and ArrayList<Integer> may not be null"
                    + "and int may not be less than 0 ");
        
        this.name = name;
        this.baseDecade = baseDecade;
        this.rankByDecade = (ArrayList<Integer>) rankByDecade.clone();
        for(int i = 0; i < this.rankByDecade.size(); i++)
        {
            if(this.rankByDecade.get(i) == 0)
            {
                this.rankByDecade.set(i,Integer.MAX_VALUE);
            }
        }
        
    }
    /* return the name of the given NameRecord as a String */
    public String getName()
    {
        return name;
    }
    /* returns the base decade of the given NameRecord as an int */
    public int getBaseDecade()
    {
        return baseDecade;
    }
    /* returns the given NameRecord's ranking for the given decade as an int 
     * pre: 0 <= decade < rankByDecade.size()*/
    public int getRankByDecade(int decade)
    {
        if(decade < 0 || decade >= rankByDecade.size())
            throw new IllegalArgumentException("Illegal Argument Exception in method"
                    + "getRankByDecade(int), int may not be less than 0 or greater than "
                    + "rankByDecade.size()");
        
        return rankByDecade.get(decade);
    }
    
    /* returns the given NameRecord's decade for its highest rank as an int
     * if there is a tie in rank, returns the latest year */
    public int getBestDecade()
    {
        int bestYear = 0;
        int bestRank = rankByDecade.get(0);
        for(int i = 1; i < rankByDecade.size(); i++)
        {
            if(bestRank >= rankByDecade.get(i))
            {
                bestYear = baseDecade + (i*10);
                bestRank = rankByDecade.get(i);
            }
        }
        return bestYear;
    }
    /* returns the number of decades the given NameRecord has a non-zero ranking
     * ie: the number of decades for which the name is top 1000 in popularity */
    public int getNumberOfRankedDecades()
    {
        int numberOfRankedDecades = 0;
        for(int i = 0; i < rankByDecade.size(); i++)
        {
            if(rankByDecade.get(i) != Integer.MAX_VALUE)
            {
                numberOfRankedDecades++;
            }
        }
        return numberOfRankedDecades;
    }
    /* returns T or F depending on whether the given NameRecord is ranked for all
     * given decades as a boolean*/
    public boolean isRankedAllDecades()
    {
        for(int i = 0; i < rankByDecade.size(); i++)
        {
            if(rankByDecade.get(i) == Integer.MAX_VALUE)
            {
                return false;
            }
        }
        return true;
    }
    /* returns T or F depending on whether the given NameRecord is ranked for ONLY one
     * decade as a boolean. Will return false if ranked for more than one decade*/
    public boolean isRankedOnlyOneDecade()
    {
        boolean result = false;
        for(int i = 0; i < rankByDecade.size(); i++)
        {
            if(rankByDecade.get(i) != Integer.MAX_VALUE)
            {
                if(result)
                {
                    return false;
                }                    
                
                result = true;
            }
        }
        return result;
    }
    /* returns whether or not the given NameRecord's rank is always decreasing. 
     * to clarify: if the rank is equal between adjacent decades, still return false. */
    public boolean isPopularityAlwaysIncreasing()
    {
        int lastRank = rankByDecade.get(0);
        for(int i = 1; i < rankByDecade.size(); i++)
        {
            if(rankByDecade.get(i) >= lastRank)
            {
                return false;
            }
            
            lastRank = rankByDecade.get(i);
        }
        return true;
    }
    /* returns whether the given NameRecord's rank is always increasing.
     * ie: is the popularity always decreasing. adjacent rank ties still return false
     */
    public boolean isPopularityAlwaysDecreasing()
    {
        int lastRank = rankByDecade.get(0);
        for(int i = 1; i < rankByDecade.size(); i++)
        {
            if(rankByDecade.get(i) <= lastRank)
            {
                return false;
            }
            lastRank = rankByDecade.get(i);
        }
        return true;
    }
    /* overrides the toString method and returns the given NameRecord's name
     * and rank by decade */
    public String toString()
    {
        StringBuilder stringToReturn = new StringBuilder();
        stringToReturn.append(name + "\n");
        for(int i = 0; i < rankByDecade.size(); i++)
        {
            int year = baseDecade+ (i*10);
            int rank = rankByDecade.get(i);
            if(rank == Integer.MAX_VALUE)
            {
                rank = 0;
            }
            stringToReturn.append("" + year + ": " + rank + "\n");
        }
        return stringToReturn.toString();
    }
    
}
