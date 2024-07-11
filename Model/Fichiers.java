package Model;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Fichiers {
    public static void ajouterTxt(String donnee, String fichier){
        String t = lireFichier(fichier);
        try {
            FileWriter fw = new FileWriter(fichier);
            fw.write(t+ donnee);
            fw.close();
          } catch (IOException e) {
            System.out.println("erreur ajouterTxt");
          }
    }
    public static void remplacerLigne(int n,String donnee, String fichier) throws IOException{
        int i = 1;
        String t = "";
        try {
            File myObj = new File(fichier);
            Scanner myReader = new Scanner(myObj);
            
            while (myReader.hasNextLine()) {
                if(i !=n){
                    String data = myReader.nextLine();
                    t+=data+ '\n';
                }else{
                    t+=donnee+'\n';
                    myReader.nextLine();
                    
                }
                i++;
                
            }
            FileWriter fw = new FileWriter(fichier);
            myReader.close();
            fw.write(t);
            fw.close();
            
        }
        catch (FileNotFoundException e) {
            System.out.println("erreur Remplacer Ligne");
        }
    }

    public static String readLigne(int n, String fichier){
        String t ="";
        int i = 0;
        try {
            File myObj = new File(fichier);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine() && i<n) {
              String data = myReader.nextLine();
              t = data;
              i++;
            }
            if(i<n){
                return "";
            }
            myReader.close();
            return t;
        }
        catch (FileNotFoundException e) {
            System.out.println("erreur readLigne");
        }
        return t;
    }

    public static void ajouterFichier(String filename){
        try{
            File nouveau = new File(filename);
            if (!nouveau.createNewFile()) {
            }
        }
        catch(IOException e){
            System.out.println("erreur rajouterFichier");
        }
    }

    public static String lireFichier(String fichier){
        String t ="";
        try {
            File myObj = new File(fichier);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              t+=data+ '\n';
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("erreur lireFichier");
        }
        return t;
    }
}