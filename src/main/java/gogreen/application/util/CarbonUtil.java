package gogreen.application.util;

import gogreen.application.communication.AddTransportRequest.TravelType;

public class CarbonUtil {

    static  int[] values = {
        0,
        300,
        500,
        700,
        740,
        980,
        1220,
        140,
        210,
        280,
        900,
        1150,
        1350
    };

    /**
     * Returns the amount of carbon reduced in kgs by diet choices.
     * https://bit.ly/2oRzs7H
     * https://bbc.in/2EhC2w6
     *
     * @param checkBoxValue the value of the check box
     * @return returns the amount of carbon reduced in grams.
     */
    public static int getFoodCarbonReduction(String checkBoxValue, int amount) {

        if (amount == 0) {
            return 0;
        }

        int index = 0;

        if (checkBoxValue.equalsIgnoreCase("salad")) {
            index = amount;
        } else if (checkBoxValue.equalsIgnoreCase("Vegetarian Meat")) {
            index = amount + 3;
        } else if (checkBoxValue.equalsIgnoreCase("Fruit")) {
            index = amount + 6;
        } else if (checkBoxValue.equalsIgnoreCase("Vegan Meal")) {
            index = amount + 9;
        }
        return values[index];
    }

    /**
     * Returns the amount of carbon reduced in kgs by buying local produce.
     * https://bit.ly/2VJWiw7
     * https://bit.ly/2UXf5XD
     *
     * @param weight weight of the produce bought
     * @param organic true iff the produce is organic.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getLocalProduceCarbonReduction(int weight, boolean organic) {
        //0.4g of CO2 saved per g of Organic food
        //1.6g of CO2 saved if food is bought from local store
        if (organic) {
            return (int) (weight * 2.0);
        }
        return (int) (1.6 * weight);
    }

    /**
     * Returns the amount of carbon reduced in kgs by travelling by other means than a car.
     * https://bit.ly/2EOn2Tb
     * https://bit.ly/2JmXfbR
     *
     * @param travelType the type of travelling done.
     * @param distance distance covered.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getTransportCarbonReduction(TravelType travelType, int distance) {
        switch (travelType) {
            case BIKE:
                //284-16 is the difference between using a bike and a car
                return distance * (284 - 16);
            case PUB_TRANSPORT:
                //idem
                return distance * (284 - 101);
            default:
                return -1;
        }
    }

    /**
     * Returns the amount of carbon reduced in kgs by turning a home thermostat lower than the
     * average.
     * https://bit.ly/2DbKoUt
     * https://bit.ly/2UVWX0n
     *
     * @param temp the amount of degrees lowered.
     * @param duration the time in hours the thermostat stays lowered.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getHomeTempCarbonReduction(int temp, int duration) {
        // 270g of CO2 saved per hour of heating turned off.
        //Per degree reduced on average 40g is reduced.
        return (int) duration * (40 * temp);
    }

    /**
     * Returns the amount of carbon reduced in kgs by using solar panels.
     * https://bit.ly/2E0dE2G
     *
     * @param area the area covered by solar panels.
     * @param hoursSunlight the time in hours the solar panels are generating electricity.
     * @return returns the amount of carbon reduced in kgs.
     */
    public static int getSolarPanelCarbonReduction(int area, int hoursSunlight) {
        //Each meter squared of solar panel saves 19.4g of CO2 per hour
        return (int) (19.4 * area * hoursSunlight);
    }
}
