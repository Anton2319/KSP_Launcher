package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        Pane root = new Pane();
        //Проверяем, есть ли папка versions
        File versionsDir = new File("versions");
        if(versionsDir.isDirectory()) {}
        else {
            versionsDir.mkdir();
        }

        //Создаём выпадающее меню для выбора версии
        ObservableList<String> listVers = FXCollections.observableArrayList();
        int i;
        i = 0;
        while(i < versionList().size()) {
            listVers.add((String) versionList().get(i));
            i++;
        }
        i--;
        ChoiceBox<String> versionChoiceBox = new ChoiceBox<String>(listVers);
        versionChoiceBox.setValue((String) versionList().get(i));
        versionChoiceBox.setLayoutX(640);
        versionChoiceBox.setLayoutY(539);
        versionChoiceBox.setMinWidth(140);
        versionChoiceBox.setMaxWidth(140);
        versionChoiceBox.setMinHeight(30);
        versionChoiceBox.setMaxHeight(30);
        root.getChildren().add(versionChoiceBox);

        //Создаём и добавляем кнопку
        final Button start = new Button("Запустить");
        start.toFront();
        start.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 0px; fx-border-radius: 0px; -fx-text-fill: white;");
        start.setMinWidth(150);
        start.setMaxWidth(150);
        start.setMinHeight(41);
        start.setMaxHeight(41);
        start.setLayoutX(800);
        start.setLayoutY(534);
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File file = new File("versions/"+versionChoiceBox.getValue()+"/KSP_x64.exe");
                    try {
                        Desktop.getDesktop().open(file);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Запустить KSP не удалось!");
                        e.printStackTrace();
                    }
                }
            });
            root.getChildren().addAll(start);
        //Cоздаём ImageView для фона
        File file = new File("bg.png");
        Image image = new Image(file.toURI().toString());
        ImageView bg = new ImageView();
        bg.setFitWidth(950);
        bg.setImage(image);
        bg.toBack();
        root.getChildren().add(bg);
        root.getChildren().get(root.getChildren().size() - 1).toBack();

        //Создаём сцену и задаём параметры
        final Scene scene = new Scene(root, 950, 575);
        stage.setScene(scene);
        scene.getStylesheets().add("/style.css");
        stage.show();
        stage.setTitle("KSP Launcher");

    }


    public static void main(String[] args) {
        launch(args);
    }
    public static ArrayList versionList() throws FileNotFoundException {
        ArrayList versions = new ArrayList();
        Scanner in = new Scanner(new File("versionList.txt"));
        while (in.hasNextLine()) {
            versions.add(in.nextLine());
        }
        return versions;
    }
}
