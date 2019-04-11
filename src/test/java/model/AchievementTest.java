package model;

import gogreen.application.model.Achievement;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AchievementTest {

    @Test
    void getTotalAchievements() {
        assertEquals(14, Achievement.getTotalAchievements());
    }

    @Test
    void changeAchievements() {
        // Mockito test
    }

    @Test
    void getName() {
        assertEquals(Achievement.getName(1), "November Grain");
        assertEquals(Achievement.getName(2), "Beet It");
        assertEquals(Achievement.getName(3), "Guys, I'm Super Cereal!");
        assertEquals(Achievement.getName(4), "Killing ManBearPig");
        assertEquals(Achievement.getName(5), "Lettuce Start");
        assertEquals(Achievement.getName(6), "Starting The Cornversation");
        assertEquals(Achievement.getName(7), "Don't Leaf Me!");
        assertEquals(Achievement.getName(8), "Taking a Leek");
        assertEquals(Achievement.getName(9), "Bush Did 7-Eleven");
        assertEquals(Achievement.getName(10), "Keeping Up With The Vegetarians");
        assertEquals(Achievement.getName(11), "World Peas");
        assertEquals(Achievement.getName(12), "I Love Fishsticks");
        assertEquals(Achievement.getName(13), "Bean There, Done That");
        assertEquals(Achievement.getName(14), "So Long And Thanks For The Fish");

    }


    @Test
    void getDescription() {
        assertEquals(Achievement.getDescription(1), "Switch on Night Mode");
        assertEquals(Achievement.getDescription(2), "Get to number 1 on the Leaderboard");
        assertEquals(Achievement.getDescription(3), "Get 1,000 points");
        assertEquals(Achievement.getDescription(4), "Get 10,000 points");
        assertEquals(Achievement.getDescription(5), "Add 1 Friend");
        assertEquals(Achievement.getDescription(6), "Add 10 Friends");
        assertEquals(Achievement.getDescription(7), "Decline a Friend Request");
        assertEquals(Achievement.getDescription(8), "Eat your first Vegetarian Meal");
        assertEquals(Achievement.getDescription(9), "Go to a Local Store");
        assertEquals(Achievement.getDescription(10), "Eat Organic Food");
        assertEquals(Achievement.getDescription(11), "Lower your Home Temperature");
        assertEquals(Achievement.getDescription(12), "Install Solar Panels");
        assertEquals(Achievement.getDescription(13), "Take a Bike Ride");
        assertEquals(Achievement.getDescription(14), "Use Public Transport");
    }
}