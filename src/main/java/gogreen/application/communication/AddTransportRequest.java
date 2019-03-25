package gogreen.application.communication;

public class AddTransportRequest extends ClientMessage {

    private int distance;
    private int timesaweek;

    /**
     * Making an object which contains the login data, the distance and the timesaweek parameters.
     * @param loginData the login data of the user
     * @param distance the distance of transportation
     * @param timesaweek the amount this has been done in a week
     */

    public AddTransportRequest(LoginData loginData, int distance, int timesaweek) {
        super(loginData);
        this.distance = distance;
        this.timesaweek = timesaweek;
    }

    public AddTransportRequest(){}

    public int getDistance() {
        return distance;
    }

    public int getTimesaweek() {
        return timesaweek;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTimesaweek(int timesaweek) {
        this.timesaweek = timesaweek;
    }

    @Override
    public String toString() {


        return "<Co2Request{"
                + "\n    distance: " + distance
                + "\n    timesaweek: " + timesaweek
                + "\n}>";
    }

}
