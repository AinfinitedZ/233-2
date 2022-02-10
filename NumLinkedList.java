import java.util.Objects;
/**
 * This body contains all methods and interfaces that are used in JUnit tests and other body methods.
 * Type <code>NumLinkedList</code> provides methods that similar to Type <code>LinkedList</code>, including
 * <code>insert()</code>, <code>remove()</code>.
 * <br></br>
 * <br></br>
 * Here are some example about how to use <code>NumArrayList</code>:
 * <br></br>
 * <br></br>
 * Considering a <code>testList</code> in type of <code>NumLinkedList</code>. That <code>testList</code>
 * is consist of 1.0 -> 3.0 -> 5.0 -> 7.0 -> 3.0 -> 9.0
 * <dl>
 * <dt><code>testList.add(3.0);</code> would result 1.0 -> 3.0 -> 5.0 -> 7.0 -> 3.0 -> 9.0 -> 3.0 </dt>
 * <dt><code>testList.insert(2, 3.0);</code> would result 1.0 -> 3.0 -> 3.0 -> 5.0 -> 7.0 -> 3.0 -> 9.0.</dt>
 * <dt><code>testList.remove(3);</code> would result 1.0 -> 3.0 -> 5.0 -> 3.0 -> 9.0.</dt>
 * <dt><code>testList.lookup(3);</code> would return 7.0.</dt>
 * <dt><code>testList.contains(5.0);</code> would return <code>true</code>.</dt>
 * <dt><code>testList.equals({1.0, 3.0, 5.0})</code> would return <code>false</code>.</dt>
 * <dt><code>testList.removeDuplicate()</code> would result 1.0 -> 3.0 -> 5.0 -> 7.0 -> 9.0.</dt>
 * <dt><code>testList.toString()</code> would return "1.0 3.0 5.0 7.0 3.0 9.0 ";</dt>
 * <dt><code>testList.isSorted()</code> would return false. A NumList that consist of 1.0 -> 3.0 -> 5.0 -> 7.0
 * would return true after invoking isSorted()</dt>
 * <dt><code>testList.reverse()</code> would return 9.0 -> 3.0 - 7.0 -> 5.0 -> 3.0 -> 1.0</dt>
 * </dl>
 * @author dxl746
 */
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
     * Initializing the <code>NumLinkedList</code>. would potentially invoke by add() method
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
     * return the total numbers of elements in NumLinkedList.
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
     * insert an element at specific i-th position (i start from 0). Append it to the end
     * if <code>i</code> is larger than elements of list.
     * @param i position of appended element (start from 0)
     * @param value element to be inserted or appended. 
     */
    public void insert(int i, double value) {
        LLNode ptr = dummyHead;
        if (i < this.size()) {
            for (int j = 0; j < i; j++) {
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
     * Remove an element at specific i-th position(i start from 0). Do nothing if <code>i</code> is
     * larger than elements of list
     * @param i position of removed element
     */
    public void remove(int i) {
        if (i < elements && this.size() != 0) {
            LLNode ptr = dummyHead;
            for (int j = 0; j < i; j++) {
                ptr = ptr.getNext();
            }
            LLNode ptrNext = ptr.getNext().getNext();
            ptr.setNext(ptrNext);
            ptrNext.setPrevious(ptr);
            elements--;
        }
    }

    /**
     * Determining whether <code>value</code> exist in array. Return <code>true</code> if exists.
     * @param value value that tend to be searched
     * @return isValueExist
     */
    public boolean contains(double value) {
        LLNode ptr = dummyHead.getNext();
        for (int i = 0; i < this.size(); i++) {
            if (ptr.getValue() == value) return true;
            ptr = ptr.getNext();
        }
        return false;
    }

    /**
     * Look for the value of specific i-th element.
     * @param i position of targeted element
     * @return value of specific i-th element
     * @throws NotValidIndexException Throw if i-th element couldn't be found
     */
    public double lookup(int i) throws NotValidIndexException {
        if (this.size() < i + 1 || i < 0) {
            throw new NotValidIndexException("This index is not valid");
        }
        LLNode ptr = dummyHead.getNext();
        for (int j = 0; j < i; j++) {
            ptr = ptr.getNext();
        }
        return ptr.getValue();
    }

    /**
     * Indicating whether <code>otherList</code> is equal to this one. Two NumList is said equal when each element
     * of them is equal and have same size.
     * @param otherList the reference NumList with which to compare
     * @return <code>true</code> if this NumList is the same as <code>otherList</code>; <code>false</code> otherwise.
     */
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
               e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * Reverse all first occurred element and remove other recurring elements.
     */
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

    /**
     * Return a string representation of NumLinkedList. Every element would be printed in order.
     * @return a string representation of NumLinkedList
     */
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

    /**
     * Indicating whether this NumLinkedList is sorted by increasing order.
     * @return <code>true</code> if this NumLinkedList is sorted by increasing order. <code>false</code> otherwise.
     */
    public boolean isSorted() {
        if (this.size() > 1) {
            LLNode ptr = dummyHead.next;
            LLNode ptrNext = dummyHead.next.next;
            for(int i = 0; i < this.size() - 1; i++){
                if (ptr.value > ptrNext.value) {
                    return false;
                }
                ptr = ptr.next;
                ptrNext = ptrNext.next;
            }
        }
        return true;
    }

    /**
     * Reverse the order of elements in NumList by swapping their previous element and next element.
     */
    public void reverse() {
        if(this.size() > 1) {
            LLNode prev = null;
            LLNode temp = null;
            LLNode curr = dummyHead.next;
            LLNode tail = dummyHead.next;
            LLNode head = dummyTail.previous;
            while (curr.next != null) {
                temp = curr.next;
                curr.next = prev;
                curr.previous = temp;
                prev = curr;
                curr = temp;
            }
            dummyHead.next = head;
            head.previous = dummyHead;
            dummyTail.previous = tail;
            tail.next = dummyTail;
        }
    }
}
