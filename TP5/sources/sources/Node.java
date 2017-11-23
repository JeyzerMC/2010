
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maitr
 */
public class Node {

    public int ordre;
    public Node parent;

    private int valeur;
    private ArrayList<Node> enfants;

    public Node(int valeur) {
        this.valeur = valeur;
        ordre = 0;
        this.parent = null;
        enfants = new ArrayList<Node>();
    }

    public Node(int valeur, Node parent) {
        ordre = 0;
        this.valeur = valeur;
        this.parent = parent;
        enfants = new ArrayList<Node>();
    }

    public int getVal() {
        return valeur;
    }

    public ArrayList<Node> getEnfants() {
        return enfants;
    }

    public void addEnfant(Node enfant) {
        enfants.add(enfant);
    }

    public boolean removeEnfant(Node enfant) {
        if (enfants.contains(enfant)) {
            enfants.remove(enfant);
            return true;
        }
        return false;
    }

    public void removeEnfants(ArrayList<Node> enfants) {
        this.enfants.removeAll(enfants);
    }

    // Done
    public Node fusion(Node autre) throws DifferentOrderTrees {
        if (this.parent != null || autre.parent != null)
            return null;

        if (this.ordre != autre.ordre)
            throw DifferentOrderTrees();

        if (this.valeur < autre.valeur) {
            this.addEnfant(autre);
            autre.parent = this;
            this.ordre++;
            return this;
        }

        autre.addEnfant(this);
        this.parent = autre;
        autre.ordre++;
        return autre;
    }

    // MoveUp à refaire, pas complet
    private void moveUp() {
        this.parent.removeEnfant(this);
        ArrayList<Node> ancienEnfants = this.parent.enfants;

        this.parent.enfants = this.enfants;
        this.enfants = ancienEnfants;
        this.addEnfant(this.parent);

        Node ancienParent = this.parent.parent;
        this.parent.parent = this;
        this.parent = ancienParent;
    }

    // Done
    public ArrayList<Node> delete() {
        while (this.parent != null)
            this.moveUp();

        for (Node enfant : this.enfants)
            enfant.parent = null;

        return this.enfants;
    }

    // En cours
    public void print(String tabulation) {
        // Soon™
    }

    // Done
    public Node findValue(int valeur) {
        Noeud result = null;
        if (this.valeur == valeur)
            result = this;
        else
            for (Node enfant : this.enfants)
                if (enfant.valeur <= valeur && result == null)
                    result = enfant.findValue(valeur);
        return result;
    }

    // En cours
    public ArrayList<Integer> getElementsSorted() {
        ArrayList<Integer> result = new ArrayList<Integer>();
        result.add(this.valeur);

        for (Node enfant : this.delete()) {
        }

        return null;
    }
}
