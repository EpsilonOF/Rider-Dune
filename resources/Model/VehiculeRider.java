package Model;

public class VehiculeRider extends Vehicule{
    public VehiculeRider(Terrain terrain){
        super(terrain);
    }
    //fonction qui permet de faire gerer les mouvement du vehicule
    public void move(){
        double nextPosY = Math.sin(angle) * v*t - 0.5*g*t*t + y0; 
        double nextPosX = Math.cos(angle) * v*t+x0;
        int yTerrain = terrain.points.get((Integer)(x+1)).y;

        //ajoute 1 a super.score tout les 500m parcourus
        super.score=(int)(x/500)+super.scoreTourne;
        if(!saut){
            mouvement_sol();
        }
        else if(nextPosY < yTerrain && saut){
            atterissage();
        }
        else if(saut){
            x = (int)nextPosX;
            y = (int)nextPosY;
            vitesseX = Math.cos(angle) * v*t;
        }
    }

    //fonction pour le mouvement du vehicule lorsque la vehicule est sur le sol
    public void mouvement_sol(){
        angle = Math.atan(pente(x));
        avancer();
        this.setPosition(x, y);
        anglevue = angle;
        if(terrain.points.get((Integer)x).danger){
            meche --;
        }
        try{
            if((Math.atan(pente(x)) - Math.atan(pente(x+3)))*v > 3 || terrain.points.get((Integer)((int)(Math.cos(angle) * v*t+x0))).danger){
                angle = Math.atan(pente(x));
                saut = true;
                t = 0;
            }
            anglevue = angle;
        }catch(NullPointerException e){}
    }

    //fonction permet de gerer les mouvement de la voiture pendant l'atterisage 
    public void atterissage(){
        avancer();
        saut = false;
        this.setPosition(x, y);
        this.angle = Math.atan(pente(x));
        vitesseX = 0; 
        v -= v*0.1;;   
        if(anglevue%(Math.PI*2)>angle+Math.PI/2){
            if(anglevue%(Math.PI*2)<angle+(3*Math.PI/2)){
                this.meche--;
            }
        }
        if(anglevue%(Math.PI*2)<=angle+Math.PI/2 && anglevue%(Math.PI*2)>=angle-Math.PI/2){
            super.scoreTourne+=anglevue/Math.PI;
        }
        anglevue = angle;
    }
    public void rotation(){
        anglevue += 0.1;
    }

    // rotation par rapport au centre
    public void rotationCentre(double angle){
        double xr = (x - 25) * Math.cos(angle) - (y - 20) * Math.sin(angle) + 25;
        double yr = (x - 25) * Math.sin(angle) + (y - 20) * Math.cos(angle) + 20;
        x += xr;
        y += yr;
    }
}