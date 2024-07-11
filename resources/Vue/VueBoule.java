package Vue;

import java.awt.Color;
import java.awt.Graphics;

import Model.Vehicule;

public class VueBoule {
    public Vehicule vehicule;
    public VueBoule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }

    public void drawBoule(Graphics gd){
        gd.setColor(Color.WHITE);
        gd.fillOval(195, 485 - vehicule.y - 15, 30, 30);
    }
    
    
}
