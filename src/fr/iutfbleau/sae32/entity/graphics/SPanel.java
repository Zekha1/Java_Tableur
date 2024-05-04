package fr.iutfbleau.sae32.entity.graphics;

import fr.iutfbleau.sae32.entity.Tree;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Un panneau personnalisé pour afficher des informations spécifiques.
 */
public class SPanel extends JPanel {
    private JLabel label;
    private String id;
    private Double valeur;
    private String formuleBrut;
    private Tree formule;
    private List<SPanel> panelContain;

    /**
     * Constructeur de la classe SPanel.
     *
     * @param id L'identifiant du panneau.
     */
    public SPanel(String id) {

        this.id = id;
        this.formuleBrut = "";
        this.label = new JLabel();
        setLabel(null);
        add(this.label);
        this.id = id;
        this.valeur = null;
        this.panelContain = new ArrayList<>();
    }

    /**
     * Récupère l'identifiant du panneau.
     *
     * @return L'identifiant du panneau.
     */
    public String getId() {
        return id;
    }

    /**
     * Récupère la valeur du panneau.
     *
     * @return La valeur du panneau.
     */
    public double getValeur() {
        return this.valeur;
    }

    /**
     * Récupère le texte affiché dans le panneau.
     *
     * @return Le texte affiché dans le panneau.
     */
    public String getTexte() {
        return label.getText();
    }
    public void setLabel(String texte){
        this.label.setText(texte);
    }

    /**
     * Définit la valeur du panneau.
     *
     * @param valeur La valeur à définir.
     */
    public void setValeur(Double valeur) {
        this.valeur = valeur;
        this.setLabel(Double.toString(this.valeur));
        repaint();
    }

    /**
     * Définit la formule brute du panneau.
     *
     * @param s La formule brute à définir.
     */
    public void setFormuleBrut(String s){
        this.formuleBrut = s;
    }

    /**
     * Récupère la formule brute du panneau.
     *
     * @return La formule brute du panneau.
     */
    public String getFormuleBrut() {
        return formuleBrut;
    }

    /**
     * Définit la formule arborescente du panneau.
     *
     * @param formule La formule arborescente à définir.
     */
    public void setFormule(Tree formule) {
        this.formule = formule;
    }

    /**
     * Récupère la formule arborescente du panneau.
     *
     * @return La formule arborescente du panneau.
     */
    public Tree getFormule() {
        return formule;
    }

    public void setPanelUsed(List<SPanel> panels){
        this.panelContain = panels;
    }

    public List<SPanel> getPanelUsed(){
        return this.panelContain;
    }

    public void reset(){
        this.panelContain = new ArrayList<>();
    }

    /**
     * Vérifie s'il existe une dépendance circulaire lors de l'ajout d'un SPanel.
     *
     * @param panel Le SPanel à ajouter.
     * @return true s'il existe une dépendance circulaire, false sinon.
     */
    public boolean hasCircularDependency() {
        return hasCircularDependency(this, this.panelContain);
    }

    private boolean hasCircularDependency(SPanel panel, List<SPanel> panelContain) {
        for (SPanel usedPanel : panelContain) {
            if(usedPanel.getPanelUsed().isEmpty()){
                return false;
            } else {
                if(usedPanel.getPanelUsed().contains(panel)){
                    return true;
                } else {
                    if(hasCircularDependency(panel, usedPanel.getPanelUsed())){
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return false;
    }
}
