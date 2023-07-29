package com.example.nqueenhw1;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
public class GenerateRandomMap {
    int x;
    public void setNumber(int x) {

    }

    public void randomMatrix(int[][] queenArray, int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                queenArray[i][j] = 0;
            }
        }
    }

    public void clearBoard(GridPane board, int num, int[][] queenArray) {
        randomMatrix(queenArray, num);
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ObservableList<Node> node = board.getChildren();
                Node pane = node.get((i * num) + j);
                AnchorPane anchorPane = (AnchorPane) pane;
                anchorPane.getChildren().clear();
            }
        }
    }

    public void justClearBoard(GridPane board, int num) {
        for (int i = 0; i < num; i++) {
            for (int j = 0; j < num; j++) {
                ObservableList<Node> node = board.getChildren();
                Node pane = node.get((i * num) + j);
                AnchorPane anchorPane = (AnchorPane) pane;
                anchorPane.getChildren().clear();
            }
        }
    }

    public void randomMap(GridPane board, int num, int[][] queenArray) {
        if (num == 8){
        queenArray[0][0] = 1;
        queenArray[0][1] = 1;
        queenArray[0][2] = 1;
        queenArray[1][4] = 1;
        queenArray[1][3] = 1;
        queenArray[0][5] = 1;
        queenArray[1][6] = 1;
        queenArray[1][7] = 1;

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (queenArray[i][j] == 1) {
                        int index = (i * num) + j;
                        ObservableList<Node> node = board.getChildren();
                        Node pane = node.get(index);
                        AnchorPane anchorPane = (AnchorPane) pane;
                        Image img = new Image(getClass().getResourceAsStream("crown.png"));
                        ImageView imageView = new ImageView(img);
                        imageView.fitWidthProperty().bind(anchorPane.widthProperty());
                        imageView.fitHeightProperty().bind(anchorPane.heightProperty());
                        anchorPane.getChildren().add(imageView);
                    }
                }
            }

        }
        else if(num == 7){
            queenArray[0][0] = 1;
            queenArray[2][1] = 1;
            queenArray[0][2] = 1;
            queenArray[3][3] = 1;
            queenArray[1][4] = 1;
            queenArray[1][5] = 1;
            queenArray[1][6] = 1;
            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (queenArray[i][j] == 1) {
                        int index = (i * num) + j;
                        ObservableList<Node> node = board.getChildren();
                        Node pane = node.get(index);
                        AnchorPane anchorPane = (AnchorPane) pane;
                        Image img = new Image(getClass().getResourceAsStream("crown.png"));
                        ImageView imageView = new ImageView(img);
                        imageView.fitWidthProperty().bind(anchorPane.widthProperty());
                        imageView.fitHeightProperty().bind(anchorPane.heightProperty());
                        anchorPane.getChildren().add(imageView);
                    }
                }
            }

        }
        else if(num == 6){
            queenArray[0][1] = 1;
            queenArray[1][0] = 1;
            queenArray[1][2] = 1;
            queenArray[1][3] = 1;
            queenArray[2][5] = 1;
            queenArray[3][4] = 1;

            for (int i = 0; i < num; i++) {
                for (int j = 0; j < num; j++) {
                    if (queenArray[i][j] == 1) {
                        int index = (i * num) + j;
                        ObservableList<Node> node = board.getChildren();
                        Node pane = node.get(index);
                        AnchorPane anchorPane = (AnchorPane) pane;
                        Image img = new Image(getClass().getResourceAsStream("crown.png"));
                        ImageView imageView = new ImageView(img);
                        imageView.fitWidthProperty().bind(anchorPane.widthProperty());
                        imageView.fitHeightProperty().bind(anchorPane.heightProperty());
                        anchorPane.getChildren().add(imageView);
                    }
                }
            }

        }
        else{
            randomMatrix(queenArray , num);
            for (int i = 0 ; i < num ; i++) {
                int random = (int) (10 * Math.random() % 4); // result 0 - 3
                int index = random * num + i;
                queenArray[random][i] = 1;
                ObservableList<Node> node = board.getChildren();
                Node pane = node.get(index);
                AnchorPane anchorPane = (AnchorPane) pane;
                Image img = new Image(getClass().getResourceAsStream("crown.png"));
                ImageView imageView = new ImageView(img);
                imageView.fitWidthProperty().bind(anchorPane.widthProperty());
                imageView.fitHeightProperty().bind(anchorPane.heightProperty());
                anchorPane.getChildren().add(imageView);
            }
            }
        }

    public void generatNewMap(GridPane board, int num, int[][] queenArray) {
        clearBoard(board, num, queenArray);
        randomMap(board, num, queenArray);
    }
}
