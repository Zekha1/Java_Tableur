package fr.iutfbleau.sae32.controller;

import fr.iutfbleau.sae32.entity.graphics.SPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * La classe CelluleController gère les interactions de souris avec une cellule graphique (SPanel) dans un champ de jeu.
 * Elle implémente l'interface MouseListener pour écouter les événements de souris sur la cellule.
 */
public class CelluleController implements MouseListener {

    // La cellule sélectionnée
    private SPanel selectedPanel;

    // Le contrôleur de champ associé
    private FieldController fieldController;

    /**
     * Constructeur de CelluleController.
     * @param panel Le panneau de cellule à contrôler.
     * @param fc Le contrôleur de champ associé.
     */
    public CelluleController(SPanel panel, FieldController fc){
        this.selectedPanel = panel;
        this.fieldController = fc;
    }

    /**
     * Méthode appelée lorsqu'un bouton de la souris est cliqué.
     * Change la bordure de la cellule sélectionnée et met à jour le contrôleur de champ avec la cellule sélectionnée.
     * @param e L'événement de souris associé.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (this.selectedPanel!=null) {
            this.selectedPanel.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.LIGHT_GRAY));
        }
        this.selectedPanel = (SPanel) e.getSource();
        this.selectedPanel.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Color.BLACK));
        this.fieldController.setSelectedPanel(this.selectedPanel);
        String concatenatedString = String.join("", this.selectedPanel.getFormuleBrut());
        this.fieldController.setText(concatenatedString);
    }

    /**
     * Méthode appelée lorsqu'un bouton de la souris est pressé.
     * Non utilisée dans cette classe.
     * @param e L'événement de souris associé.
     */
    @Override
    public void mousePressed(MouseEvent e) {

    }

    /**
     * Méthode appelée lorsqu'un bouton de la souris est relâché.
     * Non utilisée dans cette classe.
     * @param e L'événement de souris associé.
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Méthode appelée lorsque la souris entre dans la zone de la cellule.
     * Non utilisée dans cette classe.
     * @param e L'événement de souris associé.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Méthode appelée lorsque la souris quitte la zone de la cellule.
     * Non utilisée dans cette classe.
     * @param e L'événement de souris associé.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
