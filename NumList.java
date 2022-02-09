/**
 * Interface <code>NumList</code> contains all abstract methods that must be inherited in
 * <code>NumArrayList</code> and <code>NumLinkedList</code>
 */
public interface NumList {
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
        boolean isSorted();                                     // return if the NumList is sorted in increasing order.
        void reverse();                                         // reverse the elements of the lists.
        // union two sorted lists and return the head of union list. The union list would satisfy isSorted()
        static <T extends NumList> T union(T List1, T List2){return null;}
}
