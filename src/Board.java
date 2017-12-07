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

    public static void main(String args []) {
        // This class may be run to provide an example
        // of a Board with a randomly placed block
        // with a value of 2
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        // This code draws the Board class example
        Board board = new Board(primaryStage,4,600);

        board.drawBackground();

        Block block = board.randomPlace();

        block.value(2);

        board.drawBlocks();

        primaryStage.setScene(board.scene());

        primaryStage.show();
    }

    public Board(){;};

    public Board(Stage primaryStage, int squarePlaces, int squarePixels){
        this.primaryStage = primaryStage;
        this.squarePlaces = squarePlaces;
        this.squarePixels = squarePixels;

        root = new Group();
        scene = new Scene(root,squarePixels,squarePixels, Color.WHITE);
        board = new Block[squarePlaces][squarePlaces];

        primaryStage.setTitle("J2048");

        // This fills the places of the board with 0-value blocks.
        int j = 0; //
        for (int i = 0; i < squarePlaces; i++) {
            for (j = 0; j < squarePlaces; j++) {
                board[i][j] = new Block(0);
            }
            j = 0;
        }

    }

    public void addBlock(int x, int y, int value) {
        board[x][y]= new Block(value);
    }

    //provides a single integer address for each block
    public Block block(int b) {

        int x = b / squarePlaces();
        int y = b % squarePlaces();

        return board[x][y];
    }

    public int squarePlaces(){return squarePlaces;}

    public int squarePixels(){return squarePixels;}

    public Scene scene(){ return scene; }

    public Pane background(){
        Pane background = new Pane();

        int x, y;
        int width, height;

        spacer = 8;

        for (int i = 0; i < Math.pow(squarePlaces,2); i++) {

            // provide an identifying x and y coordinate for our board grid
            x = i / squarePlaces;
            y = i % squarePlaces;

            //provide the width and height of a place rectangle
            width = squarePixels/squarePlaces - spacer * 2;
            height = squarePixels/squarePlaces - spacer * 2;

            // provide an x and y coordinate for our pixel grid
            x = x * squarePixels/squarePlaces + spacer;
            y = y * squarePixels/squarePlaces + spacer;

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
        root.getChildren().add(background());
    }
    public Block randomPlace() {
        int x = (int) (Math.random()*(double)squarePlaces);
        int y = (int) (Math.random()*(double)squarePlaces);

        return board[x][y];
    }

    public Group blocks() {
        int minValue = 1;

        int x, y, width, height;

        spacer = 10;

        Group blockDrawing = new Group();

        int j = 0;
        for (int i = 0; i < squarePlaces; i++) {
            for (j = 0; j < squarePlaces; j++) {

                //provide the width and height of a place rectangle
                width = squarePixels/squarePlaces - spacer * 2;
                height = squarePixels/squarePlaces - spacer * 2;

                // provide an x and y coordinate for our pixel grid
                x = i * squarePixels/squarePlaces + spacer;
                y = j * squarePixels/squarePlaces + spacer;

                blockDrawing = board[i][j].drawing(width,height);

                blockDrawing.setTranslateX(x);
                blockDrawing.setTranslateY(y);

                if (board[i][j].value() <= minValue) {
                    blockDrawing.setVisible(false);
                }

                board[i][j].image(blockDrawing);

                image.getChildren().add(blockDrawing);

            }
            j = 0;
        }
        return image;
    }

    public void drawBlocks(){
        root.getChildren().add(blocks());
    }

    public Group root() { return root; }

    public PathTransition move(int aX, int aY, int bX, int bY) {

        Group image = board[aX][aY].image();

        aX = (int) placeOriginX(aX);
        aY = (int) placeOriginY(aY);

        bX = (int) placeOriginX(bX);
        bY = (int) placeOriginY(bY);

        Path path = new Path();
        // root.getChildren().add(path); // comment this line to hide path from stage

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
        double squarePlacePixel = squarePixels/squarePlaces;
        return a * squarePlacePixel + (0.5 * squarePlacePixel) ;
    }

    public double placeOriginY (int a) {
        double squarePlacePixel = squarePixels/squarePlaces;
        return a * squarePlacePixel + (0.5 * squarePlacePixel) ;
    }

    public void scene(Scene scene){ this.scene = scene; }

    public void root(Group root){ root = root; }

    public Stage primaryStage() { return primaryStage; }

    public int placesBelow (int i, int j){
        int count = squarePlaces-1-j;

        for (int y = j + 1; y < squarePlaces(); y++){
            if (board[i][y].value() > 0) { count--; }
        }

        return count;
    }

    public int placesAbove (int i, int j){
        int count = j;

        for (int y = count-1; y >= 0; y--){
            if (board[i][y].value() > 0) { count--; }
        }

        return count;
    }

    public int placesRight (int i, int j){
        int count = 0;

        for (int x = i + 1; x < squarePlaces; x++) {
            if (board[x][j].value() <= 0) {
                count++;
            }
        }

        return count;
    }

    public int placesLeft (int i, int j){
        int count = 0;

        for (int x = i - 1; x >= 0 ; x--) {
            if (board[x][j].value() <= 0) {
                count++;
            }
        }

        return count;
    }

    public void swapPlaces(int aX, int aY, int bX, int bY) {

        Block space = new Block();

        space = board[aX][aY];
        board[aX][aY] = board[bX][bY];
        board[bX][bY] = space;
    }

    public void fallDown() {
        int placesBelow;
        int j;
        PathTransition move;

        for (int i = squarePlaces-1; i >= 0; i--) {
            for (j = squarePlaces-1; j >= 0; j--) {
                int value = board[i][j].value();

                if (value > 0) {
                    placesBelow = placesBelow(i, j);

                    if (placesBelow > 0) {
                        move = move(i, j, i, j + placesBelow);
                        move.play();
                        //root.getChildren().remove(move);

                        swapPlaces(i, j, i,  j + placesBelow);
                    }
                }
            }
            j = squarePlaces-1;
        }
    }

    public void fallUp() {
        PathTransition move;
        int placesAbove;
        int j;

        for (int i = 0; i < squarePlaces; i++) {
            for (j = 0; j < squarePlaces; j++) {
                int value = board[i][j].value();

                if (value > 0) {
                    placesAbove = placesAbove(i, j);

                    if (placesAbove > 0) {
                        move = move(i, j, i, j - placesAbove);
                        move.play();

                        swapPlaces(i, j, i, j - placesAbove);
                    }
                }
            }
            j = 0;
        }
    }

    public void fallRight() {
        PathTransition move;
        int placesRight;
        int i;

        for (int j=squarePlaces-1; j >= 0; j--){
            for ( i=squarePlaces-1; i >= 0; i--){
                int value = board[i][j].value();

                if (value > 0) {
                    placesRight = placesRight(i, j);

                    if (placesRight > 0) {
                        move = move(i, j, i + placesRight, j);
                        move.play();
                        swapPlaces(i, j, i + placesRight, j);
                    }
                }
            }
            i=squarePlaces-1;
        }
    }

    public void fallLeft() {
        PathTransition move;
        int placesLeft;
        int i;

        for (int j = 0; j < squarePlaces; j++) {
            for (i = 0; i < squarePlaces; i++) {
                int value = board[i][j].value();

                if (value > 0) {
                    placesLeft = placesLeft(i, j);

                    if (placesLeft > 0) {
                        move = move(i, j, i - placesLeft, j);
                        move.play();

                        swapPlaces(i, j, i - placesLeft, j);
                    }
                }
            }
            i = 0;
        }
    }

    public Group image() { return image; }

    public void primaryStage(Stage primaryStage) { primaryStage = primaryStage; }

// TODO: Combine Method
//    public void combine(int aX, int aY, int bX, int bY) {
//
//        Block blockA = board[aX][aY];
//        Block blockB = board[bX][bY];
//
//        if (blockA.value() == blockB.value()){
//            board[aX][aY] = new Block()
//
//    }















    //////////

    public boolean isFull() {
        for (int i = 0; i < Math.pow(squarePlaces,2); i++){
            if (block(i).value() == 0){
                return false;
            }
        }
        return true;
    }

//    public Block[] emptyPlaces() {
//
//        Block block;
//
//        Block[] emptyPlaces =
//
//        int j = 0; //
//        for (int i = 0; i < squarePlaces; i++) {
//            for (j = 0; j < squarePlaces; j++) {
//            block = board[i][j];
//
//            if (block.value() >= 0) { ; }
//            }
//            j = 0;
//        }
//
//
//    }

    public void generateNewBlock(){
        // This method randomly selects a block with
        // a current value of 0 and sets that value to 1
        double c = squarePlaces-1;

        double i = 0;
        double j = 0;

        boolean flag = false;
        boolean full = isFull();

        Block block;

        do{
            i = Math.random() * c;
            j = Math.random() * c;
            if (board[(int)i][(int)j].value() == 0){
                block = board[(int)i][(int)j];


                flag = true;
            }
        }while (flag==false && !full);
    }

}

