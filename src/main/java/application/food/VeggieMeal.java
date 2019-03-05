package application.food;

public class VeggieMeal extends application.Activity {

    private String name;

    public VeggieMeal(int date, int co2Reduction, String name) {
        super(co2Reduction, date);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return null;
    }
}
