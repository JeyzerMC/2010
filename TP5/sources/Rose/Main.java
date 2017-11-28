import java.util.ArrayList;
//Par Rose Hirigoyen en Mehdi Chaid 

public class Main {
	public static void main(String[] args) {
		//Exercice 1
		Node a = new Node(3);
		Node b = new Node(4);
		Node c = new Node(5);
		Node d = new Node(9);
		Node e = new Node(7);
		Node f = new Node(8);	
		Node g = new Node(12);
		Node h = new Node(13);
		
		Node i = new Node(11);
		Node j = new Node(30);
		Node k = new Node(1);
		Node l = new Node(50);
		Node m = new Node(3);
		Node n = new Node(24);
		Node o = new Node(10);
		Node p = new Node(2);
		
		
		System.out.println("\n\nEXERCICE 1");
		System.out.println("===============================");
		System.out.println("TEST 1 : FUSION");
		
		try{
			i = i.fusion(j);
			k = k.fusion(l);
			m = m.fusion(n);
			o = o.fusion(p);
			
			i = i.fusion(k);
			m = m.fusion(o);
			 
			i = i.fusion(m);

			System.out.println("1 : Fusion de 2 arbres d'ordre 0");
			a = a.fusion(b);
			a.print(" ");
			System.out.println("ordre de cet arbre: " + a.ordre);
			c = c.fusion(d);

			e = e.fusion(f);
			g = g.fusion(h);

			
			System.out.println("1 : Fusion de 2 arbres d'ordre 1");
			a = a.fusion(c);
			a.print(" ");
			System.out.println("ordre de cet arbre: " + a.ordre);

			
			e = e.fusion(g);
			System.out.println("1 : Fusion de 2 arbres d'ordre 2");
			a = a.fusion(e);
			a.print(" ");
			System.out.println("ordre de cet arbre: " + a.ordre);

			//System.out.println("1 : Fusion de 2 arbres d'ordre 3");
			//a = a.fusion(i);
			//a.print(" ");
			//System.out.println("ordre de cet arbre: " + a.ordre);
			
		}
		catch(Exception err){
			System.out.println(err);
		}

		System.out.println("===============================");
		System.out.println("TEST 2 : SORTED");
		
		System.out.println("Attendu : [3, 4, 5, 7, 8, 9, 12, 13]");
		System.out.println("Reçu : " + a.getElementsSorted());
		

		System.out.println("===============================");
		System.out.println("TEST 3 : FIND VALUE");

		System.out.println("Attendu : 9");
		System.out.println("Reçu : " + a.findValue(9).getVal());
		System.out.println("");
		System.out.println("Attendu : null");
	 	System.out.println("Reçu : " + a.findValue(40));

		System.out.println("===============================");
		System.out.println("TEST 4 :  DELETE ");
		
		ArrayList<Node> resultat = b.delete();

		for(Node noeud : resultat){
			noeud.print(" ");
			System.out.println("ordre de cet arbre: " + noeud.ordre);
			System.out.println("");
		}
		
		System.out.println("===============================");
		System.out.println("TEST 5 : SORTED");
		
		System.out.println("Attendu: [3]");
		System.out.println("Reçu: " + a.getElementsSorted());

		System.out.println("===============================");
		System.out.println("TEST 6 : FIND VALUE");
		System.out.println("Attendu : null");
		System.out.println("Reçu: " + a.findValue(12));
		System.out.println("===============================");
		
		//Exercice 2
		System.out.println("\n\nEXERCICE 2");
		Monceau monceauTestA = new Monceau();
		Monceau monceauTestB = new Monceau();

		System.out.println("===============================");
		System.out.println("TEST 1 : INSERT\n");
		System.out.println("Dans le monceau A");
		System.out.println("insertion de 4, 5, 10, 12 et 3");
		monceauTestA.insert(4);
		monceauTestA.insert(5);
		monceauTestA.insert(20);
		monceauTestA.insert(12);
		monceauTestA.insert(3);
		System.out.println("+-+-+-+-+-+-+\n");
		System.out.println("Dans le monceau B");
		System.out.println("insertion de 30, 20 et 19");
		monceauTestB.insert(30);
		monceauTestB.insert(20);
		monceauTestB.insert(19);
		System.out.println("+-+-+-+-+-+-+\n");
		System.out.println("Monceau A:");
		monceauTestA.print();
		System.out.println("\nMonceau B:");
		monceauTestB.print();

		System.out.println("===============================");
		System.out.println("TEST 2 : FUSION\n");
		monceauTestA.fusion(monceauTestB);
		System.out.println("Monceau A:");
		monceauTestA.print();
		System.out.println("===============================");
		System.out.println("TEST 3 : DELETE\n");
		System.out.println("Suppression de 19");
		monceauTestA.delete(19);
		monceauTestA.print();
		
		
	}

}
