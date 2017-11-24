import java.util.ArrayList;

//import org.classpath.icedtea.pulseaudio.Stream.SuspendedListener;

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
		
		System.out.println("\n\nEXERCICE 1");
		System.out.println("===============================");
		System.out.println("TEST 1 : FUSION");

		try{
			System.out.println("1 : Fusion de 2 arbres d'ordre 0");
			a = a.fusion(b);
			a.print(" ");
			c = c.fusion(d);

			e = e.fusion(f);
			g = g.fusion(h);

			
			System.out.println("1 : Fusion de 2 arbres d'ordre 1");
			a = a.fusion(c);
			a.print(" ");
			e = e.fusion(g);

			
			System.out.println("1 : Fusion de 2 arbres d'ordre 2");
			a = a.fusion(e);
			a.print(" ");
		}
		catch(Exception err){
			System.out.println(err);
		}

		System.out.println("===============================");
		System.out.println("TEST 2 : SORTED");
		
		System.out.println("Attendu : [3, 4, 5, 6, 7, 8, 9, 12, 13]");
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
		
		ArrayList<Node> resultat = g.delete();

		for(Node noeud : resultat){
			noeud.print(" ");
			System.out.println("");
		}
		
		System.out.println("===============================");
		System.out.println("TEST 5 : SORTED");
		
		System.out.println("Attendu: [3, 7, 8, 13]");
		System.out.println("Reçu: " + a.getElementsSorted());

		System.out.println("===============================");
		System.out.println("TEST 6 : FIND VALUE");
		System.out.println("Attendu : null");
		System.out.println("Reçu: " + a.findValue(12));
		System.out.println("===============================");
		
		//Exercice 2
		System.out.println("\n\nEXERCICE 2");
		Monceau monceauTest = new Monceau();
		System.out.println("test du insert");
		System.out.println("insertion de 4");
		monceauTest.insert(4);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		System.out.println("insertion de 5");
		monceauTest.insert(5);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		System.out.println("insertion de 10");
		monceauTest.insert(10);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		System.out.println("insertion de 12");
		monceauTest.insert(12);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		System.out.println("insertion de 3");
		monceauTest.insert(3);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		System.out.println("insertion de 30");
		monceauTest.insert(30);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		System.out.println("insertion de 20");
		monceauTest.insert(20);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		System.out.println("insertion de 19");
		monceauTest.insert(19);
		monceauTest.print();
		System.out.println("+-+-+-+-+-+-+");
		
		
	}

}
