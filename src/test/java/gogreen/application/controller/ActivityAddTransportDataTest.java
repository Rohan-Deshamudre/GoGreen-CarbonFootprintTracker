package gogreen.application.controller;


import static gogreen.application.controller.MockitoTestHelper.setCarbonRecord;
import static gogreen.application.controller.MockitoTestHelper.setUserValid;
import static gogreen.application.controller.MockitoTestHelper.toJSONString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.communication.AddTransportRequest;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.UserRepository;
import gogreen.application.util.CarbonUtil;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(ActivityController.class)
public class ActivityAddTransportDataTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ActivityController activityController;

    @MockBean
    public UserRepository userRepository;

    @MockBean
    public CO2Repository co2Repository;

    private final LoginData fakeLoginData = new LoginData("Max", "v3rSt@ppEn");
    private final int fakeDistance = 10;
    private final int fakeTimesaweek = 3;
    private final int fakeCO2Reduction = CarbonUtil
        .getTransportCarbonReduction(fakeDistance, fakeTimesaweek);

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }

    /**
     * Test correct post request for an unregistered username. Result to pass: HTTP 401
     * Unauthorized
     */
    @Test
    void unregisteredUser() throws Exception {
        // invalid username returns an empty list.
        when(userRepository.findByUsername(fakeLoginData.getUsername()))
            .thenReturn(new ArrayList<>());

        AddTransportRequest req = new AddTransportRequest(fakeLoginData, fakeDistance,
            fakeTimesaweek);
        mockMvc.perform(
            post("/activity/transport/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(req)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    /**
     * Test correct post request for a user supplying a wrong password. Result to pass: HTTP 401
     * Unauthorized
     */
    @Test
    void wrongPassword() throws Exception {
        // same username but different password.
        setUserValid(new LoginData(fakeLoginData.getUsername(), "hunter2"), userRepository);

        AddTransportRequest req = new AddTransportRequest(fakeLoginData, fakeDistance,
            fakeTimesaweek);
        mockMvc.perform(
            post("/activity/transport/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(req)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    /**
     * Test a correct food carbon reduction update for a user with an empty record. Result to pass:
     * HTTP 200 OK, with a correct CO2Response.
     */
    @Test
    void emptyCarbonRequest() throws Exception {
        setUserValid(fakeLoginData, userRepository);
        setCarbonRecord(new CO2(fakeLoginData.getUsername(), 0, 0, 0, 0), co2Repository);

        AddTransportRequest req = new AddTransportRequest(fakeLoginData, fakeDistance,
            fakeTimesaweek);
        MvcResult res = mockMvc.perform(
            post("/activity/transport/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(req)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // validate CO2 response
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertEquals(fakeCO2Reduction, co2Response.getCO2Reduction());

        // validate CO2Repository updated correctly
        ArgumentCaptor<CO2> co2ArgumentCaptor = ArgumentCaptor.forClass(CO2.class);
        verify(co2Repository).save(co2ArgumentCaptor.capture());
        CO2 savedCO2 = co2ArgumentCaptor.getValue();
        assertEquals(fakeLoginData.getUsername(), savedCO2.getCUsername());
        assertEquals(0, savedCO2.getCO2food());
        assertEquals(fakeCO2Reduction, savedCO2.getCO2transport());
        assertEquals(0, savedCO2.getCO2energy());
        assertEquals(fakeCO2Reduction, savedCO2.getCO2reduc());
    }

    /**
     * Test a correct food carbon reduction update for a user who already has a carbon record.
     * Result to pass: HTTP 200 OK, with a correct CO2Response.
     */
    @Test
    void dataCarbonRequest() throws Exception {
        setUserValid(fakeLoginData, userRepository);
        CO2 fakeCO2 = new CO2(fakeLoginData.getUsername(), 23, 42, 99, 164);
        setCarbonRecord(fakeCO2, co2Repository);

        AddTransportRequest req = new AddTransportRequest(fakeLoginData, fakeDistance,
            fakeTimesaweek);
        MvcResult res = mockMvc.perform(
            post("/activity/transport/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(req)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // validate CO2 response
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertEquals(fakeCO2Reduction, co2Response.getCO2Reduction());

        // validate CO2Repository updated correctly
        ArgumentCaptor<CO2> co2ArgumentCaptor = ArgumentCaptor.forClass(CO2.class);
        verify(co2Repository).save(co2ArgumentCaptor.capture());
        CO2 savedCO2 = co2ArgumentCaptor.getValue();
        assertEquals(fakeLoginData.getUsername(), savedCO2.getCUsername());
        assertEquals(fakeCO2.getCO2food(), savedCO2.getCO2food());
        assertEquals(fakeCO2.getCO2transport() + fakeCO2Reduction, savedCO2.getCO2transport());
        assertEquals(fakeCO2.getCO2energy(), savedCO2.getCO2energy());
        assertEquals(fakeCO2.getCO2reduc() + fakeCO2Reduction, savedCO2.getCO2reduc());
    }
}
