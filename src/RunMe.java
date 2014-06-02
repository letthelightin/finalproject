import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RunMe extends Application {

    public static void main(String args [] ) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        int squareWindowSize = 400;

        primaryStage.setTitle("Java 2048 by Daniel Retta");
        Group root = new Group();
        Scene scene = new Scene(root,squareWindowSize,squareWindowSize, Color.WHITE);
        //////
        // This is the code that draws the Board class example

        Board board = new Board(root,4,squareWindowSize); // creates a new Board

        settings(board);

        board.drawBackground();

        board.randomBlock().value(2);

        board.drawBlocks();

        //////
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void settings(Board board) {
        //Block Settings

        //Board Settings

    }

}



