package fr.iutfbleau.sae32.entity;

/**
 * La classe NodeOperation représente un nœud opérationnel dans un arbre binaire.
 * Elle hérite de la classe Node.
 */
public class NodeOperation extends Node {

    // L'opération associée au nœud
    private String operation;

    // Le nœud fils gauche
    private Node left;

    // Le nœud fils droit
    private Node right;

    /**
     * Constructeur de NodeOperation.
     * Crée un nœud opérationnel avec une opération spécifiée.
     * @param operation L'opération du nœud opérationnel.
     */
    public NodeOperation(String operation) {
        super();
        this.operation = operation;
    }

    /**
     * Méthode permettant d'obtenir l'opération associée au nœud.
     * @return L'opération associée au nœud.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Méthode permettant d'obtenir le nœud fils gauche.
     * @return Le nœud fils gauche.
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Méthode permettant d'obtenir le nœud fils droit.
     * @return Le nœud fils droit.
     */
    public Node getRight() {
        return right;
    }

    /**
     * Méthode d'affichage du nœud opérationnel.
     * Affiche l'opération du nœud suivi d'un espace, puis affiche les nœuds fils gauche et droit.
     */
    @Override
    public void affichage() {
        System.out.print(this.operation +" ");
        this.left.affichage();
        this.right.affichage();
    }

    /**
     * Méthode permettant de définir le nœud fils gauche.
     * @param left Le nœud fils gauche à définir.
     */
    @Override
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Méthode permettant de définir le nœud fils droit.
     * @param right Le nœud fils droit à définir.
     */
    @Override
    public void setRight(Node right) {
        this.right = right;
    }
}
