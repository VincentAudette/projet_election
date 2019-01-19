import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Le cadre (GUI) qui affiche les panneaux bas et haut. Cette classe contient
 * une sous classe qui est le panneau avec les sous-panneaux.
 * @author Vincent Audette
 * @version AUT 2018
 */
public class CadreElection extends JFrame implements Runnable {

    private Election election;

    /**
     * Constructeur par défaut qui intialise le Frame avec un titre
     */
    public CadreElection(){
        super(Constantes.TITRE_GUI);
        this.election = new Election(Constantes.ANNEE_ELECTION);
    }

    public void run(){

        configFrame();

    }

    public void setElection(Election election) {
        this.election = election;
        initPanneaux();
        this.validate();
    }

    /**
     * Dispose les panneaux sur le frame
     */
    public void initPanneaux(){

        //Générer l'index de l'élection
        election.genererIndex();

        //Ajouté le panneau du haut au frame
        getContentPane().add(new PanneauHaut(election));
        //Ajouté le panneau du bas au frame
        getContentPane().add(new PanneauBas(election).getjPanel());
    }

    /**
     * Configure le frame en maximisant la fenêtre, puis
     * en ajoutant une gestion de la sortie par pop-up
     */
    private void configFrame(){

        // maximize la fenêtre
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Ajoute un pop-up pour la fermeture
        optionFermetureOuiNon();

        getContentPane().setLayout(new BoxLayout(getContentPane(),
                BoxLayout.Y_AXIS));

        //Initialisation du Menu
        setJMenuBar(new BarMenu(this));

        setVisible(true);


    }

    /**
     * Une confirmation pour la fermeture du gui
     */
    private void optionFermetureOuiNon(){
        // ajoute une gestion du EXIT par confirmation pop-up
        addWindowListener(new WindowAdapter() {
            // gestionnaire d'événement
            public void windowClosing(WindowEvent we) {

                int result = JOptionPane.showConfirmDialog(null,
                        "Voulez-vous quitté ?", "Confirmation",
                        JOptionPane.YES_NO_OPTION);

                if (result == JOptionPane.YES_OPTION){
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
                else if (result == JOptionPane.NO_OPTION){
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }

        });

    }
}
