package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    private final String url = "jdbc:postgresql://ec2-54-217-208-105.eu-west-1.compute.amazonaws.com:5432/dcjj3m1uq8pga7?user=tsqmrdhoskuqxt&password=cd13fbd0b9cc187248262dd2902a4229fa6fecbcf08df284cccc5f2111f1b2cf&sslmode=require";
    private final String user = "tsqmrdhoskuqxt";
    private final String password = "cd13fbd0b9cc187248262dd2902a4229fa6fecbcf08df284cccc5f2111f1b2cf";

    public static void main(String[] args) {
        Main main = new Main();
        int userCount = main.getUserCount();
        System.out.println(String.format("%d user(s) found.", userCount));
    }

    public int getUserCount() {
        String SQL = "SELECT count(*) FROM User";
        int count = 0;

        try(Connection con = connect();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL)) {

            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return count;
    }

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
