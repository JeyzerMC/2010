import java.util.ArrayList;

// Implémentation de l'interface IBibliotheque
// à l'aide d'un arbre de recherche binaire.
public class BibliothequeBst implements IBibliotheque
{
    private BST<String> livres = new AvlTree<String>();

    // Complexité: O(log(n))
    // Explication:
    // À COMPLÉTER
    public void ajouterLivre(String livre)
    {
        livres.insert(livre);
    }

    // Complexité: O(log(n))
    // Explication:
    // À COMPLÉTER
    public boolean contientLivre(String livre)
    {
        return livres.contains(livre);
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre/ascendant.
    // À COMPLÉTER
    public String afficherLivresAlpha()
    {
        String msg = "";
        ArrayList<String> livres_en_ordre = livres.traverseInOrder();
        for (String node : livres_en_ordre) {
            msg += node + " ";
        }
        return msg;
    }

    // Complexité: O(n)
    // Explication: Il ne suffit ici que de parcourir
    //              l'arbre de recherche binaire selon
    //              un parcours en ordre inverse/descendant.
    // À COMPLÉTER
    public String afficherLivresAlphaInverse()
    {
        String msg = "";
        ArrayList<String> livres_en_ordre = livres.traverseReverseOrder();
        for (String node : livres_en_ordre) {
            msg += node + " ";
        }

        return msg;
    }
}