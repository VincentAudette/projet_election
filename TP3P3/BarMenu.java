import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BarMenu extends JMenuBar{

    private JMenu fichier;

    private JMenuItem ouvrirFichierTexte, ouvrirFichierBinaire,
            sauvegarderBinaire, quitter;

    private CadreElection frame;

    private Election election;

    public BarMenu(CadreElection frame){

        this.frame = frame;
        election = new Election(Constantes.ANNEE_ELECTION);

        fichier = new JMenu("Fichier");

        ouvrirFichierTexte = new JMenuItem("Ouvrir fichier texte");
        ouvrirFichierBinaire = new JMenuItem("Ouvrir fichier binaire");
        sauvegarderBinaire = new JMenuItem("Sauvegarder en binaire");
        quitter = new JMenuItem("Quitter");

        Ecouteur monEcouteur = new Ecouteur();

        ouvrirFichierTexte.addActionListener(monEcouteur);
        ouvrirFichierBinaire.addActionListener(monEcouteur);
        sauvegarderBinaire.addActionListener(monEcouteur);
        quitter.addActionListener(monEcouteur);

        fichier.add(ouvrirFichierTexte);
        fichier.add(ouvrirFichierBinaire);
        fichier.add(sauvegarderBinaire);
        fichier.addSeparator();
        fichier.add(quitter);

        add(fichier);

    }

    private class Ecouteur implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            frame.getContentPane().removeAll();
            if(e.getSource() == ouvrirFichierTexte){
                ModuleFichier.getElection(election);
                // Le SP génère le tableau d'index 2D avec les données du fichier
                election.genererIndex();
                frame.setElection(election);
            }else if(e.getSource() == ouvrirFichierBinaire){
                ModuleFichier.getElectionBinaire();
                // Le SP génère le tableau d'index 2D avec les données du fichier
                election.genererIndex();
                frame.setElection(election);
            }
            //FIXME: il y a un bug lors de la sauvegarde
            else if(e.getSource() == sauvegarderBinaire){
                ModuleFichier.sauverFichierBinaire(election);
            } else {
                System.exit(0);
            }
        }
    }
}
