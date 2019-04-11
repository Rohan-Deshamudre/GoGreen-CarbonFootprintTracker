package gogreen.application.model;

import gogreen.application.client.ClientApplication;
import gogreen.application.gui.AlertBox;
import gogreen.application.gui.GuiMain;

public class Achievement {

    private static String[] achievements = {
        "easterEgg",
        "November Grain",
        "Beet It",
        "Guys, I'm Super Cereal!",
        "Killing ManBearPig",
        "Lettuce Start",
        "Starting The Cornversation",
        "Don't Leaf Me!",
        "Taking a Leek",
        "Bush Did 7-Eleven",
        "Keeping Up With The Vegetarians",
        "World Peas",
        "I Love Fishsticks",
        "Bean There, Done That",
        "So Long And Thanks For The Fish"
    };
    private static String[] descriptions = {
        "easterEgg",
        "Switch on Night Mode",
        "Get to number 1 on the Leaderboard",
        "Get 1,000 points",
        "Get 10,000 points",
        "Add 1 Friend",
        "Add 10 Friends",
        "Decline a Friend Request",
        "Eat your first Vegetarian Meal",
        "Go to a Local Store",
        "Eat Organic Food",
        "Lower your Home Temperature",
        "Install Solar Panels",
        "Take a Bike Ride",
        "Use Public Transport"
    };

    public static int getTotalAchievements() {
        return 14;
    }

    /**
     * Marks one achievement for a user as done.
     * @param user the user.
     * @param id the index of the achievement to be changed.
     */
    public static void changeAchievements(CO2 user, int id) {
        String oldAchievements = user.getAchievement();
        String newAchievements = oldAchievements.substring(0,id)
                + '1' + oldAchievements.substring(id + 1);
        System.out.println(newAchievements);

        // Show new achievement pop up.
        AlertBox.display(getName(id + 1) + "\n\n" + getDescription(id + 1),
                "New Achievement!");

        GuiMain.setAchievements(newAchievements);
        ClientApplication.changeAchievements(newAchievements);
    }

    /**
     * Getter of the name of the achievement.
     * @param id id.
     * @return the name of the achievement.
     */
    public static String getName(int id) {
        return achievements[id];
    }

    /**
     * Getter for the description.
     * @param id the id.
     * @return the description.
     */
    public static String getDescription(int id) {
        return descriptions[id];
    }
}
