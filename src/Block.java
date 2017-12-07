import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Block extends Application {

    private int value;              // value of the block

    private int x, y;

    private double strokeWidth;     // strength of the stroke

    private Rectangle rectangle;

    private Color block;            // color of the block
    private Color stroke;           // color of the border
    private Color label;            // color of the label

    private Group image;            // group of javafx components

    public static void main(String args[]) {
        // This class may be run to provide an example
        // of a single block being drawn.
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        // This code draws the Class Block Example
        primaryStage.setTitle("Example of " + getClass().getSimpleName());

        Group root = new Group();

        Scene scene = new Scene(root, 600, 600, Color.WHITE);

        Block block = new Block();

        block.draw(root, 50, 50, 500, 500);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public Block() {
        value = 0;
        strokeWidth = 0;
        block = Color.WHITE;
        stroke = Color.BLACK;
        label = Color.BLACK;
    }

    public Block(int value) {
        this.value = value;
        strokeWidth = 2;
        block = Color.BLACK;
        stroke = Color.BLACK;
        label = Color.WHITE;
    }

    // SET Constructors for private variables of this Block
    public void value(int value) {
        this.value = value;
    }

    public void strokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void block(Color color) {
        block = color;
    }

    public void stroke(Color color) {
        stroke = color;
    }

    public void label(Color color) {
        label = color;
    }

    public int value() { return value; }

    public double strokeWidth() { return strokeWidth; }

    public Color block() { return block; }

    public Color stroke() { return stroke; }

    public Color label() { return label; }

    public Group image() { return image; }


    // controls block characteristics
    public Group drawing(int width, int height) {

        image = new Group();

        rectangle = new Rectangle(width, height);
        rectangle.setSmooth(true);
        rectangle.setArcHeight(35 * width / 100);
        rectangle.setArcWidth(35 * width / 100);
        rectangle.setStroke(stroke);
        rectangle.setFill(block);
        rectangle.setStrokeWidth(3 * width / 100);

        image.getChildren().add(rectangle);

        Label label = new Label("" + value);
        label.setLayoutX(38 * width / 100);
        label.setLayoutY(22 * height / 100);
        label.setFont(Font.font("Calibri", 50 * width / 100));
        label.setTextFill(this.label);

        image.getChildren().add(label);

        return image;
    }

    public void draw(Group root, double x, double y, int width, int height) {
        root.getChildren().add(drawing(width, height));
        image.setLayoutX(x);
        image.setLayoutY(y);
    }

    public void image(Group image){image = image; }

}