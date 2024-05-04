package fr.iutfbleau.sae32.controller;

import fr.iutfbleau.sae32.entity.Tree;
import fr.iutfbleau.sae32.entity.graphics.SPanel;
import fr.iutfbleau.sae32.utils.PrimaryColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * La classe FieldController gère les interactions clavier dans un champ de jeu.
 * Elle implémente l'interface KeyListener pour écouter les événements de clavier sur le champ.
 */
public class FieldController implements KeyListener {

    // Le champ de texte associé
    private JTextField jtf;

    // Le panneau sélectionné
    private SPanel selectedPanel;

    // Une carte de panneaux
    private Map<String, SPanel> panels;

    /**
     * Constructeur de FieldController.
     * @param j Le champ de texte à contrôler.
     * @param s Le panneau sélectionné.
     */
    public FieldController(JTextField j, SPanel s){
        this.jtf = j;
        this.selectedPanel = s;
    }

    /**
     * Méthode appelée lorsqu'une touche est tapée.
     * @param e L'événement de clavier associé.
     */
    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == KeyEvent.VK_ENTER){
            if(this.selectedPanel == null){
                System.out.println("Aucune case sélectionnée");
            } else {
                try {
                    String formule = this.jtf.getText();
                    this.selectedPanel.reset();
                    this.selectedPanel.setFormuleBrut(this.jtf.getText());
                    this.selectedPanel.setFormule(new Tree(computeString(this.jtf.getText()), this.panels));
                    this.selectedPanel.setValeur(this.selectedPanel.getFormule().calculer());
                    this.selectedPanel.setPanelUsed(this.selectedPanel.getFormule().getPanelUsed());
                    if (formule.isEmpty()){
                        this.selectedPanel.setBackground(PrimaryColor.CASE_VIDE);
                        return;
                    }
                    this.selectedPanel.setBackground(PrimaryColor.CELLULE_BONNE);
                    if(this.selectedPanel.hasCircularDependency()){
                        this.selectedPanel.setBackground(PrimaryColor.MAUVAISE_FORMULE);
                        String[] nuls = {"0"};
                        this.selectedPanel.setFormule(new Tree(nuls, this.panels));
                    }
                } catch (ArithmeticException f) { // Division par 0
                    this.selectedPanel.setBackground(PrimaryColor.CALCUL_IMPOSSIBLE);
                    this.selectedPanel.setLabel("Division par 0");
                } catch (IllegalArgumentException f){ // Mauvais caractère genre é , etc.
                    this.selectedPanel.setBackground(PrimaryColor.CARACTERE_INCONNU);
                    this.selectedPanel.setLabel("Caractère inconnu");
                } catch (IndexOutOfBoundsException f){ // Formule mauvaise genre - 1 2 3
                    this.selectedPanel.setBackground(PrimaryColor.MAUVAISE_FORMULE);
                    this.selectedPanel.setLabel("Formule non calculable");
                } catch (IllegalStateException f){ // Boucle des cellules
                    this.selectedPanel.setBackground(PrimaryColor.BOUCLE);
                    this.selectedPanel.setLabel("Boucle de cellules");
                } catch (NullPointerException f) { // Si on vide le jtf
                    this.selectedPanel.setBackground(PrimaryColor.CARACTERE_INCONNU);
                    this.selectedPanel.setLabel("Case vide");
                }
            }
            this.selectedPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.LIGHT_GRAY));
        }
    }

    /**
     * Méthode appelée lorsqu'une touche est enfoncée.
     * Non utilisée dans cette classe.
     * @param e L'événement de clavier associé.
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Méthode appelée lorsqu'une touche est relâchée.
     * Non utilisée dans cette classe.
     * @param e L'événement de clavier associé.
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     * Méthode permettant de définir la carte de panneaux.
     * @param dico La carte de panneaux à définir.
     */
    public void setDico(Map<String, SPanel> dico){
        this.panels = dico;
    }

    /**
     * Méthode permettant de définir le panneau sélectionné.
     * @param selectedPanel Le panneau sélectionné à définir.
     */
    public void setSelectedPanel(SPanel selectedPanel) {
        this.selectedPanel = selectedPanel;
    }

    /**
     * Méthode permettant de définir le texte du champ de texte.
     * @param value La valeur du texte à définir.
     */
    public void setText(String value){
        this.jtf.setText(value);
        this.jtf.repaint();
    }

    /**
     * Méthode permettant de calculer une chaîne de caractères séparée par des espaces.
     * @param s La chaîne de caractères à calculer.
     * @return Un tableau de chaînes de caractères résultant du calcul.
     */
    private static String[] computeString(String s){
        return s.split(" ");
    }
}
