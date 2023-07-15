package com.example.nqueenhw1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GenerateRandomMap {

    public void clearBoard (GridPane board , int num ) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ObservableList<Node> node = board.getChildren();
                Node pane = node.get((i * num) + j);
                AnchorPane anchorPane = (AnchorPane) pane;
                anchorPane.getChildren().clear();
            }
        }
    }
    public void randomMap(GridPane board , int num) {
        for (int i = 0 ; i < num ; i++) {
            int index = (i*num)+(int) (Math.random()*num);
            ObservableList<Node> node = board.getChildren();
            System.out.println(index);
            Node pane = node.get(index);
            AnchorPane anchorPane = (AnchorPane)pane;
            Image img = new Image(getClass().getResourceAsStream("crown.png"));
            ImageView imageView = new ImageView(img);
            imageView.fitWidthProperty().bind(anchorPane.widthProperty());
            imageView.fitHeightProperty().bind(anchorPane.heightProperty());
            anchorPane.getChildren().add(imageView);

        }
    }
    public void generatNewMap (GridPane board , int num) {
        clearBoard(board , num);
        randomMap(board , num);
    }

}
