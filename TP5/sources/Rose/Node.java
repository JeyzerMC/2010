
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


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

		if (this.valeur <= autre.valeur) {
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
		Node grandParent = this.parent.parent;
		ArrayList<Node> enfantsPere = this.parent.enfants;

		this.parent.ordre = this.ordre;
		this.ordre++;

		this.parent.enfants = this.enfants;
		this.enfants = enfantsPere;

		enfantsPere.remove(this);
		this.enfants.add(this.parent);

		for (Node enfant : this.enfants){
			enfant.parent = this;
		}

		for (Node enfant : this.parent.enfants){
			enfant.parent = this.parent;
		}

		this.parent.parent = this;
		this.parent = grandParent;

		//réordonner les enfants selon leur ordre pour faciliter le traitement des prochaines étapes
		Collections.sort(enfants, Comparator.comparing(n -> n.ordre));
	}

	public ArrayList<Node> delete() {
		while (this.parent != null)
			this.moveUp();

		for (Node enfant : this.enfants)
			enfant.parent = null;

		return enfants;
	}

	public void print(String tabulation) {
		System.out.print(tabulation + valeur);
		if (enfants.isEmpty())
			System.out.println();

		for (int i = 0; i < ordre; i++)
			for (Node enfant : enfants)
				if (enfant.enfants.size() == i)
					if (enfant.enfants.isEmpty()) {
						System.out.println("  " + enfant.valeur);
						tabulation += tabulation.substring(0, 1) + tabulation.substring(0, 1)
								+ tabulation.substring(0, 1);
					} else
						enfant.print(tabulation);

	}

	public Node findValue(int valeur) {
		Node temp = null;

		for (Node enfant : enfants) {
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
		for (Node enfant : enfants)
			elements.addAll(enfant.getElementsSorted());
		elements.sort(null);
		return elements;
	}
}
