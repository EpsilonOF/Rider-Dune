package Model;

import java.util.LinkedList;

public class Fumee extends Particules<Point> {
    Vehicule v;

    public Fumee(Vehicule v){
        particules = new LinkedList<Point>();
        this.v = v;
        this.taille = 25;
    }

    @Override
    public void update(){
        particules.add(new Point(v.x - 5, v.y-20 +(int)(Math.random()*15)));
        if( particules.size() > taille){
            particules.removeFirst();
        }
    }
}
