package gui;

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

    /**
     * Displays the ConfirmBox and the message on it.
     * @param title title of the ConfirmBox
     * @param message message shown on the ConfirmBox
     * @return returns the boolean
     */

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

    /**
     * Add string on the ConfirmBox.
     * @param title add title to the ConfirmBox
     * @param option add option on the ConfirmBox
     */

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

            //call method in ServerApplication to update the database with username as a parameter,
            // server.ServerApplication.method(username.getText();
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
