package gogreen.application.util;

public class CarbonUtil {

    public static final String AES_ENCRYPTION_SECRETKEY = "Bar12345Bar12345";

    public static final String FOOD_OPTION1 = "Ate a vegetarian meal";
    public static final String FOOD_OPTION2 = "Bought from a biological store";

    private CarbonUtil() {

    }

    /**
     * Saves the carbon footprint.
     * @param checkBoxValue the value of the check box
     * @return returns the method
     */

    public static int getFoodCarbonfootprint(String checkBoxValue) {
        int carbonFootprint = 0;
        if (checkBoxValue.equalsIgnoreCase("salad")) {
            carbonFootprint = 100;
        } else if (checkBoxValue.equalsIgnoreCase("Vegetarian Meat")) {
            carbonFootprint = 200;
        } else if (checkBoxValue.equalsIgnoreCase("Fruit")) {
            carbonFootprint = 80;
        } else if (checkBoxValue.equalsIgnoreCase("Else")) {
            carbonFootprint = 150;
        }
        return carbonFootprint;
    }


    public static int getTransportCarbonfootprint(int distance, int timesaweek) {
        int transportCarbonFootPrint = distance * timesaweek + 50;
        return transportCarbonFootPrint;
    }
}
