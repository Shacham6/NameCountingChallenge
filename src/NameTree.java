import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by shach on 5/10/2016.
 */
public class NameTree {

    private ArrayList<FullName> names;
    private boolean hasReduced;
    {
        hasReduced = false;
    }

    public NameTree(ArrayList<FullName> names) {
        this.names = names;
    }

    public NameTree(FullName... names) {
        this.names = new ArrayList<FullName>(Arrays.<FullName>asList(names));
    }

    public NameTree removeDuplicates() {
        boolean foundDupe = false;
        for (int i = 0; i < names.size()-1 && !foundDupe; i++) {
            for(int j = i+1; j < names.size() && !foundDupe; j++) {

                if (names.get(i).equals(names.get(j))) {
                    foundDupe = true;
                    names.remove(i);
                    removeDuplicates();
                }
            }
        }
        hasReduced = true;

        return this;
    }

    public void addBranch(FullName name) {
        names.add(name);
    }

    public int numOfBranches() {
        return names.size();
    }
}
