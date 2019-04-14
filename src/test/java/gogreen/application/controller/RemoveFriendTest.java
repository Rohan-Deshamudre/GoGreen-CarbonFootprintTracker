package gogreen.application.controller;

import static gogreen.application.controller.MockitoTestHelper.setUserValid;
import static gogreen.application.controller.MockitoTestHelper.toJsonString;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.communication.AddFriendRequest;
import gogreen.application.communication.LoginData;
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
public class RemoveFriendTest {

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

    private final String url = "/removefriend";

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
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        when(userRepository.findByUsername(fakeLoginData.getUsername()))
            .thenReturn(new ArrayList<>());

        mockMvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(new AddFriendRequest(fakeLoginData, "dummy"))))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    /**
     * Test correct post request for a user supplying a wrong password. Result to pass: HTTP 401
     * Unauthorized
     */
    @Test
    void wrongPassword() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(new LoginData(fakeLoginData.getUsername(), "hunter2"),
                userRepository, passwordEncoder);

        mockMvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(new AddFriendRequest(fakeLoginData, "dummy"))))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    @Test
    void notFriendsTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(fakeLoginData, userRepository, passwordEncoder);

        List<Friend> friend = new ArrayList<>();
        when(friendRepository.findByFusernameAndFriend( "dummy","dummyFriend"))
                .thenReturn(friend);

        AddFriendRequest request = new AddFriendRequest(fakeLoginData, "dummyFriend");

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Boolean response = objectMapper
                .readValue(res.getResponse().getContentAsString(), Boolean.class);
        assertFalse(response);
    }

    @Test
    void removeFriendTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(new LoginData(fakeLoginData.getUsername(), fakeLoginData.getPassword()),
                userRepository, passwordEncoder);

        List<Friend> list1 = new ArrayList<>();
        Friend friend1 = new Friend(0, "dummyFriend", "dummy");
        list1.add(friend1);
        when(friendRepository.findByFusernameAndFriend( "dummy","dummyFriend"))
                .thenReturn(list1);
        List<Friend> list2 = new ArrayList<>();
        list2.add(friend1);
        when(friendRepository.findByFusernameAndFriend( "dummyFriend","dummy"))
                .thenReturn(list2);

        AddFriendRequest request = new AddFriendRequest(fakeLoginData, "dummyFriend");

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(request)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Boolean response = objectMapper
                .readValue(res.getResponse().getContentAsString(), Boolean.class);
        assertTrue(response);
    }
}
