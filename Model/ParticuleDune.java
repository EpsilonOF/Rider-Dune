package Model;

import java.util.LinkedList;

public class ParticuleDune extends Particules<Boule>{
    Vehicule v;
    int largeur;

    public ParticuleDune(Vehicule v){
        particules = new LinkedList<Boule>();
        this.v = v;
        this.taille = 25;
    }

    @Override
    public void update(){
        particules.add(new Boule(v.x, v.y, 30));
        if( particules.size() > taille){
            particules.removeFirst();
        }
    }
    
}
