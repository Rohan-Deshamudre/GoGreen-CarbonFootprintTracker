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
import gogreen.application.model.CO2;
import gogreen.application.model.Friend;
import gogreen.application.model.FriendRequest;
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
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ActivityController.class)
public class AddFriendTest {

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

    private final String url = "/addfriend";

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

                userRepository);

        mockMvc.perform(
            post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(new AddFriendRequest(fakeLoginData, "dummy"))))
            .andExpect(status().isUnauthorized())
            .andReturn();
    }

    @Test
    void friendNotExistTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(fakeLoginData, userRepository);

        when(co2Repository.findByCusername("dummyFriend")).thenReturn(null);

        List<FriendRequest> requests = new ArrayList<>();
        when(friendRequestRepository.findByUsernameAndRequestTo("dummy", "dummyFriend"))
                .thenReturn(requests);

        AddFriendRequest req = new AddFriendRequest(fakeLoginData, "dummyFriend");

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Boolean response = objectMapper
                .readValue(res.getResponse().getContentAsString(), Boolean.class);
        assertFalse(response);
    }

    @Test
    void alreadyFriendsTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(fakeLoginData, userRepository);

        List<CO2> all = new ArrayList<>();
        all.add(new CO2("dummyFriend", 4, 4, 4, 4));
        when(co2Repository.findByCusername("dummyFriend")).thenReturn(all);
        List<Friend> friends = new ArrayList<>();
        friends.add(new Friend(0, "dummy", "dummyFriend"));
        when(friendRepository.findByFusernameAndFriend("dummy", "dummyFriend"))
                .thenReturn(friends);

        List<FriendRequest> requests = new ArrayList<>();
        when(friendRequestRepository.findByUsernameAndRequestTo("dummy", "dummyFriend"))
                .thenReturn(requests);

        AddFriendRequest req = new AddFriendRequest(fakeLoginData, "dummyFriend");

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Boolean response = objectMapper
                .readValue(res.getResponse().getContentAsString(), Boolean.class);
        assertFalse(response);
    }

    @Test
    void alreadyFriendRequestTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(fakeLoginData, userRepository);

        List<CO2> all = new ArrayList<>();
        all.add(new CO2("dummyFriend", 4, 4, 4, 4));
        when(co2Repository.findByCusername("dummyFriend")).thenReturn(all);

        List<Friend> friends = new ArrayList<>();
        when(friendRepository.findByFusernameAndFriend("dummy", "dummyFriend"))
                .thenReturn(friends);

        List<FriendRequest> requests = new ArrayList<>();
        requests.add(new FriendRequest(0, "dummy", "dummyFriend"));
        when(friendRequestRepository.findByUsernameAndRequestTo("dummy", "dummyFriend"))
                .thenReturn(requests);

        AddFriendRequest req = new AddFriendRequest(fakeLoginData, "dummyFriend");

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Boolean response = objectMapper
                .readValue(res.getResponse().getContentAsString(), Boolean.class);
        assertFalse(response);
    }

    @Test
    void notYourOwnFriendTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(fakeLoginData, userRepository);

        List<CO2> all = new ArrayList<>();
        all.add(new CO2("dummy", 4, 4, 4, 4));
        when(co2Repository.findByCusername("dummy")).thenReturn(all);

        List<Friend> friends = new ArrayList<>();
        when(friendRepository.findByFusernameAndFriend("dummy", "dummy"))
                .thenReturn(friends);

        List<FriendRequest> requests = new ArrayList<>();
        when(friendRequestRepository.findByUsernameAndRequestTo("dummy", "dummy"))
                .thenReturn(requests);

        AddFriendRequest req = new AddFriendRequest(fakeLoginData, "dummy");

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Boolean response = objectMapper
                .readValue(res.getResponse().getContentAsString(), Boolean.class);
        assertFalse(response);
    }

    @Test
    void madeNewFriendTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(new LoginData(fakeLoginData.getUsername(), fakeLoginData.getPassword()),
                userRepository);

        List<CO2> all = new ArrayList<>();
        all.add(new CO2("dummyFriend", 4, 4, 4, 4));
        when(co2Repository.findByCusername("dummyFriend")).thenReturn(all);

        List<FriendRequest> requests = new ArrayList<>();
        when(friendRequestRepository.findByUsernameAndRequestTo("dummy", "dummyFriend"))
                .thenReturn(requests);

        AddFriendRequest req = new AddFriendRequest(fakeLoginData, "dummyFriend");

        MvcResult res = mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(req)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        Boolean response = objectMapper
                .readValue(res.getResponse().getContentAsString(), Boolean.class);
        assertTrue(response);
    }
}
