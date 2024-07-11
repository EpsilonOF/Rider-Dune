package Model;
public class Vehicule {
    public int x, y,x0,y0;
    public double t = 0;
    public double angle;
    public double anglevue;
    public static double v = 0;
    public static double g = 9.81;
    public double vitesseX;
    public Terrain terrain;
    public boolean saut = false;
    public boolean tombe = false;
    public int meche;
    public int score;
    public int scoreTourne;

    public Vehicule(Terrain terrain){
        this.terrain = terrain;
        this.y = terrain.points.get((Integer)x).y;
        this.x  = 200;
        this.x0 = x;
        this.y0 = y;
        this.angle = Math.atan(pente(x));
        this.anglevue = angle;
        this.meche = 1;
        this.score = x/500;
    }
    //fonction qui permet de faire gerer les mouvement du vehicule qui differe selon le type de vehicule
    public void move(){}
    //fonction pour le mouvement du vehicule
    public void trajectoire_courbe(){
        anglevue += 0.1;
        angle = anglevue;
        t += 0.1;
        x = (int)(Math.cos(angle) * v*t+x0);
        y = (int)(Math.sin(angle) * v*t - 0.5*g*t*t + y0);
    }
    //fonction qui permet de faire avancer le vehicule
    public void avancer(){
        for(int i = x; i<=x+(Math.cos(angle) * v * 0.1);i++){
            if(terrain.points.get((Integer)i).piece){
                Conn.gagne(1);
            }
            terrain.points.get((Integer)i).piece = false;
            if(terrain.points.get((Integer)i).boost){
                Vehicule.v = 140;
            }
            if( terrain.points.get((Integer)i).disparait){
                terrain.points.get((Integer)i-1).danger = true;
            }
        }
        x += (Math.cos(angle) * v * 0.1);
        y = terrain.points.get((Integer)x).y;
    }

    public void setPosition(int x, int y){  
        this.x0 = x;
        this.y0 = y;
    }
    //la pente au sol
    public double pente(int x){
        if( terrain.points.get((Integer)(x+20)).danger){
            return pente(x-1);
        }
        if( terrain.points.get((Integer)(x-20)).y== -1000){
            return pente(x+1);
        }
        return (double)((double)terrain.points.get((Integer)(x+20)).y - (double)terrain.points.get((Integer)(x-20)).y) / (double)((double)terrain.points.get((Integer)(x+20)).x - (double)terrain.points.get((Integer)(x-20)).x);
    }

    //la pente dans les airs
    public double penteAir(){
        return (double)((Math.sin(angle) * v * t - 0.5 * g * t * t*(1.5) + y0 - y) / (Math.cos(angle) * v * t*(1.5) + x0 - x));
    }
}