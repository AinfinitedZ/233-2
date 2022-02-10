import java.lang.reflect.Constructor;

/**
 * Interface <code>NumList</code> contains all abstract methods that must be inherited in
 * <code>NumArrayList</code> and <code>NumLinkedList</code>
 * @author dxl746
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
    /**
     *  union two sorted lists and return the head of union list. Two passed sorted Lists would produce a sorted return list. 
     *  Two passed unsorted lists would produce a unsorted but combined list. 
     */ 
    public static <T extends NumList> T union(T list1, T list2){
        Class cla = list1.getClass();
        T list = null;
        try {
            list = (T)cla.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
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

