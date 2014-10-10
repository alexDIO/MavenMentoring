package storage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olomakovskyi on 10/9/2014.
 */
public class ListHolder {
    private List<String> storedList;

    public ListHolder(){
        storedList = new ArrayList<String>();
    }

    public List getList(){
        return storedList;
    }

}
