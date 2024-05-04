package fr.iutfbleau.sae32.view;

import fr.iutfbleau.sae32.controller.CelluleController;
import fr.iutfbleau.sae32.controller.FieldController;
import fr.iutfbleau.sae32.entity.graphics.SPanel;
import fr.iutfbleau.sae32.utils.PrimaryColor;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * La classe Fenetre représente la fenêtre principale de l'application Aiqussèle.
 * Elle contient un champ de texte pour la saisie des formules et un tableau de panneaux pour afficher les données.
 */
public class Fenetre extends JFrame {

    // Le panneau actuellement sélectionné
    private SPanel selectedPanel;

    // Le contrôleur de champ de texte
    private FieldController fieldController;

    // Le dictionnaire associant les identifiants de panneaux à leurs instances
    private Map<String,SPanel> dico;

    /**
     * Constructeur de la classe Fenetre.
     * Crée une nouvelle fenêtre avec un champ de texte et un tableau de panneaux.
     */
    public Fenetre() throws HeadlessException {
        super();
        setTitle("Aiqussèle");
        Dimension d = new Dimension(1280,720);
        setSize(d);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        JTextField jtf = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 5;
        gbc.fill = GridBagConstraints.BOTH;
        jtf.setFont(new Font("Calibri", Font.PLAIN, 32));
        this.fieldController = new FieldController(jtf, this.selectedPanel);
        jtf.addKeyListener(this.fieldController);
        add(jtf,gbc);

        JPanel tableau = new JPanel();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 100;
        creationTableau(tableau);
        this.selectedPanel = null;
        add(tableau,gbc);

        fieldController.setDico(this.dico);
    }

    /**
     * Méthode interne pour la création du tableau de panneaux.
     * @param tableau Le panneau dans lequel ajouter les panneaux du tableau.
     */
    private void creationTableau(JPanel tableau){
        GridLayout gl = new GridLayout(10,10);
        tableau.setLayout(gl);
        CelluleController celluleController = new CelluleController(this.selectedPanel, this.fieldController);
        String[] tab = { "A", "B", "C", "D", "E", "F", "G", "H", "I" };
        this.dico = new HashMap<>();

        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                if(i==0 && j==0){
                    JPanel panoVide = new JPanel();
                    panoVide.setBackground(PrimaryColor.FOND);
                    panoVide.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.DARK_GRAY));
                    tableau.add(panoVide);
                } else if (i == 0){
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.setBackground(PrimaryColor.FOND);
                    JLabel label = new JLabel(tab[j-1], JLabel.CENTER);
                    panel.add(label, BorderLayout.CENTER);
                    panel.setBorder(BorderFactory.createMatteBorder(1,0,1,1,Color.DARK_GRAY));
                    tableau.add(panel, BorderLayout.CENTER);
                } else if (j == 0){
                    JPanel panel = new JPanel(new BorderLayout());
                    panel.setBackground(PrimaryColor.FOND);
                    JLabel label = new JLabel(""+i, JLabel.CENTER);
                    panel.add(label, BorderLayout.CENTER);
                    panel.setBorder(BorderFactory.createMatteBorder(0,1,1,1,Color.DARK_GRAY));
                    tableau.add(panel, BorderLayout.CENTER);
                } else {
                    SPanel caseVide = new SPanel(tab[j-1]+i);
                    caseVide.setBackground(PrimaryColor.CASE_VIDE);
                    caseVide.addMouseListener(celluleController);
                    caseVide.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.LIGHT_GRAY));
                    tableau.add(caseVide);
                    this.dico.put(tab[j-1]+i, caseVide);
                }
            }
        }
    }

    /**
     * Méthode permettant d'obtenir le dictionnaire associant les identifiants de panneaux à leurs instances.
     * @return Le dictionnaire des panneaux.
     */
    public Map<String, SPanel> getDico() {
        return dico;
    }
}
