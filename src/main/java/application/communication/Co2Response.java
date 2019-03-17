package application.communication;

public class Co2Response extends ServerMessage {

    private boolean result;
    private String username;
    private int oldCarbonfootprint;
    private int newCarbonfootprint;

    public Co2Response() {}


    public boolean getResult() {
        return this.result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getUsername() {
        return username;
    }

    public int getOldCarbonfootprint() {
        return oldCarbonfootprint;
    }

    public int getNewCarbonfootprint() {
        return newCarbonfootprint;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOldCarbonfootprint(int oldCarbonfootprint) {
        this.oldCarbonfootprint = oldCarbonfootprint;
    }

    public void setNewCarbonfootprint(int newCarbonfootprint) {
        this.newCarbonfootprint = newCarbonfootprint;
    }

    @Override
    public String toString() {
        return "<Co2Response{"
                + "\n    result: " + result
                + "\n    username: " + username
                + "\n    oldCarbonfootprint: " + oldCarbonfootprint
                + "\n    newCarbonfootprint: " + newCarbonfootprint
                + "\n}>";
    }
}
