package gogreen.application.communication;

public class CO2Response extends ServerMessage {

    private boolean success;
    private int oldCarbonfootprint;
    private int newCarbonfootprint;
    private LoginData loginData;

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

    public LoginData getLoginData() {
        return loginData;
    }

    public void setLoginData(LoginData loginData) {
        this.loginData = loginData;
    }

    @Override
    public String toString() {
        return "\n===CO2Response===\n"
                + "   success: " + success
                + "\n   username: " + loginData.getUsername()
                + "\n   oldCarbonfootprint: " + oldCarbonfootprint
                + "\n   newCarbonfootprint: " + newCarbonfootprint
                + "\n";
    }
}
