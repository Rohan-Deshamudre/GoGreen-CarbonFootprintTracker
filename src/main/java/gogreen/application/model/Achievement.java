package gogreen.application.model;

public class Achievement {

    private static int totalAchievements = 14;

    public static int getTotalAchievements() {
        return totalAchievements;
    }

    public static String getName(int id) {
        switch (id) {
            case 1:
                return "November Grain";
            case 2:
                return "Beet It";
            case 3:
                return "Guys, I'm Super Cereal!";
            case 4:
                return "Killing ManBearPig";
            case 5:
                return "Lettuce Start";
            case 6:
                return "Starting The Cornversation";
            case 7:
                return "Don't Leaf Me!";
            case 8:
                return "Taking a Leek";
            case 9:
                return "Bush Did 7-Eleven";
            case 10:
                return "Keeping Up With The Vegetarians";
            case 11:
                return "World Peas";
            case 12:
                return "I Love Fishsticks";
            case 13:
                return "Bean There, Done That";
            default:
                return "So Long And Thanks For The Fish";
        }
    }

    public static String getDescription(int id) {
        switch (id) {
            case 1:
                return "";
            case 2:
                return "";
            case 3:
                return "";
            case 4:
                return "";
            case 5:
                return "";
            case 6:
                return "";
            case 7:
                return "";
            case 8:
                return "";
            case 9:
                return "";
            case 10:
                return "";
            case 11:
                return "";
            case 12:
                return "";
            case 13:
                return "";
            default:
                return "";
        }
    }
}
