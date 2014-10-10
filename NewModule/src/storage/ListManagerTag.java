package storage;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;


/**
 * Created by olomakovskyi on 10/9/2014.
 */
public class ListManagerTag extends TagSupport {
    private List<String> storage;
    private String valueToAdd;
    private Integer indexToRemove;
    private String newValueToSet;
    private Integer indexToSet;

    public void setStorage(List<String> storage) {
        this.storage = storage;
    }

    public void setValueToAdd(String valueToAdd) {
        this.valueToAdd = valueToAdd;
    }

    public void setIndexToRemove(Integer indexToRemove) {
        this.indexToRemove = indexToRemove;
    }

//    public void setIndexToRemove(String indexToRemove) {
//        this.indexToRemove = Integer.parseInt(indexToRemove);
//    }

    public void setNewValueToSet(String newValueToSet) {
        this.newValueToSet = newValueToSet;
    }

    public void setIndexToSet(String indexToSet) {
        this.indexToSet = Integer.parseInt(indexToSet);
    }


    @Override
    public int doStartTag() throws JspException {
        if (storage != null) {
            if (valueToAdd != null) {
                storage.add(valueToAdd);
            }

            if (indexToRemove != null) {
                storage.remove(indexToRemove.intValue());
            }

            if (indexToSet != null && newValueToSet != null) {
                storage.set(indexToSet.intValue(), newValueToSet);
            }
        }

        return SKIP_BODY;
    }
}
