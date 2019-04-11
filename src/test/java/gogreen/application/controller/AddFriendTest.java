package gogreen.application.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.communication.AddFriendRequest;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.model.Friend;
import gogreen.application.model.FriendRequest;
import gogreen.application.model.User;
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

import static gogreen.application.controller.MockitoTestHelper.setUserValid;
import static gogreen.application.controller.MockitoTestHelper.toJsonString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    private final String URL = "/addfriend";

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
            post(URL)
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

        setUserValid(new LoginData(fakeLoginData.getUsername(), "hunter2"), userRepository);

        mockMvc.perform(
            post(URL)
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

        AddFriendRequest request = new AddFriendRequest(fakeLoginData, "dummyFriend");
        System.out.println(request.getLoginData().toString());
        MvcResult res = mockMvc.perform(
                post(URL)
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
    void madeNewFriendTest() throws Exception {
        LoginData fakeLoginData = new LoginData("dummy", "qwerty");

        setUserValid(new LoginData(fakeLoginData.getUsername(), fakeLoginData.getPassword()), userRepository);

        List<CO2> all = new ArrayList<>();
        all.add(new CO2("dummyFriend", 4, 4, 4, 4, "101010"));
        when(co2Repository.findByCusername("dummyFriend")).thenReturn(all);

        AddFriendRequest request = new AddFriendRequest(fakeLoginData, "dummyFriend");

        MvcResult res = mockMvc.perform(
                post(URL)
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
