package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import client.ClientApplication;
import server.ServerApplication;


public class GUI_Main extends Application {

    private Stage window;
    private Scene loginScene;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        loginPage(primaryStage);
    }

    /**
     * This is the login page
     * @param primaryStage
     * @throws Exception
     */
    private void loginPage(Stage primaryStage) throws Exception {
        window = primaryStage;

        // TOP
        Group topGroup = new Group();
        Text goGreenText = new Text("Go Green!");
        goGreenText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 50));
        topGroup.getChildren().add(goGreenText);

        // CENTER
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100, 100, 100, 100));
        grid.setVgap(8);
        grid.setHgap(10);

        // Hello client label
        String helloString = ClientApplication.getRequestHeroku() + "!";
        Label helloLabel = new Label(helloString);

        // Enter username
        Label usernameLabel = new Label("Username: ");
        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setMaxWidth(300);

        // Enter password
        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        passwordField.setMaxWidth(300);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            loginButtonAction(usernameField.getText(), passwordField.getText());
            usernameField.setText("");
            passwordField.setText("");
        });

        // Add all elements to the grid
        GridPane.setConstraints(helloLabel, 1, 0);
        GridPane.setConstraints(usernameLabel, 0, 1);
        GridPane.setConstraints(usernameField, 1, 1);
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordField, 1, 2);
        GridPane.setConstraints(loginButton, 1, 3);

        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,10,10,10));
        borderPane.setTop(topGroup);
        borderPane.setCenter(grid);


        // Make scene
        grid.getChildren().addAll(helloLabel, usernameLabel, usernameField, passwordLabel, passwordField, loginButton);
        loginScene = new Scene(borderPane, 600, 400);

        // Show window
        window.setScene(loginScene);
        window.setTitle("Login Page");
        window.show();
    }

    /**
     * NOT GUI
     * @param username
     * @param password
     */
    private void loginButtonAction(String username, String password){
        boolean ok = server.ServerApplication.checkLoginData(username, password);
        if (ok) {
            System.out.println("LOGIN SUCCESFULL");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println();
        }
        else {
            System.out.println("WRONG USERNAME OR PASSWORD");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println();
        }
    }


}

