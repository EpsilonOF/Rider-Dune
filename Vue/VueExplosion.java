package Vue;

import Model.Explosion;
import Model.Vehicule;

import java.awt.Color;
import java.awt.Graphics;

public class VueExplosion {
    public Explosion explosionRouge;
    public Explosion explosionJaune;
    public Vehicule vehicule;

    public VueExplosion(Explosion explosionRouge, Explosion explosionJaune, Vehicule vehicule) {
        this.explosionRouge = explosionRouge;
        this.explosionJaune = explosionJaune;
        this.vehicule = vehicule;
    }
    // la fonction affiche les particules des explosions et leur ajoute de de l'aléatoire dans la couleur et dans leur position
    public void draw(Graphics gd){
        gd.setColor(new Color(55+ (int)(Math.random()*200), 12, 97));
        for(int i = 0; i<explosionRouge.taille; i++){
                explosionRouge.particules.get(i).p1.x += (int)(Math.random()*10);
                explosionRouge.particules.get(i).p1.y += (int)(Math.random()*10);
            gd.fillRect(explosionRouge.particules.get(i).p1.x - (int)vehicule.vitesseX -vehicule.x0+ 200  , 470 - explosionRouge.particules.get(i).p1.y, explosionRouge.particules.get(i).taille, explosionRouge.particules.get(i).taille);
        }
        gd.setColor(new Color(180, 182, (int)(Math.random()*255)));
        for(int i = 0; i<explosionJaune.taille; i++){
            int random = (int)(Math.random()*20);
            explosionJaune.particules.get(i).p1.x += (int)(Math.random()*10);
            explosionJaune.particules.get(i).p1.y += (int)(Math.random()*10);
            gd.fillRect(explosionJaune.particules.get(i).p1.x - (int)vehicule.vitesseX -vehicule.x0+200 , 470 - explosionJaune.particules.get(i).p1.y, random, random);
        }
    }
    
}
