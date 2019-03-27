package gogreen.application.client;

import gogreen.application.model.CO2;

import java.util.Collections;
import java.util.List;

public class Leaderboard {


    private List<CO2> users;

    /**
     * Constructor of the class.
     * @param users - List of users.
     */

    public Leaderboard(List<CO2> users) {
        this.users = users;
    }

    /**
     * This method is temporary, just for testing.
     */
    public Leaderboard() {
        CO2 a1 = new CO2("a1", 1, 2, 3, 4);
        CO2 b2 = new CO2("b2", 1, 2, 3 ,4);
        CO2 c3 = new CO2("c3", 1, 2, 3, 4);
        a1.setCo2reduc(50);
        b2.setCo2reduc(10);
        c3.setCo2reduc(500);
        List<CO2> al = null;
        al.add(a1);
        al.add(b2);
        al.add(c3);
        users = al;
    }

    /**
     * Sorts the users field by co2reduc.
     * the higher co2reduc on the top
     */
    public void sortLeaderboard() {
        Collections.sort(users, new SortbyCo2reduc());
    }
    /**
     * Getter of the users attribute.
     * @return - returns the users field.
     */

    public List<CO2> getUsers() {
        return this.users;
    }

    /**
     * ToString method.
     * @return - a String representation of the object.
     */
    @Override
    public String toString() {
        String string = "<Leaderboard[\n";
        for (CO2 user: users) {
            string += "    " + user + "\n";
        }
        string += "]>";
        return string;
    }
}
