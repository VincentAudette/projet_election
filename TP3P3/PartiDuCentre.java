import java.io.Serializable;
import java.util.Vector;
import java.lang.StringBuffer;

/**
 * Classe de parti du centre pour l'élection qui contient une collection
 * de type Circonscription et permet de regrouper les circonscriptions
 * supportrices
 *
 * @author Vincent Audette
 * @version Aut 2018
 */
public class PartiDuCentre extends Parti implements Serializable{

    private Vector<Circonscription> circonsSupporter;

    /**
     * Constructeur par défault
     */
    public PartiDuCentre(String nom){
        super(nom);
        this.circonsSupporter = new Vector<>();
    }

    /**
     * Constructeur par copie d'objet
     */
    public PartiDuCentre(PartiDuCentre p){
        super.setNom(p.getNom());
        this.circonsSupporter = p.circonsSupporter;
    }

    /**
     * Ajoute une circonscription à la collection circonsSupporter
     * @param c (Une circonscription)
     */
    public void ajouterCirconscription(Circonscription c){
        this.circonsSupporter.add(c);
    }

    /**
     * Vérifie si une Circonscription est contenu dans la collection
     * @return vrai si l'élément est dans la collection sinon faux
     */
    public boolean circonsSupporterContains(Circonscription c){
        return circonsSupporter.contains(c);
    }

    /**
     * Retourne un tableau des supporteurs
     * @return circonsSupporter en forme de tableau
     */
    public Circonscription[] obtenirTabSupporteurs(){
        return this.circonsSupporter.
            toArray(new Circonscription[(circonsSupporter.size())]);
    }

    /**
     * Donne le type de catégorie de cette classe
     * @return Constantes.CATEGORIE[2]
     */
    @Override
    public String getCategorie(){
        return Constantes.CATEGORIE_CENTRE;
    }

    @Override
    public int getValCategorie() {
        return Constantes.PARTI_DU_CENTRE;
    }

    @Override
    public PartiDuCentre clone() {
        return new PartiDuCentre(this);
    }

    public String[] convertirTab2String(){

        Vector<String> vectCircons = new Vector<>();

        for (Circonscription circonsSupporter: obtenirTabSupporteurs()) {
            vectCircons.add(circonsSupporter.getNomCircons());
        }
        return vectCircons.toArray(new String[vectCircons.size()]);
    }

    public String debugCircons(){
        StringBuffer sb = new StringBuffer();

        for (Circonscription circonsSupporter:
                obtenirTabSupporteurs()) {
            sb.append(circonsSupporter + Constantes.CHANGER_LIGNE);
        }
        return sb.toString();
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Circonscription Supportrices:" +
                Constantes.CHANGER_LIGNE + debugCircons();
    }
}