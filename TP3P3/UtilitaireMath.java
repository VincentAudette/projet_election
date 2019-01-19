/**
 * Offre des fonctions utilitaires communes au projet
 * @author pbelisle
 * 
 * @author Pierre Bélisle
 * @revisor Mélanie Lord
 *  
 * @version A2018
 *
 */
public class UtilitaireMath {

	/**
	 * Retourne une nombre aléatoire dans un intervalle entier donné.
	 * 
	 * ATTENTION : min doit être plus petit que max  mais aucune validation
	 *             n'est effectuée. 
	 *             
	 * @param min La plus petite valeur possible
	 * @param max La plus grande valeur possible
	 * @return Un nombre entre min et max (inclusivement)
	 */
	public static int alea(int min,int max)
	{
		/*
		 * Stratégie : On utilise le générateur de Java qui retourne une valeur 
		 * réelle entre 0 et 1.  Ensuite, on le ramène dans l'intervalle 
		 * min..max et on le transforme en entier.
		 */
		return (int) (Math.random() * (max - min + 1) + min);
	}


}
