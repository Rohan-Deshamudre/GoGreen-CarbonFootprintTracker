package application.communication;

public class Co2Request extends ClientMessage {

    private String choiceBoxValue;


    public Co2Request(String choiceBoxValue) {
        this.choiceBoxValue=choiceBoxValue;

    }

    /**
     * This class needs to have a default constructor.
     * Otherwise resttemplate.postForObject(...) does not work.
     */
    public Co2Request() {
    }

    public String getChoiceBoxValue() {
        return choiceBoxValue;
    }

    public void setChoiceBoxValue(String choiceBoxValue) {
        this.choiceBoxValue = choiceBoxValue;
    }

    @Override
    public String toString() {


        return "<Co2Request{"
                + "\n    checkboxValue: " + choiceBoxValue
                + "\n}>";
    }
}
