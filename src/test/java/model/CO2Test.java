package model;

import application.model.CO2;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CO2Test {
    @Test
    public void setCo2reducTest() {
    }

    @Test
    public void getCo2reducTest() {

    }
    @Test
    public void toStringTest() {

        CO2 cuser = new CO2("user");
        assertThat(cuser.toString().equals("<CO2[\n    cusername: user\n    Co2Reduction: reduc\n]>"));
    }
}
