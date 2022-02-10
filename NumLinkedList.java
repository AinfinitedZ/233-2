import java.util.Objects;


public class NumLinkedList implements NumList{
    // attributes of linked list
    private final int capacity = Integer.MAX_VALUE;
    private int elements = 0;
    /**
     * dummy head that used to avoid empty linked list. Every node thus have a previous
     * node and a next node.
     */
    private LLNode dummyHead = new LLNode(null);
    private LLNode dummyTail = new LLNode(null);

    /**
     * Create a NumLinkedList that initialized with two dummyNodes. 
     */
    public NumLinkedList() {
        dummyHead.setNext(dummyTail);
        dummyTail.setPrevious(dummyHead);
    }
    /**
     * same constructor as <code>NumLinkedList()</code>. Could reuse test of NumArrayList
     * after declaring this Constructor.
     * @param capacity value that doesn't necessary.
     */
    public NumLinkedList(int capacity) {
        dummyHead.setNext(dummyTail);
        dummyTail.setPrevious(dummyHead);
    }

    /**
     * Initialzing the <code>NumLinkedList</code>. would potential invoke by add() method
     * to avoid NullPointerException. 
     * @param value value passed by add() method.
     */
    private void addIfNull(double value) {
        if (elements == 0) {
            LLNode newNode = new LLNode(value);
            newNode.setPrevious(dummyHead);
            newNode.setNext(dummyTail);
            dummyTail.setPrevious(newNode);
            dummyHead.setNext(newNode);
            elements++;
        }
    }
    /**
     * return the total numbers of elements in NumLinkedList
     * @return the total numbers of elements
     */
    public int size() {
        return this.elements;
    }

    /**
     * return the capacity of NumLinkedList. Since the NumLinkedList is dynamic data type, 
     * the capacity would just be declared as <code>final</code> variable.
     * @return Integer.MAX_VALUE
     */
    public int capacity() {
        return this.capacity;
    }

    /**
     * append the specified element to the end of the NumLinkedList.
     * @param value element to be appended
     */
    public void add(double value) {
        if (this.size() == 0) {
            this.addIfNull(value);
        } else {
            LLNode newNode = new LLNode(value);
            newNode.previous = dummyTail.previous;
            newNode.next = dummyTail;
            newNode.getPrevious().setNext(newNode);
            dummyTail.setPrevious(newNode);
            elements++;
        }
    }
    /**
     * insert a element at specific i-th position (i start from 0). Append it to the end
     * if <code>i</code> is larger than elements of list.
     * @param i position (start from 0)
     * @param value element to be inserted or appended. 
     */
    public void insert(int i, double value) {
        LLNode ptr = dummyHead.getNext();
        if (i < this.size()) {
            for (int j = 0; j < i - 1; j++) {
                ptr = ptr.getNext();
            }
            LLNode newNode = new LLNode(value);
            newNode.setPrevious(ptr);
            newNode.setNext(ptr.getNext());
            ptr.getNext().setPrevious(newNode);
            ptr.setNext(newNode);
            elements++;
        } else {
            this.add(value);
        }
    }
    
    /**
     * 
     */
    public void remove(int i) {
        if (i == 0 && this.size() != 0) {
            dummyHead.setNext(dummyHead.getNext().getNext());
            elements--;
        } else {
            if (i < elements - 1 && this.size() != 0) {
                LLNode ptr = dummyHead.getNext();
                for (int j = 0; j < i - 1; j++) {
                    ptr = ptr.getNext();
                }
                LLNode ptrNext = ptr.getNext().getNext();
                ptr.setNext(ptrNext);
                ptrNext.setPrevious(ptr);
                elements--;
            } else if (i == elements - 1 && this.size() != 0) {
                LLNode tail = dummyTail.getPrevious().getPrevious();
                tail.setNext(dummyTail);
                dummyTail.setPrevious(tail);
                elements--;
            }
        }
    }

    public boolean contains(double value) {
        LLNode ptr = dummyHead.getNext();
        for (int i = 0; i < this.size(); i++) {
            if (ptr.getValue() == value) return true;
            ptr = ptr.getNext();
        }
        return false;
    }

    public double lookup(int i) throws NotValidIndexException {
        if (this.size() < i + 1) {
            throw new NotValidIndexException("This index is not valid");
        }
        LLNode ptr = dummyHead.getNext();
        for (int j = 0; j < i; j++) {
            ptr = ptr.getNext();
        }
        return ptr.getValue();
    }

    public boolean equals(NumList otherList) {
        if (this.size() == 0 && otherList.size() == 0) {
            return true;
        }
        if (this.size() != otherList.size()) {
            return false;
        }
        LLNode ptr = dummyHead.getNext();
        for (int i = 0; i < this.size(); i++) {
            try {
                if (ptr.getValue() != otherList.lookup(i)) return false;
                ptr = ptr.getNext();
            } catch (NotValidIndexException e) {
                System.out.println("This index is not valid");
            }
        }
        return true;
    }

    public void removeDuplicates() {
        if(this.size() > 1){
            LLNode ptr = dummyHead.next;
            while(ptr.next != null){
                LLNode qtr = ptr.next;
                while(qtr != null){
                    if(Objects.equals(ptr.value, qtr.value)) {
                        qtr.previous.next = qtr.next;
                        qtr.next.previous = qtr.previous;
                        elements--;
                    }
                    qtr = qtr.next;
                }
                ptr = ptr.next;
            }
        }
    }

    public String toString() {
        if (this.size() == 0) return "";
        LLNode ptr = dummyHead.getNext();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            if (ptr != null) str.append(ptr.getValue());
            str.append(" ");
            if (ptr != null) ptr = ptr.getNext();
        }
        return str.toString();
    }

    public boolean isSorted() {
        if (this.size() != 0) {
            LLNode ptr = dummyHead.getNext();
            LLNode ptrNext = dummyHead.getNext().getNext();
            for (int i = 0; i < this.size() - 1; i++) {
                if (ptr.getValue() > ptrNext.getValue()) {
                    return false;
                }
                ptr = ptr.getNext();
                ptrNext = ptrNext.getNext();
            }
        }
        return true;
    }

    public void reverse() {
        if(this.size() > 1) {
            LLNode prev = null;
            LLNode temp = null;
            LLNode curr = dummyHead.next;
            LLNode tail = dummyHead.next;
            LLNode head = dummyTail.previous;
            while (curr != null) {
                temp = curr.next;
                curr.next = prev;
                curr.previous = temp;
                prev = curr;
                curr = temp;
            }
            dummyHead.next = head;
            head.previous = dummyHead;
            dummyTail.previous = tail;
            if (tail != null) tail.next = dummyTail;
        }
    }
}
