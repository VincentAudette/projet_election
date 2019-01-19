/**
 * Super class des parti, ce Parti contient le nom et les meéthodes communes
 * à ses sous-classe
 */
public abstract class Parti{

    // Le nom du parti
    private String nom;

    //#region Constructeurs
    /**
     * Constructeur par défaut
     */
    public Parti() {
        this.nom = null;
    }

    /**
     * Constructeur par copie d'attributs
     * @param nom
     */
    public Parti(String nom) {
        this.nom = nom;
    }

   /**
    * Constructeur par copie d'attribut d'objet
    * @param p (un parti)
    */ 
    public Parti(Parti p){
        this.nom = p.nom;
    }
    //#endregion

    //#region Accésseur et mutateur
    /**
     * Accésseur pour attribut nom
     * @return nom
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Mutateur pour le nom de parti
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Retourne un tableau des supporteurs
     */
    public abstract Object[] obtenirTabSupporteurs();

    /**
     * Donne le type de catégorie de cette classe
     * @return Constantes.CATEGORIE[0]
     */
    public String getCategorie(){
        return Constantes.PARTI;
    }

    public abstract int getValCategorie();

    //#endregion

    //#region equals, clone et toString
    /**
     * Permet de voir si deux objets sont identique
     * @param p un parti à comparer
     * @return si le Parti paramètre est le même que celui-ci
     */
    public boolean equals(Parti p) {
        return p.nom.equals(this.nom);
    }

    /**
     * Permet de retourner une copie de cette objet
     * @return une copie du parti présent
     */
    public abstract Parti clone();

    /**
     * Méthode pour voir le nom du parti
     * @return chaîne de caractères avec nom de parti
     */
    public String toString() {
        return "{" + " Nom Parti: " + this.nom + "}";
    }
    //#endregion






}