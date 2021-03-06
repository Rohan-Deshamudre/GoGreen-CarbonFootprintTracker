package gogreen.application.gui;

import gogreen.application.client.ClientApplication;
import gogreen.application.client.Leaderboard;
import gogreen.application.communication.AddTransportRequest.TravelType;
import gogreen.application.communication.CO2Response;
import gogreen.application.model.Achievement;
import gogreen.application.model.Badge;
import gogreen.application.model.CO2;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;

public class GuiMain extends Application {

    private Stage window;
    private Scene loginScene;
    private Scene mainmenuScene;
    private Scene foodScene;
    private Scene homeScene;
    private Scene transportScene;
    private Scene statsScene;
    private int screenWidth;
    private int screenHeight;
    private boolean nightmodeon;

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
        nightmodeon  = false;

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

        // layout
        VBox vert = new VBox();
        vert.setAlignment(Pos.CENTER);
        vert.setSpacing(10);
        vert.setPadding(new Insets(0, 0, 20, 0));

        // Logo
        Group topGroup = new Group();
        topGroup.setId("logo");
        ImageView image = new ImageView("images/GoGreenlogo.jpg");
        image.setFitWidth(450);
        image.setFitHeight(200);
        topGroup.getChildren().add(image);
        HBox logo = new HBox();
        logo.setId("hbox1");
        logo.setAlignment(Pos.CENTER);
        logo.getChildren().addAll(topGroup);

        // Enter username
        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setMaxWidth(300);

        Label usernameLabel = new Label("Username:   ");
        HBox textUsername = new HBox();
        textUsername.setAlignment(Pos.CENTER);
        textUsername.setPadding(new Insets(10, 0, 0, 0));
        textUsername.getChildren().addAll(usernameLabel, usernameField);

        // Enter password
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");
        passwordField.setMaxWidth(300);

        Label passwordLabel = new Label("Password:    ");
        HBox textPassword = new HBox();
        textPassword.setAlignment(Pos.CENTER);
        textPassword.getChildren().addAll(passwordLabel, passwordField);

        // Buttons
        Button registrationButton = new Button("Register");
        registrationButton.setFocusTraversable(false);
        registrationButton.setId("regisbutton");
        registrationButton.setOnAction(e -> {
            registrationPage();
        });

        Button loginButton = new Button();
        loginButton.setDefaultButton(true);
        loginButton.setId("loginbutton");
        loginButton.setMinSize(100, 50);
        loginButton.setOnAction(e -> {
            loginButtonAction(usernameField.getText(), passwordField.getText());
            usernameField.setText("");
            passwordField.setText("");
        });
        loginButton.setText("Login");

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        buttons.setPadding(new Insets(10, 0, 0, 0));
        buttons.getChildren().addAll(registrationButton, loginButton);

        // Make the scene
        String helloString = ClientApplication.getRequestHeroku();
        Label helloLabel = new Label(helloString);
        Label whitespace = new Label("");
        vert.getChildren().addAll(logo, whitespace, helloLabel,
                textUsername, textPassword, buttons);

        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10, 10, 10, 10));
        borderPane.setCenter(vert);

        loginScene = new Scene(borderPane, screenWidth, screenHeight);
        loginScene.getStylesheets().add("Login_css.css");
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
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        // TOP
        Group topGroup = new Group();
        Text goGreenText = new Text("Registration");
        goGreenText.setFont(Font.font("Verdana", FontWeight.BOLD, 70));
        topGroup.getChildren().add(goGreenText);

        // CENTER
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(250, 100, 100, 550));
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
        loginButton.setId("loginbutton");
        loginButton.setFocusTraversable(false);
        loginButton.setOnAction(e -> {
            loginPage();
        });
        Button registrationButton = new Button("Register");
        registrationButton.setId("regisbutton");
        registrationButton.setFocusTraversable(false);
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
        borderPane.setAlignment(topGroup, Pos.TOP_CENTER);
        borderPane.setCenter(grid);

        // Make scene
        grid.getChildren().addAll(
            usernameLabel, usernameField, passwordLabel,
            passwordLabel1, passwordField, passwordField1, buttons
        );
        loginScene = new Scene(borderPane, screenWidth, screenHeight);
        loginScene.getStylesheets().add("Login_css.css");
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

        AnchorPane buttons = new AnchorPane();
        buttons.setPadding(new Insets(20));
        int buttonWidth = 180;
        int buttonHeight = 180;

        Button food = new Button();
        food.setFocusTraversable(false);
        food.setId("button1");
        food.setShape(new Circle(4));
        food.setOnAction(e -> showFoodPage());
        food.setMinSize(buttonWidth, buttonHeight);
        AnchorPane.setTopAnchor(food, 150.0);
        AnchorPane.setRightAnchor(food, 460.0);
        AnchorPane.setLeftAnchor(food, 390.0);
        buttons.getChildren().add(food);
        Label food1 = new Label("Food");
        food1.setId("labelmain");
        AnchorPane.setTopAnchor(food1, 340.0);
        AnchorPane.setRightAnchor(food1, 460.0);
        AnchorPane.setLeftAnchor(food1, 460.0);
        buttons.getChildren().add(food1);

        Button homeE = new Button();
        homeE.setFocusTraversable(false);
        homeE.setId("button2");
        homeE.setShape(new Circle(4));
        homeE.setOnAction(e -> showHomeEnergy());
        homeE.setMinSize(buttonWidth, buttonHeight);
        AnchorPane.setTopAnchor(homeE, 150.0);
        AnchorPane.setRightAnchor(homeE, 260.0);
        AnchorPane.setLeftAnchor(homeE, 590.0);
        buttons.getChildren().add(homeE);
        Label home = new Label("Home");
        home.setId("labelmain");
        AnchorPane.setTopAnchor(home, 340.0);
        AnchorPane.setRightAnchor(home, 260.0);
        AnchorPane.setLeftAnchor(home, 660.0);
        buttons.getChildren().add(home);

        Button transport = new Button();
        transport.setFocusTraversable(false);
        transport.setId("button3");
        transport.setShape(new Circle(4));
        transport.setOnAction(e -> showTransportPage());
        transport.setMinSize(buttonWidth, buttonHeight);
        AnchorPane.setTopAnchor(transport, 400.0);
        AnchorPane.setRightAnchor(transport, 460.0);
        AnchorPane.setLeftAnchor(transport, 390.0);
        buttons.getChildren().add(transport);
        Label transport1 = new Label("Transport");
        transport1.setId("labelmain");
        AnchorPane.setTopAnchor(transport1, 590.0);
        AnchorPane.setRightAnchor(transport1, 490.0);
        AnchorPane.setLeftAnchor(transport1, 440.0);
        buttons.getChildren().add(transport1);

        Button stats = new Button();
        stats.setFocusTraversable(false);
        stats.setId("button4");
        stats.setShape(new Circle(4));
        stats.setMinSize(buttonWidth, buttonHeight);
        stats.setOnAction(e -> userPage());
        AnchorPane.setTopAnchor(stats, 400.0);
        AnchorPane.setRightAnchor(stats, 250.0);
        AnchorPane.setLeftAnchor(stats, 600.0);
        buttons.getChildren().add(stats);
        Label stat = new Label("Stats");
        stat.setId("labelmain");
        AnchorPane.setTopAnchor(stat, 590.0);
        AnchorPane.setRightAnchor(stat, 250.0);
        AnchorPane.setLeftAnchor(stat, 670.0);
        buttons.getChildren().add(stat);


        // Leaderboard
        Leaderboard leaderboard = null;
        try {
            leaderboard = ClientApplication.sendGetFriendListRequest();
            System.out.println(leaderboard);
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        CO2 user = null;
        try {
            user = ClientApplication.sendGetUserStatsRequest();
        } catch (RestClientException e) {
            e.printStackTrace();
        }

        leaderboard.getUsers().add(user);
        leaderboard.sortLeaderboard();

        // Check for getting to top of the leaderboard achievement.
        boolean gotten = ClientApplication.checkAchievement1(leaderboard);
        if (gotten) {
            ClientApplication.changeAchievements(ClientApplication.getUser(), 1);
        }
        // Check for 1,000 points achievement
        gotten = ClientApplication.checkAchievement2();
        if (gotten) {
            ClientApplication.changeAchievements(ClientApplication.getUser(), 2);
        }
        // Check for 10,000 points achievement
        gotten = ClientApplication.checkAchievement3();
        if (gotten) {
            ClientApplication.changeAchievements(ClientApplication.getUser(), 3);
        }



        VBox scoreboard = leaderboard(leaderboard.getUsers());
        scoreboard.setId("vbox3");
        scoreboard.setPadding(new Insets(10, 200, 10, 10));
        scoreboard.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setMargin(scoreboard, new Insets(10, 10, 10, 10));

        //setting up the window
        BorderPane menuPane = new BorderPane();
        menuBar(menuPane);
        menuPane.setCenter(buttons);
        menuPane.setRight(scoreboard);

        //setting up the scene
        mainmenuScene = new Scene(menuPane, screenWidth, screenHeight);
        if (nightmodeon) {
            mainmenuScene.getStylesheets().add("NightMode_css.css");
        } else {
            mainmenuScene.getStylesheets().add("MainMenu_css.css");
        }
        window.setScene(mainmenuScene);
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
        center.setPadding(new Insets(20));
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10);

        // Set up buttons
        HBox buttons = new HBox();
        buttons.setFocusTraversable(false);

        buttons.setAlignment(Pos.CENTER);
        buttons.setPrefWidth(180);
        buttons.setPrefHeight(180);
        buttons.setSpacing(20);

        Button option1 = new Button("Ate a vegetarian meal");
        option1.setFocusTraversable(false);
        option1.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option1.setId("buttonop1");
        option1.setOnAction(e -> veggieMealPage());

        Button option2 = new Button("Bought from a biological store");
        option2.setFocusTraversable(false);
        option2.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option2.setId("buttonop2");
        option2.setOnAction(e -> localStorePage());

        buttons.getChildren().addAll(option1, option2);

        Label foodTitle = new Label("Food");
        foodTitle.setId("labeltitle");

        center.getChildren().addAll(foodTitle, buttons);

        //set up border pane
        BorderPane foodPane = new BorderPane();
        menuBar(foodPane);
        foodPane.setCenter(center);

        //set up the scene
        foodScene = new Scene(foodPane, screenWidth, screenHeight);
        if (nightmodeon) {
            foodScene.getStylesheets().add("NightMode_css.css");
        } else {
            foodScene.getStylesheets().add("Food_css.css");
        }
        window.setScene(foodScene);
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

        String sliderStyleFormat =
                "-slider-track-color: linear-gradient(to right, -slider-filled-track-color 0%%, "
                        + "-slider-filled-track-color %1$f%%, -fx-base %1$f%%, -fx-base 100%%);";

        Slider sizeSlider1 = sizeSlider();
        sizeSlider1.styleProperty().bind(Bindings.createStringBinding(() -> {
            double percentage = (sizeSlider1.getValue()
                    - sizeSlider1.getMin()) / (sizeSlider1.getMax()
                    - sizeSlider1.getMin()) * 100.0 ;
            return String.format(sliderStyleFormat, percentage);
        }, sizeSlider1.valueProperty(), sizeSlider1.minProperty(), sizeSlider1.maxProperty()));

        Slider sizeSlider2 = sizeSlider();
        sizeSlider2.styleProperty().bind(Bindings.createStringBinding(() -> {
            double percentage = (sizeSlider2.getValue()
                    - sizeSlider2.getMin()) / (sizeSlider2.getMax()
                    - sizeSlider2.getMin()) * 100.0 ;
            return String.format(sliderStyleFormat, percentage);
        }, sizeSlider2.valueProperty(), sizeSlider2.minProperty(), sizeSlider2.maxProperty()));

        Slider sizeSlider3 = sizeSlider();
        sizeSlider3.styleProperty().bind(Bindings.createStringBinding(() -> {
            double percentage = (sizeSlider3.getValue()
                    - sizeSlider3.getMin()) / (sizeSlider3.getMax()
                    - sizeSlider3.getMin()) * 100.0 ;
            return String.format(sliderStyleFormat, percentage);
        }, sizeSlider3.valueProperty(), sizeSlider3.minProperty(), sizeSlider3.maxProperty()));

        Slider sizeSlider4 = sizeSlider();
        sizeSlider4.styleProperty().bind(Bindings.createStringBinding(() -> {
            double percentage = (sizeSlider4.getValue()
                    - sizeSlider4.getMin()) / (sizeSlider4.getMax()
                    - sizeSlider4.getMin()) * 100.0 ;
            return String.format(sliderStyleFormat, percentage);
        }, sizeSlider4.valueProperty(), sizeSlider4.minProperty(), sizeSlider4.maxProperty()));




        Button addButton = new Button("Add");
        addButton.setId("buttonaddfood");
        addButton.setFocusTraversable(false);
        addButton.setMinSize(100, 50);

        addButton.setOnAction(e -> {
            boolean gotten = ClientApplication.checkAchievement(7);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 7);
            }

            foodAddButtonAction(
                sizeSlider1.getValue(),
                sizeSlider2.getValue(),
                sizeSlider3.getValue(),
                sizeSlider4.getValue());
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        Label titleLabel = new Label("Vegetarian Meal");
        titleLabel.setId("labelpage");
        Label sizeLabel = new Label("Size:");
        sizeLabel.setId("labelpage");
        Label label1 = new Label("Salad");
        label1.setId("labelpage");
        Label label2 = new Label("Fruits");
        label2.setId("labelpage");
        Label label3 = new Label("Vegetarian Meat");
        label3.setId("labelpage");
        Label label4 = new Label("Vegan Meal");
        label4.setId("labelpage");

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
        foodScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            foodScene.getStylesheets().add("NightMode_css.css");
        } else {
            foodScene.getStylesheets().add("veggieMealPage_css.css");
        }
        window.setScene(foodScene);
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
        weightField.setPromptText("weight(gram)");

        CheckBox checkBox = new CheckBox("Organic");

        Button addButton = new Button("Add");
        addButton.setId("buttonaddlocal");
        addButton.setFocusTraversable(false);
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            boolean gotten = ClientApplication.checkAchievement(8);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 8);
            }
            ClientApplication.checkAchievement9(checkBox.isSelected());

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
        titleLabel.setId("labelpage");
        Label weightLabel = new Label("Weight:");
        weightLabel.setId("labelpage");

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
        foodScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            foodScene.getStylesheets().add("NightMode_css.css");
        } else {
            foodScene.getStylesheets().add("localStorePage_css.css");
        }
        window.setScene(foodScene);
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
        buttons.setFocusTraversable(false);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPrefWidth(180);
        buttons.setPrefHeight(180);
        buttons.setSpacing(20);

        Button option1 = new Button("Bike");
        option1.setFocusTraversable(false);
        option1.setId("buttonop3");
        option1.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option1.setOnAction(e -> bikeRidePage());

        Button option2 = new Button("Public transport");
        option2.setFocusTraversable(false);
        option2.setId("buttonop4");
        option2.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option2.setOnAction(e -> publicTransportPage());

        buttons.getChildren().addAll(option1, option2);

        Label transportTitle = new Label("Transport");
        transportTitle.setId("labeltitle");

        center.getChildren().addAll(transportTitle, buttons);

        //set up border pane
        BorderPane foodPane = new BorderPane();
        menuBar(foodPane);
        foodPane.setCenter(center);

        //set up the scene
        transportScene = new Scene(foodPane, screenWidth, screenHeight);
        if (nightmodeon) {
            transportScene.getStylesheets().add("NightMode_css.css");
        } else {
            transportScene.getStylesheets().add("Transport_css.css");
        }
        window.setScene(transportScene);
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

        Button addButton = new Button("Add");
        addButton.setId("buttonaddbike");
        addButton.setFocusTraversable(false);
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            boolean gotten = ClientApplication.checkAchievement(12);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 12);
            }
            transportAddButtonAction(TravelType.BIKE, Integer.parseInt(distanceField.getText()));
            distanceField.setText("");
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);
        Label titleLabel = new Label("Bike Ride");
        titleLabel.setId("labelpage");
        Label distanceLabel = new Label("Total distance (km):");
        distanceLabel.setId("labelpage");

        centerGrid.add(titleLabel, 1, 0);
        centerGrid.add(distanceLabel, 0, 1);
        centerGrid.add(distanceField, 1, 1);
        centerGrid.add(addButton, 1, 5);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(centerGrid);

        //set up the scene
        transportScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            transportScene.getStylesheets().add("NightMode_css.css");
        } else {
            transportScene.getStylesheets().add("bikeRidePage_css.css");
        }
        window.setScene(transportScene);
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
        addButton.setId("buttonaddtrans");
        addButton.setFocusTraversable(false);
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            boolean gotten = ClientApplication.checkAchievement(13);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 13);
            }
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
        titleLabel.setId("labelpage");
        Label distanceLabel = new Label("Total distance (km):");
        distanceLabel.setId("labelpage");

        centerGrid.add(titleLabel, 1, 0);
        centerGrid.add(distanceLabel, 0, 1);
        centerGrid.add(distanceField, 1, 1);
        centerGrid.add(addButton, 1, 5);

        //set up border pane
        BorderPane borderPane = new BorderPane();
        menuBar(borderPane);
        borderPane.setCenter(centerGrid);

        //set up the scene
        transportScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            transportScene.getStylesheets().add("NightMode_css.css");
        } else {
            transportScene.getStylesheets().add("publicTransportPage_css.css");
        }
        window.setScene(transportScene);
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
        buttons.setFocusTraversable(false);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPrefWidth(180);
        buttons.setPrefHeight(180);
        buttons.setSpacing(20);

        Button option1 = new Button("Home Temperature");
        option1.setFocusTraversable(false);
        option1.setId("buttonop5");
        option1.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option1.setOnAction(e -> homeTemperaturePage());

        Button option2 = new Button("Solar Panels");
        option2.setFocusTraversable(false);

        option2.setId("buttonop6");
        option2.setMinSize(buttons.getPrefWidth(), buttons.getPrefHeight());
        option2.setOnAction(e -> solarPanelPage());

        buttons.getChildren().addAll(option1, option2);

        Label energyTitle = new Label("Home energy");
        energyTitle.setId("labeltitle");

        center.getChildren().addAll(energyTitle, buttons);

        //set up border pane
        BorderPane energyPane = new BorderPane();
        menuBar(energyPane);
        energyPane.setCenter(center);

        //set up the scene
        homeScene = new Scene(energyPane, screenWidth, screenHeight);
        if (nightmodeon) {
            homeScene.getStylesheets().add("NightMode_css.css");
        } else {
            homeScene.getStylesheets().add("HomeEnergy_css.css");
        }
        window.setScene(homeScene);
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
        addButton.setId("buttonaddtem");
        addButton.setFocusTraversable(false);
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            boolean gotten = ClientApplication.checkAchievement(10);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 10);
            }

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
        titleLabel.setId("labelpage");
        Label temperatureLabel = new Label("Temperature reduction:");
        temperatureLabel.setId("labelpage");
        Label durationLabel = new Label("Duration:");
        durationLabel.setId("labelpage");

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
        homeScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            homeScene.getStylesheets().add("NightMode_css.css");
        } else {
            homeScene.getStylesheets().add("homeTemperaturePage_css.css");
        }
        window.setScene(homeScene);
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
        addButton.setFocusTraversable(false);
        addButton.setId("buttonaddpanel");
        addButton.setMinSize(100, 50);
        addButton.setOnAction(e -> {
            boolean gotten = ClientApplication.checkAchievement(11);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 11);
            }

            solarPanelAction(Integer.parseInt(areaField.getText()),
                Integer.parseInt(hoursSunlightField.getText()));
            areaField.setText("");
            hoursSunlightField.setText("");
        });

        Label titleLabel = new Label("Solar Panels");
        titleLabel.setId("labelpage");
        Label areaLabel = new Label("Total area:");
        areaLabel.setId("labelpage");
        Label hoursSunlightLabel = new Label("Sunlight:");
        hoursSunlightLabel.setId("labelpage");

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
        homeScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            homeScene.getStylesheets().add("NightMode_css.css");
        } else {
            homeScene.getStylesheets().add("solarPanelPage_css.css");
        }
        window.setScene(homeScene);
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
        grid.setId("griduser");
        grid.setPadding(new Insets(100, 20, 100, 50));
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
        allFriends.setId("vboxfriends");
        allFriends.setPadding(new Insets(100, 40, 100, 150));

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

        // Check for friends achievements
        boolean gotten = ClientApplication.checkAchievement4(leaderboard);
        if (gotten) {
            ClientApplication.changeAchievements(ClientApplication.getUser(), 4);
        }
        gotten = ClientApplication.checkAchievement5(leaderboard);
        if (gotten) {
            ClientApplication.changeAchievements(ClientApplication.getUser(), 5);
        }

        Label stats = new Label("Stats");
        grid.add(stats, 0, 0);
        Label username = new Label("Username:");
        grid.add(username, 0, 1);
        Label usernameValue = new Label(user.getCUsername());
        grid.add(usernameValue, 1, 1);
        Label totalCO2 = new Label("Total CO2 reduction:");
        grid.add(totalCO2, 0, 2);
        Label totalCO2Value = new Label(Integer.toString(user.getCO2reduc()));
        grid.add(totalCO2Value, 1, 2);
        Label co2food = new Label("CO2 reduction for food:");
        grid.add(co2food, 0, 3);
        Label co2foodValue = new Label(Integer.toString(user.getCO2food()));
        grid.add(co2foodValue, 1, 3);
        Label co2transport = new Label("CO2 reduction for transport:");
        grid.add(co2transport, 0, 4);
        Label co2transportValue = new Label(Integer.toString(user.getCO2transport()));
        grid.add(co2transportValue, 1, 4);
        Label co2energy = new Label("CO2 reduction for energy:");
        grid.add(co2energy, 0, 5);
        Label co2energyValue = new Label(Integer.toString(user.getCO2energy()));
        grid.add(co2energyValue, 1, 5);
        Label badgeLabel = new Label("Badge:");
        grid.add(badgeLabel, 0, 6);
        String url = Badge.getBadge(user.getCO2reduc());
        ImageView badge = new ImageView(url);
        badge.setFitHeight(20);
        badge.setFitWidth(20);
        HBox badgeInfo = new HBox();
        grid.add(badgeInfo, 1, 6);
        Label badgeTitle = new Label(" " + Badge.getTitle(user.getCO2reduc()));
        badgeInfo.getChildren().addAll(badge, badgeTitle);
        Button viewAchievements = new Button("Achievements");
        viewAchievements.setId("buttonachieve");
        grid.add(viewAchievements, 0, 7);
        viewAchievements.setOnAction(e -> showAchievements());

        stats.setId("label1");
        username.setId("label1");
        usernameValue.setId("label1");
        totalCO2.setId("label1");
        totalCO2Value.setId("label1");
        co2food.setId("label1");
        co2foodValue.setId("label1");
        co2transport.setId("label1");
        co2transportValue.setId("label1");
        co2energy.setId("label1");
        co2energyValue.setId("label1");
        badgeLabel.setId("label1");
        badge.setId("image1");

        loginScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            loginScene.getStylesheets().add("NightMode_css.css");
        } else {
            loginScene.getStylesheets().add("Stats_css.css");
        }
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
        grid.setId("grid1");
        grid.setPadding(new Insets(100));
        grid.setVgap(8);
        grid.setHgap(10);


        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(grid);
        menuBar(borderPane);

        Label stats = new Label("Stats");
        grid.add(stats, 0, 0);
        Label username = new Label("Username:");
        grid.add(username, 0, 3);
        Label usernameValue = new Label(user.getCUsername());
        grid.add(usernameValue, 4, 3);
        Label totalCO2 = new Label("Total CO2 reduction:");
        grid.add(totalCO2, 0, 6);
        Label totalCO2Value = new Label(Integer.toString(user.getCO2reduc()));
        grid.add(totalCO2Value, 4, 6);
        Label co2food = new Label("CO2 reduction for food:");
        grid.add(co2food, 0, 9);
        Label co2foodValue = new Label(Integer.toString(user.getCO2food()));
        grid.add(co2foodValue, 4, 9);
        Label co2transport = new Label("CO2 reduction for transport:");
        grid.add(co2transport, 0, 12);
        Label co2transportValue = new Label(Integer.toString(user.getCO2transport()));
        grid.add(co2transportValue, 4, 12);
        Label co2energy = new Label("CO2 reduction for energy:");
        grid.add(co2energy, 0, 15);
        Label co2energyValue = new Label(Integer.toString(user.getCO2energy()));
        grid.add(co2energyValue, 4, 15);
        Label badgeLabel = new Label("Badge:");
        grid.add(badgeLabel, 0, 18);
        String url = Badge.getBadge(user.getCO2reduc());
        ImageView badge = new ImageView(url);
        badge.setFitHeight(20);
        badge.setFitWidth(20);
        HBox badgeInfo = new HBox();
        grid.add(badgeInfo, 4, 18);
        Label badgeTitle = new Label(" " + Badge.getTitle(user.getCO2reduc()));
        badgeInfo.getChildren().addAll(badge, badgeTitle);
        Button viewAchievements = new Button("Achievements");
        viewAchievements.setId("buttonachieve");
        grid.add(viewAchievements, 0, 21);
        viewAchievements.setOnAction(e -> showAchievements(user));

        stats.setId("label1");
        username.setId("label1");
        usernameValue.setId("label1");
        totalCO2.setId("label1");
        totalCO2Value.setId("label1");
        co2food.setId("label1");
        co2foodValue.setId("label1");
        co2transport.setId("label1");
        co2transportValue.setId("label1");
        co2energy.setId("label1");
        co2energyValue.setId("label1");

        statsScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            statsScene.getStylesheets().add("NightMode_css.css");
        } else {
            statsScene.getStylesheets().add("Stats_css.css");
        }
        window.setScene(statsScene);
        window.show();
    }

    /**
     * This is the menu bar for the top corner.
     *
     * @param pane - the window in which to display the menu bar.
     */
    public void menuBar(BorderPane pane) {
        MenuItem logout = new MenuItem("Logout");
        logout.setOnAction(e -> logout());

        String nightText = "Night mode";
        if (nightmodeon) {
            nightText = "Day mode";
        }
        MenuItem nightMode = new MenuItem(nightText);
        nightMode.setOnAction(e -> {
            nightmodeon = !nightmodeon;
            boolean gotten = ClientApplication.checkAchievement(0);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 0);
            }
            showMainMenu();
        });
        Menu settings = new Menu("Settings");
        settings.getItems().addAll(nightMode, logout);

        MenuBar menuBar = new MenuBar();
        menuBar.setId("menuBar1");
        menuBar.getMenus().addAll(settings);

        Button homeButton = new Button("Home");
        homeButton.setFocusTraversable(false);

        homeButton.setId("buttonhome");
        homeButton.setMinSize(10, 10);
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
            AlertBox.display("Wrong username/password combination. Please try again.",
                    "Something went wrong");
            System.out.println("LOGIN UNSUCCESSFUL");
            System.out.println();
        }
    }

    private void registerButtonAction(String username, String password, String passwordConfirm) {
        if (!password.equals(passwordConfirm)) {
            AlertBox.display("Passwords do not match!", "Something went wrong");
        } else if (ClientApplication.sendRegisterRequest(username, password)) {
            AlertBox.display("Successfully registered.", "Success");
            showMainMenu();
        } else if (username.equals("") || password.equals("") || passwordConfirm.equals("")) {
            AlertBox.display("One or multiple fields have not been filled in!", "Empty field(s)");
        } else {
            AlertBox.display("Username already taken!", "Something went wrong");
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

            AlertBox.display("By choosing to have a Vegetarian Meal instead of a Meat meal,"
                    + " you reduced CO2 by: "
                    + co2Response.getCO2Reduction() + " grams. Good job!", "Success!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred processing your request:\n" + e.getMessage(),
                    "Something went wrong");
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
            AlertBox.display("By choosing to buy from a Local Store reduced CO2 by: "
                    + res.getCO2Reduction() + " grams. Good job!", "Success!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred processing your request:\n" + e.getMessage(),
                    "Something went wrong");
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
            AlertBox.display("By choosing to travel by: " + travelType + " you reduced CO2 by "
                    + res.getCO2Reduction() + " grams. Good job!", "Success!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred:\n" + e.getMessage(), "Something went wrong");
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
            AlertBox.display("By reducing the temperature you reduced CO2 by: "
                    + res.getCO2Reduction() + " grams. Good job!", "Success!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred:\n" + e.getMessage(), "");
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
            AlertBox.display("Your Solar Panel has reduced CO2 by: "
                    + res.getCO2Reduction() + "grams. Good job!", "Success!");
        } catch (RestClientException e) {
            AlertBox.display("An error occurred:\n" + e.getMessage(), "Something went wrong");
        }
        showMainMenu();
    }

    /**
     * The tile for the string "username" and "co2reuduction".
     * @return The string
     */
    public GridPane nameTile() {
        GridPane nametile = new GridPane();
        nametile.setId("grid2");
        nametile.setPadding(new Insets(10, 20, 10, 10));
        nametile.setHgap(20);

        Label username = new Label("   Name");
        username.setId("label1");
        username.setPrefWidth(150);
        Label co2reduc = new Label("   Score");
        co2reduc.setId("label1");

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
        gridtile.getStylesheets().add("Leaderboard_css.css");
        gridtile.setId("grid1");

        gridtile.setPadding(new Insets(5, 10, 5, 10));
        gridtile.setHgap(20);

        Label cusername = new Label(user.getCUsername());
        cusername.setId("labelleaderboard");
        cusername.setPrefWidth(150);

        Label co2reduc = new Label(" " + Integer.toString(user.getCO2reduc()));
        co2reduc.setId("labelleaderboard");

        String url = Badge.getBadge(user.getCO2reduc());
        ImageView badge = new ImageView(url);
        badge.setFitHeight(25);
        badge.setFitWidth(25);

        HBox nameBox = new HBox();
        nameBox.getChildren().addAll(badge, co2reduc);

        gridtile.add(cusername, 0, 0);
        gridtile.add(nameBox, 1, 0);

        Button tile = new Button("", gridtile);
        tile.setId("buttonleaderboard");
        tile.setFocusTraversable(false);

        return tile;
    }

    /**
     * The VBox showing the leaderboard of an arraylist of users in CO2 class.
     * @param users username
     * @return return the vBox
     */
    public VBox leaderboard(ArrayList<CO2> users) {
        VBox vbox = new VBox();
        vbox.setId("vBoxleader");
        GridPane nametile = nameTile();
        vbox.getChildren().add(nametile);
        vbox.setPadding(new Insets(10));

        for (CO2 user: users) {
            Button tile = leaderboardTile(user);
            tile.setId("buttontile");
            tile.setFocusTraversable(false);
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

        String url = Badge.getBadge(user.getCO2reduc());
        ImageView badge = new ImageView(url);
        badge.setFitHeight(25);
        badge.setFitWidth(25);
        Label cusername = new Label(user.getCUsername());

        cusername.setId("labeluser");
        HBox nameBox = new HBox();
        nameBox.setPrefWidth(120);
        nameBox.getChildren().addAll(cusername, badge);

        Label co2reduc = new Label(Integer.toString(user.getCO2reduc()));
        co2reduc.setId("labelco2reduc");
        co2reduc.setPrefWidth(50);
        Button accept = new Button("Accept");
        accept.setId("buttonaccept");
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
        decline.setId("buttondecline");
        decline.setOnAction(e -> {
            // Check for achievement
            boolean gotten = ClientApplication.checkAchievement(6);
            if (gotten) {
                ClientApplication.changeAchievements(ClientApplication.getUser(), 6);
            }
            try {
                ClientApplication.respondToFriendRequest(user.getCUsername(), false);
                userPage();
            } catch (RestClientException e1) {
                e1.printStackTrace();
            }
        });
        decline.setPadding(new Insets(10));

        GridPane friendTile = new GridPane();
        friendTile.add(nameBox,0,0);
        friendTile.add(co2reduc,1,0);

        Button friend = new Button("", friendTile);
        friend.setId("buttonfriend");
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
        table.setPadding(new Insets(100, 100, 100, 50));
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
        Label friendrequest = new Label("Friend requests");
        friendrequest.setId("label1");
        vbox.getChildren().add(friendrequest);

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

        scrollPane.setMaxWidth(340);
        scrollPane.setId("friends");
        scrollPane.setPrefHeight(350);

        VBox leaderboard = leaderboard(friends);
        leaderboard.setPadding(new Insets(10, 20, 10, 20));
        leaderboard.setMinWidth(340);
        leaderboard.setId("vboxleader");
        scrollPane.setContent(leaderboard);

        Label addFriendLabel = new Label("\nAdd friend: ");
        addFriendLabel.setId("labelfriend");
        TextField addFriendField = new TextField();
        addFriendField.setId("field1");
        Button addFriendButton = new Button("Add");
        addFriendButton.setId("addFriend");

        addFriendButton.setOnAction(e -> {
            String friendUsername = addFriendField.getText();
            addFriendField.setText("");
            try {
                ClientApplication.sendAddFriendRequest(friendUsername);
            } catch (RestClientException e1) {
                e1.printStackTrace();
            }

            addFriendField.requestFocus();
        });

        TextField removeFriendField = new TextField();
        removeFriendField.setId("field1");
        Button removeFriendButton = new Button("Remove");
        removeFriendButton.setId("buttonfriend");

        removeFriendButton.setOnAction(e -> {
            String friendUsername = removeFriendField.getText();
            removeFriendField.setText("");
            Boolean answer = ConfirmBox.display("Remove Friend",
                    "Are you sure you want to remove "
                            + friendUsername + " as your friend?");
            if (answer) {
                try {
                    boolean check = ClientApplication.sendRemoveFriendRequest(friendUsername);
                    if (check) {
                        ConfirmBox.display("Remove friend", "Your friend has been removed");
                    } else {
                        ConfirmBox.display("Remove friend went wrong",
                                "This person is not in your friend list");
                    }
                } catch (RestClientException e1) {
                    e1.printStackTrace();
                }
            }
            userPage();
        });

        Label removeFriendLabel = new Label("\nRemove friend: ");
        removeFriendLabel.setId("labelfriend");

        HBox addFriendBox = new HBox();
        addFriendBox.setSpacing(10);
        addFriendBox.getChildren().addAll(addFriendField, addFriendButton);

        HBox removeFriendBox = new HBox();
        removeFriendBox.setSpacing(10);
        removeFriendBox.getChildren().addAll(removeFriendField, removeFriendButton);

        Label friendLabel = new Label("Friends: ");
        friendLabel.setId("label1");
        VBox total = new VBox();
        total.setId("totalvbox");
        total.getChildren().addAll(friendLabel, scrollPane,
                addFriendLabel, addFriendBox, removeFriendLabel, removeFriendBox);
        return total;
    }


    /**
     * Gets the user information from the server and calls
     * showAchievements(CO2 user).
     * @throws RestClientException when retrieving the user information fails
     */
    public void showAchievements() {
        CO2 user = null;
        try {
            user = ClientApplication.sendGetUserStatsRequest();
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        showAchievements(user);
    }

    /**
     * Opens a window that contains information about
     * the users achievements.
     */
    public void showAchievements(CO2 user) {
        window.setTitle("Achievements");

        // CENTER
        GridPane grid = new GridPane();
        grid.setId("gridachieve");
        grid.setPadding(new Insets(100));
        grid.setVgap(0);
        grid.setHgap(20);
        grid.setAlignment(Pos.CENTER);


        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setLeft(grid);
        menuBar(borderPane);

        Label username = new Label("Username:");
        username.setPadding(new Insets(50,10,0,10));
        username.setId("labeluser");
        Label usernameValue = new Label(user.getCUsername());
        usernameValue.setPadding(new Insets(50,10,0,10));
        usernameValue.setId("labeluser");
        grid.add(username, 0, 0);
        grid.add(usernameValue, 1, 0);

        int itr = 0;
        int row = 1;
        int max = Achievement.getTotalAchievements();
        while (itr < max) {
            for (int j = 0; j < 7; j++ ) {
                if (itr < max) {
                    achievementTile(user, itr + 1, grid, j, row);
                }
                itr++;
            }
            row += 3;
        }

        loginScene = new Scene(borderPane, screenWidth, screenHeight);
        if (nightmodeon) {
            loginScene.getStylesheets().add("NightMode_css.css");
        } else {
            loginScene.getStylesheets().add("Achievements_css.css");
        }
        // Show window
        window.setScene(loginScene);
        window.show();
    }

    /**
     * Tile for the achievement page.
     * @param user the user.
     * @param id achievement id.
     * @param grid the grid on the page.
     * @param x1 x coordinate on the grid.
     * @param y1 y coordinate on the grid.
     */
    private void achievementTile(CO2 user, int id, GridPane grid, int x1, int y1) {
        Label name = new Label(Achievement.getName(id));
        name.setId("labelname");
        name.setPrefWidth(120);
        name.setPrefHeight(180);
        name.setWrapText(true);
        name.setPadding(new Insets(5));


        VBox nameBox = new VBox();
        nameBox.setMinHeight(30);

        Label description = new Label(Achievement.getDescription(id));
        description.setPrefWidth(120);
        description.setPrefHeight(200);
        description.setWrapText(true);
        description.setPadding(new Insets(5));
        description.setId("labeldescrip");

        if (user.getAchievement().charAt(id - 1) == '1') {
            name.setId("labelnamehave");
            description.setId("labeldescriphave");
        }

        grid.add(nameBox, x1, y1);
        grid.add(name, x1, y1 + 1);
        grid.add(description, x1 , y1 + 2);
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

