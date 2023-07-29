package com.example.nqueenhw1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class error {

    @FXML
    private Button Okbtn;

    @FXML
    private ImageView imageerror;

    @FXML
    private Label loginerror;

    @FXML
    private Text msg;

    @FXML
    void tryagain(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        stage.setResizable(true);
    }
}