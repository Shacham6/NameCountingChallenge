/**
 * Created by shach on 5/9/2016.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Scanner;

import static org.junit.Assert.*;

public class Main {

    private static ArrayList<StringPair> nicknames = new ArrayList<>();

    public static void main(String... args) {
        runTestCases();
    }


    public static int countUniqueNames(String bFN, String bLN, String sFN, String sLN, String bNOC) {

        int bmi = bFN.indexOf(" "),
                smi = sFN.indexOf(" ");
        FullName[] names = {
                ((bmi == -1)) ? new FullName(bFN, "", bLN) : new FullName(bFN.substring(0, bmi), bFN.substring(bmi + 1), bLN),
                ((smi == -1)) ? new FullName(sFN, "", sLN) : new FullName(sFN.substring(0, smi), sFN.substring(smi +1), sLN),
                new FullName(bNOC.split(" "))
        };

        int sum = new NameTree(names).removeDuplicates().numOfBranches();
        //  Switching between the first name and the last name, since they may be present in reverse order
        String temp = names[2].getFirstName();
        names[2].setFirstName(names[2].getLastName());
        names[2].setLastName(temp);

        return (sum == 1) ? 1 : Math.min(sum, new NameTree(names).removeDuplicates().numOfBranches());
    }

    public static void runTestCases() {

        assertEquals("1-->", 1, countUniqueNames("Deborah", "Egli", "Deborah", "Egli", "Deborah Egli"));
        assertEquals("2-->", 1, countUniqueNames("Deborah", "Egli", "Debbie", "Egli", "Debbie Egli"));
        assertEquals("3-->", 1, countUniqueNames("Deborah", "Egni", "Deborah", "Egli", "Deborah Egli"));
        assertEquals("4-->", 1, countUniqueNames("Deborah S", "Egli", "Deborah", "Egli", "Egli Deborah"));
        assertEquals("5-->", 2, countUniqueNames("Michele", "Egli", "Deborah", "Egli", "Michele Egli"));
        assertEquals("6-->", 1, countUniqueNames("Deborah", "Egli", "Deborah", "Egli", "Deborah Egli"));
        assertEquals("7-->", 2, countUniqueNames("Deborah S", "Egli", "Deborah", "G Egli", "Deborah Egli"));
        assertEquals("8-->", 1, countUniqueNames("Deborah", "Egli", "Debbie", "Egli", "Debbie Egli"));
        assertEquals("9-->", 1, countUniqueNames("Deborah", "Egni", "Deborah", "Egli", "Deborah Egli"));
        assertEquals("10-->", 2, countUniqueNames("Deborah Bra Lo", "Ehni", "Deborah", "S Egli", "Deborah Egli"));
        //assertEquals("10-->", 3, countUniqueNames("Deborah Bra Lo", "Ehni", "Deborah", "S Egli", "Deborah Egli"));
        assertEquals("11-->", 1, countUniqueNames("Deborah", "Egli", "Deborah", "Egli", "Deborah Gangsta Man Egli"));
        assertEquals("12-->", 3, countUniqueNames("Deborah Bra Lo", "Egni", "Deborah", "S Egli", "Deborah G Egli"));
        assertEquals("13-->", 2, countUniqueNames("Deborah Ahi", "Tov Egni", "Deborah", "Egli", "Deborah Ahi Tov Egli"));
        assertEquals("14-->", 2, countUniqueNames("Deborah Ahi", "Tov Egni", "Deborah", "Egli", "Deborah AhiTov Egli"));
        assertEquals("15-->", 1, countUniqueNames("Deborah S", "Egli", "Deborah", "Egli", "Egli Deborah"));
        assertEquals("16-->", 2, countUniqueNames("Michele", "Egli", "Deborah", "Egli", "Michele Egli"));
    }


}
