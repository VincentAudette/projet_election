import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * Classe du panneau de bas qui hérite par composition. Le panneau contient les
 * noms des partis, des députés et les noms (ou le nombre) des supporteurs
 * pour le premier parti de la liste.
 *
 * @author Vincent Audette
 * @version AUT 2018
 */
public class PanneauBas {

    // Le panneau du bas initalisé par composition
    private JPanel jPanel;

    // Le panneau qui permet d'avoir une liste pour chaque panneau d'infos
    private PanneauAffichageNom panneauParti, panneauDep, panneauSup;

    // Stock les données de l'élection
    private Election election;

    /**
     * Initialise les panneaux avec informations initiales de des panneaux
     *
     * @param election les données de l'élection
     */
    public PanneauBas(Election election) {

        // Référence de l'élection
        this.election = election;

        // Initialisation des panneaux avec informations de partis
        initPanneauxInfos();
    }

    /**
     * Initialise le panneau du bas, met un layout et les informations
     * des panneaux avec les informations sur le parti sélectionné
     */
    private void initPanneauxInfos(){

        // Initialise le panneau
        jPanel = new JPanel();

        // Met le layout approprié
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,100));

        // Remplir les informaitons avec sélection initale
        remplirPans(Constantes.NOM_PREMIER_PARTI_LISTE_BAS,0);

        // Ajoute les panneau au panneau de cette classee
        ajouterPanneauxInfosAuPanneauBas();

    }

    /**
     * Ajoute les panneaux d'information au panneau du haut
     */
    private void ajouterPanneauxInfosAuPanneauBas(){
        jPanel.add(panneauParti);
        jPanel.add(panneauDep);
        jPanel.add(panneauSup);
    }

    /**
     * Accésseur du panneau
     * @return
     */
    public JPanel getjPanel() {
        return jPanel;
    }

    /**
     * SP qui obtient les informations des partis, députés et supporteurs
     * @param nomParti le nom du parti sélectionné
     */
    private void remplirPans(String nomParti, int index){

        panneauParti = new PanneauAffichageNom("Partis",
                election.obtenirNomsEtCatParti());
        panneauParti.setIndexEtSelectionVisible(index);
        panneauParti.getListe().addListSelectionListener(listSelectionListener);

        panneauDep = new PanneauAffichageNom("Noms des députés",
                election.obtenirNomsDeputesParParti(nomParti));

        panneauSup = new PanneauAffichageNom("Supporteurs",
                election.obtenirNomsSupporteursParParti(nomParti));
    }

    /**
     * Ceci stock une écouteur de sélection de liste la variable
     * listSelectionListener. Lorsque la valeure du panneau de parti est
     * sélectionné les données des autres listes sont modifié.
     *
     * Voir description du lamda dans le fichier PanneauHaut à la ligne 107.
     */
    ListSelectionListener listSelectionListener = (e) -> {

        // Enlève tout les panneaux
        jPanel.removeAll();

        // L'index sélectionné est mis dans une variable
        int indexSelectionne =  panneauParti.getListe().getSelectedIndex();

        // Créer les nouveaux parti selon l'index selectionné
        remplirPans(election.obtenirStrPartiA(indexSelectionne),
                indexSelectionne);

        // Ajoute les nouveaux panneaux au panneau
        ajouterPanneauxInfosAuPanneauBas();

        // Assure que tout est bien afficher dans le panneaux
        jPanel.repaint();
        jPanel.validate();
    };

}