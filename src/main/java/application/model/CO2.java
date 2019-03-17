package application.model;

        import javax.persistence.Column;
        import javax.persistence.Entity;
        import javax.persistence.Id;
        import javax.persistence.Table;

@Entity
@Table(name = "co2", schema = "public")
public class CO2 {

    @Id
    @Column(name = "cusername")
    private String cusername;

    @Column(name = "co2reduc")
    private int co2reduc;

    protected CO2() {}

    public CO2(String cusername) {
        this.cusername = cusername;
    }

    /**
     * Getter of username.
     * @return - username.
     */
    public String getCusername() {
        return cusername;
    }

    /**
     * Getter of co2reduc.
     * @return - co2reduc.
     */
    public int getCo2reduc() {
        return co2reduc;
    }

    /**
     * Setter of cusername.
     * @param cusername - the username.
     */
    public void setCusername(String cusername) {
        this.cusername = cusername;
    }

    /**
     * Setter of co2reduc.
     * @param co2reduc - the co2 reduction.
     */
    public void setCo2reduc(int co2reduc) {
        this.co2reduc = co2reduc;
    }

    @Override
    public String toString() {
        return String.format(
                "CO2[cusername='%s', Co2Reduction='%d']",
                cusername,co2reduc);
    }
}
