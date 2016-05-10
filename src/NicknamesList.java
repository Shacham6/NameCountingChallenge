import java.util.ArrayList;

/**
 * Created by shach on 5/10/2016.
 */
public class NicknamesList extends ArrayList<String> {

    public boolean contains(String o) {
        for (String nick : this)
            if (o.equals(nick))
                return true;
        return false;
    }
}
