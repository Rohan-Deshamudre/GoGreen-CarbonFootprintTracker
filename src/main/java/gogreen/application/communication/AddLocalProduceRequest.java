package gogreen.application.communication;

public class AddLocalProduceRequest extends ClientMessage {

    private int weight;
    private boolean organic;

    /**
     * A request message that contains the weight of the local produce bought.
     *
     * @param loginData - login credentials
     * @param weight - the weight of the local produce bought.
     * @param organic - true iff the produce is organic produce.
     */
    public AddLocalProduceRequest(LoginData loginData, int weight, boolean organic) {
        super(loginData);
        this.weight = weight;
        this.organic = organic;
    }

    public AddLocalProduceRequest() {
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "\n===AddFoodRequest===\n"
            + loginData
            + "\n   weight: " + weight
            + "\n";
    }

    public boolean isOrganic() {
        return organic;
    }
}
