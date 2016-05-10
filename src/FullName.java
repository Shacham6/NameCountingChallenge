import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by shach on 5/10/2016.
 */
public class FullName {

    public static final int HAMMING_DISTANCE_TYPO_LIMIT = 8;

    private String firstName,
            middleName,
            lastName;

    NicknamesList knownNicknames;

    public FullName(String firstName, String middleName, String lastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        knownNicknames = NicknamesDatabase.getInstance().getNicksForName(this.firstName);
    }

    public FullName(String... parts) {
        ArrayList<String> ls = new ArrayList<>(Arrays.<String>asList(parts));

        firstName = ls.remove(0);
        lastName = ls.remove(ls.size() - 1);

        middleName = "";
        for (String s : ls) middleName += s + " ";
        middleName = middleName.trim();

        knownNicknames = NicknamesDatabase.getInstance().getNicksForName(firstName);

    }

    public boolean hasNickname(String nick) {
        return knownNicknames.contains(nick);
    }

    public boolean equals(FullName ot) {
        boolean acceptableDistance = couldBeTypo(firstName, ot.firstName) &&
                couldBeTypo(lastName, ot.lastName);

        if (acceptableDistance) {
            if (middleName != "" && ot.middleName != "")
                //  If the differences between the first + last names of the two full names are acceptable, than the differences between the middle ones settles it
                acceptableDistance = couldBeTypo(middleName, ot.middleName);
            else acceptableDistance = true; //  Redundant. Can remove.

            return acceptableDistance;
        }

        return hasNickname(ot.firstName);
    }


    public static boolean couldBeTypo(String s1, String s2) {
        if (s1.equals(s2)) return true;

        BitSet lSet = BitSet.valueOf(s1.getBytes()),
                rSet = BitSet.valueOf(s2.getBytes());

        lSet.xor(rSet);

        if (lSet.cardinality() > HAMMING_DISTANCE_TYPO_LIMIT)
            return false;

        return true;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
