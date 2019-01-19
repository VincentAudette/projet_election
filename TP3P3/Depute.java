import java.io.Serializable;

/**
 * 
 * Cette classe prend les données d'un Député, notamment son
 * nom, les n° de case où se trouvent la circonscription et 
 * le n° du nom de parti dans les collections associées.
 * 
 * @author Vincent
 * @version Automne 2018
 * 
 */
public class Depute implements Serializable{

	//Le nom du député: concaténation du nom et du prénom
	private String nomDepute;
	// Les numéros de case où se trouvent la circonscription
	private int numCaseCirc;
	// Le n° du nom de parti dans les collections associées 
	// de la classe Election.
	private int numParti;

	//#region Constructeurs
		
		/**
		 * Constructeur par défault
		 */
		public Depute(){}

		/**
		 * Constructeur par attributs, donne le nom et prénom
		 * du député.
		 * @param nom
		 * @param prenom
		 */
		public Depute(String nom, String prenom){
			this.nomDepute = nom + prenom;
		}

        /**
         * Constructeur par attributs, prend
         * @param nomDepute le nom du député
         * @param numCaseCirc la case de la circonscription
         * @param numParti le numéro du parti
         */
		public Depute(String nomDepute, int numCaseCirc,int numParti){
		    this.nomDepute = nomDepute;
		    this.numCaseCirc = numCaseCirc;
		    this.numParti = numParti;
        }

		/**
		 * Constructeur par copie
		 * @param d objet copier
		 */
		public Depute(Depute d){
			this.nomDepute = d.nomDepute;
			this.numCaseCirc = d.numCaseCirc;
			this.numParti = d.numParti;
		}

	//#endregion Constructeurs

	//#region Accésseurs et mutateurs

		/**
		 * Mutateur pour le nom du Député
		 * @param nomDepute
		 */
		public void setNomDepute(String nomDepute){
			this.nomDepute = nomDepute;
		}

		/**
		 * Accesseur pour nom Député
		 * @return nomDepute
		 */
		public String getNomDepute(){
			return this.nomDepute;
		}
		
		/**
		 * Mutateur pour le numéro de la case circonscription
		 * @param numCaseCirc
		 */
		public void setNumCaseCirc(int numCaseCirc){
			this.numCaseCirc = numCaseCirc;
		}

		/**
		 * Accesseur pour le numéro de case circonscription
		 * @return numCaseCirc
		 */
		public int getNumCaseCirc(){
			return this.numCaseCirc;
		}

		/**
		 * Mutateur pour le numéro de parti
		 * @param numParti
		 */
		public void setNumParti(int numParti){
			this.numParti = numParti;
		}

		/**
		 * Accesseur pour le numéro de parti
		 * @return numParti
		 */
		public int getNumParti(){
			return this.numParti;
		}

	//#endregion Accésseurs et mutateurs

	//#region Méthodes equals, clone, toString

	/**
     * Vérifie si le député passer en paramètre est égale à this
     * @param d (un objet de type depute)
     * @return true || false dépendant si'ils sont égale
     */
	public boolean equals(Depute d){
		return d.nomDepute.equals(this.nomDepute)
		&& d.numCaseCirc == this.numCaseCirc
		&& d.numParti == this.numParti;
	}

	/**
     * Envoi une copie du député
     * @return cette objet
     */
	public Depute clone(){
		return new Depute(this);
	}

	/**
	 * Affichage des attributs de cette objet
	 * @return chaîne de caractère avec les informations de l'objet
	 */
	public String toString(){
		return this.nomDepute;
	}
	//#endregion Méthodes equals, clone, toString
	

}