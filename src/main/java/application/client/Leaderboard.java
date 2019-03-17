package application.client;

import application.model.CO2;
import java.util.ArrayList;
import java.util.Collections;

public class Leaderboard {

    private ArrayList<CO2> users;

    /**
     * The main method. FOR TESTING ONLY.
     * @param args - the args.
     */
    public static void main(String[] args) {
        CO2 a = new CO2("a");
        CO2 b = new CO2("b");
        CO2 c = new CO2("c");

        a.setCo2reduc(50);
        b.setCo2reduc(10);
        c.setCo2reduc(500);

        ArrayList<CO2> al = new ArrayList<>();
        al.add(a);
        al.add(b);
        al.add(c);


        Leaderboard leaderboard = new Leaderboard(al);
        leaderboard.sortLeaderboard();
        System.out.println(leaderboard);
    }


    /**
     * Constructor of the class.
     * @param users - List of users.
     */
    public Leaderboard(ArrayList<CO2> users) {
        this.users = users;
    }

    /**
     * Sorts the users field by co2reduc.
     */

    public void sortLeaderboard() {

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
            string += "    " + user + "\n";
        }
        string += "]>";
        return string;
    }

}
