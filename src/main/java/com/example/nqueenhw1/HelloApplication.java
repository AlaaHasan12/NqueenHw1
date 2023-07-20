package com.example.nqueenhw1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        Stage regstage = new Stage();
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("user.fxml")));
        regstage.resizableProperty().setValue(false);
        regstage.initStyle(StageStyle.UNDECORATED);
        regstage.setScene(new Scene(root));
        new animatefx.animation.ZoomIn(root).play();
        regstage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}