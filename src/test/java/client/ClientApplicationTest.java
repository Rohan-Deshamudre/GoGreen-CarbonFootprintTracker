package client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static gogreen.application.client.ClientApplication.checkAchievement;
import static gogreen.application.client.ClientApplication.setUser;

import gogreen.application.model.CO2;
import org.junit.jupiter.api.Test;

class ClientApplicationTest {

    static CO2 user = new CO2("bob", 8, 8, 8, 8, "101010");

    @Test
    public void checkAchievementTest() {
        setUser(user);
        assertTrue(checkAchievement(5));
        assertFalse(checkAchievement(4));
    }

}