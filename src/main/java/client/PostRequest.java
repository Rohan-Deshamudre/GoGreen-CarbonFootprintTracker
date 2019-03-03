package client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.http.HttpResponse.PushPromiseHandler;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;
import communication.LoginRequest;
import communication.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PostRequest {

    public static void main(String[] args) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        // Make uri from the url
//        final String baseUrl = "https://gogreen32.herokuapp.com/test/mirror";
        final String baseUrl = "http://localhost:8080/login";
        URI uri = new URI(baseUrl);

        // Make LoginRequest
        LoginRequest loginRequest = new LoginRequest("Roy", "Donders");

        // POST Request
        LoginResponse result = restTemplate.postForObject(uri, loginRequest, LoginResponse.class);

        System.out.println();
        System.out.println(result);

    }
}
