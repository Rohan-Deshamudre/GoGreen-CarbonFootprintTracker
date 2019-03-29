package gogreen.application.communication;

public class AddFoodRequest extends ClientMessage {

    private String choiceBoxValue;
    private int amount;

    /**
     * A request message that contains the amount and type of food.
     * @param loginData login credentials
     * @param choiceBoxValue The type of food.
     * @param amount The amount.
     */
    public AddFoodRequest(LoginData loginData, String choiceBoxValue, int amount) {
        super(loginData);
        this.choiceBoxValue = choiceBoxValue;
        this.amount = amount;
    }

    /**
     * The default constructor.
     */
    public AddFoodRequest() {
    }

    public String getChoiceBoxValue() {
        return choiceBoxValue;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "\n===AddFoodRequest===\n"
                + loginData
                + "   choiceBoxValue: " + choiceBoxValue
                + "\n   amount: " + amount
                + "\n";
    }
}
