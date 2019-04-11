package model;

import static junit.framework.TestCase.assertTrue;

import gogreen.application.model.Badge;
import org.junit.jupiter.api.Test;

public class BadgeTest {

    @Test
    public void getBadgeTest() {
        assertTrue(Badge.getBadge(99).equals("images/Veggie.png"));
        assertTrue(Badge.getBadge(399).equals("images/Squirrel.png"));
        assertTrue(Badge.getBadge(999).equals("images/Carrot.png"));
        assertTrue(Badge.getBadge(3999).equals("images/PolarBear.png"));
        assertTrue(Badge.getBadge(9999).equals("images/MelonHead.png"));
        assertTrue(Badge.getBadge(10000).equals("images/WildCabbage.png"));
    }

    @Test
    public void getTitleTest() {
        assertTrue(Badge.getTitle(99).equals("Veggie"));
        assertTrue(Badge.getTitle(399).equals("Squirrel"));
        assertTrue(Badge.getTitle(999).equals("Carrot"));
        assertTrue(Badge.getTitle(3999).equals("Polar Bear"));
        assertTrue(Badge.getTitle(9999).equals("Melon Head"));
        assertTrue(Badge.getTitle(10000).equals("Wild Cabbage"));
    }
}
