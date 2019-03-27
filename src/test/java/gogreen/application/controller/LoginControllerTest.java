package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.toJSONString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.communication.ErrorMessage;
import gogreen.application.communication.LoginData;
import gogreen.application.communication.LoginResponse;
import gogreen.application.model.CO2;
import gogreen.application.model.User;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.UserRepository;
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
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    LoginController loginController;

    @MockBean
    UserRepository userRepository;

    @MockBean
    CO2Repository co2Repository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    // ===== LOGIN TESTING ======

    /**
     * Test a user that does not exist in the database. Result to pass: HTTP 401 Unauthorized
     */
    @Test
    void nonExistentUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");

        MvcResult res = mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();

        // validate error message is correct.
        LoginResponse loginResponse = objectMapper
            .readValue(res.getResponse().getContentAsString(), LoginResponse.class);
        assertEquals(ErrorMessage.LOGIN_WRONG_USER, loginResponse.getErrorMessage().getMessage());
    }

    /**
     * Test a user that has an empty entry in the database. Result to pass: HTTP 401 Unauthorized
     */
    @Test
    void emptyUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserDB(fakeLoginData, false);

        MvcResult res = mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();

        // validate error message is correct.
        LoginResponse loginResponse = objectMapper
            .readValue(res.getResponse().getContentAsString(), LoginResponse.class);
        assertEquals(ErrorMessage.LOGIN_WRONG_PASS, loginResponse.getErrorMessage().getMessage());
    }

    /**
     * Test an incorrect password for a user that exists in the database. Result to pass: HTTP 401
     * Unauthorized
     */
    @Test
    void passwordUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserDB(new LoginData(fakeLoginData.getUsername(), "boogie"), true);

        MvcResult res = mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();

        // validate error message is correct.
        LoginResponse loginResponse = objectMapper
            .readValue(res.getResponse().getContentAsString(), LoginResponse.class);
        assertEquals(ErrorMessage.LOGIN_WRONG_PASS, loginResponse.getErrorMessage().getMessage());
    }

    /**
     * Test a valid user/password combination. Result to pass: HTTP 200 OK
     */
    @Test
    void validUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserDB(fakeLoginData, true);

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(fakeLoginData)))
            .andExpect(status().isOk());
    }

    // ===== REGISTRATION TESTING ======

    /**
     * Test registration for a username that is already taken. Result to pass: HTTP 403 Forbidden,
     * body: "Username is taken!"
     */
    @Test
    void usernameTakenRegistrationTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Bob", "R0$$");
        setUserDB(new LoginData(fakeLoginData.getUsername(), "marl3y"), true);

        MvcResult res = mockMvc.perform(
            post("/login/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(fakeLoginData)))
            .andExpect(status().isForbidden())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // validate error message is correct.
        LoginResponse loginResponse = objectMapper
            .readValue(res.getResponse().getContentAsString(), LoginResponse.class);
        assertEquals(ErrorMessage.LOGIN_WRONG_USER, loginResponse.getErrorMessage().getMessage());
    }

    /**
     * Test registration for a new user. Result to pass: HTTP 200 OK
     */
    @Test
    void newUserRegistrationTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Marco", "p0lO");

        mockMvc.perform(
            post("/login/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(fakeLoginData)))
            .andExpect(status().isOk());

        // verify that the user is correctly saved to the userRepository
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User userSaved = userArgumentCaptor.getValue();

        assertEquals(fakeLoginData.getUsername(), userSaved.getUsername());
        assertEquals(fakeLoginData.getPassword(), userSaved.getPassword());

        // verify that an empty CO2 entry for the user is saved to the co2repository.
        ArgumentCaptor<CO2> co2ArgumentCaptor = ArgumentCaptor.forClass(CO2.class);
        verify(co2Repository).save(co2ArgumentCaptor.capture());
        CO2 co2Saved = co2ArgumentCaptor.getValue();

        assertEquals(fakeLoginData.getUsername(), co2Saved.getCUsername());
        assertEquals(0, co2Saved.getCO2Food());
        assertEquals(0, co2Saved.getCO2Energy());
        assertEquals(0, co2Saved.getCO2Transport());
        assertEquals(0, co2Saved.getCO2Reduc());
    }

    /**
     * Configures the mocked database to control the login behaviour.
     *
     * @param loginData - login credentials to set to a state.
     * @param valid - whether the provided login credentials should be valid or not
     */
    private void setUserDB(LoginData loginData, boolean valid) {
        User fakeUser = mock(User.class);

        if (valid) {
            when(fakeUser.getPassword()).thenReturn(loginData.getPassword());
        }

        List<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(fakeUser);

        when(userRepository.findByUsername(loginData.getUsername())).thenReturn(fakeUserList);
    }
}