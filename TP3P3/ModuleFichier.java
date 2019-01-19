
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.StandardCopyOption;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 * Module utilitaire qui permet de récupérer et de sauvegarder les données des
 * maîtres, des engagements et des assignations.
 *
 * (voir énoncé tp1A18INF111).
 *
 * @author Pierre Bélisle
 * @revisor Mélanie Lord
 * @student Vincent Audette
 * @version Automne 2018
 */
public class ModuleFichier {

//#region générer les partis

   /**
    * Génère un parti de soit gauche, centre ou droite pour finalement venir
    * instancier ce type d'objet et l'ajouter au à la collection de objsParti
    * 
    * @param election l'objet avec les données de l'application
    */
   public static void genererPartis(Election election){
        /*
         * Stratégie: On génère un nombre entre 1 et NB_CATEGORIES_PARTI
         * si et on le compare a PARTI_DE_GAUCHE, PARTI_DU_CENTRE, 
         * PARTI_DE_DROITE et si la valeur est équivalente on instancie
         * un objet du type. ensuite on ajoute cet objet de type Parti
         * à la collection dans la classe élection.
         */

     for (int i = 0; i < election.obtenirNomsParti().length; i++) {

        Parti partiAlea = instancierTypeParti(

                // Nombre aléatoire qui sélectionne le parti
                UtilitaireMath.alea(1,Constantes.NB_CATEGORIES_PARTI),
                // Le nom du parti ajouté
                election.obtenirNomsParti()[i]

        );

        election.ajouterObjParti(partiAlea);
     }
   }

    /**
     * Instancie le type de parti selon la valeur aléatoire passer en paramèrtre
     * @param numAlea la valeur qui déterminera le type de parti
     * @param nom le nom du parti qui sera instancier
     * @return le nouveau parti, soit PartiDeGauche, PartiDuCentre ou PartiDeDroite
     */
   private static Parti instancierTypeParti(int numAlea, String nom){

       /*
        * Stratégie: Selon la valeur on sélectionne dans un switch le
        * type de parti qui sera instancier
        */

        Parti partiTemporaire = null;

        switch(numAlea){
                case Constantes.PARTI_DE_GAUCHE:
                        partiTemporaire = new PartiDeGauche(nom);
                break;
                case Constantes.PARTI_DU_CENTRE:
                        partiTemporaire = new PartiDuCentre(nom);
                break;
                case Constantes.PARTI_DE_DROITE:
                        partiTemporaire = new PartiDeDroite(nom);
                break;
        }
        return partiTemporaire;
    }


   /**
    * Génère des supporteurs pour tout les partis et la catégorie de ceux-ci
    * sont de type soit OBNL, Circonscription ou Député est déterminer par une
    * valeure généré aléatoirement
    * @param election les données de l'application
    */
    private static void genererSupporteurs(Election election){

        /*
         * Stratégie: on parcour tout les parti et pour chaqun une valeur
         * aléatoire est déterminer pour ajoutrer un supporteur qui sera
         * généré par son SP respecifs
         */

        for (int i = 0; i < election.obtenirNomsParti().length; i++) {

         switch(election.tabObjsParti()[i].getValCategorie()){
             case Constantes.PARTI_DE_GAUCHE:
                 genererOBNLSupporteurs(election, i);
                 break;
             case Constantes.PARTI_DU_CENTRE:
                 genererCiconsSupportrices(election, i);
                 break;
             case Constantes.PARTI_DE_DROITE:
                 genererDeputeSupporteurs(election, i);
                 break;
         }
        }


    }

    /**
     * Génère des (une quantitée aléatoire entre 1 et NB_OBNL_MAX) supporteurs
     * d'organisme a but non lucratif
     * @return le parti de gauche avec la collection OBNL initialisé avec
     * des chaînes de caractère null selon le nombre de valeur déterminer
     * aléatoirement
     */
    private static void genererOBNLSupporteurs(Election election, int pos){

        PartiDeGauche partiGauche = (PartiDeGauche) election.obtenirPartiA(pos);

        int valAlea = UtilitaireMath.alea(1,Constantes.NB_OBNL_MAX);

        for (int i = 0; i < valAlea; i++) {
                partiGauche.ajouterOBNL(null);
        }

        election.tabObjsParti()[pos] = partiGauche;
    }


    /**
     * Génère des (une quantitée aléatoire entre 1 et NB_CIRCONSCRIPTION)
     * circonscriptions supportrices
     * @param election les données de l'élection
     * @return
     */
    private static void genererCiconsSupportrices(Election election,
                                                       int pos){
        /*
         * Stratégie: Avec un nombre aléatoire de supporteur, générer à la
         * bonne dimension. On ajoute les circonscriptions supportrice à la
         * collection du parti de centre. Une fois les supporteurs bien
         * placé, met le parti générer à cette position
         */

        //  Retrouver le parti selon la positon
        PartiDuCentre partiCentre = (PartiDuCentre) election.obtenirPartiA(pos);

        // Génère une valeure aléatoire pour le nombre supporteur
        int valAlea = UtilitaireMath.alea(1,Constantes.NB_CIRCONSCRIPTION);

        Circonscription circonsTemp;

        int nbCirconsAjouter = valAlea;

        while(nbCirconsAjouter >= 1){

            circonsTemp = election.obtenirCirconsA(
                    UtilitaireMath.alea( 0, (valAlea - 1) ));

            if(!partiCentre.circonsSupporterContains(circonsTemp)){
                partiCentre.ajouterCirconscription(circonsTemp);
                nbCirconsAjouter--;
            }
        }

        election.tabObjsParti()[pos] = partiCentre;
    }

    /**
     * On sélectionne génère un montant aléatoire pour le nombre de députés
     * à ajouter, et on ajoute ceux-ci si il sont de parti de droite.
     * @param election les données de l'élection
     */
    private static void genererDeputeSupporteurs(Election election, int pos){

        /*
         * Stratégie: On obtient un tableau de parti de droite avec le SP
         * lesDeputeDroite et avec ce tableau on génère un nombre aléatoire
         * de supporteurs à générer et on ajoute ces supporteurs au tbaleau
         * d'objet à la position courante dans le tableau tabObjsParti de la
         * classe election
         */

        // Initialisation d'un parti de droite avec avec nom du parti
        PartiDeDroite partiDroite = (PartiDeDroite) election.obtenirPartiA(pos);

        Depute deputeTemp; // capturer infos

        // Le nombre de député de parti droite contenu dans objsDepute
        Depute[] tabDeputeDroite = lesDeputeDroite(election,
                Constantes.CATEGORIE_DROITE);

        // Nombre de députés de droites
        int nbDepute = tabDeputeDroite.length;

        // Nombre de députés à ajouter
        int deputeAjouter = UtilitaireMath.alea(1, nbDepute);

        while(deputeAjouter >= 0){
            deputeTemp = tabDeputeDroite[UtilitaireMath.alea(0, nbDepute-1)];

            if( !partiDroite.deputeEntrepreneursContains(deputeTemp)){
                partiDroite.ajouterDepute(deputeTemp);
                deputeAjouter--;
            }
        }
        election.tabObjsParti()[pos] = partiDroite;
    }

    /**
     * Compte le nombre de Parti de Droite contenu dans la collection de député
     * @param election les données de l'élection
     * @param strPDroite le parti que l'on cherche en forme de string
     * @return le montant de Député de type droite
     */
    private static Depute[] lesDeputeDroite(Election election,
            String strPDroite){

        /*
         * On parcour tout les députés, si il est de droite on l'ajoute à
         * un vector. Ensuite on retourne ces données sous forme de taboleau de
         * Depute.
         */

        Vector<Depute> deputesDroite = new Vector<>();

        for (Depute depute: election.tabObjsDepute()){
            if(election.obtenirCategorieDepute(depute).equals(strPDroite)){
                deputesDroite.add(depute);
            }
        }
        return deputesDroite.toArray(new Depute[deputesDroite.size()]);
    }

    //#endregion

//#region gestion des données de l'élection

   /**
    * Decompose les informations du fichier pour ensuite capturer les données
    * @param election
    * @param fic
    * @throws IOException
    */
   private static void decomposerFicTxt(Election election, File fic) 
                                                        throws IOException{

        /*
         * Stratégie : On utilise un BufferedReader avec FileReader du 
         * fichier passer en paramètre. Ensuite nous lisons ligne par 
         * ligne les données toute en les séparant avec la fonction split. 
         * Les information sont ensuite placer dans chaque attributs avec 
         * le SP placerInfos
         */

        
        try(BufferedReader bReader = 
                new BufferedReader(new FileReader(fic))){

                String lignePasser;

                while((lignePasser = bReader.readLine()) != null){
                        placerInfos(election, 
                        lignePasser.split(Constantes.TABULATION));
                }

        }catch(FileNotFoundException e){
                e.printStackTrace();
        }
   }

   /**
    * Met les données passer en paramètre dans leurs attributs via
    * les méthodes de la classe Election
    *
    * @param chaqueInfo Contient:
    * [0] numéro de circonscription
    * [1] nom de circonscription
    * [2] nom du parti
    * [3] prénom du député
    * [4] nom du député
    */
   private static void placerInfos(Election election, String[] chaqueInfo){

        /*
        * Startégie: utiliser les SP suivant ->
        *
        *  void - ajouterDepute(int circonscription, String nomDepute,
        *         int noParti)
        *  int - ajouterCirconscription(String nom, int numéro)
        *  int - ajouterNomParti(String nomParti)
        *
        *  Ces methodes ajoutes respectivement les données
        */
        
        election.ajouterDepute(
                //Numéro Circonscription
                election.ajouterCirconscription(chaqueInfo[1], 
                Integer.parseInt(chaqueInfo[0])), 

                //Nom Député
                chaqueInfo[3] + Constantes.ESPACE + chaqueInfo[4],

                //Numéro de Parti
                election.ajouterNomParti(chaqueInfo[2])
        );
        
   }

   //#endregion

//#region récupérer et sauvegarder l'élection

   public static final int SAUVE = 0;
   public static final int OPEN = 1;

   /**
    * Méthode utilitaire privée qui permet d'obtenir un fichier sélectionné par
    * l'utilisateur. L'extension ne doit pas contenir le "."
    *
    * @param description Apparaît dans "type de fichier" pour guider
    * l'utilisateur.
    *
    * @param extension Les 3 lettres en suffixe au point d'un nom de fichier.
    *
    * @param type : OUVRE ou SAUVE Sert à avoir le bon bouton dans le
    * JFileChooser, selon le type on a "ouvrir" ou "enregistrer".
    *
    * @return null si le nom n'est pas valide ou si annulé.
    *
    *@author Pierre Bélisle
    */
   public static File obtenirFic(String description,
           String extension,
           int type) {

      /*
       * Stratégie : On utilise le JFileChooser de javax.swing selon 
       * le type (SAUVE ou OPEN) reçue.
       * 
       * FileNameExtensionFilter permet de filtrer les extensions.
       */
      
      //Création du sélectionneur de fichier (répertoire courant).
      JFileChooser fc = new JFileChooser(".");

      File fic = null;
      int reponse;

      //On filtre seulement les fichiers avec l'extension fournie
      FileNameExtensionFilter filter
              = new FileNameExtensionFilter(extension, extension);

      fc.setDialogTitle(description);
      fc.addChoosableFileFilter(filter);
      fc.setFileFilter(filter);

      //On obtient le nom du fichier à ouvrir en lecture ou en écriture?
      if (type == OPEN) {
         reponse = fc.showOpenDialog(null);
      } else {
         reponse = fc.showSaveDialog(null);
      }

      //On obtient le fichier seulement si le fichier a été choisi
      if (reponse == JFileChooser.APPROVE_OPTION) {
         fic = fc.getSelectedFile();
      }

      return fic;
   }

   /**
    * Récupérer les données de l'élection d'un fichier text
    * @param election
    */
   public static void getElection(Election election){

        /*
        *  Stratégie: Demander à l'utilisateur de choisir un fichier text
        *  prendre le données et les placer dans les attributs de l'élection
        */

        File ficElection = obtenirFic(Constantes.OBTENIR_TXT[0], 
                Constantes.OBTENIR_TXT[1], OPEN);

        if(ficElection != null){
                try{
                        decomposerFicTxt(election, ficElection);
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }
        genererPartis(election);
        genererSupporteurs(election);
   }

   /**
    * Le SP principal pour la sauvegarde du fichier binaire
    *
    * @param election enregistrement regroupant les données de l'élection.
    * @author Pierre Belisle
    */
    public static void sauverFichierBinaire(Election election) {

        /*
         * Stratégie : On utilise  un FileOutputStream qui permet d'écrire
         * les données de l'élection d'un coup.
         */
        
        File fic
                = obtenirFic(Constantes.SAUVEGARDER_FICHIER_BIN[0],
                        Constantes.SAUVEGARDER_FICHIER_BIN[1],
                        SAUVE);
  
        if (fic != null) {
           ObjectOutputStream tampon = null;
  
           try {
              //Crée le fichier et ouverture du tampon d'écriture		
              FileOutputStream tamponFic = new FileOutputStream(fic);
              tampon = new ObjectOutputStream(tamponFic);
  
              //	Écriture et fermeture.
              tampon.writeObject(election);
              tampon.close();
  
           } catch (FileNotFoundException e1) {
  
              e1.printStackTrace();
  
           // Une erreur de lecture, on détruit le fichier si on a eu
           // le temps de le créer.
           } catch (IOException e) {
  
              // On obtient le chemin du fichier pour le détruire.
              Path path
                      = FileSystems.getDefault().getPath(fic.getName());
  
              // Destruction du fichier ouvert (ou créé) s'il y a un problème.
              try {
                 tampon.close();
                 Files.delete(path);
  
              } catch (IOException e1) {
                 e1.printStackTrace();
              }
  
              e.printStackTrace();
           }
        }
     }
  


     /**
      * Sélectionne un fichier binaire et l'assigne à l'objet élection
      * @return l'objet Election
      */
     public static Election getElectionBinaire(){

        /*
         * Stratégie : Utilisateur obtient le fichier binaire. On converti le
         * fichier binaire en objet election avec le SP convertir2Elecection
         */

             File ficBinaire = obtenirFic(Constantes.OBTENIR_FICHIER[0],
             Constantes.OBTENIR_FICHIER[1] , OPEN);

                return (ficBinaire != null) ? 
                convertir2ObjElection(ficBinaire) : null;
     }


     /**
      * Récupérer les données de l'élection d'un fichier binaire
      * @return election lue en binaire
      */
     private static Election convertir2ObjElection(File ficBinaire){

        /*
         * Stratégie: ouvrir un ObjectInputStream en lisant le
         * fichier passer en paramètre avec FileInputStream
         * pour ensuite retourner l'objet election du fichier binaire
         *
         */

         Election electionTemp = null; 

        try(ObjectInputStream objStream = 
                new ObjectInputStream(new FileInputStream(ficBinaire))){
        
                electionTemp = (Election) objStream.readObject();

        } catch(FileNotFoundException e){
                e.printStackTrace();
        } catch(Exception e){
                JOptionPane.showMessageDialog(null, 
                Constantes.FORMAT_INVALID);
        }

        return electionTemp;
     }

     //#endregion
}
   