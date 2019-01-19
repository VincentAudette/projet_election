import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Panneau du haut qui hérite par extension et contient les noms des
 * circonscriptions, des partis et des députés de la première circonscription
 *
 * @author Vincent Audette
 * @version AUT 2018
 */
public class PanneauHaut extends JPanel {

    // Une référence sur l'élection courante
    private Election election;

    // Les panneaux du haut
    private PanneauAffichageNom panCircons, panPartis, panDepute;


    /**
     * Prend les données de l'élection et initalise les Panneaux d'informations
     * @param election la référence aux données de l'élection
     */
    public PanneauHaut(Election election){

        //Passe la référence à l'élection de la classe interne
        this.election = election;
        initPanneauxInfos();
    }

    /**
     * Initialise le panneau du haut avec le layout approprié et les panneaux
     * avec les informations initiales.
     */
    private void initPanneauxInfos(){

        this.setLayout(new FlowLayout(FlowLayout.CENTER,200,100));

        remplirPans(Constantes.NOM_PREMIER_PARTI_LISTE_HAUT,0);
        ajouterPanneauxInfosATHIS();
    }

    /**
     * Ajoute les panneaux d'information au panneau du haut
     */
    private void ajouterPanneauxInfosATHIS(){
        add(panCircons);
        add(panPartis);
        add(panDepute);
    }

    /**
     * Remplir tous les panneaux qui seront exposé au haut du cadre principal
     * @param circonscription la circonscription qui est sélectionné
     * @param index la position de la circonscription sélectionné
     */
    private void remplirPans(String circonscription , int index){

        panCircons = initPan("Noms des circonscription",
                election.obtenirNomsCirconscription());
        // Met l'index au lieu approprié
        panCircons.setIndexEtSelectionVisible(index);
        panCircons.getListe().addListSelectionListener(listSelectionListener);

        panPartis = initPan("Noms des partis",
                listePartisSlnCircons(circonscription));

        panDepute = initPan("Noms des députés",
                listeDeputeSlnCircons(circonscription));
    }

    /**
     * Obtenir la liste de parti selon la circonscription
     * @param circons circonscription
     * @return une liste des partis
     */
    private String[] listePartisSlnCircons(String circons){
        return election.obtenirNomsPartisParCirconscription(circons);
    }

    /**
     * Obtenir la liste de noms de députés selon la circonscription
     * @param circons circonscription
     * @return une liste des députés
     */
    private String[] listeDeputeSlnCircons(String circons){
        return election.obtenirNomsDeputesParCirconscription(circons,
                listePartisSlnCircons(circons));
    }

    /**
     * Constrution du panneau d'affichage de nom
     * @param titre du panneau
     * @param tab leau qui sera afficher avec ce panneau
     * @return un panneau avec le titre et les élements du tableau
     */
    private PanneauAffichageNom initPan(String titre, String[] tab){
        return new PanneauAffichageNom(titre, tab);
    }

    /**
     * Écouteur qui change les panneaux lorsqu'une circonscription est
     * sélectionnée.
     *
     * Note: l'expression lambda va chercher directement la seule méthode
     * dans l'interface ListSelectionListener:
     *          void valueChanged (ListSelectionEvent e);
     * Le (e) représente le paramètre du type ListSelectionEvent. Dans ce cas
     * quand il y un valueChanged nous n'utilisons pas les méthodes inclus
     * dans le ListSelectionEvent, mais ceci serait une option.
     * ex: e.getFirstIndex()
     */
    ListSelectionListener listSelectionListener = (e) -> {

        // Éfface les panneau courant
        removeAll();

        // Stock l'index sélectionné
        int indexSelectionner=  panCircons.getListe().getSelectedIndex();

        // Créer des nouveaux panneaux d'informations avec les données
        remplirPans(election.obtenirStrCirconscriptionA(indexSelectionner),
                indexSelectionner);

        // Met les panneaux dans le pannau haut
        ajouterPanneauxInfosATHIS();

        // Rafraichi le panneau du haut
        repaint();
        validate();

        };


}
