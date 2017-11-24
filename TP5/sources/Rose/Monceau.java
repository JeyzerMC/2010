
import java.util.ArrayList;

public class Monceau {
    ArrayList<Node> arbres;
    
    public Monceau() {
        arbres = new ArrayList<Node>();
    }
    
    public void fusion(Monceau autre) {
       int taille_max = this.arbres.size() > autre.arbres.size() ? this.arbres.size() : autre.arbres.size();

       ArrayList<Node> tempArbres = new ArrayList<>();
       Node retenue = null;
       int i = 0;
       Node arbreA, arbreB;
        System.out.println("CURRENT: " + arbres);
        System.out.println("AUTRE: " + autre.arbres);
       for(; i < taille_max; i++){
           arbreA = i < this.arbres.size() ? this.arbres.get(i) : null;
           System.out.println("A: " + arbreA);
           arbreB = i < autre.arbres.size() ? autre.arbres.get(i) : null;
           System.out.println("B: " + arbreB);
           
           ArrayList<Node> ABR = new ArrayList<Node>();
           
           if(arbreA != null){
               ABR.add(arbreA);
               System.out.println(arbreA.ordre);
            }
            if(arbreB != null) {
                ABR.add(arbreB);
                System.out.println(arbreB.ordre);
            }
           if(retenue != null) {
               ABR.add(retenue);
           }
           System.out.println("ABR: " + ABR);

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
       System.out.println("FINAL:" + arbres);
    }
    
    public void insert(int val) {
        Node nouvNoeud = new Node(val);
        Monceau nouvMonceau = new Monceau();
        nouvMonceau.arbres.add(nouvNoeud);
        this.fusion(nouvMonceau);
    }
    
    public boolean delete (int val) {
        boolean res = false;

        //pour chaque arbre
        for(int i = 0; i < arbres.size(); i++) {
            Node found = arbres.get(i).findValue(val);
            //tant quon trouve la valeur
            while(found != null){
                res = true;
                //tableaux a fusionner
                ArrayList<Node> nouvNodes = found.delete();
                //pour chaque tableau
                for (Node noeud : nouvNodes) {
                    //faire un nouveau monceau contenant l'arbre
                    Monceau nouvMonceau = new Monceau();
                    nouvMonceau.arbres.add(noeud);
                    //remettre dans le monceau
                    this.fusion(nouvMonceau);
                }
                //rechercher
                found = (arbres.get(i)).findValue(val);

            }
            
        }
        
        return res;
    }
    
    public void print() {
        for (Node arbre : arbres){
            if(arbre != null){
                System.out.print("L'ordre de l'arbre est:" + arbre.ordre + "\n");
                arbre.print("   ");
            }
        }
    }
    
}
