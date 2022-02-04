import java.util.Objects;

interface NumList{
    int size();                                             // return the number of elements of NumArrayList
    int capacity();                                         // return the capacity of NumArrayList
    void add(double value);                                 // add a value to the end of NumArrayList.
    void insert(int i, double value);                       // inserts a element before i-th element. using 0 as start.
    void remove(int i);                                     // remove the i-th element. do nothing if i > size
    double lookup(int i) throws NotValidIndexException;     // look for the i-th element. throw an exception if not found.
    boolean contains(double value);                         // return true if the array contains input value.
    boolean equals(NumList otherList);                      // return true if the otherList equals the other list.
    void removeDuplicates();                                // remove duplicates of any elements in the list.
    String toString();                                      // convert the list to a String.
}

public class NumLinkedList implements NumList{
    // the head of the whole linked list
    private LLNode head;
    // the tail of the whole linked list
    private LLNode tail;
    // attributes of linked list
    private final int capacity = Integer.MAX_VALUE;
    private int elements = 0;
    // dummy head that used to avoid empty linked list.
    private LLNode dummyHead = new LLNode(null);
    private LLNode dummyTail = new LLNode(null);

    public NumLinkedList(){
        dummyHead.setNext(dummyTail);
        dummyTail.setPrevious(dummyHead);
    }

    public NumLinkedList(int capacity){
        dummyHead.setNext(dummyTail);
        dummyTail.setPrevious(dummyHead);
    }

    private void addIfNull(double value){
        if(elements == 0){
            LLNode newNode = new LLNode(value);
            newNode.setPrevious(dummyHead);
            newNode.setNext(dummyTail);
            dummyTail.setPrevious(newNode);
            dummyHead.setNext(newNode);
            this.head = newNode;
            this.tail = newNode;
            elements++;
        }
    }

    public int size(){
        return this.elements;
    }

    public int capacity(){
        return this.capacity;
    }

    public void add(double value){
        this.addIfNull(value);
        LLNode newNode = new LLNode(value);
        newNode.setPrevious(tail);
        newNode.setNext(dummyTail);
        tail.setNext(newNode);
        dummyTail.setPrevious(tail);
        tail = tail.getNext();
        elements++;
    }

    public void insert(int i, double value){
        LLNode ptr = head;
        if(i < elements){
            for(int j = 0; j < i; j++) {
                ptr = ptr.getNext();
            }
            LLNode newNode = new LLNode(value);
            newNode.setPrevious(ptr);
            newNode.setNext(ptr.getNext());
            ptr.getNext().setPrevious(newNode);
            ptr.setNext(newNode);
            if(newNode.getNext().getValue() == null) tail = tail.getNext();
        }
        else {this.add(value);}
        elements++;
    }

    public void remove(int i){
        if(i < elements){
            tail = tail.getPrevious();
            tail.setNext(dummyTail);
            dummyTail.setPrevious(tail);
        }
        elements--;
    }

    public boolean contains(double value) {
        LLNode ptr = head;
        while(ptr.getValue() != null){
            if (ptr.getValue() == value) return true;
            ptr = ptr.getNext();
        }
        return false;
    }

    public double lookup(int i) throws NotValidIndexException{
        if(this.size() < i+1){
            throw new NotValidIndexException("This index is not valid");
        }
        LLNode ptr = head;
        for(int j = 0; j < i; j++){
            ptr = ptr.getNext();
        }
        return ptr.getValue();
    }

    public boolean equals(NumList otherList){
        if(this.size() == 0 && otherList.size() == 0){
            return true;
        }
        if(this.size() != otherList.size()){
            return false;
        }
        LLNode ptr = head;
        for(int i = 0; i < this.size(); i++){
            try {
                if(ptr.getValue() != otherList.lookup(i)){
                    return false;
                }
            } catch (NotValidIndexException e) {
                System.out.println("This index is not valid");
            }
        }
        return true;
    }

    public void removeDuplicates(){
        LLNode ptr = head;
        NumLinkedList newList = new NumLinkedList();
        while(ptr.getValue() != null){
            if(!newList.contains(ptr.getValue())) newList.add(ptr.getValue());
        }
    }

    public String toString(){
        LLNode ptr = head;
        StringBuilder str = new StringBuilder();
        while(ptr.getValue() != null){
            str.append(ptr.getValue());
            ptr = ptr.getNext();
        }
        return str.toString();
    }

    public boolean isSorted(){
        LLNode ptr = head, ptrNext = head.getNext();
        while(ptrNext.getValue() != null){
            if(!Objects.equals(ptr.getValue(), ptrNext.getValue())){
                return false;
            }
            else {
                ptr = ptr.getNext();
                ptrNext = ptrNext.getNext();
            }
        }
        return true;
    }

    public void reverse(){
        LLNode ptr = tail, ptrNext = tail.getPrevious();
        while(ptrNext.getValue() != null){

        }
    }
}