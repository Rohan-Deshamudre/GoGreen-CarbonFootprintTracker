package gogreen.application.communication;

public abstract class ServerMessage extends Message {

    private ErrorMessage errorMessage;

    public ErrorMessage getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }
}
