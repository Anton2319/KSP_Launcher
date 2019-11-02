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

        //Обновляем список версий
        String[] versList = new String[5];
        try {
            versList[0] = (String) versionList().get(0);
            versList[1] = (String) versionList().get(1);
            versList[2] = (String) versionList().get(2);
            versList[3] = (String) versionList().get(3);
            versList[4] = (String) versionList().get(4);
        } catch (FileNotFoundException e) {
            File list = new File("versionList.txt");
            list.createNewFile();
            versList[0] = (String) versionList().get(0);
            versList[1] = (String) versionList().get(1);
            versList[2] = (String) versionList().get(2);
            versList[3] = (String) versionList().get(3);
            versList[4] = (String) versionList().get(4);
        }

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
                File file =new File("versions/1.7.3/ksp.exe");
                try {
                    Desktop.getDesktop().open(file);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "Запустить KSP не удалось!");
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().addAll(start);

        //Создаём выпадающее меню для выбора версии
        ObservableList<String> langs = FXCollections.observableArrayList(versList[4], versList[3], versList[2], versList[1], versList[0]);
        ChoiceBox<String> versionChoiceBox = new ChoiceBox<String>(langs);
        versionChoiceBox.setValue(versList[4]);
        versionChoiceBox.setLayoutX(700);
        versionChoiceBox.setLayoutY(539);
        versionChoiceBox.setMinWidth(80);
        versionChoiceBox.setMaxWidth(80);
        versionChoiceBox.setMinHeight(30);
        versionChoiceBox.setMaxHeight(30);
        root.getChildren().add(versionChoiceBox);

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
