public class DoublyLinkedList<AnyType>
{
    // Un noeud de la liste.
    @SuppressWarnings("hiding")
    private class Node<AnyType>
    {
        private AnyType value;
        private Node prev;
        private Node next;

        public Node(AnyType value, Node prev, Node next)
        {
            this.value = value;
            this.prev = prev;
            this.next = next;
        }

        public void setPrev(Node prev) { this.prev = prev; }

        public Node<AnyType> getPrev() { return this.prev; }

        public void setNext(Node next)
        {
            this.next = next;
        }

        public Node<AnyType> getNext()
        {
            return next;
        }

        public AnyType getValue()
        {
            return value;
        }
    }

    private int size = 0;		 // Nombre d'éléments dans la liste.
    private Node<AnyType> front; // Premier élément de la liste.
    private Node<AnyType> back;  // Dernier élément de la liste.

    // Indique si la liste est vide.
    public boolean empty()
    {
        return size == 0;
    }

    // Retourne la taille de la liste.
    public int size()
    {
        return size;
    }

    // Retourne l'élément à la fin de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekBack()
    {
        if(size == 0) return null;
        return back.getValue();
    }

    // Retourne l'élément au début de la liste.
    // Retourne null si la liste est vide.
    // Complexité asymptotique: O(1)
    public AnyType peekFront()
    {
        if(size == 0) return null;
        return front.getValue();
    }

    // Retourne le noeud à l'indice donné.
    // Complexité asymptotique: O(n)
    private Node<AnyType> getNodeAt(int index)
    {
        Node<AnyType> iterator = front;

        for(int i=0; i<index;i++)
            iterator = iterator.getNext();

        return iterator;
    }

    // Retourne l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public AnyType getAt(int index) throws IndexOutOfBoundsException
    {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException("WESH ALORS");
        Node<AnyType> iterator = front;
        
        for(int i=0; i<index;i++)
            iterator = iterator.getNext();

        return iterator.getValue();
    }

    // Retire l'élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void popBack() throws EmptyListException
    {
        if(size == 0) throw new EmptyListException("WESH ALORS");

        if(size > 1){
            back = back.getPrev();
            back.setNext(null);
        }else{
            back = null;
            front = null;
        }
        
        size--;
        
    }

    // Retire l'élément au début de la liste."
    // Complexité asymptotique: O(1)
    public void popFront() throws EmptyListException
    {
        if(size == 0) throw new EmptyListException("WESH ALORS");

        if(size > 1){
            front = front.getNext();
            front.setPrev(null);
        }else{
            back = null;
            front = null;
        }
        size--;
    }

    // Retire l'élément à l'indice donné.
    // Complexité asymptotique: O(n)
    public void removeAt(int index) throws IndexOutOfBoundsException
    {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException("WESH ALORS");

        Node<AnyType> rmv = getNodeAt(index);
        
        if(index == size - 1){ //si a la fin
            try{
                popBack();
            }
            catch (EmptyListException e) {
                e.printStackTrace();
            }
        }
        else if(index == 0){ //si au début
            try{
                popFront();
            }
            catch (EmptyListException e) {
                e.printStackTrace();
            }
        }
        else{ //si dans le milieu
            rmv.getNext().setPrev(rmv.getPrev());
            rmv.getPrev().setNext(rmv.getNext());
            size--;
        }

    }

    // Ajoute un élément à la fin de la liste.
    // Complexité asymptotique: O(1)
    public void pushBack(AnyType item)
    {
        Node<AnyType> nouv = new Node<AnyType>(item,null,null);
        if(front == null)
            front = nouv;
        else{
            nouv.setPrev(back);
            back.setNext(nouv);
        }
        back = nouv; 
        size++;
    }

    // Ajoute un élément au début de la liste.
    // Complexité asymptotique: O(1)
    public void pushFront(AnyType item)
    {
        Node<AnyType> nouv = new Node<AnyType>(item,null,null);

        if(front == null)
            back = nouv;
        else{
            nouv.setNext(front);
            front.setPrev(nouv);
        }
        front = nouv;
        size++;
    }

    // Ajoute un élément à l'indice donné.
    // Complexité asymtotique: O(n)
    public void insertAt(AnyType item, int index) throws IndexOutOfBoundsException
    {
        if(index >= size || index < 0) throw new IndexOutOfBoundsException("WESH ALORS");
        Node<AnyType> curr = getNodeAt(index);
        Node<AnyType> nouv = new Node<AnyType>(item,null,null);
        if(index == size - 1){ //si a la fin
            pushBack(item);
        }
        else if(index == 0){ //si au début
            pushFront(item);
        }
        else{ //si dans le milieu
            System.out.println("ici");
            System.out.println(index);
            nouv.getNext().setPrev(curr);
            nouv.getPrev().setNext(curr);
            size++;
        }
            
    }
}