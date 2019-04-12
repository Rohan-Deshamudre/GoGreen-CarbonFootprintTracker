package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.setCarbonRecord;
import static gogreen.application.controller.MockitoTestHelper.setUserValid;
import static gogreen.application.controller.MockitoTestHelper.toJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.FriendRepository;
import gogreen.application.repository.FriendRequestRepository;
import gogreen.application.repository.UserRepository;
import gogreen.application.util.CarbonUtil;
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

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@WebMvcTest(ActivityController.class)
public class ActivityAddFoodDataTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ActivityController activityController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CO2Repository co2Repository;

    @MockBean
    private FriendRepository friendRepository;

    @MockBean
    private FriendRequestRepository friendRequestRepository;

    private final LoginData fakeLoginData = new LoginData("Gucci", "Mane");
    private final String fakeCheckBoxValue = "salad";
    private final int fakeCO2Reduction = CarbonUtil.getFoodCarbonReduction(fakeCheckBoxValue);

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

        AddFoodRequest req = new AddFoodRequest(fakeLoginData, fakeCheckBoxValue, 2);
        mockMvc.perform(
            post("/activity/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(req)))
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

        AddFoodRequest req = new AddFoodRequest(fakeLoginData, fakeCheckBoxValue, 2);
        mockMvc.perform(
            post("/activity/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(req)))
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

        AddFoodRequest req = new AddFoodRequest(fakeLoginData, fakeCheckBoxValue, 2);
        MvcResult res = mockMvc.perform(
            post("/activity/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(req)))
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
        assertEquals(fakeCO2Reduction, savedCO2.getCO2food());
        assertEquals(0, savedCO2.getCO2transport());
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

        AddFoodRequest req = new AddFoodRequest(fakeLoginData, fakeCheckBoxValue, 2);
        MvcResult res = mockMvc.perform(
            post("/activity/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(req)))
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
        assertEquals(fakeCO2.getCO2food() + fakeCO2Reduction, savedCO2.getCO2food());
        assertEquals(fakeCO2.getCO2transport(), savedCO2.getCO2transport());
        assertEquals(fakeCO2.getCO2energy(), savedCO2.getCO2energy());
        assertEquals(fakeCO2.getCO2reduc() + fakeCO2Reduction, savedCO2.getCO2reduc());
    }
}
