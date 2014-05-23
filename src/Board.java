/**This class is a Constructor for object Board of the Java 2048 Project. 
 * The variables of Board objects include
 *
 * int blockCountSquared (quantity of rows&columns of Block objects) 
 * int boardPixelSizeSquared (quantity of pixels wide&high of Board objects)
 * int blockPixelSizeSquared (quantity of pixels wide&high of Board objects)

 * @author Daniel Retta 
 * @version 2014.05.14
 */

//graphics libraries
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;

public class Board {
    private Block[][] board;
    private int blockCountSquared;
    private int boardPixelSizeSquared;
    private int blockPixelSizeSquared;

    public Board() {
        board = new Block[0][0];

        blockCountSquared = 0;
        boardPixelSizeSquared = 0;
    }

    public void defineBoard(int blockCountSquared, int boardPixelSizeSquared){
        this.blockCountSquared = blockCountSquared;
        this.boardPixelSizeSquared = boardPixelSizeSquared;

        board = new Block[this.blockCountSquared][this.blockCountSquared];
        int j = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (j = 0; j < this.board[0].length; j++) {
                board[i][j] = new Block(0,1);
            }
            j = 0;
        }
    }

    public Block block(int x, int y) { //actual block address
        return this.board[x][y];
    }

    public Block block(int b) { //unique integer for each block address
        int x = b / this.blockCountSquared();
        int y = b % this.blockCountSquared();

        return this.board[x][y];
    }

    public int blockCountSquared(){return this.blockCountSquared;}

    public int boardPixelSizeSquared(){return this.boardPixelSizeSquared;}

    public int blockPixelSizeSquared(){return this.blockPixelSizeSquared;}

    public boolean checkIfFull() {
        for (int i = 0; i < Math.pow(this.blockCountSquared,2); i++){
            if (block(i).value() == 0){
                return false;
            }
        }
        return true;
    }

    public void draw(GraphicsContext gC){

        int j = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (j = 0; j < this.board[0].length; j++) {

                gC.fillRect(                                    // draw each block
                        i * this.blockPixelSizeSquared + 5,     // x
                        j * this.blockPixelSizeSquared + 5,     // y

                        i * this.blockPixelSizeSquared - 5,     // width
                        j * this.blockPixelSizeSquared - 5      // height
                );
            }
            j = 0;
        }

    }

    public void generateNewBlock(){
        // This method randomly selects a block with
        // a current value of 0 and sets that value to 1

        double c = this.blockCountSquared-1;

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

