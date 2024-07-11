package Vue;

import Model.Terrain;
import Model.Vehicule;

import java.awt.Color;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.*; 


public class VueSol {
    public Terrain sol;
    public Vehicule vehicule;
    public Color bord, fond;
    private Image bgImage;

    public VueSol(Terrain sol, Vehicule vehicule, Color bord, Color fond) {
        this.sol = sol;
        this.vehicule = vehicule;
        this.bord = bord;
        this.fond = fond;
        try {
            bgImage = ImageIO.read(getClass().getResource("../resources/coin.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void drawSol(Graphics2D gd){
            for(int i = vehicule.x -200; i<vehicule.x+ 2200; i++){
                try{
                    if(sol.points.get((Integer)i).danger) gd.setColor(Color.RED);
                    else if(sol.points.get((Integer)i).disparait) gd.setColor(Color.green);
                    else if(!sol.points.get((Integer)i).danger) gd.setColor(bord);
                }catch(NullPointerException e){}
                gd.fillRect(i - vehicule.x0+200 - (int)vehicule.vitesseX , 500 - sol.points.get((Integer)i).y, 5, 5);

                if(!sol.points.get((Integer)i).danger){
                    gd.setColor(fond);
                }if(!sol.points.get((Integer)i).disparait){
                    gd.fillRect(i - vehicule.x0+200 - (int)vehicule.vitesseX  , 500 - sol.points.get((Integer)i).y +5 , 5,550 - sol.points.get((Integer)i).y );
                }
                //Fait tomber le terrain quand le joueur passe dessus;
                if( sol.points.get((Integer)i).disparait && sol.points.get((Integer)i).danger){
                    sol.points.get((Integer)i).y -- ;
                }

                if(sol.points.get((Integer)i).boost){
                    gd.setColor(Color.PINK);
                    gd.rotate(-Math.atan(sol.pente(i)), i - vehicule.x0+200 - (int)vehicule.vitesseX , 475 - sol.points.get((Integer)i).y);
                    //Dessine un bosst pour rider
                    for( int j = 0; j <3 ;++j){
                        Polygon boost = new Polygon();
                        boost.addPoint(i - vehicule.x0+200 - (int)vehicule.vitesseX +10*j - 17, 485 - sol.points.get((Integer)i).y );
                        boost.addPoint(i - vehicule.x0+200 - (int)vehicule.vitesseX + 5+10*j- 17, 485 - sol.points.get((Integer)i).y );
                        boost.addPoint(i - vehicule.x0+200 - (int)vehicule.vitesseX + 15+10*j- 17, 475 - sol.points.get((Integer)i).y );
                        boost.addPoint(i - vehicule.x0+200 - (int)vehicule.vitesseX + 5+10*j- 17, 465 - sol.points.get((Integer)i).y );
                        boost.addPoint(i - vehicule.x0+200 - (int)vehicule.vitesseX +10*j- 17, 465 - sol.points.get((Integer)i).y );
                        boost.addPoint(i - vehicule.x0+200 - (int)vehicule.vitesseX + 10+10*j- 17, 475 - sol.points.get((Integer)i).y );
                        gd.fillPolygon(boost);
                    }
                    gd.rotate(Math.atan(sol.pente(i)), i - vehicule.x0+200 - (int)vehicule.vitesseX , 475 - sol.points.get((Integer)i).y);
                }
                if( sol.points.get((Integer)i).piece){
                    gd.drawImage(bgImage,i - vehicule.x0+200 - (int)vehicule.vitesseX , 475 - sol.points.get((Integer)i).y, 25,25, null);
                }
            }
    }
    
}
