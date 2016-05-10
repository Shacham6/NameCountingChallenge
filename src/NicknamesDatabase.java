import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by shach on 5/10/2016.
 */
public class NicknamesDatabase {

    private static NicknamesDatabase database;
    private ArrayList<StringPair> list;

    {
        list = new ArrayList<StringPair>();
    }

    private NicknamesDatabase() {

        try {
            File in = new File("NicknamesDatabase.data");
            Scanner scn = new Scanner(in);
            while (scn.hasNextLine()) {
                String[] line = scn.nextLine().split(", ");

                StringPair a = new StringPair(line[1], line[2]);
                list.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static NicknamesDatabase getInstance() {
        return (database == null) ? (database = new NicknamesDatabase()) : database;
    }

    public NicknamesList getNicksForName(String name) {
        NicknamesList nicks = new NicknamesList();

        for (final StringPair pair : list) {
            if (name.equals(pair.getLeft())) nicks.add(pair.getRight());
            else if (name.equals(pair.getRight())) nicks.add(pair.getLeft());
        }

        return nicks;
    }
}
