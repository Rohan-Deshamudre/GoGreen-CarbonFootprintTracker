package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.toJSONString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import gogreen.application.communication.LoginData;
import gogreen.application.communication.LoginRequest;
import gogreen.application.model.User;
import gogreen.application.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(LoginController.class)
class LoginControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    LoginController loginController;

    @MockBean
    UserRepository userRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(loginController).build();
    }

    /**
     * Test a user that does not exist in the database.
     * Result to pass: HTTP 401 Unauthorized
     */
    @Test
    void nonExistentUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(new LoginRequest(fakeLoginData))))
            .andExpect(status().isUnauthorized());
    }

    /**
     * Test a user that has an empty entry in the database.
     * Result to pass: HTTP 401 Unauthorized
     */
    @Test
    void emptyUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserDB(fakeLoginData, false);

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(new LoginRequest(fakeLoginData))))
            .andExpect(status().isUnauthorized());
    }

    /**
     * Test an incorrect password for a user that exists in the database.
     * Result to pass: HTTP 401 Unauthorized
     */
    @Test
    void passwordUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserDB(new LoginData(fakeLoginData.getUsername(), "boogie") , true);

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(new LoginRequest(fakeLoginData))))
            .andExpect(status().isUnauthorized());
    }

    /**
     * Test a valid user/password combination.
     * Result to pass: HTTP 200 OK
     */
    @Test
    void validUserTest() throws Exception {
        LoginData fakeLoginData = new LoginData("Albert", "HoFFman420");
        setUserDB(fakeLoginData , true);

        mockMvc.perform(
            post("/login")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJSONString(new LoginRequest(fakeLoginData))))
            .andExpect(status().isOk());
    }

    /**
     * Configures the mocked database to control the login behaviour.
     * @param loginData - login credentials to set to a state.
     * @param valid - whether the provided login credentials should be valid or not
     */
    private void setUserDB(LoginData loginData, boolean valid){
        User fakeUser = mock(User.class);

        if (valid)
            when(fakeUser.getPassword()).thenReturn(loginData.getPassword());

        List<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(fakeUser);

        when(userRepository.findByUsername(loginData.getUsername())).thenReturn(fakeUserList);
    }
}