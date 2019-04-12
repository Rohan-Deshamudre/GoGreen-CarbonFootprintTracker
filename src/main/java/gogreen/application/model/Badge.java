package gogreen.application.model;

public class Badge {

    /**
     * Badge getter.
     * @param points amount of points.
     * @return the url of the badge location.
     */
    public static String getBadge(int points) {
        if (points < 100) {
            return "images/Veggie.png";
        }
        if (points < 400) {
            return "images/Squirrel.png";
        }
        if (points < 1000) {
            return "images/Carrot.png";
        }
        if (points < 4000) {
            return "images/PolarBear.png";
        }
        if (points < 10000) {
            return "images/MelonHead.png";
        }
        return "images/WildCabbage.png";
    }

    /**
     * Title getter.
     * @param points the amount of points a user has.
     * @return the title.
     */
    public static String getTitle(int points) {
        if (points < 100) {
            return "Veggie";
        }
        if (points < 400) {
            return "Squirrel";
        }
        if (points < 1000) {
            return "Carrot";
        }
        if (points < 4000) {
            return "Polar Bear";
        }
        if (points < 10000) {
            return "Melon Head";
        }
        return "Wild Cabbage";
    }
}
