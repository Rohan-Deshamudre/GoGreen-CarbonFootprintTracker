package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.setUserValid;
import static gogreen.application.controller.MockitoTestHelper.toJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.model.User;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
class LoginControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoginController loginController;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CO2Repository co2Repository;

    @MockBean
    private PasswordEncoder passwordEncoder;

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

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    /**
     * Test a user that has an empty entry in the database. Result to pass: HTTP 401 Unauthorized
     */
    @Test
    void emptyUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        User fakeUser = mock(User.class);
        List<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(fakeUser);
        when(userRepository.findByUsername(fakeLoginData.getUsername())).thenReturn(fakeUserList);

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    /**
     * Test an incorrect password for a user that exists in the database. Result to pass: HTTP 401
     * Unauthorized
     */
    @Test
    void passwordUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserValid(new LoginData(fakeLoginData.getUsername(), "boogie"), userRepository, passwordEncoder);

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    /**
     * Test a valid user/password combination. Result to pass: HTTP 200 OK
     */
    @Test
    void validUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserValid(fakeLoginData, userRepository, passwordEncoder);

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
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
        setUserValid(new LoginData(fakeLoginData.getUsername(), "marl3y"), userRepository, passwordEncoder);

        mockMvc.perform(
            post("/login/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isForbidden())
            .andReturn();
    }

    /**
     * Test registration for a new user. Result to pass: HTTP 201 Created
     */
    @Test
    void newUserRegistrationTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Marco", "p0lO");
        when(passwordEncoder.encode(fakeLoginData.getPassword())).thenReturn("TestEncryptedPassword");

        mockMvc.perform(
            post("/login/register")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isCreated());

        // verify that the user is correctly saved to the userRepository
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User userSaved = userArgumentCaptor.getValue();

        assertEquals(fakeLoginData.getUsername(), userSaved.getUsername());
        assertEquals(passwordEncoder.encode(fakeLoginData.getPassword()), userSaved.getPassword());

        // verify that an empty CO2 entry for the user is saved to the co2repository.
        ArgumentCaptor<CO2> co2ArgumentCaptor = ArgumentCaptor.forClass(CO2.class);
        verify(co2Repository).save(co2ArgumentCaptor.capture());
        CO2 co2Saved = co2ArgumentCaptor.getValue();

        assertEquals(fakeLoginData.getUsername(), co2Saved.getCUsername());
        assertEquals(0, co2Saved.getCO2food());
        assertEquals(0, co2Saved.getCO2energy());
        assertEquals(0, co2Saved.getCO2transport());
        assertEquals(0, co2Saved.getCO2reduc());
    }
}