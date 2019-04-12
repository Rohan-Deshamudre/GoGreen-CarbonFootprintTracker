package client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static gogreen.application.client.ClientApplication.checkAchievement;
import static gogreen.application.client.ClientApplication.checkAchievement1;
import static gogreen.application.client.ClientApplication.checkAchievement2;
import static gogreen.application.client.ClientApplication.checkAchievement3;
import static gogreen.application.client.ClientApplication.checkAchievement4;
import static gogreen.application.client.ClientApplication.checkAchievement5;
import static gogreen.application.client.ClientApplication.setUser;

import gogreen.application.client.Leaderboard;
import gogreen.application.model.CO2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ClientApplicationTest {

    static CO2 user = new CO2("bob", 8, 8, 8, 8, "101010");
    static CO2 user1 = new CO2("bobby", 88, 8, 8, 88, "00000000000000");
    static CO2 user2 = new CO2("bobby", 88, 8, 8, 88, "11111111111111");
    static CO2 user3 = new CO2("bobby", 88, 8, 8, 20000, "00000000000000");
    static Leaderboard leaderboard = new Leaderboard();



    @Test
    public void checkAchievementTest() {
        setUser(user);
        assertTrue(checkAchievement(5));
        assertFalse(checkAchievement(4));
    }
    @Test
    public void checkAchievement2Test() {
        setUser(user3);
        assertTrue(checkAchievement2());
        setUser(user2);
        assertFalse(checkAchievement2());
        setUser(user1);
        assertFalse(checkAchievement2());
    }

    @Test
    public void checkAchievement3Test() {
        setUser(user3);
        assertTrue(checkAchievement2());
        setUser(user2);
        assertFalse(checkAchievement2());
        setUser(user1);
        assertFalse(checkAchievement2());
    }

    @Test
    public void checkAchievement1Test() {
        setUser(user);
        leaderboard.getUsers().add(user);
        assertTrue(checkAchievement1(leaderboard));

        leaderboard.getUsers().add(user1);
        leaderboard.sortLeaderboard();
        assertFalse(checkAchievement1(leaderboard));
    }

    @Test
    public void checkAchievement4Test() {
        setUser(user1);
        assertFalse(checkAchievement4(leaderboard));
        leaderboard.getUsers().add(user2);
        assertTrue(checkAchievement4(leaderboard));
        setUser(user2);
        assertFalse(checkAchievement4(leaderboard));
    }

    @Test
    public void checkAchievement5Test() {
        setUser(user1);
        assertFalse(checkAchievement5(leaderboard));
        for (int i = 0; i < 10; i++) {
            leaderboard.getUsers().add(user2);
        }
        assertTrue(checkAchievement5(leaderboard));
        setUser(user2);
        assertFalse(checkAchievement5(leaderboard));
    }

}