package gogreen.application.client;

import gogreen.application.model.CO2;

import java.util.Comparator;

public class SortbyCo2reduc implements Comparator<CO2> {
    /**
     * compare the integer a and b and returns in descending order.
     * @param a1 for parameter a1
     * @param b1 for parameter b1
     * @return retuning
     */
    public int compare(CO2 a1, CO2 b1) {
        return b1.getCO2reduc() - a1.getCO2reduc();
    }
}
