package gogreen.application.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gogreen.application.communication.LoginData;
import gogreen.application.model.CO2;
import gogreen.application.model.User;
import gogreen.application.repository.CO2Repository;
import gogreen.application.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

public class MockitoTestHelper {

    /**
     * Converts an arbitrary object to a json string using the jackson library.
     *
     * @param obj - an arbitrary object to convert.
     * @return - a string representation of the given object.
     */
    public static String toJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * Updates the mocks so that the provided user will pass the authorization test.
     *
     * @param loginData - login credentials for the user.
     */
    public static void setUserValid(LoginData loginData,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder) {
        User fakeUser = mock(User.class);
        when(fakeUser.getPassword()).thenReturn("TestEncryptedPassword");
        List<User> fakeUserList = new ArrayList<>();
        fakeUserList.add(fakeUser);
        when(userRepository.findByUsername(loginData.getUsername())).thenReturn(fakeUserList);
        when(passwordEncoder.matches(loginData.getPassword(),
                fakeUser.getPassword())).thenReturn(true);
    }

    /**
     * Adds a co2 record to the mocked co2repository.
     *
     * @param co2 - the CO2 record to add to the mocked repository.
     */
    public static void setCarbonRecord(CO2 co2, CO2Repository co2Repository) {
        List<CO2> dbUserCO2List = new ArrayList<>();
        dbUserCO2List.add(
            new CO2(co2.getCUsername(), co2.getCO2food(), co2.getCO2transport(),
                    co2.getCO2energy(), co2.getCO2reduc(), "101010"));
        when(co2Repository.findByCusername(co2.getCUsername())).thenReturn(dbUserCO2List);
    }
}
