import java.lang.Comparable;

import com.sun.org.apache.regexp.internal.recompile;

public class AvlTree<T extends Comparable<T>> extends BST<T>
{
    public boolean isBalanced() { return isBalanced(root); }

    private boolean isBalanced(Node<T> node)
    {
        if (node == null) {
            return true;
        }

        boolean childsBalanced = isBalanced(node.left)
                              && isBalanced(node.right);

        int heightDiff = Math.abs(getHeight(node.left) - getHeight(node.right));

        return childsBalanced && heightDiff <= 1;
    }

    public void insert(T elem) { root = insert(root, elem); }

    private Node<T> insert(Node<T> node, T elem)
    {
        if(node == null){
            return new Node(elem);
        }
        int res = elem.compareTo(node.val);

        if(res < 0){
            node.left = insert(node.left, elem);

            if(getHeight(node.left) - getHeight(node.right)  == 2)
            {
                if(elem.compareTo(node.left.val) < 0) {
                    node = balanceLeftLeft(node);
                }

                else {
                    node = balanceLeftRight(node);
                }
            }
        }

        else if(res > 0) {
            node.right = insert(node.right,elem);
            if(getHeight(node.right) - getHeight(node.left) == 2){
                if(elem.compareTo(node.right.val) > 0){
                    node = balanceRightRight(node);
                }

                else{
                    node = balanceRightLeft(node);
                }
            }
        }

        else{
            ;
        }

        return node;

 
    }

    //Rotate with right child - right right
    private Node<T> balanceRightRight(Node<T> node)
    {
        Node k2 = node.right;

        node.right = k2.left;
        k2.left = node;

        return k2;
    }
    //DoubleWithRightChild - right left
    private Node<T> balanceRightLeft(Node<T> node)
    {
        node.right = balanceLeftLeft(node.right);
        return balanceRightRight(node);
    }

    //Rotate with left child - left left
    private Node<T> balanceLeftLeft(Node<T> node)
    {
        Node k2 = node.left;

        node.left = k2.right;
        k2.right = node;

        return k2;
    }

    //Double with left child - left right
    private Node<T> balanceLeftRight(Node<T> node)
    {
       node.left = balanceRightRight(node.left);
       return balanceLeftLeft(node);
    }


}


//balanceLeftRight  ::   DoubleWithLeftChild //LeftRight
//balanceRightLeft  ::  DoubleWithRightChild //RightLeft
//balanceRightRight :: RotateWithRightChild //RightRight
//balanceLeftLeft   ::    RotateWithLeftChild //LeftLeft
