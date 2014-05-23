/**This class is a Constructor for object Block of the Java 2048 Project. 
 * The variables of Block objects include
 * ~ int value (numeric value & displayed character of Block)
 * ~ int catalyst (those that combine w/ this Block to change/advance)
 *
 * Methods will 
 * ~ return all variables individually
 * ~ set all variables individually
 *
 * @author Daniel Retta 
 * @version 2014.05.14
 */
public class Block {
    // instance variables - replace the example below with your own
    private int value;
    private int catalyst;

    public Block() {
        this.value = 0;
        this.catalyst = 1;
    }

    public Block(int value, int catalyst){
        this.value = value;
        this.catalyst = catalyst;
    }

    //these methods alter individual variables of this Block
    public void value(int value){this.value = value;}

    public void catalyst(int catalyst){this.catalyst = catalyst;}

    //these methods return individual variables of this Block
    public int value(){return this.value;}

    public int catalyst(){return this.catalyst;}

}