package olomakovskyi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olomakovskyi on 8/29/2014.
 */
public class PurchasesList {
    List<String> purchasesList = new ArrayList<>();

    public List<String> getPurchasesList() {
        return purchasesList;
    }

    public void setPurchasesList(List<String> purchasesList) {
        this.purchasesList = purchasesList;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
