// Daniel Retta
// 2014.04.22
// Java I Programming
// Project

// To generally recreate the game 2048 found here:
// http://gabrielecirulli.github.io/2048/

// 2048 is fundamentally a four by four grid, a 2d array. 
// Each place in the array contains a block which represents a binary value.
// A round starts with a randomly placed block of either a value of 1 or 2.
// The user then selects a direction of up, down, left or right.
// The block moves to the furthest wall in the selected direction.
// Another block then appears.
// When two blocks of the same value are pushed together, they merge to form their combined value.

import java.awt.*;

//graphics libraries
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.scene.shape.Rectangle;


//key reception libraries
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class main extends Application implements KeyListener{

    private static Board board = new Board();

    public static void main(String [] args) {

        int j = 0;

        board.defineBoard(4, 400);
        board.generateNewBlock();
        launch(args);



    }

    public void keyPressed(KeyEvent evt) {   }

    public void keyReleased(KeyEvent evt) {   }

    public void keyTyped(KeyEvent evt) {    }


    public void draw(GraphicsContext gC) {
        Rectangle canvas = new Rectangle(0,0,board.boardPixelSizeSquared(),board.boardPixelSizeSquared());
        drawBoard(gC, canvas);
    }

    public void drawBoard(GraphicsContext gC, Rectangle base) {

        board.draw(gC);

    }

    public void drawHouse(GraphicsContext gC, Rectangle base) {
        //roof shape
        double[] pX = new double[3];                            //roof polygon x's
        pX[0] = base.getX();
        pX[1] = base.getX() + ( base.getWidth() / 2 );
        pX[2] = base.getX() + base.getWidth();

        double[] pY = new double[3];                            //roof polygon y's
        pY[0] = base.getY() + ( base.getHeight() / 3 );
        pY[1] = base.getY();
        pY[2] = base.getY() + ( base.getHeight() / 3 );

        gC.setFill(Color.RED);                                   // make draw color red
        gC.fillPolygon(pX, pY, 3);                               // draw roof polygon

        //house shape
        gC.setFill(Color.YELLOW);                                // make draw color yellow
        gC.fillRect(                                             // draw house rectangle
                base.getX(),
                base.getY()+ (base.getHeight() / 3),
                base.getWidth(),
                base.getHeight() - (base.getHeight() / 3) );

        //window left
        gC.setFill(Color.GREEN);                                 // make draw color green
        gC.fillRect(                                             // draw window
                base.getX() + base.getWidth() / 7,
                base.getY() + (base.getHeight() / 7) * 3,
                base.getHeight() / 7,
                base.getHeight() / 7);

        //window right
        gC.setFill(Color.GREEN);                                 // make draw color green
        gC.fillRect(                                             // draw window
                base.getX() + ((base.getWidth() / 7) * 5) + (base.getWidth()/7 - base.getHeight()/7),
                base.getY() + (base.getHeight() / 7) * 3,
                base.getHeight() / 7,
                base.getHeight() / 7);

        //door
        gC.setFill(Color.BLUE);                                  // make draw color blue
        gC.fillRect(                                             // draw door
                base.getX() + ((base.getWidth() / 7) * 3),
                base.getY() + ((base.getHeight() / 7) * 5),
                (base.getWidth() / 7) * 1,
                (base.getHeight() / 7) * 2);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("J2048");
        BorderPane borderPane = new BorderPane();
        Canvas canvas = new Canvas(board.boardPixelSizeSquared(), board.boardPixelSizeSquared());
        borderPane.setCenter(canvas);
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.setFill(Color.GRAY);
        draw(graphicsContext);  // This is our custom method

        stage.setScene(new Scene(borderPane));
        stage.show();
    }

}
