public class Set<T> { //Class Set with generic of type T
    private LinearNode<T> setStart; // Set LinearNode to setStart
    public Set() {
        setStart = null; // Head of Single Linked List
    }
    public void add(T element) {
        LinearNode<T> newNode = new LinearNode<T>(element); // Create new Node
        newNode.setNext(setStart); // Point new node to start
        setStart = newNode; // Set start to new node



    }
    public int getLength() {
        LinearNode<T> current = setStart; // Start at the head
        int i = 0; // Counter for length
        while (current != null) { // null is when we have reached the end
            current = current.getNext(); // keep going
            i++; //incrementing by one
        }
        return i; // return length
    }
    public T getElement(int i) {
        LinearNode<T> current = setStart; //start at head
        int counter = 0; // counter
        while (current != null) { //while we have not reached end of list
            if (i == counter) { // if i is equal to the counter, then that is the element we want
                return current.getElement(); //return element and end program
            }
            current = current.getNext(); // if not continue
            counter++; // keep incrementing
        }
        return null; // return null should it not be found
    }
    public boolean contains(T element) {
        LinearNode<T> current = setStart; // Start at head of list
        while (current != null) { //while we are not at the end of list
            if (current.getElement() == element) { //if the current element equals the element we are looking for then:
                return true; // return true and exits function
            }
            current = current.getNext(); // keep going if not
        }
        return false; // return false should it not be found
    }
    public String toString() {
        String S = ""; // empty string
        LinearNode<T> current = setStart; // head of linked list
        while (current != null) { //while not at tail
            S+= (current.getElement() + " "); //adding current element string to S
            current = current.getNext(); // keep going until end of linked list
        }
        return S; // return string s
    }

}