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
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Board extends Application {

    private Block[][] board;

    private int squarePlaces;
    private int squarePixels;

    private int spacer;

    public static void main(String args [] ) {
        // this class can be run to provide an example
        // of an example Board with a randomly placed block
        // with a value of 2
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle(getClass().getSimpleName());

        Group root = new Group();

        Scene scene = new Scene(root,400,400, Color.LIGHTPINK);
        //////

        Board board = new Board(4,400);

        this.drawBackground(root);
        //this.drawBlocks(root);

        ///////
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Board() {
        // default values of Board presumes a 4x4 board of 400 pixels wide
        this.squarePlaces = 4;
        this.squarePixels = 400;

        this.board = new Block[this.squarePlaces][this.squarePlaces];
        int j = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (j = 0; j < this.board[0].length; j++) {
                this.board[i][j] = new Block(0);
            }
            j = 0;
        }
    }

    public Board(int squarePlaces, int squarePixels){
        this.squarePlaces = squarePlaces;
        this.squarePixels = squarePixels;

        this.board = new Block[this.squarePlaces][this.squarePlaces];
        int j = 0; //
        for (int i = 0; i < this.board.length; i++) {
            for (j = 0; j < this.board[0].length; j++) {
                this.board[i][j] = new Block(0);
            }
            j = 0;
        }
    }

    public Object block(int x, int y) { //actual block address
        return this.board[x][y];
    }

    public Block block(int b) { //unique integer for each block address
        int x = b / this.squarePlaces();
        int y = b % this.squarePlaces();

        return this.board[x][y];
    }

    public int squarePlaces(){return this.squarePlaces;}

    public int squarePixels(){return this.squarePixels;}

    public boolean checkIfFull() {
        for (int i = 0; i < Math.pow(this.squarePlaces,2); i++){
            if (block(i).value() == 0){
                return false;
            }
        }
        return true;
    }

    public void drawBackground(Group root){
        int x;      int y;
        int width;  int height;

        for (int i = 0; i < this.squarePlaces*this.squarePlaces; i++) {

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
            rectangle.setArcHeight(50);
            rectangle.setArcWidth(50);
            rectangle.setStroke(Color.BLACK);
            rectangle.setFill(Color.WHITE);
            rectangle.setStrokeWidth(2);

            root.getChildren().add(rectangle);
        }
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

