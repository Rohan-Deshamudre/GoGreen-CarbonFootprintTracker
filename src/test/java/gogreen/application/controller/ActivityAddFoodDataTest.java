package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.toJSONString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
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

    private final LoginData fakeLogin = new LoginData("Gucci", "Mane");
    private final String fakeCheckBoxValue = "salad";
    private final int fakeCO2Reduction = CarbonUtil.getFoodCarbonfootprint(fakeCheckBoxValue);

    @BeforeEach
    private void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(activityController).build();
    }

    /**
     * This test tests sending a valid add food request for a user that does not exist within the
     * database.
     */
    @Test
    void addFoodDataNullUserTest() throws Exception {
        when(userRepository.findByUsername(fakeLogin.getUsername())).thenReturn(null);

        AddFoodRequest testReq = new AddFoodRequest(fakeLogin, fakeCheckBoxValue, 1);
        MvcResult res = mockMvc.perform(
            post("/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toJSONString(testReq)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();

        // verify that the response from the server is correct
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertFalse(co2Response.getResult());
    }


    /**
     * This test tests sending a valid add food request for a user that has empty data in the
     * database.
     */
    @Test
    void addFoodDataInvalidUserTest() throws Exception {
        setUserExists(false);

        AddFoodRequest testReq = new AddFoodRequest(fakeLogin, fakeCheckBoxValue, 1);
        MvcResult res = mockMvc.perform(
            post("/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toJSONString(testReq)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();

        // verify that the response from the server is correct
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertFalse(co2Response.getResult());
    }

    /**
     * This test tests sending a valid add food request for a user that already has carbon footprint
     * data saved.
     */
    @Test
    void addFoodDataExistingTest() throws Exception {
        setUserExists(true);
        List<CO2> dbUserListMock = setUserHasCarbonRecord(true);

        AddFoodRequest testReq = new AddFoodRequest(fakeLogin, fakeCheckBoxValue, 1);
        MvcResult res = mockMvc.perform(
            post("/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toJSONString(testReq)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();

        int fakeOldCarbonfootprint = dbUserListMock.get(0).getCo2reduc();
        int fakeNewCarbonfootprint = fakeOldCarbonfootprint + fakeCO2Reduction;

        // verify that the response from the server is correct
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertTrue(co2Response.getResult());
        assertEquals(fakeOldCarbonfootprint, co2Response.getOldCarbonfootprint());
        assertEquals(fakeNewCarbonfootprint, co2Response.getNewCarbonfootprint());

        // verify that the correct value has been set to the dbUser
        verify(dbUserListMock.get(0)).setCo2reduc(fakeNewCarbonfootprint);

        // verify that the data saved to the co2 repository is correct
        ArgumentCaptor<CO2> co2ArgumentCaptor = ArgumentCaptor.forClass(CO2.class);
        verify(co2Repository).save(co2ArgumentCaptor.capture());
        CO2 dataSaved = co2ArgumentCaptor.getValue();
        verify(dataSaved).setCo2reduc(fakeNewCarbonfootprint);
    }

    /**
     * This test tests sending a valid add food request for a user that does not have carbon
     * footprint data yet.
     */
    @Test
    void addFoodDataNewNullTest() throws Exception {
        setUserExists(true);
        when(co2Repository.findByCusername(fakeLogin.getUsername())).thenReturn(null);

        AddFoodRequest testReq = new AddFoodRequest(fakeLogin, fakeCheckBoxValue, 1);
        MvcResult res = mockMvc.perform(
            post("/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toJSONString(testReq)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();

        // verify that the response from the server is correct
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertTrue(co2Response.getResult());
        assertEquals(0, co2Response.getOldCarbonfootprint());
        assertEquals(fakeCO2Reduction, co2Response.getNewCarbonfootprint());

        // verify that the data saved to the co2 repository is correct
        ArgumentCaptor<CO2> co2ArgumentCaptor = ArgumentCaptor.forClass(CO2.class);
        verify(co2Repository).save(co2ArgumentCaptor.capture());
        CO2 dataSaved = co2ArgumentCaptor.getValue();
        assertEquals(fakeLogin.getUsername(), dataSaved.getCusername());
        assertEquals(0, dataSaved.getCo2food());
        assertEquals(0, dataSaved.getCo2transport());
        assertEquals(0, dataSaved.getCo2energy());
        assertEquals(fakeCO2Reduction, dataSaved.getCo2reduc());
    }

    /**
     * This test tests sending a valid add food request for a user that has empty carbon
     * footprint data.
     */
    @Test
    void addFoodDataNewTest() throws Exception {
        setUserExists(true);
        setUserHasCarbonRecord(false);

        AddFoodRequest testReq = new AddFoodRequest(fakeLogin, fakeCheckBoxValue, 1);
        MvcResult res = mockMvc.perform(
            post("/food/add")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(toJSONString(testReq)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andReturn();

        // verify that the response from the server is correct
        CO2Response co2Response = objectMapper
            .readValue(res.getResponse().getContentAsString(), CO2Response.class);
        assertTrue(co2Response.getResult());
        assertEquals(0, co2Response.getOldCarbonfootprint());
        assertEquals(fakeCO2Reduction, co2Response.getNewCarbonfootprint());

        // verify that the data saved to the co2 repository is correct
        ArgumentCaptor<CO2> co2ArgumentCaptor = ArgumentCaptor.forClass(CO2.class);
        verify(co2Repository).save(co2ArgumentCaptor.capture());
        CO2 dataSaved = co2ArgumentCaptor.getValue();
        assertEquals(fakeLogin.getUsername(), dataSaved.getCusername());
        assertEquals(0, dataSaved.getCo2food());
        assertEquals(0, dataSaved.getCo2transport());
        assertEquals(0, dataSaved.getCo2energy());
        assertEquals(fakeCO2Reduction, dataSaved.getCo2reduc());
    }

    /**
     * Sets up the mocks so that the fake user will either pass or fail the database check.
     *
     * @param exists - result of the database check.
     * @return - the mocked userList.
     */
    private List<User> setUserExists(boolean exists) {
        List<User> userList = mock(ArrayList.class);
        when(userList.isEmpty()).thenReturn(!exists);
        when(userRepository.findByUsername(fakeLogin.getUsername())).thenReturn(userList);
        return userList;
    }

    /**
     * Sets up mocks defining whether the fake user will have a fake carbon data record or not;
     *
     * @param hasRecord - defines if a fake carbon record should be added or not.
     * @return - the mocked dbUserList.
     */
    private List<CO2> setUserHasCarbonRecord(boolean hasRecord) {
        List<CO2> dbUserList = mock(ArrayList.class);
        when(dbUserList.isEmpty()).thenReturn(!hasRecord);

        if (hasRecord) {
            CO2 fakeRecord = mock(CO2.class);
            when(fakeRecord.getCo2reduc()).thenReturn(69);
            when(dbUserList.get(0)).thenReturn(fakeRecord);
        }

        when(co2Repository.findByCusername(fakeLogin.getUsername())).thenReturn(dbUserList);

        return dbUserList;
    }
}
