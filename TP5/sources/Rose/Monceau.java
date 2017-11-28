
import java.util.ArrayList;


public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }

    public void fusion(Monceau autre) {
       int taille_max = this.arbres.size() > autre.arbres.size() ? this.arbres.size() : autre.arbres.size();

       ArrayList<Node> tempArbres = new ArrayList<Node>();
       ArrayList<Node> ABR = new ArrayList<Node>();

       Node retenue = null;
       int i = 0;
       Node arbreA, arbreB;

       for(; i < taille_max; i++){
           arbreA = i < this.arbres.size() ? this.arbres.get(i) : null;
           arbreB = i < autre.arbres.size() ? autre.arbres.get(i) : null;      
           ABR.clear();

           if(arbreA != null){
               ABR.add(arbreA);
            }
            if(arbreB != null) {
                ABR.add(arbreB);
            }
           if(retenue != null) {
               ABR.add(retenue);
               retenue = null;
           }
        
           try{
               switch (ABR.size()) {
                    case 0:
                        tempArbres.add(null);
                        break;
                
                    case 1:
                        tempArbres.add(ABR.get(0));
                        break;
                
                    case 2:
                        tempArbres.add(null);
                        retenue = (ABR.get(0)).fusion(ABR.get(1));
                        break;
                
                    case 3:
                        retenue = (ABR.get(0)).fusion(ABR.get(1));
                        tempArbres.add(ABR.get(2));
                        break;
                    }
            
            }    
            catch (Exception e) {
                System.out.println(e.getMessage());                
            }
       }

       if(retenue != null) tempArbres.add(retenue);
       this.arbres = tempArbres;
    }
    
    public void insert(int val) {
        Node nouvNoeud = new Node(val);
        Monceau nouvMonceau = new Monceau();
        nouvMonceau.arbres.add(nouvNoeud);
        this.fusion(nouvMonceau);
    }
    
    public boolean delete (int val) {
        boolean removed = false;
        Node found = null;
        Monceau monc_a_reinserer = null;
        ArrayList<Node> found_nodes;

        for(int i = 0; i < arbres.size(); i++) {
            if(arbres.get(i) != null) {
                found = arbres.get(i).findValue(val);

                while(found!=null) {
                    monc_a_reinserer = new Monceau();
                    removed = true;
                    arbres.set(i, null);

                    found_nodes = found.delete();

                    for (Node noeud : found_nodes) {

                        monc_a_reinserer.arbres.add(noeud);
                    }

                    fusion(monc_a_reinserer);
                    found = arbres.get(i) == null ? null : (arbres.get(i)).findValue(val);
                }
            }
        }

        return removed;

    }
    
    public void print() {
        for (Node arbre : arbres){
            if(arbre != null){
                System.out.print("L'ordre de l'arbre est: " + arbre.ordre + "\n");
                arbre.print("   ");
            }
        }
    }
    
}
