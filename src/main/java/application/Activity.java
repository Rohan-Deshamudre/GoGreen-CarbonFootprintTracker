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

    public void setDate(int date) {
        this.date = date;
    }

    public int getCo2Reduction() {
        return co2Reduction;
    }

    public void setCo2Reduction(int co2Reduction) {
        this.co2Reduction = co2Reduction;
    }
    public abstract String toString();

}
