import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

/**
 * Classe qui contient les informations de l'élection
 * @author Vincent Audette
 * @version Automne 2018
 */
public class Election implements Serializable{

    //#region Attributs
    /*
     * L'année de l'élection
     */
    private int anneeElection;

    /*
     * Collections des noms des circonscriptions, partis et députés
     */
    private Vector<String> nomsCircons;
    private Vector<String> nomsPartis;
    private Vector<String> nomsDepute;

    /*
     * Collection des objets de circonscription et député
     */ 
    private ArrayList<Circonscription> objsCircons;
    private LinkedList<Depute> objsDepute;
    private ArrayList<Parti> objsParti;

    /*
     * Index qui contient les circonscriptions en rangées et les 
     * partis en colonnes et évalue à un député
     * index[posCircons][posParti]
     */
    private int[][] index;

    //#endregion

    //#region Constructeurs

    /**
     * Initialisation de tout les attributs local avec l'année en paramètre
     * @param annee
     */
     public Election(int annee){

        anneeElection = annee;
        nomsCircons = new Vector<>();
        nomsPartis = new Vector<>();
        nomsDepute = new Vector<>();
        objsCircons = new ArrayList<>();
        objsDepute = new LinkedList<>();
        objsParti = new ArrayList<>();
     }

     //#endregion Constructeurs

    //#region  Méthodes pricipales

    /**
     * Retourne un tableau contenant le nom de tous les partis  d'une circonscription,
     * en ordre croissant.
     *
     * @param nom Le nom de la circonscription cherchée
     * @return Un tableau de noms des partis de la circonscription
     */
    public String[]  obtenirNomsPartisParCirconscription(String nom) {

        /*
         * STRATÉGIE: obtenir la positon du nom de circonscrition et ensuite
         * parcouvoir le tableau 2d pour trouver les valeurs équivalente à
         * celle de cette index.
         */

        // On converti le vecteur de partis en tableau de Strings
        String[] tabPartis = convertirVect2String(remplirVectSelonCircons(nom));
        // On met en ordre croissant les Partis
        Arrays.sort(tabPartis);
        return tabPartis;
    }

    private Vector<String> remplirVectSelonCircons(String nom){

        Vector<String> itemDeLaCircons = new Vector<>();

        // On parcour le tableau 2d d'index pour trouver les circons
        for (int i = 0; i <index[0].length; i++) {
                if(index[indexDeCirconscription(nom)][i] != Constantes.VIDE){
                    itemDeLaCircons.add(obtenirStrPartiA(i));
                }
        }
        return itemDeLaCircons;
    }

    private String[] convertirVect2String(Vector<String> vector){
        return vector.toArray(new String[vector.size()]);
    }

    /**
     * Retourne un tableau avec les noms des députés pour une circonscription
     * donnée. Le tableau retourné doit contenir les noms des députés dans le même
     * ordre que leur parti d’attache, dans le tableau lesPartis donné en paramètre.
     *
     * @param nom Le nom de la circonscription cherchée
     * @param lesPartis Un tableau contenant le nom de tous les partis de la
     *                                circonscription cherchée, en ordre croissant.
     * @return Le nom des députés de cette circonscription dans le même ordre que
     *                leur parti d’attache, dans le tableau lesPartis donné en paramètre.
     */
    public String[] obtenirNomsDeputesParCirconscription(String nom, String[]
            lesPartis) {

        Vector<String> vectDeputes = new Vector<>();
        int posCircons = nomsCircons.indexOf(nom);

        for (String parti : lesPartis) {
            int posParti = nomsPartis.indexOf(parti);
            if(index[posCircons][posParti] != Constantes.VIDE){
                vectDeputes.add(obtenirStrDeputeA(index[posCircons][posParti]));
            }
        }

        return convertirVect2String(vectDeputes);
    }



    /**
     * Retourne le nom de tous les députés d'un parti en ordre croissant.
     *
     * @param nomParti Le nom du parti cherché
     * @return Un tableau des noms des députés de ce parti
     */
    public String[] obtenirNomsDeputesParParti(String nomParti) {

        Vector<String> nomsDepute = new Vector<>();
        int posPart = indexDeParti(nomParti);

        for (int i = 0; i < index.length; i++) {
            if(index[i][posPart] != Constantes.VIDE){
                nomsDepute.add(obtenirStrDeputeA(index[i][posPart]));
            }
        }

        String[] tab = convertirVect2String(nomsDepute);
        Arrays.sort(tab);

        return tab;

    }

    /**
     * Retourne le nom de tous les supporteurs d'un parti en ordre croissant.
     *
     * @param nomParti Le nom du parti cherché
     * @return Un tableau des noms des supporteurs de ce parti
     */
    public String[]  obtenirNomsSupporteursParParti(String nomParti) {

        /*
         * STRATÉGIE: On obtient la position du parti dans le tableau pour
         * ensuit rechercher de quelle parti il est et selon son parti on
         * obtient les noms des supporteurs.
         */

        int pos = indexDeParti(nomParti);
        Parti p = tabObjsParti()[pos];
        String[] tab;

        if(p instanceof PartiDeDroite){

            tab = (((PartiDeDroite) p).convertirTab2String());

        }else if(p instanceof PartiDuCentre){

            tab = (((PartiDuCentre) p).convertirTab2String());
        }else {
            String[] tabTemp = {((PartiDeGauche) p).nbOBNL() + " OBNL"};
            tab = tabTemp;
        }

        Arrays.sort(tab);

        return tab;
    }

    /**
     * Ajoute une circonscription à la collection de Circonscription
     * @param nom
     * @param numero
     * @return index position où elle à été ajouter
     */
    public int ajouterCirconscription(String nom, int numero){

        /*
         * Stratégie: Créer un objet de ciroconscription qui sera
         * ajouté à la collection une fois que les données sont
         * récupérés. Retourner l'index de l'objet ajouter.
         * 
         */

         // Objet temporaire pour capture les données de la circonscription
        Circonscription objetCirconsTemp = new Circonscription();

        if(!nomsCircons.contains(nom)){

            // Ajouter le nom de circonscription à la collection de celui-ci
            nomsCircons.add(nom);

            //Capturer le données du nom et numero et les mettre dans l'objet
            objetCirconsTemp.setNomCircons(nom);
            objetCirconsTemp.setNumCircons(numero);
            objetCirconsTemp.setElu(Constantes.VIDE);

            // Ajouté l'objet à la collection de circonscription
            objsCircons.add(objetCirconsTemp);

        }

        // Retourner l'index du nom ajouté
        return nomsCircons.indexOf(nom);
    }


    /**
     * Verifi si le nom de parti est dans sa colletion si non il sera ajouté
     * @param nomParti
     * @return l'index du nom de parti dans la collection
     */
    public int ajouterNomParti(String nomParti){

        if(!nomsPartis.contains(nomParti)){
            nomsPartis.add(nomParti);
        }
        return nomsPartis.indexOf(nomParti);
    }

    /**
     * Créer un Deputé qui sera ajouter à la collection de Députés et à la 
     * collection des noms de Deputés si son nom n'est pas déja dans la 
     * collection de nomsDepute
     * 
     * @param circonscription
     * @param nomDepute
     * @param noParti
     */
    public void ajouterDepute(int circonscription, String nomDepute, 
    int noParti){
       
        /*
         * Stratégie: Vérifie si le Député passer en paramètre est dans la 
         * collection nomsDepute si il ne l'est pas, il sera ajouter et un 
         * objet de celui-ci sera créer.
         */

        if(!nomsDepute.contains(nomDepute)){

            // Ajouter le nom de député à la collection de celui-ci
            nomsDepute.add(nomDepute);

            // Objet Depute temporaire :
            // Capturer le données de la circonscription, le nom et
            // le numero de parti du député et les mettre dans l'objet
            Depute deputeTemp = new Depute(nomDepute, circonscription, noParti);

            // Ajouté l'objet à la collection des députés
            objsDepute.add(deputeTemp);
            
        }
    }

    /**
     * SP qui permet d'ajouter un Parti à la collection: objsParti
     * @param p Le parti à ajouter
     */
    public void ajouterObjParti(Parti p){
        objsParti.add(p);
    }



    /**
     * Ce SP crée un tableau 2D d'index, pour retrouver les données plus
     * éfficacement. Les circonscriptions forme les rangées, les
     * partis forme les colonnes et l'intersection réfère au numéro de député.
     * 
     */
    public void genererIndex(){

        /*
         * Stratégie: Initialiser la dimension de l'attribut local
         * index. Mettre tout les cases à Constantes.VIDE. Ensuite,
         * parcourir le tableau des députés et pour chaque objet député
         * récupérer, obtenir le numéro de numéro de circonscription(1)
         * et le numéro du parti(2). Mettre les index de positionnement
         * dans le tableau index tel que: index[(1)][(2)] et à cette
         * position mettre l'index du nom de député.
         */

        index = new int[obtenirNomsCirconscription().length]
                                        [obtenirNomsParti().length];

        // Mettre chaque index du tableau à Constantes.VIDE
        flip02VIDE(index);

        // Copie de la liste des députés pour pouvoir enlever de la liste
        LinkedList<Depute> depute = (LinkedList<Depute>) objsDepute.clone();
       
        // Député temporaire pour aller chercher des informations de l'objet
        Depute deputeTemp;
        
        for (int i = 0; i < obtenirNomsDepute().length; i++) {

            deputeTemp = depute.remove();

            index[deputeTemp.getNumCaseCirc()][deputeTemp.getNumParti()] 
            = nomsDepute.indexOf(deputeTemp.getNomDepute());
        }
    }

    /**
     * Met tout les 0 du tableau à Constantes.VIDE
     * @param index
     */
    private void flip02VIDE(int[][] index){

        for (int i = 0; i < index.length; i++) {
            for (int j = 0; j < index[0].length; j++) {
                    index[i][j] = Constantes.VIDE;
            }
        }
    }
    
    //#endregion  Méthodes pricipales

    //#region Obtenir données des collections

    public String[] obtenirNomsEtCatParti(){

        Vector<String> partisEtLeurCat = new Vector<>();

        for (Parti parti: tabObjsParti()) {
            partisEtLeurCat.add(parti.getNom() +
                    " (" + parti.getCategorie() +")");
        }

        return convertirVect2String(partisEtLeurCat);

    }

    /**
     * Obtien les partis de gauche
     * @return le tableau de tout les partis de gauche
     */
    public String[] obtenirPartiGauche() {
        Vector<String> partisGauche = new Vector<>();
        for (Parti p: tabObjsParti()) {
            if(p instanceof PartiDeGauche){
                partisGauche.add(p.getNom());
            }
        }
        return convertirVect2String(partisGauche);
    }

    /**
     * Obtenir les noms des Circonscriptions
     * @return tableau de la collection nomsCircons
     */
    public String[] obtenirNomsCirconscription(){
        return nomsCircons.toArray(new String[nomsCircons.size()]);
    }

    /**
     * Obtenir les noms des Partis
     * @return tableau de la collection nomsPartis
     */
    public String[] obtenirNomsParti(){
        return nomsPartis.toArray(new String[nomsPartis.size()]);
    }

    /**
     * Obtenir les noms des Députés
     * @return tableau de la collection nomsDepute
     */
    public String[] obtenirNomsDepute(){
        return nomsDepute.toArray(new String[nomsDepute.size()]);
    }

    /**
     * Obtenir le tableau d'instances du tableau Parti sous forme organisé
     * @return tab de string avec instances
     */
    public String[] obtenirInstancesParti(){

        String[] strParti = new String[objsParti.size()];
        int i = 0;

        for (Parti p: tabObjsParti()) {
            strParti[i] = p.getNom() + Constantes.ESPACE + p.getCategorie();
            i++;
        }
        Arrays.sort(strParti);
        return strParti;
    }

    /**
     * Obtient les noms des députer et les met en ordre alphabetique
     * @return tableau de députe bien classé
     */
    public String[] tabNomsDeputeClasse(){

        String[] nomsDepute = obtenirNomsDepute().clone();
        Arrays.sort(nomsDepute);

        return nomsDepute;
    }

    /**
     * Retour les items de la collection objsParti sous forme de tableau
     * @return tableau des objets de Parti
     */
    public Parti[] tabObjsParti(){

        return objsParti.toArray(new Parti[objsParti.size()]);

    }

    /**
     * Retourne les Circonscription dans la collection objsCircons sous forme
     * de tableau.
     * @return tableau des objets de Circonscription
     */
    public Circonscription[] tabObjsCircons(){

        return objsCircons.toArray(new Circonscription[objsCircons.size()]);

    }

    public Depute[] tabObjsDepute(){
        return objsDepute.toArray(new Depute[objsDepute.size()]);
    }

    /**
     * Obtenir le nombre de Circonscriptions
     * @return le nombre dans la collection objsCircons
     */
    public int obtenirNombreCirconscription(){
        return objsCircons.size();
    }

    /**
     * Obtenir le nombre de Député
     * @return le nombre dans la collection objsDepute
     */
    public int obtenirNombreDepute(){
        return objsDepute.size();
    }

    public int obtenirNombreParti(){
        return objsParti.size();
    }

    /**
     * Obtien le députe à la position en paramètre
     * @param position
     * @return string du députe
     */
    public String obtenirStrDeputeA(int position){
        return obtenirNomsDepute()[position];
    }

    /**
     * Obtien le parti à la position en paramètre
     * @param position
     * @return string du parti
     */
    public String obtenirStrPartiA(int position){
        return obtenirNomsParti()[position];
    }

    /**
     * Obtien la circonscription à la position en paramètre
     * @param position
     * @return string de la cironscription
     */
    public String obtenirStrCirconscriptionA(int position){
        return nomsCircons.get(position);
    }


    /**
     * Retourne la circonscription à une position particulière
     * @param position
     * @return une circonscription
     */
    public Circonscription obtenirCirconsA(int position){
        return objsCircons.get(position);
    }

    /**
     * Retourne le/la député(e) à une position particulière
     * @param position
     * @return un(e) député(e)
     */
    public Depute obtenirDeputeA(int position){
        return objsDepute.get(position);
    }

    /**
     * Retrouve le parti dans la collection de député
     * @param position la position du parti dans la collection
     * @return le parti recherché
     */
    public Parti obtenirPartiA(int position){
        return objsParti.get(position);
    }

    /**
     * Trouver l'index de cString dans la collection nomsCircons
     * @param cString
     * @return index de la circonscription
     */
    public int indexDeCirconscription(String cString){
        return nomsCircons.indexOf(cString);
    }

    /**
     * Trouver l'index de dString dans la collection nomsDepute
     * @param dString
     * @return index du député
     */
    public int indexDeDepute(String dString){
        return nomsDepute.indexOf(dString);
    }

    /**
     * Trouver l'index de pString dans la collection nomsPartis
     * @param pString
     * @return index du parti
     */
    public int indexDeParti(String pString){
        return nomsPartis.indexOf(pString);
    }

    public String obtenirCategorieDepute(Depute depute) {
        return obtenirPartiA(depute.getNumParti()).getCategorie();
    }

    //#endregion Obtenir données des collections

    //#region  Accésseurs et mutateurs

        /**
         * Accésseur de l'année d'élection
         * @return anneeElection
         */
        public int getAnneeElection(){
            return this.anneeElection;
        }

        /**
         * Mutateur de l'année d'élection
         * @param anneeElection
         */
        public void setAnneeElection(int anneeElection){
            this.anneeElection = anneeElection;
        }

        /**
         * Accésseur de l'index 2D
         * @return index
         */
        public int[][] getIndexTab(){
            return index;
        }

        /**
         * Mutateur de l'index 2D
         * @param index
         */
        public void setIndex(int[][] index){
            this.index = index;
        }
        //#endregion  Accésseurs et mutateurs

    //#region Méthodes equals, clone, toString


    /**
     * Envoi une copie de l'élection
     * @return cette objet
     */
    public Election clone(){
        return new Election(this.anneeElection);
    }

    /**
     * Génère un tableau pour visualisé le tableau d'index
     * @return le tableau de l'élection
     */
    public String toString(){

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < index[0].length + 1; i++) {
            sb.append(String.format(Constantes.ELECTION_TOSTRING + 
            Constantes.ESPACE,i));
        }
        

        for (int i = 0; i < index.length; i++) {
            sb.append(Constantes.CHANGER_LIGNE +
            String.format(Constantes.ELECTION_TOSTRING, (i+1)) 
            + Constantes.ESPACE);

            for (int j = 0; j < index[0].length; j++) {

                sb.append(String.format(Constantes.ELECTION_TOSTRING +
                 Constantes.ESPACE, index[i][j]));
                    
            }
        }

        return sb.toString();
    }

    //#endregion Méthodes equals, clone, toString

}