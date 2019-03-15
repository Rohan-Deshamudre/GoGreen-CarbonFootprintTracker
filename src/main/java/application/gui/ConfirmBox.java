package application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {

    private static boolean answer;

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);

        Label label = new Label(message);

        StackPane close = new StackPane();
        close.getChildren().addAll(label);
        close.setAlignment(Pos.CENTER);

        Button yes = new Button("yes");
        yes.setOnAction(e -> {
            answer = true;
            window.close();
        });
        Button no = new Button("no");
        no.setOnAction(e -> {
            answer = false;
            window.close();
        });

        HBox layout = new HBox(20);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.getChildren().addAll(yes, no);
        layout.setAlignment(Pos.CENTER);

        BorderPane confirm = new BorderPane();
        confirm.setPadding(new Insets(10, 10, 10, 10));
        confirm.setTop(close);
        confirm.setCenter(layout);

        Scene scene = new Scene(confirm);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }

    public static void add(String title, String option) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(350);

        Label label = new Label("To add in: '" + option + "', please type in your username");

        TextField username = new TextField();
        username.setPromptText("username");

        Button add = new Button("add");
        add.setOnAction(e -> {
            //call method in ServerApplication to update the database with username as a parameter, application.ServerApplication.method(username.getText();
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, username, add);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
