package com.mixbaaljun.mpb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class App extends Application {

    private static final String titlename = "Tu presupuesto personal mensual";

    private static final String PRINCIPALCOMPONENT = "principal-view.fxml";

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PRINCIPALCOMPONENT));
        Scene scene = new Scene(fxmlLoader.load());
        // Pane pane = (new VBalanceProgressBar("Saldo actual del mes", "$
        // 30,000,000.00", "#00ffff")).getComponent();
        // Scene scene = new Scene(pane);

        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

        URL url = getClass().getResource("images/icon.png");
        System.out.println(url);

        Image icon = new Image(url.toString());
        stage.getIcons().add(icon);
        stage.setResizable(false);

        stage.setTitle(titlename);
        stage.setIconified(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}