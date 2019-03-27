package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.toJSONString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.communication.AddFoodRequest;
import gogreen.application.communication.CO2Response;
import gogreen.application.communication.ErrorMessage;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.model.User;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.UserRepository;
import gogreen.application.util.CarbonUtil;
import java.util.ArrayList;
import java.util.List;
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
public class ActivityAddFoodDataTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private ActivityController activityController;

    @MockBean
    public UserRepository userRepository;

    @MockBean
    public CO2Repository co2Repository;

    private final LoginData fakeLoginData = new LoginData("Gucci", "Mane");
    private final String fakeCheckBoxValue = "salad";
    private final int fakeCO2Reduction = CarbonUtil.getFoodCarbonfootprint(fakeCheckBoxValue);

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
        MvcResult res = mockMvc.perform(
            post("/activity/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(req)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // validate error message is correct.
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertEquals(ErrorMessage.LOGIN_WRONG_USER, co2Response.getErrorMessage().getMessage());
    }

    /**
     * Test correct post request for a user supplying a wrong password. Result to pass: HTTP 401
     * Unauthorized
     */
    @Test
    void wrongPassword() throws Exception {
        // same username but different password.
        setUserValid(new LoginData(fakeLoginData.getUsername(), "hunter2"));

        AddFoodRequest req = new AddFoodRequest(fakeLoginData, fakeCheckBoxValue, 2);
        MvcResult res = mockMvc.perform(
            post("/activity/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(req)))
            .andExpect(status().isUnauthorized())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // validate error message is correct.
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertEquals(ErrorMessage.LOGIN_WRONG_PASS, co2Response.getErrorMessage().getMessage());
    }

    /**
     * Test a correct food carbon reduction update for a user with an empty record. Result to pass:
     * HTTP 200 OK, with a correct CO2Response.
     */
    @Test
    void emptyCarbonRequest() throws Exception {
        setUserValid(fakeLoginData);
        setCarbonRecord(new CO2(fakeLoginData.getUsername(), 0, 0, 0, 0));

        AddFoodRequest req = new AddFoodRequest(fakeLoginData, fakeCheckBoxValue, 2);
        MvcResult res = mockMvc.perform(
            post("/activity/food/add")
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
        assertEquals(fakeCO2Reduction, savedCO2.getCO2Food());
        assertEquals(0, savedCO2.getCO2Transport());
        assertEquals(0, savedCO2.getCO2Energy());
        assertEquals(fakeCO2Reduction, savedCO2.getCO2Reduc());

        // validate no error message is sent
        assertNull(co2Response.getErrorMessage());
    }

    /**
     * Test a correct food carbon reduction update for a user who already has a carbon record.
     * Result to pass: HTTP 200 OK, with a correct CO2Response.
     */
    @Test
    void dataCarbonRequest() throws Exception {
        setUserValid(fakeLoginData);
        CO2 fakeCO2 = new CO2(fakeLoginData.getUsername(), 23, 42, 99, 164);
        setCarbonRecord(fakeCO2);

        AddFoodRequest req = new AddFoodRequest(fakeLoginData, fakeCheckBoxValue, 2);
        MvcResult res = mockMvc.perform(
            post("/activity/food/add")
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
        assertEquals(fakeCO2.getCO2Food() + fakeCO2Reduction, savedCO2.getCO2Food());
        assertEquals(fakeCO2.getCO2Transport(), savedCO2.getCO2Transport());
        assertEquals(fakeCO2.getCO2Energy(), savedCO2.getCO2Energy());
        assertEquals(fakeCO2.getCO2Reduc() + fakeCO2Reduction, savedCO2.getCO2Reduc());

        // validate no error message is sent
        assertNull(co2Response.getErrorMessage());
    }

    /**
     * Updates the mocks so that the provided user will pass the authorization test.
     *
     * @param loginData - login credentials for the user.
     */
    private void setUserValid(LoginData loginData) {
        User fakeUser = mock(User.class);
        when(fakeUser.getPassword()).thenReturn(loginData.getPassword());
        List<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(fakeUser);
        when(userRepository.findByUsername(loginData.getUsername())).thenReturn(fakeUserList);
    }

    /**
     * Adds a co2 record to the mocked co2repository.
     *
     * @param co2 - the CO2 record to add to the mocked repository.
     */
    private void setCarbonRecord(CO2 co2) {
        List<CO2> dbUserCO2List = new ArrayList<>();
        dbUserCO2List.add(
            new CO2(co2.getCUsername(), co2.getCO2Food(), co2.getCO2Transport(), co2.getCO2Energy(),
                co2.getCO2Reduc()));
        when(co2Repository.findByCusername(co2.getCUsername())).thenReturn(dbUserCO2List);
    }
}
