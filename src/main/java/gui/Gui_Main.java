package gui;

import client.ClientApplication;
import util.CarbonUtil;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
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
    public void start(Stage primaryStage)throws Exception{
        window = primaryStage;

        loginPage();
    }

    /**
     * This is the login page.
     *
     * @throws Exception: in case of an exception.
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
        loginScene = new Scene(borderPane, 600, 400);
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
        TextField passwordField = new TextField();
        passwordField.setPromptText("password");
        passwordField.setMaxWidth(300);
        GridPane.setConstraints(passwordLabel, 0, 2);
        GridPane.setConstraints(passwordField, 1, 2);

        // Repeat password
        Label passwordLabel1 = new Label("Password: ");
        TextField passwordField1 = new TextField();
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
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

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
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        //set up the page
        VBox center = new VBox();
        center.setAlignment(Pos.CENTER);

        Label foodTitle = new Label("Food");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll(CarbonUtil.FOOD_OPTION1,
                CarbonUtil.FOOD_OPTION2);
        //choiceBox.setValue("Ate a vegetarian meal");
        Label usernameLabel = new Label("Username: ");

        TextField usernameField = new TextField();
        usernameField.setPromptText("username");
        usernameField.setMaxWidth(300);

        Button addOption = new Button("Add");
//       addOption.setOnAction(e -> ConfirmBox.add("Changes to your CO2 reduction", choiceBox.getValue()));
        addOption.setOnAction(e -> {
            foodAddButtonAction(choiceBox.getValue(), usernameField.getText());
            usernameField.setText("");
            choiceBox.setValue("");
        });
        center.getChildren().addAll(foodTitle, choiceBox, usernameLabel, usernameField, addOption);

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
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

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
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

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
        loginScene = new Scene(borderPane, 600, 400);

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
     * NOT GUI
     *
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

    private void foodAddButtonAction(String choiceBoxValue, String username) {
        String message = "";
        try {
            message = ClientApplication.co2Add(choiceBoxValue,username);
        } catch (Exception e) {
            e.printStackTrace();
            message="We are extremely sorry! There seems to be a technical issue in updating your Carbon Footprint.";
        }
        AlertBox.display(message);
        showMainMenu();



    }

    private void closeProgram() {
        Boolean answer = ConfirmBox.display("Closing the program", "Are you sure you want to exit?");
        if(answer) {
            window.close();
        }
    }

    private void logout() {
        Boolean answer = ConfirmBox.display("Logout", "Are you sure you want to logout?");
        try {
            if(answer) {
                AlertBox.display("You have logged out");
                loginPage();
            }
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
