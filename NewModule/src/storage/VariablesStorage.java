package storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 10/4/2014.
 */
public class VariablesStorage {
    public static List<String> list = new ArrayList<>();
    public static String text = "";
    public static String buttonName = "Add";
    public static int elemToEdit;

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        VariablesStorage.text = text;
    }

    public static String getButtonName() {
        return buttonName;
    }

    public static void setButtonName(String buttonName) {
        VariablesStorage.buttonName = buttonName;
    }

    public static int getElemToEdit() {
        return elemToEdit;
    }

    public static void setElemToEdit(int elemToEdit) {
        VariablesStorage.elemToEdit = elemToEdit;
    }
}
