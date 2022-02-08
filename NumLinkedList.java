import java.util.Objects;

interface NumList {
    int size();                                             // return the number of elements of NumList

    int capacity();                                         // return the capacity of NumList

    void add(double value);                                 // add a value to the end of NumList.

    void insert(int i, double value);                       // inserts a element before i-th element. using 0 as start.

    void remove(int i);                                     // remove the i-th element. do nothing if i > size

    double lookup(int i) throws NotValidIndexException;     // look for the i-th element. throw an exception if not found.

    boolean contains(double value);                         // return true if the array contains input value.

    boolean equals(NumList otherList);                      // return true if the otherList equals the other list.

    void removeDuplicates();                                // remove duplicates of any elements in the list.

    String toString();                                      // convert the list to a String.

    boolean isSorted();                                     // return if the NumList is sorted in increasing order.

    void reverse();                                         // reverse the elements of the lists.

    static NumList union(NumList List1, NumList List2){return new NumLinkedList();} // union two sorted lists and return the head of union list. The union list would satisfy isSorted()
}

public class NumLinkedList implements NumList {
    // attributes of linked list
    private final int capacity = Integer.MAX_VALUE;
    private int elements = 0;
    // dummy head that used to avoid empty linked list.
    private LLNode dummyHead = new LLNode(null);
    private LLNode dummyTail = new LLNode(null);

    public NumLinkedList() {
        dummyHead.setNext(dummyTail);
        dummyTail.setPrevious(dummyHead);
    }

    public NumLinkedList(int capacity) {
        dummyHead.setNext(dummyTail);
        dummyTail.setPrevious(dummyHead);
    }

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

    public int size() {
        return this.elements;
    }

    public int capacity() {
        return this.capacity;
    }

    public void add(double value) {
        if (this.size() == 0) {
            this.addIfNull(value);
        } else {
            LLNode newNode = new LLNode(value);
            dummyTail.getPrevious().setNext(newNode);
            dummyTail.setPrevious(newNode);
            elements++;
        }
    }

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
        LLNode prev = null;
        LLNode temp = null;
        LLNode curr = dummyHead.next;
        if (curr != null){
            temp = curr.next;
            curr.next = prev;
            curr.previous = temp;
            prev = curr;
            curr = temp;
        }
        dummyHead.next = prev;
    }

    public static NumLinkedList union(NumList list1, NumList list2) {
        NumLinkedList list = new NumLinkedList();
        int i = 0, j = 0;
        try {
            if (list1.isSorted() && list2.isSorted()) {
                if (list1.size() != 0 && list2.size() != 0) {
                    while (i < list1.size() && j < list2.size()) {
                        if (list1.lookup(i) < list2.lookup(j)) {
                            list.add(list1.lookup(i));
                            i++;
                        } else {
                            list.add(list2.lookup(j));
                            j++;
                        }
                    }
                    if (i < list1.size()) {
                        while (i != list1.size()) {
                            list.add(list1.lookup(i));
                            i++;
                        }
                    } else if (j < list2.size()) {
                        while (j != list2.size()) {
                            list.add(list2.lookup(j));
                            j++;
                        }
                    }
                }
            } else {
                if (list1.size() != 0 && list2.size() != 0) {
                    while (i < list1.size() && j < list2.size()) {
                        list.add(list1.lookup(i));
                        list.add(list2.lookup(j));
                        i++;
                        j++;
                    }
                    if (i < list1.size()) {
                        while (i != list1.size()) {
                            list.add(list1.lookup(i));
                            i++;
                        }
                    } else if (j < list2.size()) {
                        while (j != list2.size()) {
                            list.add(list2.lookup(j));
                            j++;
                        }
                    }
                }
            }
        } catch (NotValidIndexException e) {
            System.out.println("This index is not valid");
        }
        return list;
    }

}
