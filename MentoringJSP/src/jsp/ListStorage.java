package jsp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olomakovskyi on 9/30/2014.
 */
public class ListStorage {
    public static List<String> list = new ArrayList<>();
    public static String text = "";
    public static String buttonName = "Add";
    public static int elemToEdit;

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        ListStorage.text = text;
    }

    public static String getButtonName() {
        return buttonName;
    }

    public static void setButtonName(String buttonName) {
        ListStorage.buttonName = buttonName;
    }

    public static int getElemToEdit() {
        return elemToEdit;
    }

    public static void setElemToEdit(int elemToEdit) {
        ListStorage.elemToEdit = elemToEdit;
    }
}
