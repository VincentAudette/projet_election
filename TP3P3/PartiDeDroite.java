import java.io.Serializable;
import java.util.Vector;

/**
 * Classe de parti de droite pour l'élection qui contient une collection
 * des députés enterpreneurs
 *
 * @author Vincent Audette
 * @version Aut 2018
 */
public class PartiDeDroite extends Parti implements Serializable{

    private Vector<Depute> deputeEntrepreneurs;


    /**
     * Constructeur par défault
     */
    public PartiDeDroite(String nom){
        super(nom);
        this.deputeEntrepreneurs = new Vector<>();
    }

    /**
     * Constructeur par copie d'objet
     */
    public PartiDeDroite(PartiDeDroite p){
        super.setNom(p.getNom());
        this.deputeEntrepreneurs = p.deputeEntrepreneurs;
    }

    /**
     * Retourne un tableau des supporteurs
     * @return deputeEntrepreneurs en forme de tableau
     */
    public Depute[] obtenirTabSupporteurs(){
        return this.deputeEntrepreneurs.
            toArray(new Depute[(deputeEntrepreneurs.size())]);
    }

    /**
     * Vérifie si un élément est dans la collection
     * @param d Depute rechercher dans la collection
     * @return vrai si le député est dans la collection
     */
    public boolean deputeEntrepreneursContains(Depute d){
        return deputeEntrepreneurs.contains(d);
    }

    /**
     * Ajoute une député à la collection deputeEntrepreneurs
     * @param d (Un Député)
     */
    public void ajouterDepute(Depute d){
        this.deputeEntrepreneurs.add(d);
    }

    /**
     * Donne le type de catégorie de cette classe
     * @return Constantes.CATEGORIE[3]
     */
    @Override
    public String getCategorie(){
        return Constantes.CATEGORIE_DROITE;
    }

    @Override
    public int getValCategorie() {
        return Constantes.PARTI_DE_DROITE;
    }

    @Override
    public PartiDeDroite clone() {
        return new PartiDeDroite(this);
    }

    public String[] convertirTab2String(){

        Vector<String> vectDeputes = new Vector<>();

        for (Depute deputeEntrepreneurs: obtenirTabSupporteurs()) {
            vectDeputes.add(deputeEntrepreneurs.getNomDepute());
        }

        return vectDeputes.toArray(new String[vectDeputes.size()]);
    }

    /**
     * String de la collection
     * @return les député entrepreneurs de la collection
     */
    @Override
    public String toString() {
        return "Députés supporteurs:"+Constantes.CHANGER_LIGNE+
                convertirTab2String();
    }

}