package fr.iutfbleau.sae32.main;

import fr.iutfbleau.sae32.view.Fenetre;

/**
 * La classe Main est la classe principale de l'application.
 * Elle lance l'application en créant une instance de la fenêtre principale et en la rendant visible.
 */
public class Main {
    
    /**
     * Méthode principale de l'application.
     * Elle crée une instance de la fenêtre principale et la rend visible.
     * @param args Les arguments de ligne de commande (non utilisés dans cette application).
     */
    public static void main(String[] args) {
        Fenetre f = new Fenetre();
        f.setVisible(true);
    }
}
