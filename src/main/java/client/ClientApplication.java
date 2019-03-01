package client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ClientApplication extends javafx.application.Application {

    private final static String URL = "https://fast-meadow-28971.herokuapp.com/";

    /**
     * get requests index page of our heroku server
     * @return the text response from the server
     */
    private String getRequestHeroku () {
        RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject(URL, String.class);
        System.out.println(quote);
        return quote;
    }

    @Override
    public void start(Stage stage) throws Exception {
        String res = getRequestHeroku();
        Label l = new Label(res);
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String args[]) {

        launch();
    }
}
