package gogreen.application.util;

import gogreen.application.communication.AddTransportRequest.TravelType;

public class CarbonUtil {

    public static final String AES_ENCRYPTION_SECRETKEY = "Bar12345Bar12345";

    public static final String FOOD_OPTION1 = "Ate a vegetarian meal";
    public static final String FOOD_OPTION2 = "Bought from a biological store";

    /**
     * Returns the amount of carbon reduced in kgs by diet choices.
     *
     * @param checkBoxValue the value of the check box
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getFoodCarbonReduction(String checkBoxValue, int amount) {

        if (checkBoxValue.equalsIgnoreCase("salad")) {
            switch (amount) {
                case 1:
                    return 900;
                case 2:
                    return 1100;
                case 3:
                    return 1300;
            }
        } else if (checkBoxValue.equalsIgnoreCase("Vegetarian Meat")) {
            switch (amount) {
                case 1:
                    return 900;
                case 2:
                    return 1100;
                case 3:
                    return 1300;
            }
        } else if (checkBoxValue.equalsIgnoreCase("Fruit")) {
            switch (amount) {
                case 1:
                    return 900;
                case 2:
                    return 1100;
                case 3:
                    return 1300;
            }
        } else if (checkBoxValue.equalsIgnoreCase("Else")) {
            switch (amount) {
                case 1:
                    return 900;
                case 2:
                    return 1100;
                case 3:
                    return 1300;
            }
        }
        return -1;
    }

    /**
     * Returns the amount of carbon reduced in kgs by buying local produce.
     *
     * @param weight weight of the produce bought
     * @param organic true iff the produce is organic.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getLocalProduceCarbonReduction(int weight, boolean organic) {
        if (organic) {
            return (int) (weight * 2.3);
        }
        return (int) (weight * 1.7);
    }

    /**
     * Returns the amount of carbon reduced in kgs by travelling by other means than a car.
     *
     * @param travelType the type of travelling done.
     * @param distance distance covered.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getTransportCarbonReduction(TravelType travelType, int distance) {
        switch (travelType) {
            case BIKE:
                //284-16 is the difference between using a bike and a car
                return distance * (284-16);
            case PUB_TRANSPORT:
                //idem
                return distance * (284-101);
            default:
                return -1;
        }
    }

    /**
     * Returns the amount of carbon reduced in kgs by turning a home thermostat lower than the
     * average.
     *
     * @param temp the amount of degrees lowered.
     * @param duration the time in hours the thermostat stays lowered.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getHomeTempCarbonReduction(int temp, int duration) {
        return (int) (temp * duration * 0.3);
    }

    /**
     * Returns the amount of carbon reduced in kgs by using solar panels.
     *
     * @param area the area covered by solar panels.
     * @param hoursSunlight the time in hours the solar panels are generating electricity.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getSolarPanelCarbonReduction(int area, int hoursSunlight) {
        return (int) (area * hoursSunlight * 0.4);
    }
}
