package application;

public abstract class Activity {

    protected int co2Reduction;
    protected int date;

    public Activity(int co2Reduction, int date) {
        this.co2Reduction = co2Reduction;
        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public int getCo2Reduction() {
        return co2Reduction;
    }

    public abstract String toString();

}
