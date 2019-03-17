package application.gui;

import application.client.ClientApplication;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URISyntaxException;


public class Gui_Main extends Application {

    private Stage window;
    private Scene loginScene;

    /**
     * Main method of the class, launches the application.
     *
     * @param args: the input
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method starts the window.
     *
     * @param primaryStage: this is the window of the application.
     * @throws Exception: in case of an exception.
     */
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;

        loginPage();
    }

    /**
     * This is the login page.
     *
     * @throws Exception: in case of an exception.
     */
    private void loginPage() throws Exception {
        window.setTitle("Login");

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
        GridPane.setConstraints(helloLabel, 1, 0);

        // Enter username
        Label usernameLabel = new Label("Username: ");
        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setMaxWidth(300);
        GridPane.setConstraints(usernameLabel, 0, 1);
        GridPane.setConstraints(usernameField, 1, 1);


        // Enter password
        Label passwordLabel = new Label("Password: ");
        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        passwordField.setMaxWidth(300);
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordField, 1, 2);

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            loginButtonAction(usernameField.getText(), passwordField.getText());
            usernameField.setText("");
            passwordField.setText("");
        });
        GridPane.setConstraints(loginButton, 1, 3);

        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(topGroup);
        borderPane.setCenter(grid);


        // Make scene
        grid.getChildren().addAll(
                helloLabel, usernameLabel, usernameField,
                passwordLabel, passwordField, loginButton
        );
        loginScene = new Scene(borderPane, 600, 400);

        // Show window
        window.setScene(loginScene);
        window.setTitle("Login Page");
        window.show();
    }

    /**
     * This is the method for the main menu.
     */
    private void showMainMenu() {
        window.setTitle("Main menu");

        //left part
        VBox left = new VBox();
        left.setPrefWidth(160);
        left.setPrefHeight(160);
        left.setAlignment(Pos.CENTER);
        left.setPadding(new Insets(10, 10, 10, 10));

        Button food = new Button("Food");
        food.setMinSize(left.getPrefWidth(), left.getPrefHeight());
        food.setOnAction(e -> showFoodPage());

        Button homeE = new Button("Home");
        homeE.setMinSize(left.getPrefWidth(), left.getPrefHeight());
        homeE.setOnAction(e -> showHomeEnergy());

        left.getChildren().addAll(food, homeE);

        //middle part
        VBox middle = new VBox();
        middle.setPrefWidth(160);
        middle.setPrefHeight(160);
        middle.setAlignment(Pos.CENTER_LEFT);

        Button transport = new Button("Transport");
        transport.setMinSize(middle.getPrefWidth(), middle.getPrefHeight());
        transport.setOnAction(e -> showTransportPage());

        Button share = new Button("Share");
        share.setMinSize(middle.getPrefWidth(), middle.getPrefHeight());
        share.setOnAction(e -> showShare());

        middle.getChildren().addAll(transport, share);

        //right part
        StackPane right = new StackPane();
        right.setAlignment(Pos.CENTER_RIGHT);

        Label scoreboard = new Label("Scoreboard");
        scoreboard.setMinSize(128, 320);

        right.getChildren().addAll(scoreboard);

        //setting up the window
        BorderPane menuPane = new BorderPane();
        menuBar(menuPane);
        menuPane.setLeft(left);
        menuPane.setCenter(middle);
        menuPane.setRight(right);

        //setting up the scene
        Scene scene = new Scene(menuPane, 600, 400);
        window.setScene(scene);
        window.show();
    }

    /**
     * This is the page for the food page.
     */
    private void showFoodPage() {
        window.setTitle("Food");

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        Label foodTitle = new Label("Food");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Ate a vegetarian meal",
                "Bought from a biological store");
        choiceBox.setValue("Ate a vegetarian meal");

        Button addOption = new Button("Add");
        //add something that updates the database

        center.getChildren().addAll(foodTitle, choiceBox, addOption);

        //set up border pane
        BorderPane foodPane = new BorderPane();
        menuBar(foodPane);
        foodPane.setCenter(center);

        //set up the scene
        Scene foodPage = new Scene(foodPane, 600, 400);
        window.setScene(foodPage);
        window.show();
    }

    /**
     * This is the method for the transport page.
     */
    private void showTransportPage() {
        window.setTitle("Transport");

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        Label transportTitle = new Label("Transport");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Using bike instead of car",
                "Using public transport instead of car");
        choiceBox.setValue("Using bike instead of car");

        Button addOption = new Button("Add");
        //add something that updates the database

        center.getChildren().addAll(transportTitle, choiceBox, addOption);

        //set up border pane
        BorderPane foodPane = new BorderPane();
        menuBar(foodPane);
        foodPane.setCenter(center);

        //set up the scene
        Scene foodPage = new Scene(foodPane, 600, 400);
        window.setScene(foodPage);
        window.show();
    }

    /**
     * This is the method for the home energy page.
     */
    private void showHomeEnergy() {
        window.setTitle("Home Energy");

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        Label energyTitle = new Label("Home energy");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Lowering the temperature of your home",
                "Installing solar panels");
        choiceBox.setValue("Lowering the temperature of your home");

        Button addOption = new Button("Add");
        //add something that updates the database

        center.getChildren().addAll(energyTitle, choiceBox, addOption);

        //set up border pane
        BorderPane energyPane = new BorderPane();
        menuBar(energyPane);
        energyPane.setCenter(center);

        //set up the scene
        Scene energyPage = new Scene(energyPane, 600, 400);
        window.setScene(energyPage);
        window.show();
    }

    private void showShare() {
        window.setTitle("Share");

        //setup username select
        ChoiceBox<String> userChoice = new ChoiceBox<>();
        userChoice.getItems().addAll("User1", "User", "User3");
        userChoice.setValue("User1");

        Label whitespace = new Label("");

        //left buttons
        VBox left = new VBox();
        left.setPrefSize(70, 70);
        left.setAlignment(Pos.CENTER);
        left.setPadding(new Insets(10, 10, 10, 10));

        Button react1 = new Button("Reaction 1");
        react1.setMinSize(left.getPrefWidth(), left.getPrefHeight());
        Button react2 = new Button("Reaction 2");
        react2.setMinSize(left.getPrefWidth(), left.getPrefHeight());

        left.getChildren().addAll(userChoice, whitespace, react1, react2);

        //right buttons
        VBox right = new VBox();
        right.setPrefSize(50, 70);
        right.setAlignment(Pos.CENTER);
        right.setPadding(new Insets(53, 10, 10, 10));

        Button react3 = new Button("Reaction 3");
        react3.setMinSize(right.getPrefWidth(), right.getPrefHeight());
        Button react4 = new Button("Reaction 4");
        react4.setMinSize(right.getPrefWidth(), right.getPrefHeight());

        right.getChildren().addAll(react3, react4);

        //Setting up the scoreboard
        StackPane rightScore = new StackPane();
        rightScore.setAlignment(Pos.CENTER_RIGHT);

        Label scoreboard = new Label("Scoreboard");
        scoreboard.setMinSize(80, 130);

        rightScore.getChildren().addAll(scoreboard);

        //Setting up the pane
        BorderPane sharePane = new BorderPane();
        menuBar(sharePane);
        sharePane.setLeft(left);
        sharePane.setCenter(right);
        sharePane.setRight(rightScore);

        Scene shareScene = new Scene(sharePane, 600, 400);
        window.setScene(shareScene);
        window.show();
    }

    public void menuBar(BorderPane pane) {
        Menu menu = new Menu("Menu");

        MenuItem goToHomeScreen = new MenuItem("Home");
        goToHomeScreen.setOnAction(e -> showMainMenu());

        SeparatorMenuItem sep1 = new SeparatorMenuItem();
        MenuItem goToFood = new MenuItem("Food");
        goToFood.setOnAction(e -> showFoodPage());
        MenuItem goToTransport = new MenuItem("Transport");
        goToTransport.setOnAction(e -> showTransportPage());
        MenuItem goToEnergy = new MenuItem("Home energy");
        goToEnergy.setOnAction(e -> showHomeEnergy());

        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        MenuItem goToShare = new MenuItem("Share");
        goToShare.setOnAction(e -> showShare());

        SeparatorMenuItem sep3 = new SeparatorMenuItem();
        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> {
            try {
                loginPage();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        });

        menu.getItems().addAll(
                goToHomeScreen, sep1, goToFood, goToTransport,
                goToEnergy, sep2, goToShare, sep3, logout
        );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);

        pane.setTop(menuBar);
    }

    /**
     * NOT GUI
     *
     * @param username
     * @param password
     */
    private void loginButtonAction(String username, String password) {
        boolean ok = false;
        try {
            ok = ClientApplication.sendLoginRequest(username, password);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (ok) {
            System.out.println("LOGIN SUCCESFULL");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println();
            showMainMenu();
        } else {
            System.out.println("WRONG USERNAME OR PASSWORD");
            System.out.println("username: " + username);
            System.out.println("password: " + password);
            System.out.println();
        }
    }
}
