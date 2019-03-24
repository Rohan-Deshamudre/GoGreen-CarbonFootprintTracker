package gogreen.application.communication;

public class CO2Response extends ServerMessage {

    private boolean success;
    private int oldCarbonfootprint;
    private int newCarbonfootprint;

    /**
     * The default constructor.
     */
    public CO2Response() {}


    public boolean getResult() {
        return this.success;
    }

    public void setResult(boolean result) {
        this.success = result;
    }

    public int getOldCarbonfootprint() {
        return oldCarbonfootprint;
    }

    public int getNewCarbonfootprint() {
        return newCarbonfootprint;
    }

    public void setOldCarbonfootprint(int oldCarbonfootprint) {
        this.oldCarbonfootprint = oldCarbonfootprint;
    }

    public void setNewCarbonfootprint(int newCarbonfootprint) {
        this.newCarbonfootprint = newCarbonfootprint;
    }

    @Override
    public String toString() {
        return "\n===CO2Response===\n"
                + "   success: " + success
                + "\n   oldCarbonfootprint: " + oldCarbonfootprint
                + "\n   newCarbonfootprint: " + newCarbonfootprint
                + "\n";
    }
}
