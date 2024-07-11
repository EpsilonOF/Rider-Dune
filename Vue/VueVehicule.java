package Vue;

import Model.Vehicule;
import java.awt.*;

public class VueVehicule {

    public Vehicule vehicule;
    public static int aRouge = 56;
    public static int aVert = 12;
    public static int aBleu = 97;
    public static int bRouge = 53;
    public static int bVert = 182;
    public static int bBleu = 187;
    public static int [] couleurCode = {aRouge, bRouge, aVert, bVert, aBleu, bBleu};
    public static VueVehicule vueVec = new VueVehicule();

    public VueVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
    public VueVehicule(){}
   
    public void drawVehicule(Graphics2D gd){
        Color a = new Color(couleurCode[0], couleurCode[2], couleurCode[4]);
        Color b = new Color(couleurCode[1], couleurCode[3], couleurCode[5]);
        gd.rotate(-vehicule.anglevue , 200, 490-vehicule.y);

        Polygon outline = new Polygon();
        outline.addPoint(172, 484 - vehicule.y);
        outline.addPoint(190, 474 - vehicule.y);
        outline.addPoint(205, 474 - vehicule.y);
        outline.addPoint(216, 484 - vehicule.y);
        outline.addPoint(226, 486 - vehicule.y);
        outline.addPoint(224, 496 - vehicule.y);
        outline.addPoint(174, 496 - vehicule.y);

        Polygon outline2 = new Polygon();
        outline2.addPoint(171, 483 - vehicule.y);
        outline2.addPoint(190, 473 - vehicule.y);
        outline2.addPoint(205, 473 - vehicule.y);
        outline2.addPoint(217, 483 - vehicule.y);
        outline2.addPoint(227, 487 - vehicule.y);
        outline2.addPoint(225, 497 - vehicule.y);
        outline2.addPoint(173, 497 - vehicule.y);
        gd.setColor(Color.WHITE);
        gd.fillPolygon(outline2);
        gd.setColor(Color.BLACK);
        gd.fillPolygon(outline);

        GradientPaint gradient = new GradientPaint(180,  475-vehicule.y, a, 220, 495-vehicule.y, b);
        gd.setPaint(gradient);
        Polygon p = new Polygon();
        p.addPoint(173, 485 - vehicule.y);
        p.addPoint(190, 475 - vehicule.y);
        p.addPoint(205, 475 - vehicule.y);
        p.addPoint(215, 485 - vehicule.y);
        p.addPoint(225, 487 - vehicule.y);
        p.addPoint(223, 495 - vehicule.y);
        p.addPoint(175, 495 - vehicule.y);
        gd.fillPolygon(p);

            //roues
            gd.setColor(Color.BLACK);
            gd.fillOval(210, 490-vehicule.y, 10, 10);
            gd.fillOval (180, 490-vehicule.y, 10, 10);
            //roues
            gd.setColor(Color.GRAY);
            gd.fillOval(212, 492-vehicule.y, 6, 6);
            gd.fillOval (182, 492-vehicule.y, 6, 6);
        
            //vitres
            Polygon olf1 = new Polygon();
            olf1.addPoint(179,  486 - vehicule.y);
            olf1.addPoint(196,  486-vehicule.y);
            olf1.addPoint(196,  479-vehicule.y);
            olf1.addPoint(189,  479-vehicule.y);
            gd.setColor(Color.WHITE);
            gd.fillPolygon(olf1);
            gd.setColor(Color.BLACK);
            
            Polygon fentere1 = new Polygon();
            fentere1.addPoint(180, 485-vehicule.y);
            fentere1.addPoint(195, 485-vehicule.y);
            fentere1.addPoint(195, 480-vehicule.y);
            fentere1.addPoint(190, 480-vehicule.y);
            gd.fillPolygon(fentere1);

            Polygon olf2 = new Polygon();
            olf2.addPoint(212, 486-vehicule.y);
            olf2.addPoint(199, 486-vehicule.y);
            olf2.addPoint(199, 479-vehicule.y);
            olf2.addPoint(204, 479-vehicule.y);
            gd.setColor(Color.WHITE);
            gd.fillPolygon(olf2);
            gd.setColor(Color.BLACK);
        
            Polygon fenetre2 = new Polygon();
            fenetre2.addPoint(210, 485-vehicule.y);
            fenetre2.addPoint(200, 485-vehicule.y);
            fenetre2.addPoint(200, 480-vehicule.y);
            fenetre2.addPoint(205, 480-vehicule.y);
            gd.fillPolygon(fenetre2);
            gd.rotate(vehicule.anglevue , 200, 490-vehicule.y);        
    }
    public void drawParam(Graphics2D gd){
        Color a = new Color(couleurCode[0], couleurCode[2], couleurCode[4]);
        Color b = new Color(couleurCode[1], couleurCode[3], couleurCode[5]);

        Polygon outline = new Polygon();
        outline.addPoint(172, 484);
        outline.addPoint(190, 474);
        outline.addPoint(205, 474);
        outline.addPoint(216, 484);
        outline.addPoint(226, 486);
        outline.addPoint(224, 496);
        outline.addPoint(174, 496);

        Polygon outline2 = new Polygon();
        outline2.addPoint(171, 483);
        outline2.addPoint(190, 473);
        outline2.addPoint(205, 473);
        outline2.addPoint(217, 483);
        outline2.addPoint(227, 487);
        outline2.addPoint(225, 497);
        outline2.addPoint(173, 497);
        gd.setColor(Color.WHITE);
        gd.fillPolygon(outline2);
        gd.setColor(Color.BLACK);
        gd.fillPolygon(outline);

        GradientPaint gradient = new GradientPaint(180,  475, a, 220, 495, b);
        gd.setPaint(gradient);
        Polygon p = new Polygon();
        p.addPoint(173, 485);
        p.addPoint(190, 475);
        p.addPoint(205, 475);
        p.addPoint(215, 485);
        p.addPoint(225, 487);
        p.addPoint(223, 495);
        p.addPoint(175, 495);
        gd.fillPolygon(p);

            //roues
            gd.setColor(Color.BLACK);
            gd.fillOval(210, 490, 10, 10);
            gd.fillOval (180, 490, 10, 10);
            //roues
            gd.setColor(Color.GRAY);
            gd.fillOval(212, 492, 6, 6);
            gd.fillOval (182, 492, 6, 6);
        
            //vitres
            Polygon olf1 = new Polygon();
            olf1.addPoint(179,  486 );
            olf1.addPoint(196,  486);
            olf1.addPoint(196,  479);
            olf1.addPoint(189,  479);
            gd.setColor(Color.WHITE);
            gd.fillPolygon(olf1);
            gd.setColor(Color.BLACK);
            
            Polygon fentere1 = new Polygon();
            fentere1.addPoint(180, 485);
            fentere1.addPoint(195, 485);
            fentere1.addPoint(195, 480);
            fentere1.addPoint(190, 480);
            gd.fillPolygon(fentere1);

            Polygon olf2 = new Polygon();
            olf2.addPoint(212, 486);
            olf2.addPoint(199, 486);
            olf2.addPoint(199, 479);
            olf2.addPoint(204, 479);
            gd.setColor(Color.WHITE);
            gd.fillPolygon(olf2);
            gd.setColor(Color.BLACK);
        
            Polygon fenetre2 = new Polygon();
            fenetre2.addPoint(210, 485);
            fenetre2.addPoint(200, 485);
            fenetre2.addPoint(200, 480);
            fenetre2.addPoint(205, 480);
            gd.fillPolygon(fenetre2);
            gd.rotate(vehicule.anglevue , 200, 490);
    }

    public void drawVie(Graphics2D gd){
        
        for(int i=0;i<vehicule.meche;i++){
            gd.setColor(Color.GREEN);
            int x= 750+i*60;
            int y= 50;
            int width = 50;
            gd.fillRect(x, y, width, 10);
        }
    }

    public void draw(Graphics2D gd){
        drawVehicule(gd);
        drawVie(gd);
    }

    
}
