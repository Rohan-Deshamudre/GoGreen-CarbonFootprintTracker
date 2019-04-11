package gogreen.application.communication;

public class AddTransportRequest extends ClientMessage {

    public enum TravelType {
        BIKE,
        PUB_TRANSPORT,
        DEFAULT
    }

    private TravelType travelType;
    private int distance;

    /**
     * Making an object which contains the login data, the type of travel and the distance
     * travelled.
     *
     * @param loginData the login data of the user
     * @param travelType the type of travel
     * @param distance the distance of transportation
     */

    public AddTransportRequest(LoginData loginData, TravelType travelType, int distance) {
        super(loginData);
        this.travelType = travelType;
        this.distance = distance;
    }

    public AddTransportRequest() {
    }

    public int getDistance() {
        return distance;
    }

    public TravelType getTravelType() {
        return travelType;
    }

    @Override
    public String toString() {

        return "<Co2Request{"
            + "\n    distance: " + distance
            + "\n    type: " + travelType
            + "\n}>";
    }

}
