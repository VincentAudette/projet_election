import java.io.Serializable;

/**
 * Cette classe crée un objet qui contiendra des attributs
 * privées pour le numéro de circonscription, le nom de
 * circonscription et la numéro du député élu
 * 
 * @author Vincent
 * @version Automne 2018
 */
public class Circonscription implements Serializable{

    // Le numéro de la circonscription qui servira   
    private int numCircons;
    private String nomCircons;
    private int elu;


    //#region Constructeurs

    /**
     * Constructeur par défault
     */
    public Circonscription(){
        this.numCircons = 0;
        this.nomCircons = null;
        this.elu = Constantes.VIDE;
    }

    /**
     * Constructeur par copie d'attributs
     */
    public Circonscription(int numCircons, String nomCircons, int elu){
        this.numCircons = numCircons;
        this.nomCircons = nomCircons;
        this.elu = elu;
    }

    /**
     * Constructeur par copie d'objet
     */
    public Circonscription(Circonscription circonscription){
        this.numCircons = circonscription.numCircons;
        this.nomCircons = circonscription.nomCircons;
        this.elu = circonscription.elu;
    }

    //#endregion Constructeurs

    //#region  Accésseurs et mutateurs

    /**
     * Accesseur pour le numéro de circonscription
     * @return numCircons
     */
    public int getNumCircons(){
        return this.numCircons;
    }

    /**
     * Mutateur pour le numéro de circonscription
     * @param numCircons
     */
    public void setNumCircons(int numCircons){
        this.numCircons = numCircons;
    }

      /**
     * Accesseur pour le nom de circonscription
     * @return nomCircons
     */
    public String getNomCircons(){
        return this.nomCircons;
    }

    /**
     * Mutateur pour le nom de circonscription
     * @param nomCircons
     */
    public void setNomCircons(String nomCircons){
        this.nomCircons = nomCircons;
    }

    /**
     * Accesseur pour la case du député élu
     * @return elu
     */
    public int getElu(){
        return this.elu;
    }

    /**
     * Mutateur pour la case du député élu
     * @param elu
     */
    public void setElu(int elu){
        this.elu = elu;
    }

    //#endregion  Accésseurs et mutateurs

    //#region  Méthodes equals, clone et toString

    /**
     * Vérifie si l'élection passer en paramètre est égale à this
     * @param c (un objet de type Circonscription)
     * @return true || false dépendant si'ils sont égale
     */
	public boolean equals(Circonscription c){
		return c.numCircons == this.numCircons
		&& c.nomCircons.equals(this.nomCircons)
		&& c.elu == this.elu;
	}

    /**
     * Envoi une copie de la circonscription
     * @return cette objet
     */
	public Circonscription clone(){
		return new Circonscription(this);
	}

   	/**
	 * Affichage des attributs de cette objet
	 * @return chaîne de caractère avec les informations de l'objet
	 */
	public String toString(){
        return this.nomCircons;
    }
    
    //#endregion  Méthodes equals, clone et toString

}