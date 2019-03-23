package gogreen.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "co2transportbike", schema = "public")
public class CO2TransportBike {

    @Id
    @Column(name = "busername")
    private String busername;

    @Column(name = "distance")
    private int distance;

    @Column(name = "timesaweek")
    private int timesaweek;

    public CO2TransportBike(String busername, int distance, int timesaweek) {
        this.busername = busername;
        this.distance = distance;
        this.timesaweek = timesaweek;
    }

    @Override
    public String toString() {
        return String.format("CO2TransportBike[busername='%s', distance='%s', timesaweek='%s']",
                busername, distance, timesaweek);
    }

    public String getBusername() { return busername; }

    public void setBusername(String busername) { this.busername = busername; }

    public int getDistance() { return distance; }

    public void setDistance(int distance) { this.distance = distance; }

    public int getTimesaweek() { return timesaweek; }

    public void setTimesaweek(int timesaweek) { this.timesaweek = timesaweek; }
}
