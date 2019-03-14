package application;

public class User {

     String username;
     int CO2Reduction;


    public User(String username, int CO2Reduction) {
        this.username = username;
        this.CO2Reduction = CO2Reduction;
    }

    public User() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public int getCO2Reduction() {
        return this.CO2Reduction;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", CO2Reduction=" + CO2Reduction +
                '}';
    }
}
