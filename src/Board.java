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
        //board.block(3,2).value(2);
        board.block(0,0).value(2);
        board.drawBlocks();

        //board.move(3,2,3,3);
        board.move(0,0,0,3).play();

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

        this.spacer = 15;

        Group blockDrawing = new Group();

        int j = 0;
        for (int i = 0; i < this.squarePlaces; i++) {
            for (j = 0; j < this.squarePlaces; j++) {

                //provide the width and height of a place rectangle
                width = this.squarePixels/this.squarePlaces - this.spacer * 2;
                height = this.squarePixels/this.squarePlaces - this.spacer * 2;

                // provide an x and y coordinate for our pixel grid
                x = i * this.squarePixels/this.squarePlaces + this.spacer;
                y = j * this.squarePixels/this.squarePlaces + this.spacer;

                blockDrawing = this.board[i][j].drawing(width,height);

                blockDrawing.setTranslateX(x);
                blockDrawing.setTranslateY(y);

                if (this.board[i][j].value() <= minValue) {
                    blockDrawing.setVisible(false);
                }

                block(i,j).image(blockDrawing);

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

    public PathTransition move(int aX, int aY, int bX, int bY) {

        Group image = this.block(aX,aY).image();

        aX = (int) this.placeOriginX(aX);
        aY = (int) this.placeOriginY(aY);

        bX = (int) this.placeOriginX(bX);
        bY = (int) this.placeOriginY(bY);

        Path path = new Path();
        // this.root.getChildren().add(path); // comment this line to hide path from stage

        MoveTo moveTo = new MoveTo();
        moveTo.setX(aX);
        moveTo.setY(aY);

        LineTo lineTo = new LineTo();
        lineTo.setX(bX);
        lineTo.setY(bY);

        path.getElements().add(moveTo);
        path.getElements().add(lineTo);

        PathTransition pathTransition = new PathTransition(Duration.millis(100), path, image);

        return  pathTransition;
    }

    public double placeOriginX (int a) {
        double squarePlacePixel = this.squarePixels/this.squarePlaces;
        return a * squarePlacePixel + (0.5 * squarePlacePixel) ;
    }

    public double placeOriginY (int a) {
        double squarePlacePixel = this.squarePixels/this.squarePlaces;
        return a * squarePlacePixel + (0.5 * squarePlacePixel) ;
    }

    public void scene(Scene scene){ this.scene = scene; }

    public void root(Group root){ this.root = root; }



    public Stage primaryStage() { return this.primaryStage; }

    public int placesBelow (int i, int j){
        int count = this.squarePlaces-1-j;

        for (int y = j + 1; y < this.squarePlaces(); y++){
            if (this.block(i,y).value() > 0) { count--; }
        }

        return count;
    }


    public int placesAbove (int i, int j){
        int count = j;

        for (int y = count-1; y >= 0; y--){

            if (this.block(i,y).value() > 0) { count--; }

        }

        return count;
    }

    public int placesRight (int i, int j){
        int count = 0;

            for (int x = i + 1; x < squarePlaces; x++) {
                if (this.block(x, j).value() <= 0) {
                    count++;
                }
            }


        return count;
    }

    public int placesLeft (int i, int j){
        int count = 0;

        for (int x = i - 1; x >= 0 ; x--) {
            if (this.block(x, j).value() <= 0) {
                count++;
            }
        }


        return count;
    }

    public void swapPlaces(int aX, int aY, int bX, int bY) {

        Block space = new Block();

        space = this.board[aX][aY];
        this.board[aX][aY] = this.board[bX][bY];
        this.board[bX][bY] = space;

    }

    public void fallDown() {
        int placesBelow; int blocksBelow;
        int j; //

        for (int i = squarePlaces-1; i >= 0; i--) {
            for (j = squarePlaces-1; j >= 0; j--) {
                int value = this.block(i,j).value();

                if (value > 0) {
                    placesBelow = this.placesBelow(i, j);

                    if (placesBelow > 0) {
                        this.move(i, j, i, j + placesBelow).play();
                        this.swapPlaces(i, j, i,  j + placesBelow);
                    }
                }
            }
            j = squarePlaces-1;
        }
    }

    public void fallUp() {
        int placesAbove;
        int j;

        for (int i = 0; i < this.squarePlaces; i++) {
            for (j = 0; j < this.squarePlaces; j++) {
                int value = this.block(i,j).value();

                if (value > 0) {
                    placesAbove = this.placesAbove(i, j);

                    if (placesAbove > 0) {
                        this.move(i, j, i, j - placesAbove).play();

                        this.swapPlaces(i, j, i,  j - placesAbove);
                    }
                }
            }
            j = 0;
        }
    }

    public void fallRight() {
        int placesRight;
        int i;

        for (int j=squarePlaces-1; j >= 0; j--){
            for ( i=squarePlaces-1; i >= 0; i--){
                int value = this.block(i,j).value();

                if (value > 0) {
                    placesRight = this.placesRight(i, j);

                    if (placesRight > 0) {
                        this.move(i, j, i + placesRight, j).play();

                        this.swapPlaces(i, j, i + placesRight,  j);
                    }
                }
            }
            i=squarePlaces-1;
        }
    }

    public void fallLeft() {
        int placesLeft;
        int i;

        for (int j = 0; j < this.squarePlaces; j++) {
            for (i = 0; i < this.squarePlaces; i++) {
                int value = this.block(i,j).value();

                if (value > 0) {
                    placesLeft = this.placesLeft(i, j);

                    if (placesLeft > 0) {
                        this.move(i, j, i - placesLeft, j).play();

                        this.swapPlaces(i, j, i - placesLeft,  j);
                    }
                }
            }
            i = 0;
        }
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

