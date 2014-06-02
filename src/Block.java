import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Block extends Application {

    private int value;      // value of the block

    private double strokeWidth;     // strength of the stroke
    // around the rectangle

    private Color block;            // color of the block
    private Color stroke;           // color of the border
    private Color label;            // color of the label

    private Pane image;            // group of javafx components

    public static void main(String args[]) {
        // This class may be run to provide an example
        // of a single block being drawn.
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Example of " + getClass().getSimpleName());

        Group root = new Group();

        Scene scene = new Scene(root,400,400,Color.WHITE);

        //////
        // This is the code that draws the Block class example
        // by adding a drawing of Block with (x, y, width, height)
        // to the Group root submitted to Scene scene and displayed
        // by the special instance primaryStage.

        // Block.drawing() returns a Pane.

        Block block = new Block();

        root.getChildren().add(block.drawing(50,50,300,300));

        /////

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public Block() {
        this.value = 0;
        this.strokeWidth = 3;
        this.block = Color.WHITE;
        this.stroke = Color.BLACK;
        this.label = Color.BLACK;
    }

    public Block(int value) {
        this.value = 0;
        this.strokeWidth = 3;
        this.block = Color.WHITE;
        this.stroke = Color.BLACK;
        this.label = Color.BLACK;
    }

    // SET Constructors for private variables of this Block
    public void value(int value) { this.value = value; }
    public void strokeWidth(double strokeWidth) { this.strokeWidth = strokeWidth; }
    public void block(Color color) { this.block = color; }
    public void stroke(Color color) { this.stroke = color; }
    public void label(Color color) { this.label = color; }

    // GET Constructors for private variables of this Block
    public int value() { return this.value; }
    public double strokeWidth() { return this.strokeWidth; }
    public Color block() { return this.block; }
    public Color stroke() { return this.stroke; }
    public Color label() { return this.label; }
    public Pane image() { return this.image; }

    public Pane drawing(int x, int y, int width, int height) {

        this.image = new Pane();

        Rectangle rectangle = new Rectangle(x, y, width, height);
        rectangle.setSmooth(true);
        rectangle.setArcHeight(30*width/100);
        rectangle.setArcWidth(30*width/100);
        rectangle.setStroke(Color.BLACK);
        rectangle.setFill(Color.WHITE);
        rectangle.setStrokeWidth(3*width/100);
        rectangle.setManaged(true);

        this.image.getChildren().add(rectangle);

        Label label = new Label("" + this.value);
        label.setLayoutX(x + 40*width/100);
        label.setLayoutY(y + 30*height/100);
        label.setFont(Font.font("Calibri", 50*width/100));
        label.setTextFill(this.label);
        label.setManaged(true);

        this.image.getChildren().add(label);

        this.image.setManaged(true);

        return this.image;
    }

}





