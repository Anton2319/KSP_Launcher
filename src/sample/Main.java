package sample;

import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import java.awt.*;
import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        final VBox vbox = new VBox();

        //Создаём и добавляем кнопку
        final Button start = new Button("Запустить");
        start.toFront();
        start.setStyle("-fx-background-color: green; -fx-text-fill: white; -fx-background-radius: 0px; fx-border-radius: 0px; -fx-text-fill: white;");
        start.setMinWidth(150);
        start.setMaxWidth(150);
        start.setMinHeight(30);
        start.setMaxHeight(30);
        vbox.getChildren().addAll(start);

        //Cоздаём ImageView для фона
        File file = new File("bg.png");
        Image image = new Image(file.toURI().toString());
        ImageView bg = new ImageView();
        bg.setFitWidth(950);
        bg.setImage(image);
        bg.toBack();
        vbox.getChildren().add(bg);
        vbox.getChildren().get(vbox.getChildren().size() - 1).toBack();


        //Создаём сцену и задаём параметры
        final Scene scene = new Scene(vbox, 950, 575);
        stage.setScene(scene);
        scene.getStylesheets().add("/style.css");
        stage.show();
        stage.setTitle("KSP Launcher");

    }


    public static void main(String[] args) {
        launch(args);
    }
}
