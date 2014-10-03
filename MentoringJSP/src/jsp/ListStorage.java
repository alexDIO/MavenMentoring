package jsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olomakovskyi on 9/30/2014.
 */
public class ListStorage {
    public static List<String> list = new ArrayList<>();
    public static String text = "test";

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        ListStorage.text = text;
    }
}
