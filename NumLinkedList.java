interface NumList{
    int size();                                             // return the number of elements of NumArrayList
    int capacity();                                         // return the capacity of NumArrayList
    void add(double value);                                 // add a value to the end of NumArrayList.
    void insert(int i, double value);                       // inserts a element before i-th element. using 0 as start.
    void remove(int i);                                     // remove the i-th element. do nothing if i > size
    double lookup(int i) throws NotValidIndexException;     // look for the i-th element. throw an exception if not found.
    boolean contains(double value);                         // return true if the array contains input value.
    boolean equals(NumList otherList);                      // return true if the otherList equals the other list.
    void removeDuplicates() throws NotValidIndexException;  // remove duplicates of any elements in the list.
    String toString();                                      // convert the list to a String.
}

public class NumLinkedList implements NumList{
    // the head of the whole linked list
    private LLNode head;
    // the tail of the whole linked list
    private LLNode tail;
    // attributes of linked list
    private int capacity = 0, elements = 0;
    // dummy head that used to avoid empty linked list.
    private LLNode dummyHead = new LLNode(0.0); 

    public NumLinkedList(){
        this.head = dummyHead;
    }
    
    public NumLinkedList(int capacity){
        LLNode ptr = dummyHead;
        for(int i = 0; i < capacity; i++){
            LLNode node = new LLNode(null);
            ptr.setNext(node);
            node.setPrevious(ptr);
            ptr.setNext(ptr.getNext());
        }
        this.tail = ptr;
        this.head = dummyHead.getNext();
        this.capacity = capacity;
    }

    public int size(){
        return this.elements;
    }

    public int capacity(){
        return this.capacity;
    }
}