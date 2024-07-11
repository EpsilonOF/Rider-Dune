package Model;

import java.io.IOException;

public class Conn {
    public static String fichier= "profils/Invite";
    public static boolean estConnecte = false;
    public static String skinActuelBalle= "../resources/blanc.png";
    public static String imageDune = "../resources/dune1.png";
    public static String imageRider = "../resources/rider1.png";
    public static void connexion(String file){
        fichier = "profils/"+file;
        estConnecte = true;
    }

    public static void deconnexion(){
        fichier = "profils/Invite";
        estConnecte = false;
    }

    public static String profil(){
        return fichier.substring(8);
    }

    public static int argent(){
        return (int)Integer.valueOf(Fichiers.readLigne(2,fichier).substring(10));
    }
    public static void  gagne(int n){
        int argent = argent();
        argent+=n;
        try{
            Fichiers.remplacerLigne(2,"Monnaie : "+argent,fichier);   
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    
}
