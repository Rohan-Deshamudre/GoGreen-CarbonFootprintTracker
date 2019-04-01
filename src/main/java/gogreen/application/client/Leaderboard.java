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
        CO2 a1 = new CO2("a1", 1, 2, 3, 50);
        CO2 b2 = new CO2("b2", 1, 2, 3 ,10);
        CO2 c3 = new CO2("c3", 1, 2, 3, 500);
        ArrayList<CO2> al = new ArrayList<>();
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

    public static void main(String[] args) {
        CO2 user = new CO2("name", 1, 2, 3, 4);
        ArrayList<CO2> list = new ArrayList<>();
        list.add(user);
        Leaderboard lb = new Leaderboard(list);

        System.out.println(lb.toString());
    }
}
