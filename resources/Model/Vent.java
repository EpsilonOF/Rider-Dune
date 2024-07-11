package Model;

import java.util.LinkedList;

public class Vent extends Particules<Vecteur> {
    public Vehicule v;
    public double anglePart;

    public Vent(Vehicule v){
        this.v = v;
        particules = new LinkedList<Vecteur>();
        this.taille = 25;
    }

    @Override
    public void update(){
        if(v.saut && Math.random() > 0.5){
            anglePart = v.penteAir();
            int rand = (int)(Math.random()*40);
            Point p = new Point();
            p.x = v.x+40 +(int)(Math.cos(anglePart)*15);
            p.y = v.y -10 + rand +(int)(Math.sin(anglePart )*15);
            particules.add(new Vecteur(new Point(v.x +40, v.y+rand-40), p));
            if(particules.size() >taille){
                particules.removeFirst();          
            }
        }else if(!v.saut){
            if(!particules.isEmpty()) particules.removeFirst();
        }
    }
}
