package gogreen.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

    protected CO2TransportPublic(){}

    public CO2TransportPublic(String pusername, int distance, int timesaweek) {
        this.pusername = pusername;
        this.distance = distance;
        this.timesaweek = timesaweek;
    }

    @Override
    public String toString() {
        return String.format("CO2TransportPublic[pusername='%s', distance='%s', timesaweek='%s']",
                pusername, distance, timesaweek);
    }

    public String getPusername() {
        return pusername;
    }

    public int getDistance() {
        return distance;
    }

    public int getTimesaweek() {
        return timesaweek;
    }

    public void setPusername(String pusername) {
        this.pusername = pusername;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setTimesaweek(int timesaweek) {
        this.timesaweek = timesaweek;
    }
}


