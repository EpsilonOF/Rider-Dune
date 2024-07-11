package Model;

public class Vecteur {
    public Point p1;
    public Point p2;
    public Point vect;
    public int taille;

    public Vecteur(Point p1, Point p2){
        this.p1 = p1;
        this.p2 = p2;
        this.vect = new Point(p2.x-p1.x, p2.y-p1.y);
    }
    public Vecteur(){
        this(null, null);
    }
    
}
