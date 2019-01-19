import javax.swing.*;

/**
 * Classe pour afficher les tableau des noms.
 *
 * @author Vincent Audette
 * @version Aut 2018
 */
public class PanneauAffichageNom extends JPanel {

    // La liste qui permet un écouteur de type ListSelectionListener
    JList liste;

    // La liste model pour le bon fonctionnement du JList
    DefaultListModel modelListe;

    /**
     * Ajoute les éléments au tableau
     * @param nom description du tableau
     * @param tab les élements à afficher dans une liste
     */
    public PanneauAffichageNom(String nom, String[] tab){

        JLabel titre = new JLabel(nom);
        peuplerModelListe(tab);
        liste = new JList(modelListe);

        // Configuration du panneau
        configDisposition();
        configListe();

        JScrollPane listeScroller = new JScrollPane(liste);
        titre.setAlignmentX(LEFT_ALIGNMENT);

        // Ajouter titre et liste
        add(titre);
        add(listeScroller);

    }

    /**
     * Ajoute tout les informations du tableau passé en paramètre
     * @param tab de données passé en paramètre
     */
    private void peuplerModelListe(String[] tab){
        modelListe = new DefaultListModel();
        for (String s:tab) {
            modelListe.addElement(s);
        }
    }

    /**
     * Determine un mode de dispositon pour le panneau
     */
    private void configDisposition(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    /**
     * Ajoute des configurations à la liste
     */
    private void configListe(){
        liste.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        liste.setLayoutOrientation(JList.VERTICAL);
        liste.setVisibleRowCount(15);
    }

    /**
     * Accésseur pour la liste
     * @return la liste
     */
    public JList getListe() {
        return liste;
    }

    /**
     * Met l'index à la bonne position et visible à l'utilisateur
     * @param index de sélection
     */
    public void setIndexEtSelectionVisible(int index){
        liste.setSelectedIndex(index);
        liste.ensureIndexIsVisible(index);
    }
}
