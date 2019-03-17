package application.client;

import application.model.CO2;

import java.util.Comparator;

public class SortbyCo2reduc implements Comparator<CO2> {
    /**
     * compare the integer a and b and returns in descending order.
     * @param a for parameter a
     * @param b for parameter b
     * @return retuning
     */
    public int compare(CO2 a, CO2 b) {
        return b.getCo2reduc() - a.getCo2reduc();
    }
}
