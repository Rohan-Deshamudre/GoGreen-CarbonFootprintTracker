package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.setUserValid;
import static gogreen.application.controller.MockitoTestHelper.toJsonString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.client.Leaderboard;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.model.Friend;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.FriendRepository;
import gogreen.application.repository.FriendRequestRepository;
import gogreen.application.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ActivityController.class)
public class ActivityShowFriendsTest {

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

    @MockBean
    private PasswordEncoder passwordEncoder;

    private final String url = "/friendlist";

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
        LoginData fakeLoginData = new LoginData("shdah", "adjasj");
        // invalid username returns an empty list.
        when(userRepository.findByUsername(fakeLoginData.getUsername()))
            .thenReturn(new ArrayList<>());

        mockMvc.perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    /**
     * Test correct post request for a user supplying a wrong password. Result to pass: HTTP 401
     * Unauthorized
     */
    @Test
    void wrongPassword() throws Exception {
        LoginData fakeLoginData = new LoginData("shdah", "adjasj");
        // same username but different password.
        setUserValid(new LoginData(fakeLoginData.getUsername(), "hunter2"),
                userRepository, passwordEncoder);

        mockMvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    @Test
    void emptyTest() throws Exception {
        LoginData fakeLoginData = new LoginData("shdah", "adjasj");

        setUserValid(fakeLoginData, userRepository, passwordEncoder);

        when(friendRepository.findByFusername(fakeLoginData.getUsername()))
                .thenReturn(new ArrayList<>());

        MvcResult res = mockMvc.perform(
                post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(fakeLoginData)))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andReturn();

        // validate leaderboard response
        Leaderboard leaderboard = objectMapper
            .readValue(res.getResponse().getContentAsString(), Leaderboard.class);
        assertEquals(new ArrayList<>(), leaderboard.getUsers());
    }

    @Test
    void fullTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(fakeLoginData, userRepository, passwordEncoder);

        List<Friend> all = new ArrayList<>();
        all.add(new Friend(0, "dummy", "dummyFriend1"));
        all.add(new Friend(1, "dummy", "dummyFriend2"));
        when(friendRepository.findByFusername(fakeLoginData.getUsername())).thenReturn(all);

        List<CO2> friend1 = new ArrayList<>(1);
        friend1.add(new CO2("dummyFriend1", 4, 4, 4, 4, "101010"));
        List<CO2> friend2 = new ArrayList<>(1);
        friend2.add(new CO2("dummyFriend1", 20, 20, 20, 20, "101010"));

        when(co2Repository.findByCusername(all.get(0).getFriend())).thenReturn(friend1);
        when(co2Repository.findByCusername(all.get(1).getFriend())).thenReturn(friend2);

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(fakeLoginData)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        // validate leaderboard response
        Leaderboard leaderboard = objectMapper
                .readValue(res.getResponse().getContentAsString(), Leaderboard.class);
        assertEquals(friend1.get(0).getCUsername(), leaderboard.getUsers().get(0).getCUsername());
        assertEquals(friend2.get(0).getCUsername(), leaderboard.getUsers().get(1).getCUsername());
    }
}
