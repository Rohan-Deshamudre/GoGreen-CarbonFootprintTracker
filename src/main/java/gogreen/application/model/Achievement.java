package gogreen.application.model;

import gogreen.application.client.ClientApplication;
import gogreen.application.gui.AlertBox;
import gogreen.application.gui.GuiMain;

public class Achievement {

    private static int totalAchievements = 14;

    public static int getTotalAchievements() {
        return totalAchievements;
    }

    /**
     * Marks one achievement for a user as done.
     * @param user the user.
     * @param i the index of the achievement to be changed.
     * @return the new achievements String.
     */
    public static void changeAchievements(CO2 user, int i) {
        String oldAchievements = user.getAchievement();
        String newAchievements = oldAchievements.substring(0,i)
                + '1' +oldAchievements.substring(i+1);
        System.out.println(newAchievements);

        // Show new achievement pop up.
        AlertBox.display(getName(i+1) + "\n\n" + getDescription(i + 1)
                , "New Achievement!");

        GuiMain.setAchievements(newAchievements);
        ClientApplication.changeAchievements(user.getCUsername(), newAchievements);
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
                return "Switch on Night Mode";
            case 2:
                return "Get to number 1 on the Leaderboard";
            case 3:
                return "Get 1,000 points";
            case 4:
                return "Get 10,000 points";
            case 5:
                return "Add 1 Friend";
            case 6:
                return "Add 10 Friends";
            case 7:
                return "Decline a Friend Request";
            case 8:
                return "Eat your first Vegetarian Meal";
            case 9:
                return "Go to a Local Store";
            case 10:
                return "Eat Organic Food";
            case 11:
                return "Lower your Home Temperature";
            case 12:
                return "Install Solar Panels";
            case 13:
                return "Take a Bike Ride";
            default:
                return "Use Public Transport";
        }
    }
}
