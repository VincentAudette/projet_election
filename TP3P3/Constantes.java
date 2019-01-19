 /**
 * Constantes globales au projet
 * 
 * 
 * @author Pierre Belisle
 * @revisor Melanie Lord
 * @student Vincent Audette
 *  
 * @version A2018
 */
public class Constantes {

	//Le code s'adapte en changeant l'annee d'election (titre, etiquette, ...)
	public static final int ANNEE_ELECTION = 2018;

	 public static final String TITRE_GUI = "Élection 2018";

	public static final int NB_CIRCONSCRIPTION = 125;

	public static final int NB_DEPUTE_MAX = 939;

	public static final int NB_CATEGORIES_PARTI = 3;
	
	//Une case VIDE est une case non utilisee
	public static final int VIDE = -1;

	// Les options pour switch case
	public static final int[] OPTION = {1,2,3};

	public static final int NB_OBNL_MAX = 15;

	// Erreur d'exception format de fichier invalid
	public static final String 	FORMAT_INVALID = "Format de fichier invalide";

	// ToString pour les classes Depute, Election et Circonscription
	public static final String[] DEPUTE_TOSTRING =
	{"Case circonscription:", "Parti:"};
	public static final String ELECTION_TOSTRING = "%3d";
	public static final String[] CIRCONSCRIPTION_TOSTRING =
	{"n° circonscription: ", "Nom de circonscription: ","n° Case du député: "};

	// Tabulation entre mots
	public static final String TABULATION = "\\t";

	// Passer à la prochaine ligne
	public static final String CHANGER_LIGNE = "\n";

	// Espace vide
	public static final String ESPACE = " ";

	//#region Les tableaux pour dialogue/menu
	
	// Les principales attributs de l'élection
	public static final String[] ATTRIBUTS_ELECTION =
	{"Circonscription: ", "Parti: ", "Depute: "};

	// Description du d'obtenir fichier binaire
	public static final String[] OBTENIR_FICHIER =
	{"Obtenir le fichier binaire (.bin)", "bin"};

	// Description de sauvegarde
    public static final String[] SAUVEGARDER_FICHIER_BIN =
            {"Sauvegarder le fichier en binaire (.bin)", "bin"};

	// Description d'obtenir fichier text
	public static final String[] OBTENIR_TXT =
	{"Choisir fichier élection", "txt"};


	// Les catégories
	public static final String PARTI = "Parti";
	public static final String CATEGORIE_GAUCHE = "Parti de gauche ";
	public static final String CATEGORIE_CENTRE = "Parti du centre ";
	public static final String CATEGORIE_DROITE = "Parti de droite ";
	public static final int PARTI_DE_GAUCHE = 1;
	public static final int PARTI_DU_CENTRE = 2;
	public static final int PARTI_DE_DROITE = 3;

	/*
	 * Les tableau suivant servent a décrire les choix dans les 
	 * fenêtres showInputDialog du JOptionPane
	 */

	// Premier choix de l'application
	public static final String[] CHOIX_PREMIERE_FOIS = 
	{"Ouvrir fichier texte", "Ouvrir fichier binaire"};

	// Description du choix de requêtes
	public static final String[] CHOIX_TXT_OU_BIN = 
	{"Sélectionnez le type de fichier", 
	"Type de fichier (texte ou binaire?)"};

	// Demande si l'utilisateur veut sauvegarder en binaire
	public static final String[] CHOIX_SAUVEGARDE =
	{"Aimeriez-vous sauvegarder l'élection en binaire?",
	"Sauvegarder fichier .bin"};


	// Pointeur sur les premier parti des listes des panneau
	public static final String NOM_PREMIER_PARTI_LISTE_BAS = "Parti québécois";
	public static final String NOM_PREMIER_PARTI_LISTE_HAUT = "Abitibi-Est";

     //#endregion Les tableaux pour dialogue/menu
   
}
