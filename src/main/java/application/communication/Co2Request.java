package application.communication;

public class Co2Request extends ClientMessage {

    private String choiceBoxValue;
    private String cusername;

    /**
     * Setup the choiceBox and the cusername.
     * @param choiceBoxValue co2 value
     * @param cusername username
     */

    public Co2Request(String choiceBoxValue,String cusername) {
        this.choiceBoxValue = choiceBoxValue;
        this.cusername = cusername;

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

    public String getCusername() {
        return cusername;
    }

    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    @Override
    public String toString() {


        return "<Co2Request{"
                + "\n    checkboxValue: " + choiceBoxValue
                + "\n    cusername: " + cusername
                + "\n}>";
    }
}
