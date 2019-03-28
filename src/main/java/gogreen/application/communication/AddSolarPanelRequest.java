package gogreen.application.communication;

public class AddSolarPanelRequest extends ClientMessage {

    private int area;
    private int hoursSunlight;

    /**
     * Constructor for a add solar panel request.
     * @param area - m2 of solar panel added.
     * @param hoursSunlight - hours of sunlight these solar panels received.
     */
    public AddSolarPanelRequest(LoginData loginData, int area, int hoursSunlight) {
        super(loginData);
        this.area = area;
        this.hoursSunlight = hoursSunlight;
    }

    public AddSolarPanelRequest() {
    }

    public int getHoursSunlight() {
        return hoursSunlight;
    }

    public int getArea() {
        return area;
    }

    @Override
    public String toString() {
        return "\n===AddSolarPanelRequest===\n"
            + loginData
            + "   area: " + area
            + "\n   hours of sunlight: " + hoursSunlight
            + "\n";
    }
}
