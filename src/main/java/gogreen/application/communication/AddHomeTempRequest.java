package gogreen.application.communication;

public class AddHomeTempRequest extends ClientMessage {

    private int temperature;
    private int duration;

    /**
     * The object which contains the login data of the user, temperature and the duration.
     * @param loginData login data of the user
     * @param temperature temperature which has been reduced
     * @param duration the amount of time
     */

    public AddHomeTempRequest(LoginData loginData, int temperature, int duration) {
        super(loginData);
        this.temperature = temperature;
        this.duration = duration;
    }

    public AddHomeTempRequest(){}

    public int getTemperature() {
        return temperature;
    }

    public int getDuration() {
        return duration;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "\n===AddFoodRequest===\n"
                + loginData
                + "   temperature: " + temperature
                + "\n   duration: " + duration
                + "\n";
    }
}
