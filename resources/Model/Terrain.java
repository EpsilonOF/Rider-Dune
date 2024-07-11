package Model;
import java.util.HashMap;

public class Terrain {
    public HashMap<Integer, Point> points;

    public final int LARGEUR = 2000;
    public double tmp;
    public int section = 0;

    public Terrain(){
        points = new HashMap<Integer, Point>();
        initialisationLigne();

    }
     public void initialisationDune(){
        //initialisation de la liste de points a 3000 normalement
        double rand = Math.random();
        if( section != 0){
           while(Math.abs((int) (Math.sin(0.01*section)*(100*(Math.cos(section*0.003*rand)))-100) - (int)points.get((Integer)(section -1)).y )>2 ){
                rand = Math.random();
            }
        }
        for(int i = section; i< section+ LARGEUR; i++){
            Point point = new Point();
            point.x =  section+i;
            point.y = (int) (Math.sin(0.01*i)*(100*(Math.cos(i*0.003*rand)))-100) ;
            double piece = Math.random();
            points.put((Integer)i, point);
            if(piece  < 0.0005 && i > 200){
                for(int j = 0; j< 5; j++){
                    points.get((Integer)(i-j*20)).piece = true;
                }
            }
            if(piece >0.9996){
                point.boost = true;
            }
       }
       section+=LARGEUR;
       tmp = rand;
    }

        public void initialisationRider(double coef){
        //initialisation de la liste de points a 3000 normalement
        double rand = Math.random();
        if( section != 0){
            while(Math.abs((int) (Math.sin(0.01*section)*(100*(Math.cos(section*0.003*rand)))-100) - (int)points.get((Integer)(section -1)).y )>2 ){
                rand = Math.random();
            }
        }
        for(int i = section; i< section+ LARGEUR; i++){
            Point point = new Point();
            point.x =  section+i;
            point.y = (int) (Math.sin(0.01*i)*(100*(Math.cos(i*0.003*rand)))-100) ;
            double piece = Math.random();
            points.put((Integer)i, point);
            if(piece  < 0.0005 && i > 200){
                for(int j = 0; j< 5; j++){
                    points.get((Integer)(i-j*20)).piece = true;
                }
            }
            if(piece >0.9999){
                point.boost = true;
            }
            if(piece >0.999&& i>150 && pente(i- 11) == 0){
                    for(int j = 0; j< 100; j++){
                        points.get((Integer)(i-j)).danger = true;
                        points.get((Integer)(i-j)).y = -200;                   
                        }
                    }
            if( piece>0.1 && piece <0.1001+coef){
                    for(int j = 0; j< 300; j++){
                        points.get((Integer)(i-j)).disparait = true;                  
                    }
                }
        }
        section+=LARGEUR;
        tmp = rand;
    }

    public void initialisationLigne(){
        for(int i = section; i < section+LARGEUR; i++){
            Point p = new Point();
            p.x = i;
            p.y = -60;
            points.put((Integer)i,p );
        }
        section += LARGEUR;
    }

    public double pente(int x){
        return (double)((double)points.get((Integer)(x+10)).y - (double)points.get((Integer)(x-10)).y) / 20;
    }
}
