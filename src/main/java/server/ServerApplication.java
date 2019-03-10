package server;

import communication.LoginRequest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import Database.model.CO2;
import Database.model.User;

@SpringBootApplication
public class ServerApplication {
    private final String url = "jdbc:postgresql://ec2-54-217-208-105.eu-west-1.compute.amazonaws.com:5432/dcjj3m1uq8pga7?user=tsqmrdhoskuqxt&password=cd13fbd0b9cc187248262dd2902a4229fa6fecbcf08df284cccc5f2111f1b2cf&sslmode=require";
    private final String user = "tsqmrdhoskuqxt";
    private final String password = "cd13fbd0b9cc187248262dd2902a4229fa6fecbcf08df284cccc5f2111f1b2cf";
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello client";
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);

    }


    /**
     * THIS IS TEMPORARY
     * In the future this will be connected to the database.
     *
     * @param username
     * @param password
     * @return
     */
    public boolean checkLoginData(String username, String password) {
//        if (username.equals("John") && password.equals("Wick")) {
//            return true;
        String SQL = "SELECT username FROM user WHERE username = ? AND password = ?";
        boolean name = false;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL)){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            name = pstmt.executeUpdate();
        }
        catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        }

        //}
        //return false;
    public Connection connect () throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    }

    public boolean checkLoginData(LoginRequest req) {
        return checkLoginData(req.getUsername(), req.getPassword());
    }




