package gogreen.application.client;

import gogreen.application.model.CO2;

import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {


    private ArrayList<CO2> users;

    /**
     * Constructor of the class.
     * @param users - List of users.
     */

    public Leaderboard(ArrayList<CO2> users) {
        this.users = users;
    }

    /**
     * This method is temporary, just for testing.
     */
    public Leaderboard() {
        ArrayList<CO2> al = new ArrayList<>();
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

    public ArrayList<CO2> getUsers() {
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
            string += "    " + user.toString() + "\n";
        }
        string += "]>";
        return string;
    }
}
