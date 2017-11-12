import java.util.ArrayList;

// Implémentation de l'interface IBibliotheque
// à l'aide d'un arbre de recherche binaire.
public class BibliothequeBst implements IBibliotheque
{
    private BST<String> livres = new AvlTree<String>();

    // Complexité: O(log(n))
    // Explication: L'insertion dans un arbre BST
    //              se fait en complexité O(log(n))
    //              car nous n'avons pas besoin de 
    //              parcourir tous les éléments pour 
    //              trouver la position où insérer.
    public void ajouterLivre(String livre)
    {
        livres.insert(livre);
    }

    // Complexité: O(log(n))
    // Explication: On a pas besoin de parcourir 
    //              chaque element de l'arbre pour
    //              trouver le livre car à chaque 
    //              itération on élimine soit à droite
    //              soit à gauche, et donc on tombe en
    //              O(log(n)): On part toujours vers un 
    //              des deux cotés.
    public boolean contientLivre(String livre)
    {
        return livres.contains(livre);
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre/ascendant.
    //              On doit parcourir chaque élément pour 
    //              pouvoir les afficher.
    public String afficherLivresAlpha()
    {
        String msg = "";
        ArrayList<String> livres_en_ordre = livres.traverseInOrder();
        for (String node : livres_en_ordre) 
            msg += node + "\n";
        
        return msg;
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre inverse/descendant.
    //              On doit parcourir chaque élément pour 
    //              pouvoir les afficher.
    public String afficherLivresAlphaInverse()
    {
        String msg = "";
        ArrayList<String> livres_en_ordre = livres.traverseReverseOrder();
        for (String node : livres_en_ordre) 
            msg += node + "\n";
        
        return msg;
    }
}