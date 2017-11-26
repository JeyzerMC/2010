
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
    	if (ordre != autre.ordre)
    		throw new DifferentOrderTrees();
    	
    	if (this.parent != null || autre.parent != null)
    		return null;
    	
    	if (this.valeur <= autre.valeur){
    		this.addEnfant(autre);
    		autre.parent = this;
    		ordre++;
    		return this;
    	}
    	
		autre.addEnfant(this);
		parent = autre;
		autre.ordre++;
		return autre;
    }

    private void moveUp() {
		/*Node oldParent = parent;
		Node oldGrandParent = parent.parent;
		
    	if (oldGrandParent != null) {
    		oldGrandParent.addEnfant(this);
    		oldGrandParent.removeEnfant(oldParent);
    		this.parent = oldGrandParent;
    	}
    	else
    		this.parent = null;
    	
		oldParent.removeEnfant(this);
		
    	ArrayList<Node> enfantsTemp = oldParent.getEnfants();
    	for (Node enfant: enfantsTemp)
			enfant.parent = this;
			
		oldParent.enfants = enfants;
		
    	for (Node enfant: enfants)
    		enfant.parent = oldParent;
		enfants = enfantsTemp;
		
		this.addEnfant(oldParent);
		
    	oldParent.ordre--;
		this.ordre++;*/
		Node temp = this.parent;
        ArrayList<Node> enfantsTemp;
        enfantsTemp = this.enfants;

        this.parent.parent.enfants.add(this);
        this.parent.parent.enfants.remove(this.parent);

        this.enfants = this.parent.enfants;
        this.enfants.add(this.parent);
        this.enfants.remove(this);
        this.parent.enfants = enfantsTemp;
        for (Node enfant: this.enfants) {
            enfant.parent = this;
        }
        for (Node enfant: this.parent.enfants) {
            enfant.parent = this.parent;
        }
        this.parent = this;
        this.ordre++;
        temp.parent = this;
        temp.ordre--;
    }

    public ArrayList<Node> delete() {
    	while (parent != null)
    		this.moveUp();
    	
    	for (Node enfant: enfants)
			enfant.parent = null;
			
    	return enfants;
    }

    public void print(String tabulation) {
    	System.out.print(tabulation + valeur);
    	if (enfants.isEmpty())
    		System.out.println();
    	
		for (int i = 0; i < ordre; i++) 
    		for (Node enfant: enfants) 
    			if (enfant.enfants.size() == i) 
    				if (enfant.enfants.isEmpty()) {
        				System.out.println("  " + enfant.valeur);
        				tabulation += tabulation.substring(0, 1) + tabulation.substring(0, 1) + tabulation.substring(0, 1);
    				}
    				else 
						enfant.print(tabulation);
						
    }
    
    public Node findValue(int valeur) {
		Node temp = null;
		
    	for(Node enfant: enfants){
    		if (enfant.valeur == valeur)
    			return enfant;
    		if (enfant.valeur < valeur)
    			temp = enfant.findValue(valeur);
    		if (temp != null)
				return temp;
		}
		
    	return null;
    }
    
    public ArrayList<Integer> getElementsSorted() {
    	ArrayList<Integer> elements = new ArrayList<Integer>();
    	elements.add(valeur);
    	for (Node enfant: enfants)
    		elements.addAll(enfant.getElementsSorted());
    	elements.sort(null);
    	return elements;
    }
}
