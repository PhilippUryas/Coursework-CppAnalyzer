package Shingles;

import java.util.ArrayList;

public class HashCodeFiller {

    private final ArrayList<String> sources;
    private final ArrayList<Integer> hashCodes = new ArrayList<>();

    public HashCodeFiller(ArrayList<String> sources)
    {
        this.sources = sources;
        setHashCodes();
    }

    public ArrayList<Integer> getHashCodes()
    {
        return hashCodes;
    }

    private void setHashCodes()
    {
        for(String s : sources)
        {
            hashCodes.add(s.hashCode());
        }
    }
}
