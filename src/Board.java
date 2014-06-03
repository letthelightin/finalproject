/**This class is a Constructor for object Board of the Java 2048 Project. 
 * The variables of Board objects include
 *
 * int squarePlaces (quantity of rows&columns of Block objects) 
 * int squarePixels (quantity of pixels wide&high of Board objects)
 * int squarePixels / squarePlaces (quantity of pixels wide&high of Board objects)

 * @author Daniel Retta 
 * @version 2014.05.14
 */

//graphics libraries
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.shape.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Board extends Application {

    private Block[][] board;

    private int squarePlaces;
    private int squarePixels;

    private int spacer;

    private Stage primaryStage;
    private Scene scene;
    private Group root;
    private Group image = new Group();

    public static void main(String args [] ) {
        // This class may be run to provide an example
        // of a Board with a randomly placed block
        // with a value of 2
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        //////
        // This is the code that draws the Board class example

        Board board = new Board(primaryStage,4,400); // creates a new Board

        board.drawBackground();

        //board.randomPlace().value(2);
        board.block(3,2).value(2);

        board.drawBlocks();

        board.move(3,2,3,3);

        //////
        primaryStage.setScene(board.scene());
        primaryStage.show();
    }

    public Board(){;};

    public Board(Stage primaryStage, int squarePlaces, int squarePixels){
        this.primaryStage = primaryStage;
        this.squarePlaces = squarePlaces;
        this.squarePixels = squarePixels;

        this.root = new Group();
        this.scene = new Scene(root,squarePixels,squarePixels, Color.WHITE);
        this.board = new Block[this.squarePlaces][this.squarePlaces];

        this.primaryStage.setTitle("J2048");

        // This fills the places of the board with 0-value blocks.
        int j = 0; //
        for (int i = 0; i < this.squarePlaces; i++) {
            for (j = 0; j < this.squarePlaces; j++) {
                this.board[i][j] = new Block(0);
            }
            j = 0;
        }

    }

    public Block block(int x, int y) { //actual block address
        return this.board[x][y];
    }

    public Block block(int b) {
        // This constructor provides a single integer address for each block.
        int x = b / this.squarePlaces();
        int y = b % this.squarePlaces();

        return this.board[x][y];
    }

    public int squarePlaces(){return this.squarePlaces;}

    public int squarePixels(){return this.squarePixels;}

    public Scene scene(){ return this.scene; }

    public Pane background(){
        Pane background = new Pane();

        int x;      int y;
        int width;  int height;

        spacer = 10;

        for (int i = 0; i < Math.pow(this.squarePlaces,2); i++) {

            // provide an identifying x and y coordinate for our board grid
            x = i / this.squarePlaces;
            y = i % this.squarePlaces;

            //provide the width and height of a place rectangle
            width = this.squarePixels/this.squarePlaces - this.spacer * 2;
            height = this.squarePixels/this.squarePlaces - this.spacer * 2;

            // provide an x and y coordinate for our pixel grid
            x = x * this.squarePixels/this.squarePlaces + this.spacer;
            y = y * this.squarePixels/this.squarePlaces + this.spacer;

            Rectangle rectangle = new Rectangle(x,y,width,height);
            rectangle.setSmooth(true);
            rectangle.setArcHeight(25);
            rectangle.setArcWidth(25);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.WHITE);
            rectangle.setStrokeWidth(2);

            background.getChildren().add(rectangle);
        }
        return background;
    }

    public void drawBackground() {
        this.root.getChildren().add(this.background());
    }
    public Block randomPlace() {
        int x = (int) (Math.random()*(double)squarePlaces);
        int y = (int) (Math.random()*(double)squarePlaces);
        return this.board[x][y];
    }

    public Group blocks() {
        int minValue = 1;

        int x; int y; int width; int height;

        spacer = 15;

        Group blockDrawing = new Group();

        int j = 0;
        for (int i = 0; i < this.squarePlaces; i++) {
            for (j = 0; j < this.squarePlaces; j++) {

                x = i * this.squarePixels/this.squarePlaces + this.spacer;
                y = j * this.squarePixels/this.squarePlaces + this.spacer;
                width = this.squarePixels/this.squarePlaces - this.spacer * 2;
                height = this.squarePixels/this.squarePlaces - this.spacer * 2;

                blockDrawing = this.board[i][j].drawing(width,height);

                if (this.board[i][j].value() <= minValue) {
                    blockDrawing.setVisible(false);
                }

                image.getChildren().add(blockDrawing);

            }
            j = 0;
        }
        return image;
    }

    public void drawBlocks(){
        this.root.getChildren().add(this.blocks());
    }

    public Group root() { return this.root; }

    public void move(int aX, int aY, int bX, int bY) {

        aX = (int) this.placePixelCenter(aX);
        aY = (int) this.placePixelCenter(aY);

        bX = (int) this.placePixelCenter(bX);
        bY = (int) this.placePixelCenter(bY);

        Path path = new Path();
        this.root.getChildren().add(path); // comment this line to hide path from stage

        MoveTo moveTo = new MoveTo();
        moveTo.setX(aX);
        moveTo.setY(aY);

        LineTo lineTo = new LineTo();
        lineTo.setX(bX);
        lineTo.setY(bY);

        path.getElements().add(moveTo);
        path.getElements().add(lineTo);

        PathTransition pathTransition = new PathTransition(Duration.millis(250), path, image);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        pathTransition.play();

    }

    public double placePixelCenter (int a) {
        double squarePlacePixel = this.squarePixels/this.squarePlaces;
        return a * squarePlacePixel + (0.5 * squarePlacePixel) ;
    }





    //////////
    public boolean isFull() {
        for (int i = 0; i < Math.pow(this.squarePlaces,2); i++){
            if (block(i).value() == 0){
                return false;
            }
        }
        return true;
    }

    public void generateNewBlock(){
        // This method randomly selects a block with
        // a current value of 0 and sets that value to 1

        double c = this.squarePlaces-1;

        double i = 0;
        double j = 0;

        int counterLimit = 0;
        boolean flag = false;

        do{
            i = Math.random() * c;
            j = Math.random() * c;
            if (this.board[(int)i][(int)j].value() == 0){
                this.board[(int)i][(int)j].value(1);
                flag = true;
            }
        }while ((flag==false) && (counterLimit < 1000));
    }

}

