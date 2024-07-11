package Model;
public class Point {
    public int x;
    public int y;
    public boolean piece;
    public boolean boost;
    public boolean danger;
    public boolean disparait;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
        piece  = false;
        boost = false;
        disparait = false;
    }
    public Point(){
        this(0,0);
    }
}
