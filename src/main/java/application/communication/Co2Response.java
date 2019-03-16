package application.communication;

public class Co2Response extends ServerMessage {

    private boolean result;

    public Co2Response() {}

    public Co2Response(boolean result) {
        this.result = result;
    }

    public boolean getResult() {
        return this.result;
    }

    @Override
    public String toString() {
        return "<Co2Response{"
                + "\n    result: " + result
                + "\n}>";
    }
}
