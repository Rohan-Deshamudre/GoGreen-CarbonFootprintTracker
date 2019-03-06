package Database;

import java.sql.*;

public class Main {

    private final String url = "jdbc:postgresql://ec2-54-217-208-105.eu-west-1.compute.amazonaws.com:5432/dcjj3m1uq8pga7?user=tsqmrdhoskuqxt&password=cd13fbd0b9cc187248262dd2902a4229fa6fecbcf08df284cccc5f2111f1b2cf&sslmode=require";
    private final String user = "tsqmrdhoskuqxt";
    private final String password = "cd13fbd0b9cc187248262dd2902a4229fa6fecbcf08df284cccc5f2111f1b2cf";

    public static void main(String[] args) {
        Main main = new Main();
        main.connect();
        main.getUsers();
    }

    public void getUsers() {

        String SQL = "SELECT username,salt,hashed_password FROM \"User\"";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            // display actor information
            displayUser(rs);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void displayUser(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("username") + "\t"
                    + rs.getString("salt") + "\t"
                    + rs.getString("hashed_password"));

        }
    }

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
