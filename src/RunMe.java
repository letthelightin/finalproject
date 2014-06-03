import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RunMe extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        int squarePixels = 400;
        int squarePlaces = 4;

        //////
        // This is the code that draws the Board class example

        Board board = new Board(primaryStage, squarePlaces, squarePixels); // creates a new Board

        board.drawBackground();

        //board.randomPlace().value(2);

        board.block(0,0).value(2);
        board.block(0,1).value(2);
        board.block(2,2).value(2);
        board.block(3,3).value(2);
        board.block(3,2).value(2);

        board.drawBlocks();

        EventHandler<KeyEvent> kEvent = new MyKeyEventHandler(board);

        board.scene().setOnKeyPressed(kEvent);

        //////
        primaryStage.setScene(board.scene());
        primaryStage.show();
    }


    class MyKeyEventHandler implements EventHandler<KeyEvent> {

        private Board board;
        private int squarePlaces;
        private int squarePixels;
        private Group root;

        MyKeyEventHandler(Board board) {
            this.board = board;
            squarePlaces = board.squarePlaces();
            squarePixels = board.squarePixels();
            root = board.root();
        }

        public void handle(final KeyEvent keyEvent) {
            if (keyEvent.getCode() == KeyCode.DOWN) {
                down();
            } else if (keyEvent.getCode() == KeyCode.UP) {
                up();
            } else if (keyEvent.getCode() == KeyCode.RIGHT) {
                right();
            } else if (keyEvent.getCode() == KeyCode.LEFT) {
                left();
            }
        }


        public void down() {
            int placesBelow; int blocksBelow;
            int j; //

            for (int i = squarePlaces-1; i >= 0; i--) {
                for (j = squarePlaces-1; j >= 0; j--) {

                    int value = board.block(i,j).value();

                    if (value > 0) {
                        placesBelow = board.placesBelow(i, j);
                        if (placesBelow > 0) {
                            board.move(i, j, i, j + placesBelow).play();
                            board.swapPlaces(i, j, i,  j + placesBelow);
                        }
                    }
                }
                j = squarePlaces-1;
            }
        }

        public void up() {
            int placesAbove;
            int j;

            for (int i = 0; i < this.squarePlaces; i++) {
                for (j = 0; j < this.squarePlaces; j++) {
                    int value = board.block(i,j).value();
                    if (value > 0) {
                        placesAbove = board.placesAbove(i, j);

                        if (placesAbove > 0) {
                            board.move(i, j, i, j - placesAbove).play();

                            board.swapPlaces(i, j, i,  j - placesAbove);
                        }
                    }
                }
                j = 0;
            }
        }

        public void right() {
            int placesRight;
            int i;

            // pull items from the right to the right first
            for (int j=squarePlaces-1; j >= 0; j--){
                for ( i=squarePlaces-1; i >= 0; i--){

                    int value = board.block(i,j).value();
                    if (value > 0) {
                        placesRight = board.placesRight(i, j);

                        if (placesRight > 0) {
                            board.move(i, j, i + placesRight, j).play();

                            board.swapPlaces(i, j, i + placesRight,  j);
                        }
                    }


                }
                i=squarePlaces-1;
            }
        }

        public void left() {

            int placesLeft;
            int i;

            for (int j = 0; j < this.squarePlaces; j++) {
                for (i = 0; i < this.squarePlaces; i++) {

                    int value = board.block(i,j).value();
                    if (value > 0) {
                        placesLeft = board.placesLeft(i, j);

                        if (placesLeft > 0) {
                            board.move(i, j, i - placesLeft, j).play();

                            board.swapPlaces(i, j, i - placesLeft,  j);
                        }
                    }

                }
                i = 0;
            }


        }
    }

}
    

