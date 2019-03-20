package server;

import org.junit.jupiter.api.Test;

import static util.CarbonUtil.getCarbonfootprint;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarbonUtilTest {

   @Test
    public void getCarbonfootprintTest(){
        int result1 = getCarbonfootprint("Ate a vegetarian meal");
        int result2 = getCarbonfootprint("Bought from a biological store");
        int wrongResult = getCarbonfootprint("hi");
        assertEquals(100, result1);
        assertEquals(50, result2);
        assertEquals(0, wrongResult);
   }

}