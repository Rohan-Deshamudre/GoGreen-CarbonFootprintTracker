package gogreen.application.gui;

import gogreen.application.client.ClientApplication;
import gogreen.application.client.Leaderboard;
import gogreen.application.communication.AddTransportRequest.TravelType;
import gogreen.application.communication.CO2Response;
import gogreen.application.model.CO2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.web.client.RestClientException;

import java.net.URISyntaxException;

import java.util.ArrayList;

public class GuiMain extends Application {

    private Stage window;
    private Scene loginScene;
    private int screenWidth;
    private int screenHeight;

    /**
     * Main method of the class, launches the application.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method starts the window.
     */
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        screenWidth = (int) primaryScreenBounds.getWidth();
        screenHeight = (int) primaryScreenBounds.getHeight();
        window.setMaximized(true);

        loginPage();
    }

    /**
     * This is the login page.
     *
     * @throws Exception in case of an exception.
     */
    private void loginPage() {
        window.setTitle("Login");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

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
        String helloString = ClientApplication.getRequestHeroku();
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
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setMaxWidth(300);
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordField, 1, 2);

        // Buttons
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            loginButtonAction(usernameField.getText(), passwordField.getText());
            usernameField.setText("");
            passwordField.setText("");
        });
        GridPane.setConstraints(loginButton, 1, 3);

        Button registrationButton = new Button("Register");
        registrationButton.setOnAction(e -> {
            registrationPage();
        });
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(loginButton, registrationButton);
        GridPane.setConstraints(buttons, 1, 3);

        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(topGroup);
        borderPane.setCenter(grid);

        // Make scene
        grid.getChildren().addAll(
            helloLabel, usernameLabel, usernameField,
            passwordLabel, passwordField, buttons
        );

        loginScene = new Scene(borderPane, screenWidth, screenHeight);

        // Show window
        window.setScene(loginScene);
        window.show();
    }

    /**
     * GUI of the Registration page.
     *
     * @throws Exception - in case of an Exception.
     */

    private void registrationPage() {
        window.setTitle("Registration");
        // TOP
        Group topGroup = new Group();
        Text goGreenText = new Text("Registration");
        goGreenText.setFont(Font.font("Verdana", FontWeight.BOLD, 50));
        topGroup.getChildren().add(goGreenText);

        // CENTER
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100, 100, 100, 100));
        grid.setVgap(8);
        grid.setHgap(10);

        // Enter username
        Label usernameLabel = new Label("Username: ");
        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setMaxWidth(300);
        GridPane.setConstraints(usernameLabel, 0, 1);
        GridPane.setConstraints(usernameField, 1, 1);

        // Enter password
        Label passwordLabel = new Label("Password: ");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setMaxWidth(300);
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordField, 1, 2);

        // Repeat password
        Label passwordLabel1 = new Label("Password: ");
        PasswordField passwordField1 = new PasswordField();
        passwordField1.setPromptText("repeat password");
        passwordField1.setMaxWidth(300);
        GridPane.setConstraints(passwordLabel1, 0, 3);
        GridPane.setConstraints(passwordField1, 1, 3);

        // Buttons
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            loginPage();
        });
        Button registrationButton = new Button("Register");
        registrationButton.setOnAction(e -> {
            // Register
            registerButtonAction(usernameField.getText(), passwordField.getText(),
                passwordField1.getText());
            usernameField.setText("");
            passwordField.setText("");
            passwordField1.setText("");
        });
        HBox buttons = new HBox();
        buttons.setSpacing(10);
        buttons.getChildren().addAll(registrationButton, loginButton);
        GridPane.setConstraints(buttons, 1, 4);

        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setTop(topGroup);
        borderPane.setCenter(grid);

        // Make scene
        grid.getChildren().addAll(
            usernameLabel, usernameField, passwordLabel,
            passwordLabel1, passwordField, passwordField1, buttons
        );
        loginScene = new Scene(borderPane, screenWidth, screenHeight);

        // Show window
        window.setScene(loginScene);
        window.setTitle("Registration Page");
        window.show();
    }

    /**
     * This is the method for the main menu.
     */
    private void showMainMenu() {
        window.setTitle("Main menu");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        GridPane buttons = new GridPane();
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(10));
        buttons.setVgap(10);
        buttons.setHgap(10);
        int buttonWidth = 160;
        int buttonHeight = 160;

        Button food = new Button("Food");
        food.setMinSize(buttonWidth, buttonHeight);
        food.setOnAction(e -> showFoodPage());
        buttons.add(food, 0, 0);

        Button homeE = new Button("Home");
        homeE.setMinSize(buttonWidth, buttonHeight);
        homeE.setOnAction(e -> showHomeEnergy());
        buttons.add(homeE, 0, 1);

        Button transport = new Button("Transport");
        transport.setMinSize(buttonWidth, buttonHeight);
        transport.setOnAction(e -> showTransportPage());
        buttons.add(transport, 1, 0);

        Button share = new Button("Stats");
        share.setMinSize(buttonWidth, buttonHeight);
        share.setOnAction(e -> userPage());
        buttons.add(share, 1, 1);

        // Leaderboard
        Leaderboard leaderboard = null;
        try {
            leaderboard = ClientApplication.sendGetFriendListRequest();
            System.out.println(leaderboard);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        leaderboard.sortLeaderboard();
        VBox scoreboard = leaderboard(leaderboard.getUsers());
        scoreboard.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setMargin(scoreboard, new Insets(10, 100, 10, 10));

        //setting up the window
        BorderPane menuPane = new BorderPane();
        menuBar(menuPane);
        menuPane.setCenter(buttons);
        menuPane.setRight(scoreboard);

        //setting up the scene
        Scene scene = new Scene(menuPane, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * This is the page for the food page.
     */
    private void showFoodPage() {
        window.setTitle("Food");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10);

        // Set up buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setPrefWidth(180);
        buttons.setPrefHeight(180);
        buttons.setSpacing(20);

        Button option1 = new Button("Ate a vegetarian meal");
        option1.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option1.setOnAction(e -> veggieMealPage());

        Button option2 = new Button("Bought from a Local store");
        option2.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option2.setOnAction(e -> localStorePage());

        buttons.getChildren().addAll(option1, option2);

        Label foodTitle = new Label("Food");

        center.getChildren().addAll(foodTitle, buttons);

        //set up border pane
        BorderPane foodPane = new BorderPane();
        menuBar(foodPane);
        foodPane.setCenter(center);

        //set up the scene
        Scene foodPage = new Scene(foodPane, screenWidth, screenHeight);
        window.setScene(foodPage);
        window.show();
    }

    /**
     * On this page you can enter the details of your veggetarian meal.
     */
    private void veggieMealPage() {
        window.setTitle("Vegetarian Meal");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10);
        center.setPadding(new Insets(30));

        Slider sizeSlider1 = sizeSlider();
        Slider sizeSlider2 = sizeSlider();
        Slider sizeSlider3 = sizeSlider();
        Slider sizeSlider4 = sizeSlider();

        Button addButton = new Button("Add");
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> foodAddButtonAction(
                sizeSlider1.getValue(),
                sizeSlider2.getValue(),
                sizeSlider3.getValue(),
                sizeSlider4.getValue()));

        GridPane centerGrid = new GridPane();
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        Label titleLabel = new Label("Vegetarian Meal");
        Label sizeLabel = new Label("Size:");
        Label label1 = new Label("Salad");
        Label label2 = new Label("Fruits");
        Label label3 = new Label("Vegetarian Meat");
        Label label4 = new Label("Vegan Meal");

        centerGrid.add(titleLabel, 0, 0);
        centerGrid.add(label1, 0, 1);
        centerGrid.add(label2, 0, 2);
        centerGrid.add(label3, 0, 3);
        centerGrid.add(label4, 0, 4);

        centerGrid.add(sizeLabel, 1, 0);
        centerGrid.add(sizeSlider1, 1, 1);
        centerGrid.add(sizeSlider2, 1, 2);
        centerGrid.add(sizeSlider3, 1, 3);
        centerGrid.add(sizeSlider4, 1, 4);

        centerGrid.add(addButton, 1, 5);

        center.getChildren().addAll(centerGrid);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(center);

        //set up the scene
        Scene foodPage = new Scene(borderPane, screenWidth, screenHeight);
        window.setScene(foodPage);
        window.show();
    }

    /**
     * On this page you can enter the details of your veggetarian meal.
     */
    private void localStorePage() {
        window.setTitle("Local Store");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        TextField weightField = new TextField();
        weightField.setMaxWidth(300);
        weightField.setPromptText("grams");

        CheckBox checkBox = new CheckBox("Organic");

        Button addButton = new Button("Add");
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            localProduceAction(Integer.parseInt(weightField.getText()), checkBox.isSelected());
            weightField.setText("");
            checkBox.setSelected(false);
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setPadding(new Insets(30));
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        Label titleLabel = new Label("Local Store");
        Label weightLabel = new Label("Weight:");

        centerGrid.add(titleLabel, 0, 0);
        centerGrid.add(weightLabel, 0, 1);
        centerGrid.add(weightField, 1, 1);
        centerGrid.add(checkBox, 0, 2);
        centerGrid.add(addButton, 1, 5);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(centerGrid);

        //set up the scene
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * This is the method for the transport page.
     */
    private void showTransportPage() {
        window.setTitle("Transport");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10);

        // Set up buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setPrefWidth(180);
        buttons.setPrefHeight(180);
        buttons.setSpacing(20);

        Button option1 = new Button("Bike");
        option1.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option1.setOnAction(e -> bikeRidePage());

        Button option2 = new Button("Public transport");
        option2.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option2.setOnAction(e -> publicTransportPage());

        buttons.getChildren().addAll(option1, option2);

        Label transportTitle = new Label("Transport");

        center.getChildren().addAll(transportTitle, buttons);

        //set up border pane
        BorderPane foodPane = new BorderPane();
        menuBar(foodPane);
        foodPane.setCenter(center);

        //set up the scene
        Scene foodPage = new Scene(foodPane, screenWidth, screenHeight);
        window.setScene(foodPage);
        window.show();
    }

    /**
     * Bike ride page.
     */
    private void bikeRidePage() {
        window.setTitle("Bike Ride");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        TextField distanceField = new TextField();
        distanceField.setMaxWidth(300);
        distanceField.setPromptText("km");

        Button addButton = new Button("Add");
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            transportAddButtonAction(TravelType.BIKE, Integer.parseInt(distanceField.getText()));
            distanceField.setText("");
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);
        Label titleLabel = new Label("Bike Ride");
        Label distanceLabel = new Label("Total distance:");

        centerGrid.add(titleLabel, 1, 0);
        centerGrid.add(distanceLabel, 0, 1);
        centerGrid.add(distanceField, 1, 1);
        centerGrid.add(addButton, 1, 5);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(centerGrid);

        //set up the scene
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * Public transport page.
     */
    private void publicTransportPage() {
        window.setTitle("Public Transport");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        TextField distanceField = new TextField();
        distanceField.setMaxWidth(300);
        distanceField.setPromptText("km");

        Button addButton = new Button("Add");
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            transportAddButtonAction(TravelType.PUB_TRANSPORT,
                Integer.parseInt(distanceField.getText()));
            distanceField.setText("");
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);
        Label titleLabel = new Label("Public Transport");
        Label distanceLabel = new Label("Total distance:");

        centerGrid.add(titleLabel, 1, 0);
        centerGrid.add(distanceLabel, 0, 1);
        centerGrid.add(distanceField, 1, 1);
        centerGrid.add(addButton, 1, 5);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(centerGrid);

        //set up the scene
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * This is the method for the home energy page.
     */
    private void showHomeEnergy() {
        window.setTitle("Home Energy");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10);

        // Set up buttons
        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setPrefWidth(180);
        buttons.setPrefHeight(180);
        buttons.setSpacing(20);

        Button option1 = new Button("Home Temperature");
        option1.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option1.setOnAction(e -> homeTemperaturePage());

        Button option2 = new Button("Solar Panels");
        option2.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option2.setOnAction(e -> solarPanelPage());

        buttons.getChildren().addAll(option1, option2);

        Label energyTitle = new Label("Home energy");

        center.getChildren().addAll(energyTitle, buttons);

        //set up border pane
        BorderPane energyPane = new BorderPane();
        menuBar(energyPane);
        energyPane.setCenter(center);

        //set up the scene
        Scene energyPage = new Scene(energyPane, screenWidth, screenHeight);
        window.setScene(energyPage);
        window.show();
    }

    /**
     * Home temperature page.
     */
    private void homeTemperaturePage() {
        window.setTitle("Home Temperature");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        TextField temperatureField = new TextField();
        temperatureField.setMaxWidth(300);
        temperatureField.setPromptText("Degrees Celsius");

        TextField durationField = new TextField();
        durationField.setMaxWidth(300);
        durationField.setPromptText("hours");

        Button addButton = new Button("Add");
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            homeTempAddButtonAction(Integer.parseInt(temperatureField.getText()),
                Integer.parseInt(durationField.getText()));
            temperatureField.setText("");
            durationField.setText("");
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);
        Label titleLabel = new Label("Home Temperature");
        Label temperatureLabel = new Label("Temperature reduction:");
        Label durationLabel = new Label("Duration:");

        centerGrid.add(titleLabel, 1, 0);
        centerGrid.add(temperatureLabel, 0, 1);
        centerGrid.add(temperatureField, 1, 1);
        centerGrid.add(durationLabel, 0, 2);
        centerGrid.add(durationField, 1, 2);
        centerGrid.add(addButton, 1, 5);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(centerGrid);

        //set up the scene
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * Solar panel page.
     */
    private void solarPanelPage() {
        window.setTitle("Solar Panels");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        TextField areaField = new TextField();
        areaField.setMaxWidth(300);
        areaField.setPromptText("square meters");

        TextField hoursSunlightField = new TextField();
        hoursSunlightField.setMaxWidth(300);
        hoursSunlightField.setPromptText("hours");

        Button addButton = new Button("Add");
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            solarPanelAction(Integer.parseInt(areaField.getText()),
                Integer.parseInt(hoursSunlightField.getText()));
            areaField.setText("");
            hoursSunlightField.setText("");
        });

        Label titleLabel = new Label("Solar Panels");
        Label areaLabel = new Label("Total area:");
        Label hoursSunlightLabel = new Label("Sunlight:");

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        centerGrid.add(titleLabel, 1, 0);
        centerGrid.add(areaLabel, 0, 1);
        centerGrid.add(areaField, 1, 1);
        centerGrid.add(hoursSunlightLabel, 0, 2);
        centerGrid.add(hoursSunlightField, 1, 2);
        centerGrid.add(addButton, 1, 5);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(centerGrid);

        //set up the scene
        Scene scene = new Scene(borderPane, screenWidth, screenHeight);
        window.setScene(scene);
        window.show();
    }

    /**
     * Page for the share page.
     */
    private void showShare() {
        window.setTitle("Stats");
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //setup username select
        ChoiceBox<String> userChoice = new ChoiceBox<>();
        userChoice.getItems().addAll("User1", "User", "User3");
        userChoice.setValue("User1");

        //left buttons
        VBox left = new VBox();
        left.setPrefSize(70, 70);
        left.setAlignment(Pos.CENTER);
        left.setPadding(new Insets(10, 10, 10, 10));

        Button react1 = new Button("Reaction 1");
        react1.setMinSize(left.getPrefWidth(), left.getPrefHeight());
        Button react2 = new Button("Reaction 2");
        react2.setMinSize(left.getPrefWidth(), left.getPrefHeight());

        Label whitespace = new Label("");

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

        Scene shareScene = new Scene(sharePane, screenWidth, screenHeight);
        window.setScene(shareScene);
        window.show();
    }

    /**
     * This is the user page.
     *
     * @throws Exception - in case of an exception.
     */
    private void userPage() {
        window.setTitle("User");

        // LEFT: Stats
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100));
        grid.setVgap(8);
        grid.setHgap(10);

        // CENTER: Show friends
        Leaderboard leaderboard = null;
        try {
            leaderboard = ClientApplication.sendGetFriendListRequest();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        leaderboard.sortLeaderboard();
        VBox allFriends = showFriends(leaderboard.getUsers());
        allFriends.setPadding(new Insets(100));

        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(grid);
        borderPane.setCenter(allFriends);
        makeFriendRequestTable(borderPane);
        menuBar(borderPane);

        // Stats
        CO2 user = null;
        try {
            user = ClientApplication.sendGetUserStatsRequest();
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        Label stats = new Label("Stats");
        Label username = new Label("Username:");
        Label usernameValue = new Label(user.getCUsername());
        Label totalCO2 = new Label("Total CO2 reduction:");
        Label totalCO2Value = new Label(Integer.toString(user.getCO2reduc()));
        Label co2food = new Label("CO2 reduction for food:");
        Label co2foodValue = new Label(Integer.toString(user.getCO2food()));
        Label co2transport = new Label("CO2 reduction for transport:");
        Label co2transportValue = new Label(Integer.toString(user.getCO2transport()));
        Label co2energy = new Label("CO2 reduction for energy:");
        Label co2energyValue = new Label(Integer.toString(user.getCO2energy()));

        grid.add(stats, 0, 0);
        grid.add(username, 0, 3);
        grid.add(usernameValue, 4, 3);
        grid.add(totalCO2, 0, 6);
        grid.add(totalCO2Value, 4, 6);
        grid.add(co2food, 0, 9);
        grid.add(co2foodValue, 4, 9);
        grid.add(co2transport, 0, 12);
        grid.add(co2transportValue, 4, 12);
        grid.add(co2energy, 0, 15);
        grid.add(co2energyValue, 4, 15);

        loginScene = new Scene(borderPane, screenWidth, screenHeight);

        // Show window
        window.setScene(loginScene);
        window.show();
    }

    /**
     * This is the user page.
     *
     * @throws Exception - in case of an exception.
     */
    private void showUserPage(CO2 user) {
        window.setTitle("User");

        // CENTER
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100));
        grid.setVgap(8);
        grid.setHgap(10);


        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(grid);
        menuBar(borderPane);

        Label stats = new Label("Stats");
        Label username = new Label("Username:");
        Label usernameValue = new Label(user.getCUsername());
        Label totalCO2 = new Label("Total CO2 reduction:");
        Label totalCO2Value = new Label(Integer.toString(user.getCO2reduc()));
        Label co2food = new Label("CO2 reduction for food:");
        Label co2foodValue = new Label(Integer.toString(user.getCO2food()));
        Label co2transport = new Label("CO2 reduction for transport:");
        Label co2transportValue = new Label(Integer.toString(user.getCO2transport()));
        Label co2energy = new Label("CO2 reduction for energy:");
        Label co2energyValue = new Label(Integer.toString(user.getCO2energy()));

        grid.add(stats, 0, 0);
        grid.add(username, 0, 3);
        grid.add(usernameValue, 4, 3);
        grid.add(totalCO2, 0, 6);
        grid.add(totalCO2Value, 4, 6);
        grid.add(co2food, 0, 9);
        grid.add(co2foodValue, 4, 9);
        grid.add(co2transport, 0, 12);
        grid.add(co2transportValue, 4, 12);
        grid.add(co2energy, 0, 15);
        grid.add(co2energyValue, 4, 15);

        loginScene = new Scene(borderPane, screenWidth, screenHeight);

        // Show window
        window.setScene(loginScene);
        window.show();
    }

    /**
     * This is the menu bar for the top corner.
     *
     * @param pane - the window in which to display the menu bar.
     */
    public void menuBar(BorderPane pane) {
        MenuItem goToHomeScreen = new MenuItem("Home");
        goToHomeScreen.setOnAction(e -> showMainMenu());

        MenuItem goToFood = new MenuItem("Food");
        goToFood.setOnAction(e -> showFoodPage());
        MenuItem goToTransport = new MenuItem("Transport");
        goToTransport.setOnAction(e -> showTransportPage());
        MenuItem goToEnergy = new MenuItem("Home energy");
        goToEnergy.setOnAction(e -> showHomeEnergy());

        MenuItem goToUserPage = new MenuItem("Stats");
        goToUserPage.setOnAction(e -> userPage());
        MenuItem goToShare = new MenuItem("Share");
        goToShare.setOnAction(e -> showShare());

        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> {
            logout();
        });

        Menu menu = new Menu("Menu");
        SeparatorMenuItem sep1 = new SeparatorMenuItem();
        SeparatorMenuItem sep2 = new SeparatorMenuItem();
        SeparatorMenuItem sep3 = new SeparatorMenuItem();

        menu.getItems().addAll(
            goToHomeScreen, sep1, goToFood, goToTransport,
            goToEnergy, sep2, goToUserPage, goToShare, sep3, logout
        );

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);

        Button homeButton = new Button("Home");
        homeButton.setOnAction(e -> showMainMenu());
        homeButton.setFocusTraversable(false);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(homeButton);

        VBox vbox = new VBox();
        vbox.getChildren().addAll(menuBar, buttonBox);
        pane.setTop(vbox);
    }

    /**
     * With this slider you can select the size of your meal.
     *
     * @return meal size.
     */
    private Slider sizeSlider() {
        Slider slider = new Slider(0, 3, 0);
        slider.setMin(0);
        slider.setMax(3);
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(1);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        slider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double value) {
                if (value < 0.5) {
                    return "None";
                }
                if (value < 1.5) {
                    return "Small";
                }
                if (value < 2.5) {
                    return "Medium";
                }
                return "Large";
            }

            @Override
            public Double fromString(String string) {
                switch (string) {
                    case "None":
                        return 0d;
                    case "Small":
                        return 1d;
                    case "Medium":
                        return 2d;
                    case "Large":
                        return 3d;
                    default:
                        return 3d;
                }
            }
        });

        slider.setMinWidth(300);
        return slider;
    }

    /**
     * NOT GUI.
     *
     * @param username - the username
     * @param password - the password
     */
    private void loginButtonAction(String username, String password) {
        if (
            ClientApplication.sendLoginRequest(username, password)) {
            System.out.println("LOGIN SUCCESSFUL");
            System.out.println();
            showMainMenu();
        } else {
            AlertBox.display("Wrong username/password combination. Please try again.");
            System.out.println("LOGIN UNSUCCESSFUL");
            System.out.println();
        }
    }

    private void registerButtonAction(String username, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            AlertBox.display("Passwords do not match!");
        } else if (ClientApplication.sendRegisterRequest(username, password)) {
            AlertBox.display("Successfully registered.");
            showMainMenu();
        } else {
            AlertBox.display("Username already taken!");
        }
    }

    private void foodAddButtonAction(double val1, double val2, double val3, double val4) {
        int int1 = (int) val1;
        int int2 = (int) val2;
        int int3 = (int) val3;
        int int4 = (int) val4;

        try {
            CO2Response co2Response = new CO2Response(0);
            if (val1 != 0) {
                co2Response = ClientApplication.sendAddFoodRequest("Salad", int1);
            }
            if (val2 != 0) {
                co2Response = ClientApplication.sendAddFoodRequest("Fruits", int2);
            }
            if (val3 != 0) {
                co2Response = ClientApplication.sendAddFoodRequest("Vegetarian Meat", int3);
            }
            if (val4 != 0) {
                co2Response = ClientApplication.sendAddFoodRequest("Vegan Meal", int4);
            }

            AlertBox.display("By choosing to have a Vegetarian Meal instead of a Meat meal, you reduced CO2 by: "
                    + co2Response.getCO2Reduction() + " grams. Good job!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred processing your request:\n" + e.getMessage());
        }
        showMainMenu();
    }

    /**
     * Action for adding local produce.
     *
     * @param weight - the weight of produce bought.
     * @param organic - true iff the produce is organic produce.
     */
    public void localProduceAction(int weight, boolean organic) {
        try {
            CO2Response res = ClientApplication.sendAddLocalProduceRequest(weight, organic);
            AlertBox.display("By choosing to buy from a Local Store reduced CO2 by: " + res.getCO2Reduction() + " grams. Good job!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred processing your request:\n" + e.getMessage());
        }
    }

    /**
     * Button for adding action for transport.
     *
     * @param travelType the type of travel done.
     * @param distance distance of using transportation.
     */
    public void transportAddButtonAction(TravelType travelType, int distance) {
        try {
            CO2Response res = ClientApplication.sendAddTransportRequest(travelType, distance);
            AlertBox.display("By choosing to travel by: "+ travelType +" you reduced CO2 by " + res.getCO2Reduction() + " grams. Good job!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred:\n" + e.getMessage());
        }
        showMainMenu();
    }

    /**
     * Button for adding action for homeTemp.
     *
     * @param temperature the home temperature parameter
     * @param duration the parameter for duration of lowing the temperature
     */
    public void homeTempAddButtonAction(int temperature, int duration) {
        try {
            CO2Response res = ClientApplication.sendAddHomeTempRequest(temperature, duration);
            AlertBox.display("By reducing the temperature you reduced CO2 by: " + res.getCO2Reduction() + " grams. Good job!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred:\n" + e.getMessage());
        }
        showMainMenu();
    }

    /**
     * Button for adding action for solar panel.
     *
     * @param area - m2 of solar panel added.
     * @param hoursSunlight - hours of sunlight these solar panels received.
     */
    public void solarPanelAction(int area, int hoursSunlight) {
        try {
            CO2Response res = ClientApplication.sendAddSolarPanelRequest(area, hoursSunlight);
            AlertBox.display("Your Solar Panel has reduced CO2 by: " + res.getCO2Reduction() + "grams. Good job!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred:\n" + e.getMessage());
        }
        showMainMenu();
    }

    /**
     * The tile for the string "username" and "co2reuduction".
     * @return The string
     */
    public GridPane nameTile() {
        GridPane nametile = new GridPane();
        nametile.setPadding(new Insets(10));
        nametile.setHgap(20);

        Label username = new Label("Name");
        username.setPrefWidth(150);
        Label co2reduc = new Label("Score");

        nametile.add(username, 0, 0);
        nametile.add(co2reduc, 1, 0);

        return nametile;
    }

    /**
     * leaderboard containing the username and the total co2 reduction.
     * @param user username.
     * @return a tile of the leaderboard.
     */
    public Button leaderboardTile(CO2 user) {
        GridPane gridtile = new GridPane();
        gridtile.setPadding(new Insets(10));
        gridtile.setHgap(20);

        Label cusername = new Label(user.getCUsername());
        cusername.setPrefWidth(150);
        Label co2reduc = new Label(Integer.toString(user.getCO2reduc()));

        gridtile.add(cusername, 0, 0);
        gridtile.add(co2reduc, 1, 0);

        Button tile = new Button("", gridtile);

        return tile;
    }

    /**
     * The VBox showing the leaderboard of an arraylist of users in CO2 class.
     * @param users username
     * @return return the vBox
     */
    public VBox leaderboard(ArrayList<CO2> users) {
        VBox vbox = new VBox();
        GridPane nametile = nameTile();
        vbox.getChildren().add(nametile);

        for (CO2 user: users) {
            Button tile = leaderboardTile(user);
            tile.setOnAction(e -> showUserPage(user));
            tile.setPrefWidth(300);
            vbox.getChildren().add(tile);
        }
        return vbox;
    }

    /**
     * leaderboard containing the username and the total co2 reduction.
     * @param user username.
     * @return a tile of the leaderboard.
     */
    public GridPane friendRequestTile(CO2 user) {
        GridPane gridtile = new GridPane();
        gridtile.setHgap(20);

        Label cusername = new Label(user.getCUsername());
        cusername.setPrefWidth(120);
        Label co2reduc = new Label(Integer.toString(user.getCO2reduc()));
        co2reduc.setPrefWidth(50);
        Button accept = new Button("Accept");
        accept.setOnAction(e -> {
            try {
                ClientApplication.respondToFriendRequest(user.getCUsername(), true);
                userPage();
            } catch (RestClientException e1) {
                e1.printStackTrace();
            }
        });
        accept.setPadding(new Insets(10));

        Button decline = new Button("Decline");
        decline.setOnAction(e -> {
            try {
                ClientApplication.respondToFriendRequest(user.getCUsername(), false);
                userPage();
            } catch (RestClientException e1) {
                e1.printStackTrace();
            }
        });
        decline.setPadding(new Insets(10));

        GridPane friendTile = new GridPane();
        friendTile.add(cusername,0,0);
        friendTile.add(co2reduc,1,0);

        Button friend = new Button("", friendTile);
        friend.setOnAction(e -> showUserPage(user));
        friend.setPadding(new Insets(10));

        gridtile.add(friend, 0, 0);
        gridtile.add(accept, 1, 0);
        gridtile.add(decline, 2, 0);

        return gridtile;
    }

    /**
     * This method makes a friend request table for the user stat page.
     * It puts the table on the right in the borderpane.
     * @param borderPane the borderpane in which to put the table.
     */
    public void makeFriendRequestTable(BorderPane borderPane) {
        VBox table  = friendRequestTable();
        table.setPadding(new Insets(100));
        borderPane.setRight(table);
    }


    /**
     * The VBox showing the friend requests of an arraylist of users in CO2 class.
     * @return return the vBox
     */
    public VBox friendRequestTable() {
        Leaderboard friendRequests = null;
        try {
            friendRequests = ClientApplication.getFriendRequests();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        friendRequests.sortLeaderboard();

        VBox vbox = new VBox();
        vbox.getChildren().add(new Label("Friend requests: "));

        for (CO2 user: friendRequests.getUsers()) {
            GridPane tile = friendRequestTile(user);
            tile.setPrefWidth(400);
            vbox.getChildren().add(tile);
        }
        return vbox;
    }

    /**
     * This method makes a list of all of your friends.
     * @param friends a list of all the friends.
     * @return a VBox with a representation of all the friends and an option to add new friends.
     */
    public VBox showFriends(ArrayList<CO2> friends) {
        ScrollPane scrollPane = new ScrollPane();

        VBox leaderboard = leaderboard(friends);
        scrollPane.setContent(leaderboard);

        Label friendLabel = new Label("Friends: ");
        Label addFriendLabel = new Label("\nAdd friend: ");
        TextField addFriendField = new TextField();
        Button addFriendButton = new Button("Add");

        addFriendButton.setOnAction(e -> {
            String friendUsername = addFriendField.getText();
            addFriendField.setText("");
            System.out.println(friendUsername);
            try {
                ClientApplication.sendAddFriendRequest(friendUsername);
            } catch (RestClientException e1) {
                e1.printStackTrace();
            }
        });

        HBox addFriendBox = new HBox();
        addFriendBox.getChildren().addAll(addFriendField, addFriendButton);

        VBox total = new VBox();
        total.getChildren().addAll(friendLabel, scrollPane, addFriendLabel, addFriendBox);
        return total;
    }

    /**
     * This method closes the program.
     */
    private void closeProgram() {
        ClientApplication.clearLoginData();
        window.close();
    }

    /**
     * This method logs you out.
     */
    private void logout() {
        Boolean answer = ConfirmBox.display("Logout",
            "Are you sure you want to logout?");
        if (answer) {
            ClientApplication.clearLoginData();
            loginPage();
        }
    }
}

