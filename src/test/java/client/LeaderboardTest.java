package client;

import static junit.framework.TestCase.assertTrue;

import gogreen.application.client.Leaderboard;
import gogreen.application.model.CO2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LeaderboardTest {

    @Test
    public void toStringTest() {
        CO2 user = new CO2("name", 1, 2, 3, 4);
        ArrayList<CO2> list = new ArrayList<>();
        list.add(user);
        Leaderboard lb = new Leaderboard(list);
        assertTrue(lb.toString().equals("<Leaderboard[\n"
            + "    " + "CO2[cusername='name', Co2Food='1', Co2Transport='2', Co2Energy='3'"
            + ", Co2Reduction='4']" + "\n"
            + "]>"));
    }
}
