package fr.iutfbleau.sae32.entity;

import fr.iutfbleau.sae32.entity.graphics.SPanel;

/**
 * La classe NodePanel représente un nœud associé à un panneau dans un arbre binaire.
 * Elle hérite de la classe Node.
 */
public class NodePanel extends Node {

    // Le panneau associé au nœud
    private SPanel value;

    /**
     * Constructeur de NodePanel.
     * Crée un nœud associé à un panneau avec la valeur du panneau spécifiée.
     * @param value Le panneau associé au nœud.
     */
    public NodePanel(SPanel value) {
        super();
        this.value = value;
        setValue(value.getValeur());
    }

    /**
     * Méthode permettant d'obtenir la valeur du nœud.
     * La valeur du nœud est définie comme la valeur du panneau associé.
     * @return La valeur du nœud.
     */
    @Override
    public double getValue() {
        return value.getValeur();
    }

    /**
     * Méthode permettant d'obtenir le panneau associé au nœud.
     * @return Le panneau associé au nœud.
     */
    public SPanel getPanel() {
        return value;
    }

    /**
     * Méthode d'affichage du nœud associé à un panneau.
     * Affiche la valeur du panneau suivi d'un espace.
     */
    @Override
    public void affichage() {
        System.out.print(this.value.getValeur()+" ");
    }
}
