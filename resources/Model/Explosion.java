package Model;

import java.util.LinkedList;
import java.util.Random;

public class Explosion extends Particules<Vecteur>{
    public Vehicule v;

    // Initialise les particules de l'explosion et leur donne une position et une direction
    public Explosion(Vehicule v){
        particules = new LinkedList<Vecteur>();
        this.taille = 400;
        this.v = v;
        Random rand = new Random();
        for(int i = 0; i < taille; i++){
            boolean b = true;
            int x = 0;
            int y = 0;
            while(b){
                x = rand.nextInt(20);
                y = rand.nextInt(20);
                if(x*x +y*y < 400) b = false;
            }
            int signe = rand.nextInt(2);
            if(signe == 0) x = -x;
            signe = rand.nextInt(2);
            if(signe == 0) y = -y;
            Vecteur vctr = new Vecteur(new Point(0,0), new Point(x,y));
            vctr.p1.x = v.x;
            vctr.p1.y = v.y;
            vctr.taille = (int)(Math.random()*20);
            particules.add(vctr);
        }
        
    } 

    @Override
    public void update(){
        if(particules.isEmpty()) return;
        for(int i = 0; i < particules.size();i++){
            Vecteur v = particules.get(i);
            v.p1.x += v.vect.x;
            v.p1.y += v.vect.y;
        }
    }

    public void reset(){
        for(int i = 0; i < particules.size();i++){
            Vecteur v = particules.get(i);
            v.p1.x -= v.vect.x;
            v.p1.y-= v.vect.y;
        }
    }

    
}
