package fr.iutfbleau.sae32.utils;

import java.awt.*;

/**
 * La classe PrimaryColor définit une sélection de couleurs primaires utilisées dans l'application.
 * Elle contient des constantes pour représenter différentes couleurs utilisées dans l'interface utilisateur.
 */
public class PrimaryColor {
    
    // Couleur pour indiquer une formule incorrecte
    public static final Color MAUVAISE_FORMULE = Color.RED;

    // Couleur pour indiquer un caractère inconnu
    public static final Color CARACTERE_INCONNU = Color.ORANGE;

    // Couleur pour indiquer une case vide
    public static final Color CASE_VIDE = new Color(245,245,245);

    // Couleur pour indiquer une cellule correcte
    public static final Color CELLULE_BONNE = new Color(209,209,209);

    // Couleur de fond générale
    public static final Color FOND = new Color(35,215,80);

    // Couleur pour indiquer un calcul impossible
    public static final Color CALCUL_IMPOSSIBLE = new Color(255,100,90);

    // Couleur pour indiquer une boucle de cellules
    public static final Color BOUCLE = new Color(150,180,255);
}
