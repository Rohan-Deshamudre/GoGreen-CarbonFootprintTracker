package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "co2energytemperature", schema = "public")
public class CO2EnergyTemperature {

    @Id
    @Column(name = "tusername")
    private String tusername;

    @Column(name = "reduction")
    private int reduction;

    @Column(name = "duration")
    private int duration;

    /**
     * Makes a CO2EnergyTemperature object with the tusername, reduction and duration.
     * @param tusername username
     * @param reduction energy reduction
     * @param duration duration for controlling temperature
     */

    public CO2EnergyTemperature(String tusername, int reduction, int duration) {
        this.tusername = tusername;
        this.reduction = reduction;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("CO2EnergyTemperature[tusername='%s', reduction='%s', duration='%s'",
                tusername, reduction, duration);
    }

    public String getTusername() {
        return tusername;
    }

    public void setTusername(String tusername) {
        this.tusername = tusername;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
