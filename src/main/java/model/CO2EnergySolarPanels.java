package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "co2energysolarpanels", schema = "public")
public class CO2EnergySolarPanels {

    @Id
    @Column(name = "spusername")
    private String spusername;

    @Column(name = "area")
    private int area;

    @Column(name = "sunlightamount")
    private int sunlightamount;

    /**
     * The class for reducing co2 by using solar panels.
     * @param spusername the username
     * @param area the area of the solar panels
     * @param sunlightamount the sun light
     */

    public CO2EnergySolarPanels(String spusername, int area, int sunlightamount) {
        this.spusername = spusername;
        this.area = area;
        this.sunlightamount = sunlightamount;
    }

    @Override
    public String toString() {
        return String.format("CO2EnergySolarPanels[spusername='%s', area='%s', sunlightamount='%s'",
                spusername, area, sunlightamount);
    }

    public String getSpusername() {
        return spusername;
    }

    public void setSpusername(String spusername) {
        this.spusername = spusername;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getSunlightamount() {
        return sunlightamount;
    }

    public void setSunlightamount(int sunlightamount) {
        this.sunlightamount = sunlightamount;
    }
}
