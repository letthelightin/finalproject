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

    private int value;      // value of the block

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

        primaryStage.setTitle("Example of " + getClass().getSimpleName());

        Group root = new Group();

        Scene scene = new Scene(root, 400, 400, Color.WHITE);

        //////
        // This is the code that draws the Block class example
        // by adding a drawing of Block with (x, y, width, height)
        // to the Group root submitted to Scene scene and displayed
        // by the special instance primaryStage.

        Block block = new Block();

        block.draw(root, 50, 50, 300, 300);

        //////

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Block() {
        this.value = 0;
        this.strokeWidth = 0;
        this.block = Color.WHITE;
        this.stroke = Color.BLACK;
        this.label = Color.BLACK;
    }

    public Block(int value) {
        this.value = 0;
        this.strokeWidth = 3;
        this.block = Color.BLACK;
        this.stroke = Color.BLACK;
        this.label = Color.WHITE;
    }

    // SET Constructors for private variables of this Block
    public void value(int value) {
        this.value = value;
    }

    public void strokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public void block(Color color) {
        this.block = color;
    }

    public void stroke(Color color) {
        this.stroke = color;
    }

    public void label(Color color) {
        this.label = color;
    }

    // GET Constructors for private variables of this Block
    public int value() {
        return this.value;
    }

    public double strokeWidth() {
        return this.strokeWidth;
    }

    public Color block() {
        return this.block;
    }

    public Color stroke() {
        return this.stroke;
    }

    public Color label() {
        return this.label;
    }

    public Group image() {
        return this.image;
    }

    public Group drawing(int width, int height) {

        this.image = new Group();

        this.rectangle = new Rectangle(width, height);
        this.rectangle.setSmooth(true);
        this.rectangle.setArcHeight(30 * width / 100);
        this.rectangle.setArcWidth(30 * width / 100);
        this.rectangle.setStroke(this.stroke);
        this.rectangle.setFill(this.block);
        this.rectangle.setStrokeWidth(3 * width / 100);

        this.image.getChildren().add(rectangle);

        Label label = new Label("" + this.value);
        label.setLayoutX(40 * width / 100);
        label.setLayoutY(30 * height / 100);
        label.setFont(Font.font("Calibri", 50 * width / 100));
        label.setTextFill(this.label);

        this.image.getChildren().add(label);

        return this.image;
    }

    public void draw(Group root, double x, double y, int width, int height) {
        root.getChildren().add(this.drawing(width, height));
        this.image.setLayoutX(x);
        this.image.setLayoutY(y);
    }

    public void image(Group image){this.image = image; }
}