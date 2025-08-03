package com.example.antiafk;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {


            Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("hello-view.fxml")));
            Scene scene = new Scene(fxmlLoader);
            stage.setScene(scene);
            stage.setResizable(false);
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/icon.png")));
            stage.getIcons().add(icon);
            stage.show();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}