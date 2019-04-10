package gogreen.application.model;

public class Badge {

    public static String getBadge(int points) {
       if (points < 100) {
           return "images/BadgeDemo.png";
       }
       return "images/BadgeDemo.png";
    }

    public static String getTitle(int points) {
        return "Veggie";
    }
}
