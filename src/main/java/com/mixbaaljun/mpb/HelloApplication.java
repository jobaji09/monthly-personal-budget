package com.mixbaaljun.mpb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    private static String titlename = "Tu presupuesto personal mensual";

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        URL url = getClass().getResource("images/icon.png");
        System.out.println(url);

        Image icon = new Image(url.toString());
        stage.getIcons().add(icon);

        stage.setTitle(titlename);
        stage.setIconified(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}