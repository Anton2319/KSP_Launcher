package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Settings {
    public void start(final Stage stage) throws Exception{
        Pane root = new Pane();

        Label label = new Label("Источник версий KSP: ");
        root.getChildren().addAll(label);
        label.setLayoutX(25);
        label.setLayoutY(10);

        //Создаём RadioButton для выбора источника версий KSP
        ToggleGroup group = new ToggleGroup();

        // Radio 1: GitHub
        RadioButton GitHub = new RadioButton("Репозиторий в GitHub");
        GitHub.setToggleGroup(group);
        GitHub.setSelected(true);
        root.getChildren().addAll(GitHub);
        GitHub.setLayoutX(25);
        GitHub.setLayoutY(35);

        // Radio 2: SourceForge
        RadioButton SourceForge = new RadioButton("SourceForge");
        SourceForge.setToggleGroup(group);
        root.getChildren().addAll(SourceForge);
        SourceForge.setLayoutX(25);
        SourceForge.setLayoutY(55);

        //Создаём и добавляем кнопку назад
        final Button back = new Button("Назад");
        back.toFront();
        back.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 0px; fx-border-radius: 0px; -fx-text-fill: white;");
        back.setMinWidth(150);
        back.setMaxWidth(150);
        back.setMinHeight(41);
        back.setMaxHeight(41);
        back.setLayoutX(0);
        back.setLayoutY(434);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main main = new Main();
                try {
                    main.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().addAll(back);

        //Создаём и добавляем кнопку применить
        final Button apply = new Button("Применить");
        apply.toFront();
        apply.setStyle("-fx-background-color: blue; -fx-text-fill: white; -fx-background-radius: 0px; fx-border-radius: 0px; -fx-text-fill: white;");
        apply.setMinWidth(150);
        apply.setMaxWidth(150);
        apply.setMinHeight(41);
        apply.setMaxHeight(41);
        apply.setLayoutX(150);
        apply.setLayoutY(434);
        apply.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ArrayList versions = new ArrayList();
                Scanner in = null;
                try {
                    in = new Scanner(new File("settings.cfg"));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    File settings = new File("settings.cfg");
                    try {
                        settings.createNewFile();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        //GUIMessages.showError("Не удалось создать файл конфигурации,\nпопробуйте создать его вручную (settings.cfg)");
                        System.exit(1);
                    }
                }
            }
        });
        root.getChildren().addAll(apply);


        //Создаём сцену и задаём параметры
        final Scene scene = new Scene(root, 300, 475);
        stage.setScene(scene);
        scene.getStylesheets().add("/style.css");
        stage.show();
        stage.setTitle("Launcher settings");
    }
}

