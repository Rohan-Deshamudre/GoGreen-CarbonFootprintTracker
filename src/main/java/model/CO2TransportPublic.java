package model;

import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "co2transportpublic", schema = "public")
public class CO2TransportPublic {

    @Id
    @Column(name = "pusername")
    private String pusername;

    @Column(name = "distance")
    private int distance;

    @Column(name = "timesaweek")
    private int timesaweek;

    /**
     * The CO2TransportPublic object with pusername, distance and timesaweek values.
     * @param pusername username
     * @param distance the distance by public transport
     * @param timesaweek the number of times in a week
     */

    public CO2TransportPublic(String pusername, int distance, int timesaweek) {
        this.pusername = pusername;
        this.distance = distance;
        this.timesaweek = timesaweek;
    }

    @Override
    public String toString() {
        return String.format("CO2TransportBike[busername='%s', distance='%s', timesaweek='%s']",
                pusername, distance, timesaweek);
    }

    public String getBusername() {
        return pusername;
    }

    public void setBusername(String pusername) {
        this.pusername = pusername;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTimesaweek() {
        return timesaweek;
    }

    public void setTimesaweek(int timesaweek) {
        this.timesaweek = timesaweek;
    }
}
