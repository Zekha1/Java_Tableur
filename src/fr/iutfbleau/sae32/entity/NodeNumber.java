package fr.iutfbleau.sae32.entity;

/**
 * La classe NodeNumber représente un nœud numérique dans un arbre binaire.
 * Elle hérite de la classe Node.
 */
public class NodeNumber extends Node {

    /**
     * Constructeur de NodeNumber.
     * Crée un nœud numérique avec une valeur spécifiée.
     * @param value La valeur du nœud numérique.
     */
    public NodeNumber(double value) {
        super();
        setValue(value);
    }

    /**
     * Méthode d'affichage du nœud numérique.
     * Affiche la valeur du nœud numérique suivi d'un espace.
     */
    @Override
    public void affichage() {
        System.out.print(getValue()+ " ");
    }
}
