package com.example.nqueenhw1;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class GenerateRandomMap {
    int x;
    public void setNumber(int x ) {

    }

    public void randomMatrix (int [][] queenArray, int num) {
        for (int i = 0; i < num ; i++) {
            for (int j = 0 ; j < num; j++) {
                queenArray[i][j] = 0;
            }
        }
    }

    public void clearBoard (GridPane board , int num , int [][] queenArray) {
        randomMatrix(queenArray,num);
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ObservableList<Node> node = board.getChildren();
                Node pane = node.get((i * num) + j);
                AnchorPane anchorPane = (AnchorPane) pane;
                anchorPane.getChildren().clear();
            }
        }
    }

    public void justClearBoard (GridPane board, int num) {

        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ObservableList<Node> node = board.getChildren();
                Node pane = node.get((i * num) + j);
                AnchorPane anchorPane = (AnchorPane) pane;
                    anchorPane.getChildren().clear();
            }
        }
    }
    public void randomMap(GridPane board , int num , int [][] queenArray ) {
            randomMatrix(queenArray , num);
        for (int i = 0 ; i < num ; i++) {
            int random = (int)(10*Math.random()%4); // result 0 - 3
            int index = random*num + i;
            queenArray[random][i] = 1;
            ObservableList<Node> node = board.getChildren();
            Node pane = node.get(index);
            AnchorPane anchorPane = (AnchorPane)pane;
            Image img = new Image(getClass().getResourceAsStream("crown.png"));
            ImageView imageView = new ImageView(img);
            imageView.fitWidthProperty().bind(anchorPane.widthProperty());
            imageView.fitHeightProperty().bind(anchorPane.heightProperty());
            anchorPane.getChildren().add(imageView);
        }
    }
    public void generatNewMap (GridPane board , int num ,int queenArray[][]) {
            clearBoard(board , num , queenArray);
            randomMap(board , num , queenArray);
    }
}
