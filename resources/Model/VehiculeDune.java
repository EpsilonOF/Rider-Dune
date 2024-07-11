package Model;

public class VehiculeDune extends Vehicule{
    public VehiculeDune(Terrain terrain){
        super(terrain);
    }

    public void move(){
        double nextPosY = Math.sin(angle) * v*t - 0.5*g*t*t + y0; 
        double nextPosX = Math.cos(angle) * v*t+x0;
        int yTerrain = terrain.points.get((Integer)(x+1)).y;;

        if(nextPosY < yTerrain && saut){
            atterissage();
        }
        else if(saut){
            x = (int)nextPosX;
            y = (int)nextPosY;
            vitesseX = Math.cos(angle) * v*t;
        }else{
            angle = Math.atan(pente(x));
            avancer();
            this.setPosition(x, y);
            anglevue = angle;
        }
    }

    public void atterissage(){
        avancer();
        saut = false;
        if(pente(x) >0.1  && Vehicule.v > 130){
            meche--;
        }
        this.setPosition(x, y);
        this.angle = Math.atan(pente(x));
        if(g == 50){
            v+=20;
        }
        g = 9.81;
        vitesseX = 0;    
        tombe = false;
    }

}