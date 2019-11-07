package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.swing.*;

public class GUIMessages {
    public void showMessage(String text) {
        final Stage dialog = new Stage();
        Pane root = new Pane();
        final Button ok = new Button("OK");
        ok.toFront();
        ok.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 0px; fx-border-radius: 0px; -fx-text-fill: white;");
        ok.setMinWidth(120);
        ok.setMaxWidth(120);
        ok.setMinHeight(30);
        ok.setMaxHeight(30);
        ok.setLayoutX(90);
        ok.setLayoutY(95);
        root.getChildren().addAll(ok);
        Label label = new Label(text);
        root.getChildren().addAll(label);
        label.setLayoutX(25);
        label.setLayoutY(25);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialog.close();
            }
        });
        final Scene scene = new Scene(root, 300, 150);
        dialog.setScene(scene);
        dialog.show();
        dialog.setTitle("KSP Launcher");
    }

    public static void showError(String text) {
        Stage error = new Stage();
        VBox root = new VBox();
        final Button ok = new Button("OK");
        ok.toFront();
        ok.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 0px; fx-border-radius: 0px; -fx-text-fill: white;");
        ok.setMinWidth(120);
        ok.setMaxWidth(120);
        ok.setMinHeight(30);
        ok.setMaxHeight(30);
        ok.setLayoutX(90);
        ok.setLayoutY(95);
        ok.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(1);
            }
        });
        root.getChildren().addAll(ok);
        Label label = new Label(text);
        root.getChildren().addAll(label);
        label.setLayoutX(25);
        label.setLayoutY(25);
        final Scene scene = new Scene(root, 300, 150);
        error.setScene(scene);
        error.show();
        error.setTitle("KSP Launcher - ошибка");
    }

    public void callShowError(String text) {
        showError(text);
    }
}
