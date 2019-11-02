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
import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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

        //Провряем, есть ли файл versionList
        File versionListFile = new File("versionList.txt");
        if(versionListFile.exists()) {}
        else {
            //Если нет, пытаемся скачать
            try {
                JOptionPane.showMessageDialog(null,"Подождите, пока нужные файлы загрузятся\nиз интернета");
                URL website = new URL("https://downloads.sourceforge.net/project/ksp-launcher/versionList.txt?r=https%3A%2F%2Fsourceforge.net%2Fprojects%2Fksp-launcher%2Ffiles%2FversionList.txt%2Fdownload%3Fuse_mirror%3Dnetcologne%26r%3Dhttps%253A%252F%252Fsourceforge.net%252Fprojects%252Fksp-launcher%252Ffiles%252F%26use_mirror%3Dmaster&ts=1572712225");
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                FileOutputStream fos = new FileOutputStream("versionList.txt");
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
            }
            catch (Exception e) {
                //Если не получилось - создаём файл с сообщением о неудаче
                versionListFile.createNewFile();
                try(FileWriter writer = new FileWriter("versionList.txt", false))
                {
                    String text = "СБОЙ СОЕДИНЕНИЯ!";
                    String text1 = "Не удалось получить список версий";
                    String text2 = "Удалите файл versionList.txt, подключитесь к интернету и попробуйте снова";
                    writer.write(text);
                    writer.append('\n');
                    writer.write(text1);
                    writer.append('\n');
                    writer.write(text2);
                    JOptionPane.showMessageDialog(null, text+"\n"+text1+"\n"+text2);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null, "Не удалось записать данные в файл");
                    System.out.println(ex.getMessage());
                }
            }
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

        //Создаём и добавляем кнопку запуска
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

        //Создаём и добавляем кнопку настроек
        final Button settings = new Button("Настройки");
        settings.toFront();
        //start.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 0px; fx-border-radius: 0px; -fx-text-fill: white;");
        settings.setMinWidth(120);
        settings.setMaxWidth(120);
        settings.setMinHeight(30);
        settings.setMaxHeight(30);
        settings.setLayoutX(20);
        settings.setLayoutY(539);
        settings.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Settings settings = new Settings();
                try {
                    settings.start(stage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        root.getChildren().addAll(settings);

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
