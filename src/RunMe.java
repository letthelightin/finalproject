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
        board.block(3, 2).value(2);


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
            if (keyEvent.getCode() == KeyCode.RIGHT) {

            } else if (keyEvent.getCode() == KeyCode.LEFT) {

            } else if (keyEvent.getCode() == KeyCode.UP) {

            } else if (keyEvent.getCode() == KeyCode.DOWN) {
                down();
            }
        }

        public void down() {


        }

    }
}
    

