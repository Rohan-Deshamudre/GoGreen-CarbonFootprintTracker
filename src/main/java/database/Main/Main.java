package database.Main;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Connection connection = getConnection();

    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("jdbc:postgresql://ec2-54-217-208-105.eu-west-1.compute.amazonaws.com:5432/dcjj3m1uq8pga7?user=tsqmrdhoskuqxt&password=cd13fbd0b9cc187248262dd2902a4229fa6fecbcf08df284cccc5f2111f1b2cf&sslmode=require");
        return DriverManager.getConnection(dbUrl);
    }

}
