package fr.iutfbleau.sae32.entity;

import fr.iutfbleau.sae32.entity.graphics.SPanel;

/**
 * La classe Node représente un nœud dans un arbre binaire.
 */
public class Node {

    // Le panneau associé au nœud
    private SPanel panel;

    // La valeur du nœud
    private double value;

    // Le nœud fils gauche
    private Node left;

    // Le nœud fils droit
    private Node right;

    /**
     * Méthode permettant d'obtenir la valeur du nœud.
     * @return La valeur du nœud.
     */
    public double getValue(){
        return this.value;
    }

    /**
     * Méthode permettant de définir la valeur du nœud.
     * @param value La valeur à définir pour le nœud.
     */
    public void setValue(double value){
        this.value = value;
    }

    /**
     * Méthode permettant d'obtenir le nœud fils droit.
     * @return Le nœud fils droit.
     */
    public Node getRight(){
        return this.right;
    }

    /**
     * Méthode permettant de définir le nœud fils droit.
     * @param right Le nœud fils droit à définir.
     */
    public void setRight(Node right){
        this.right = right;
    }

    /**
     * Méthode permettant d'obtenir le nœud fils gauche.
     * @return Le nœud fils gauche.
     */
    public Node getLeft(){
        return this.left;
    }

    /**
     * Méthode permettant de définir le nœud fils gauche.
     * @param left Le nœud fils gauche à définir.
     */
    public void setLeft(Node left){
        this.left = left;
    }

    /**
     * Méthode d'affichage du nœud.
     * Non implémentée dans cette classe.
     */
    public void affichage(){

    }
}
