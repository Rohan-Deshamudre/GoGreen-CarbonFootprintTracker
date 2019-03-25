package gogreen.application.gui;

import gogreen.application.client.ClientApplication;
import gogreen.application.util.CarbonUtil;
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

import java.net.URISyntaxException;


public class GuiMain extends Application {

    private Stage window;
    private Scene loginScene;
    private int screenWidth;
    private int screenHeight;

    /**
     * Main method of the class, launches the application.
     *
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method starts the window.
     *
     */
    public void start(Stage primaryStage)throws Exception {
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
            usernameField.setText("");
            passwordField.setText("");
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

        Button share = new Button("Share");
        share.setMinSize(buttonWidth, buttonHeight);
        share.setOnAction(e -> showShare());
        buttons.add(share, 1, 1);

        //right part
        StackPane right = new StackPane();
        right.setAlignment(Pos.CENTER_RIGHT);

        Label scoreboard = new Label("Scoreboard");
        scoreboard.setMinSize(128, 320);

        right.getChildren().addAll(scoreboard);

        //setting up the window
        BorderPane menuPane = new BorderPane();
        menuBar(menuPane);
        menuPane.setCenter(buttons);
        menuPane.setRight(right);

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

        Button option2 = new Button("Bought from a biological store");
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

        Label titleLabel = new Label("Vegetarian Meal");
        Label sizeLabel = new Label("Size:");

        Label label1 = new Label("Salad");
        Label label2 = new Label("Fruits");
        Label label3 = new Label("Vegetarian Meat");
        Label label4 = new Label("Else");

        Button addButton = new Button("Add");
        addButton.setMinSize(100,50);
        addButton.setOnAction(e -> foodAddButtonAction(
                sizeSlider1.getValue(),
                sizeSlider2.getValue(),
                sizeSlider3.getValue(),
                sizeSlider4.getValue()));

        GridPane centerGrid = new GridPane();
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        centerGrid.add(titleLabel,0,0);
        centerGrid.add(label1,0,1);
        centerGrid.add(label2,0,2);
        centerGrid.add(label3,0,3);
        centerGrid.add(label4,0,4);

        centerGrid.add(sizeLabel,1,0);
        centerGrid.add(sizeSlider1,1,1);
        centerGrid.add(sizeSlider2,1,2);
        centerGrid.add(sizeSlider3,1,3);
        centerGrid.add(sizeSlider4,1,4);

        centerGrid.add(addButton,1,5);

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

        Button addButton = new Button("Add");
        addButton.setMinSize(100,50);
        //addButton.setOnAction(e -> );

        TextField weightField = new TextField();
        weightField.setMaxWidth(300);
        weightField.setPromptText("weight");

        CheckBox checkBox = new CheckBox("Organic");

        Label titleLabel = new Label("Local Store");
        Label weightLabel = new Label("Weight:");

        GridPane centerGrid = new GridPane();
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setPadding(new Insets(30));
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        centerGrid.add(titleLabel,0,0);
        centerGrid.add(weightLabel,0,1);
        centerGrid.add(weightField,1,1);
        centerGrid.add(checkBox,0,2);
        centerGrid.add(addButton,1,5);

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
        distanceField.setPromptText("distance");

        TextField timesAWeekField = new TextField();
        timesAWeekField.setMaxWidth(300);
        timesAWeekField.setPromptText("times a week");


        Label titleLabel = new Label("Bike Ride");
        Label distanceLabel = new Label("Distance:");
        Label timesAWeekLabel = new Label("Times a week:");

        Button addButton = new Button("Add");
        addButton.setMinSize(100,50);
        addButton.setOnAction(e -> {
            transportAddButtonAction(Integer.parseInt(distanceField.getText()), Integer.parseInt(timesAWeekField.getText()));
            distanceField.setText("");
            timesAWeekField.setText("");
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        centerGrid.add(titleLabel,1,0);
        centerGrid.add(distanceLabel,0,1);
        centerGrid.add(distanceField,1,1);
        centerGrid.add(timesAWeekLabel,0,2);
        centerGrid.add(timesAWeekField,1,2);
        centerGrid.add(addButton,1,5);

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
        distanceField.setPromptText("distance");

        TextField timesAWeekField = new TextField();
        timesAWeekField.setMaxWidth(300);
        timesAWeekField.setPromptText("times a week");


        Label titleLabel = new Label("Public Transport");
        Label distanceLabel = new Label("Distance:");
        Label timesAWeekLabel = new Label("Times a week:");

        Button addButton = new Button("Add");
        addButton.setMinSize(100,50);
        addButton.setOnAction(e -> {
            transportAddButtonAction(Integer.parseInt(distanceField.getText()), Integer.parseInt(timesAWeekField.getText()));
            distanceField.setText("");
            timesAWeekField.setText("");
        });

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        centerGrid.add(titleLabel,1,0);
        centerGrid.add(distanceLabel,0,1);
        centerGrid.add(distanceField,1,1);
        centerGrid.add(timesAWeekLabel,0,2);
        centerGrid.add(timesAWeekField,1,2);
        centerGrid.add(addButton,1,5);

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

        Button addButton = new Button("Add");
        addButton.setMinSize(100,50);
        //addButton.setOnAction(e -> );

        TextField distanceField = new TextField();
        distanceField.setMaxWidth(300);
        distanceField.setPromptText("reduction");

        TextField timesAWeekField = new TextField();
        timesAWeekField.setMaxWidth(300);
        timesAWeekField.setPromptText("hours");


        Label titleLabel = new Label("Home Temperature");
        Label distanceLabel = new Label("Temperature reduction:");
        Label timesAWeekLabel = new Label("Duration:");

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        centerGrid.add(titleLabel,1,0);
        centerGrid.add(distanceLabel,0,1);
        centerGrid.add(distanceField,1,1);
        centerGrid.add(timesAWeekLabel,0,2);
        centerGrid.add(timesAWeekField,1,2);
        centerGrid.add(addButton,1,5);

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

        Button addButton = new Button("Add");
        addButton.setMinSize(100,50);
        //addButton.setOnAction(e -> );

        TextField distanceField = new TextField();
        distanceField.setMaxWidth(300);
        distanceField.setPromptText("area");

        TextField timesAWeekField = new TextField();
        timesAWeekField.setMaxWidth(300);
        timesAWeekField.setPromptText("hours");


        Label titleLabel = new Label("Solar Panels");
        Label distanceLabel = new Label("Total area:");
        Label timesAWeekLabel = new Label("Sunlight:");

        GridPane centerGrid = new GridPane();
        centerGrid.setPadding(new Insets(30));
        centerGrid.setAlignment(Pos.CENTER);
        centerGrid.setHgap(30);
        centerGrid.setVgap(20);

        centerGrid.add(titleLabel,1,0);
        centerGrid.add(distanceLabel,0,1);
        centerGrid.add(distanceField,1,1);
        centerGrid.add(timesAWeekLabel,0,2);
        centerGrid.add(timesAWeekField,1,2);
        centerGrid.add(addButton,1,5);

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
        window.setTitle("Share");
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

        // CENTER
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(100, 100, 100, 100));
        grid.setVgap(8);
        grid.setHgap(10);

        // Make BorderPane layout
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(grid);
        menuBar(borderPane);

        // Stats
        Label stats = new Label("Stats");

        // Make scene
        grid.getChildren().addAll(
                stats
        );
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
            loginPage();
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

        pane.setTop(menuBar);
    }

    /**
     * With this slider you can select the size of your meal.
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
     * @param username - the username
     * @param password - the password
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

    private void foodAddButtonAction(double Val1, double Val2, double Val3, double Val4) {

        String message = "";
        int int1 = (int) Val1;
        int int2 = (int) Val2;
        int int3 = (int) Val3;
        int int4 = (int) Val4;

        try {
            if (Val1 != 0) {
                message = ClientApplication.sendAddFoodRequest("Salad", int1);
            }
            if (Val2 != 0) {
                message = ClientApplication.sendAddFoodRequest("Fruits", int2);
            }
            if (Val3 != 0) {
                message = ClientApplication.sendAddFoodRequest("Vegetarian Meat", int3);
            }
            if (Val4 != 0) {
                message = ClientApplication.sendAddFoodRequest("Else", int4);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
            message = "We are extremely sorry!"
                    + " There seems to be a technical issue in updating your Carbon Footprint.";
        }
        AlertBox.display(message);
        showMainMenu();
    }

    public void transportAddButtonAction(int distance, int timesaweek){
        String message="";
        try{
            message = ClientApplication.sendAddTransportRequest(distance, timesaweek);
        }
        catch(Exception e){
            e.printStackTrace();
            message="We are extremely sorry! There seems to be a technical issue in updating your Carbon Footprint.";
        }
        AlertBox.display(message);
        showMainMenu();

    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Closing the program",
                "Are you sure you want to exit?");
        if (answer) {
            ClientApplication.clearLoginData();
            window.close();
        }
    }

    private void logout() {
        Boolean answer = ConfirmBox.display("Logout",
                "Are you sure you want to logout?");
        if (answer) {
            AlertBox.display("You have logged out");
            loginPage();
        }
    }
}
