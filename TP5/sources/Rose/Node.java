
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

    public Node fusion(Node autre) throws DifferentOrderTrees {

        if(autre.parent != null || autre.valeur == self.valeur) return null;

        Node max_root,min_root;
        if(autre.valeur < self.valeur){
            max_root = self;
            min_root = autre;
        }
        else {
            max_root = autre;
            min_root = self;
        }

        min_root.parent = max_root;
        max_root.addEnfant(min_root);
        max_root.ordre++;
        return max_root;
    }

    private void moveUp() {
        // à compléter
    }

    //à compléter
    public ArrayList<Node> delete() {
        for (Node child : enfants)
            child.parent = null;

        ArrayList<Node> children = self.enfants;
        self = null;
        return children;
    }

    public void print(String tabulation) {
        // à compléter
    }
    
    //à compléter
    public Node findValue(int valeur) {
        if(self.value == valeur) return self;
        else if(self.value > valeur) return null;

        for (Node child : self.enfants)
            child.findValue(valeur);

        return null;
    }
    
    public ArrayList<Integer> getElementsSorted() {
    	// à compléter
    	
    	return null;
    }
}
