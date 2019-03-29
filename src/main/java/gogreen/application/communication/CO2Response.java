package gogreen.application.communication;

public class CO2Response extends ServerMessage {

    private int co2Reduction;

    /**
     * The default constructor.
     */
    public CO2Response() {
    }

    public CO2Response(int co2Reduction) {
        this.co2Reduction = co2Reduction;
    }

    public int getCO2Reduction() {
        return this.co2Reduction;
    }

    @Override
    public String toString() {
        return "\n===CO2Response===\n"
            + "   CO2 reduced by: " + co2Reduction;
    }
}
