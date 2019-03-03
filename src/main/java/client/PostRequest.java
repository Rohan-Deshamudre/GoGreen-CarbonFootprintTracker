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
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class PostRequest {

    public static void main(String[] args) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        // Make uri from the url
        final String baseUrl = "https://gogreen32.herokuapp.com/test/mirror";
        URI uri = new URI(baseUrl);

        // Make LoginRequest
        LoginRequest loginRequest = new LoginRequest("Tay", "Zonday");

        // POST Request
        LoginRequest result = restTemplate.postForObject(uri, loginRequest, LoginRequest.class);

        System.out.println();
        System.out.println(result.toString());

    }
}
