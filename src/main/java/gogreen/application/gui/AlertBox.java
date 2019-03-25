package gogreen.application.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertBox {
    /**
     * Displays the string and the alert box.
     * @param message the message being displayed
     */

    public static void display(String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Thank you for using this application");
        window.setMinWidth(400);

        Label label = new Label(message);

        Button close = new Button("Ok");
        close.setOnAction(e -> window.close());
        close.setPadding(new Insets(10, 10, 10, 10));

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
