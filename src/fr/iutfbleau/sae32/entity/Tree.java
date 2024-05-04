package fr.iutfbleau.sae32.entity;

import fr.iutfbleau.sae32.entity.graphics.SPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.DataFormatException;

/**
 * La classe Tree représente un arbre binaire utilisé pour évaluer des formules mathématiques.
 */
public class Tree {

    // La racine de l'arbre
    private Node root;

    // La carte des panneaux associés aux nœuds
    private Map<String, SPanel> panelMap;

    // La file contenant la formule à évaluer
    private Deque<String> formule;

    // La liste des panneaux utilisés dans la formule
    private List<SPanel> panelUsed;

    /**
     * Constructeur de Tree.
     * Crée un arbre binaire à partir d'une formule donnée et d'une carte de panneaux.
     * @param formule La formule à évaluer.
     * @param panelMap La carte des panneaux associés aux nœuds de l'arbre.
     */
    public Tree(String[] formule, Map<String,SPanel> panelMap) {
        List<String> list = Arrays.asList(formule);
        this.formule = new LinkedList<>(list);
        this.panelUsed = new ArrayList<>();
        this.panelMap = panelMap;
        try {
            create(formule);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        } catch (DataFormatException e) {
            throw new RuntimeException(e);
        } catch (NullPointerException e){
            throw new NullPointerException();
        }
    }

    /**
     * Fonction interne qui crée l'arbre à partir de la formule donnée.
     * @param formule La formule à partir de laquelle créer l'arbre.
     * @throws IllegalArgumentException Si une formule comporte des caractères illégaux.
     * @throws ArithmeticException Si une division par 0 est donnée.
     * @throws IndexOutOfBoundsException Si la formule donnée n'est pas possible à interpréter.
     */
    public void create(String[] formule) throws DataFormatException, NullPointerException {
        Deque<Node> stack = new LinkedList<>();
        try {
            if(formule.length==1){
                char[] chars = formule[0].toCharArray();
                if (Character.isDigit(chars[0])) {
                    this.root = new NodeNumber(Double.parseDouble(formule[0]));
                } else if (Character.isAlphabetic(formule[0].charAt(0)) && formule[0].charAt(0) <= 'I' && !formule[0].equals("-") && !formule[0].equals("/") && !formule[0].equals("+") && !formule[0].equals("*")){
                    if(this.panelMap.get(formule[0]) == null){
                        throw new NullPointerException();
                    } else {
                        this.root = new NodePanel(this.panelMap.get(formule[0]));
                        this.panelUsed.add(this.panelMap.get(formule[0]));
                    }
                } else {
                    throw new IllegalArgumentException("Seule un opérateur a été renseignée");
                }
            } else if (formule.length!=1 && (formule.length%2)!=0){
                String temp = this.formule.pop();
                this.root = new NodeOperation(temp);
                try {
                    computeTree(this.root, this.formule);
                } catch (NullPointerException e) {
                    throw new NullPointerException();
                }

            }
        } catch (Exception e){
            throw new DataFormatException();
        }

    }



    /**
     * Fonction interne qui crée l'arbre à partir de la formule donnée de manière récursive.
     * @param father Le nœud père dans la création de l'arbre.
     * @param data La file contenant les données de la formule.
     */
    private Node computeTree(Node father, Deque<String> data){
        String operate = data.pop();
        if (Character.isDigit(operate.charAt(0))) {
            Node child = new NodeNumber(Double.parseDouble(operate));
            if(father.getLeft()==null){
                father.setLeft(child);
                computeTree(father, data);
            } else if(father.getLeft() != null && father.getRight() == null){
                father.setRight(child);
            }
        } else if (Character.isAlphabetic(operate.charAt(0)) && operate.charAt(0) <= 'I' && !operate.equals("-") &&
                !operate.equals("/") && !operate.equals("+") && !operate.equals("*")){
            if(this.panelMap.get(operate) == null){
                throw new NullPointerException();
            } else {
                this.panelUsed.add(this.panelMap.get(operate));
                Node child = new NodePanel(this.panelMap.get(operate));
                if(father.getLeft()==null){
                    father.setLeft(child);
                    computeTree(father, data);
                } else if(father.getLeft() != null && father.getRight() == null){
                    father.setRight(child);
                }
            }
        } else {
            Node child = new NodeOperation(operate);
            if(father.getLeft()==null){
                father.setLeft(computeTree(child, data));
                if(!data.isEmpty()){
                    computeTree(father, data);
                }
            } else if(father.getLeft() != null && father.getRight() == null){;
                father.setRight(computeTree(child, data));
                if(!data.isEmpty()){
                    computeTree(father, data);
                }
            }
        }
        return father;
    }

    /**
     * Méthode permettant d'évaluer la formule mathématique et de calculer le résultat.
     * @return Le résultat du calcul.
     */
    public double calculer() {
        try {
            return evaluate(this.root);
        } catch (ArithmeticException e){
            throw e;
        }

    }

    /**
     * Fonction interne qui évalue l'arbre de manière récursive.
     * @param father Le nœud père dans l'évaluation de l'arbre.
     * @return La valeur résultante du nœud évalué.
     */
    private double evaluate(Node father) {
        double value = 0;

        if(father.getClass()==NodeNumber.class){
            return father.getValue();
        } else if(father.getClass()==NodePanel.class) {
            return father.getValue();
        } else {
            double right = 0;
            double left = 0;

            if(father.getRight().getClass()==NodeNumber.class){
                right = father.getRight().getValue();
            } else if(father.getRight().getClass()==NodePanel.class){
                right = father.getRight().getValue();
            } else {
                right = evaluate(father.getRight());
            }

            if(father.getLeft().getClass()==NodeNumber.class){
                left = father.getLeft().getValue();
            } else if(father.getLeft().getClass()==NodePanel.class){
                left = father.getLeft().getValue();
            } else {
                left = evaluate(father.getLeft());
            }
            try {
                return operator((NodeOperation) father, left, right);
            } catch (ArithmeticException e){
                throw new ArithmeticException();
            }

        }
    }

    /**
     * Fonction interne qui effectue l'opération spécifiée entre les valeurs données.
     * @param father Le nœud opération contenant l'opération à effectuer.
     * @param left La valeur gauche de l'opération.
     * @param right La valeur droite de l'opération.
     * @return Le résultat de l'opération.
     */
    private double operator(NodeOperation father, double left, double right){
        if(Objects.equals(father.getOperation(), "+")){
            return left+right;
        } else if (Objects.equals(father.getOperation(), "-")) {
            return left-right;
        } else if (Objects.equals(father.getOperation(), "*")) {
            return left*right;
        } else {
            if(right==0){
                throw new ArithmeticException();
            } else {
                return left/right;
            }
        }
    }

    /**
     * Méthode permettant d'obtenir la liste des panneaux utilisés dans la formule.
     * @return La liste des panneaux utilisés.
     */
    public List<SPanel> getPanelUsed(){
        return this.panelUsed;
    }

}
