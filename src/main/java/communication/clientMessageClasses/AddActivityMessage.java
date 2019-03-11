package communication.clientMessageClasses;

import application.Activity;
import communication.ClientMessage;

public class AddActivityMessage extends ClientMessage {

    private Activity activity;

    public AddActivityMessage(String username, String password, Activity activity) {
        super(username, password);
        this.activity = activity;
    }
    //for now we'll just send the entire activity object, later on we will convert the object
    //to a form of compressed database JSON.

    public Activity getActivity() {
        return activity;
    }

    @Override
    public String toString() {
        return null;
    }
}
