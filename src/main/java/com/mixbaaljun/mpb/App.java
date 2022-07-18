package com.mixbaaljun.mpb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private static String titlename = "Tu presupuesto personal mensual";

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("earnings-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

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