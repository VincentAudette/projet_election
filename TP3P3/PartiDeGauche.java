import java.io.Serializable;
import java.util.Vector;

/**
 * Classe de parti de gauche pour l'élection qui contient une collection
 * d'organismes a but non lucratif.
 *
 * @author Vincent Audette
 * @version Aut 2018
 */
public class PartiDeGauche extends Parti implements Serializable {

    // Collection des organismes à but lucratif (OBNL)
    private Vector<String> OBNL;

    /**
     * Constructeur par défault
     */
    public PartiDeGauche(String nom){
        super(nom);
        this.OBNL = new Vector<>();
    }

    /**
     * Constructeur par copie d'objet
     */
    public PartiDeGauche(PartiDeGauche p){
        super.setNom(p.getNom());
        this.OBNL = p.OBNL;
    }

    /**
     * Constructeur par attribut
     * @param nomOBNL Le de du OBNL ajouté
     */
    public void ajouterOBNL(String nomOBNL){
        this.OBNL.add(nomOBNL);
    }

    /**
     * Retourne un tableau des supporteurs
     * @return OBNL en forme de tableau
     */
    public String[] obtenirTabSupporteurs(){
        return this.OBNL.toArray(new String[(OBNL.size())]);
    }

    /**
     * Donne le type de catégorie de cette classe
     * @return Constantes.CATEGORIE[1]
     */
    @Override
    public String getCategorie(){
        return Constantes.CATEGORIE_GAUCHE;
    }

    @Override
    public String getNom() {
        return super.getNom();
    }

    @Override
    public void setNom(String nom) {
        super.setNom(nom);
    }

    @Override
    public boolean equals(Parti p) {
        return super.equals(p);
    }

    @Override
    public int getValCategorie() {
        return Constantes.PARTI_DE_GAUCHE;
    }

    @Override
    public PartiDeGauche clone() {
        return new PartiDeGauche(this);
    }

    public String nbOBNL(){
        int compt = 0;
        for (String OBNL: obtenirTabSupporteurs()) {
                compt++;
        }
        return String.valueOf(compt);
    }

    /**
     * String de la collection
     * @return la collection de OBNL sous format de string
     */
    @Override
    public String toString() {
        return "Supporteurs à but non lucratif:"+ Constantes.CHANGER_LIGNE +
                nbOBNL();
    }
    
}