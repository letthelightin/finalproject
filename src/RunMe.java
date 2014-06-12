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

    EventHandler<KeyEvent> kEvent;

    public static void main(String args[]) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        int squarePixels = 400;
        int squarePlaces = 4;

        Board board = new Board(primaryStage, squarePlaces, squarePixels); // creates a new Board

        board.drawBackground();

        board.block(0,0).value(2);
        board.block(0,1).value(2);
        board.block(2,2).value(2);

        board.block(3,2).value(2);

        board.drawBlocks();

        kEvent = new MyKeyEventHandler(board);

        board.scene().setOnKeyPressed(kEvent);

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

        public void handle(final KeyEvent kE) {
            if (kE.getCode() == KeyCode.DOWN || kE.getCode() == KeyCode.S) { down();
            } else if (kE.getCode() == KeyCode.UP || kE.getCode() == KeyCode.W) { up();
            } else if (kE.getCode() == KeyCode.RIGHT || kE.getCode() == KeyCode.D) { right();
            } else if (kE.getCode() == KeyCode.LEFT || kE.getCode() == KeyCode.A) { left();
            } else if (kE.getCode() == KeyCode.ESCAPE) { escape(); }
        }

        public void down() {
            board.fallDown();
            //board.randomPlace().value(2);
        }

        public void up() {
            board.fallUp();

        }

        public void right() {
            board.fallRight();
            //board.randomPlace().value(2);
        }

        public void left() {
            board.fallLeft();
            //board.randomPlace().value(2);
        }

        public void escape() {
            board.primaryStage().close();
        }
    }

}
    

