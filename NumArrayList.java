/**
 * This body contains all methods and interfaces that are used in JUnit tests and other body methods.
 * Type <code>NumArrayList</code> provides methods that similar to Type <code>ArrayList</code>, including
 * <code>insert()</code>, <code>remove()</code>. A <code>NumArrayList</code> would contains two attributes: 
 * <code>capacity</code>, which is total available space; <code>elements</code>, which is the total number of 
 * elements;
 * <br></br>
 * <br></br>
 * Here are some example about how to use <code>NumArrayList</code>:
 * <br></br>
 * <br></br>
 * Considering a <code>testArray</code> in type of <code>NumArrayList</code>. That <code>testArray</code> 
 * is consist of {1.0, 3.0, 5.0, 7.0, 3.0, 9.0};
 * <dl>
 * <dt>•<code>testArray.add(3.0);</code> would result {1.0, 3.0, 5.0, 7.0, 3.0, 9.0, 3.0}</dt>
 * <dt>•<code>testArray.insert(2, 3.0);</code> would result {1.0, 3.0, 3.0, 5.0, 7.0, 3.0, 9.0}.</dt>
 * <dt>•<code>testArray.remove(3);</code> would result {1.0, 3.0, 5.0, 3.0, 9.0}.</dt>
 * <dt>•<code>testArray.lookup(3);</code> would return 7.0.</dt>
 * <dt>•<code>testArray.contains(5.0);</code> would return <code>true</code>.</dt>
 * <dt>•<code>testArray.equals({1.0, 3.0, 5.0})</code> would return <code>false</code>.</dt>
 * <dt>•<code>testArray.removeDuplicate()</code> would result {1.0, 3.0, 5.0, 7.0, 9.0}.</dt>
 * <dt>•<code>testArray.toString()</code> would return "1.0 3.0 5.0 7.0 3.0 9.0 ";</dt>
 * </dl>
 */
public class NumArrayList implements NumList{
    // variable that store the capacity of array. 
    private int capacity;
    // Using null as an initial value; Avoid ambiguous if/else statement when considering 0.0
    private Double[] array;
    // variable that store the number of current elements
    private int elements = 0;

    /**
     * For those NumArrayList variable without input, one would construct initial NumArrayList 
     * with zero capacity. 
     */
    public NumArrayList(){
        this.array = new Double[0];
        this.capacity = 0;
    }

    /**
     * For those NumArrayList with specific input, one would construct initial NumArrayList 
     * with parameter
     * @param capacity capacity of array
     */
    public NumArrayList(int capacity){
        this.array = new Double[capacity];
        this.capacity = capacity;
    }

    /**
     * increase the capacity of array by 1 unit. Trigger when current capacity is not enough
     * for additional element.
     */
    private void ExpandIfExceed() {
        Double[] newArray = new Double[capacity + 1];
        if (capacity != 0) {
            System.arraycopy(array, 0, newArray, 0, capacity);
        }
        this.array = newArray;
        capacity++;
    }
    
    /** 
     * @return the number of elements
     */
    public int size(){
        return this.elements;
    }

    
    /** 
     * @return the value of capacity
     */
    public int capacity(){
        return this.capacity;
    }

    
    /** 
     * Accept a double value and place that input at the end of the array. Able to 
     * expand the array if needed. Element would increment after method.
     * @param value value that passed to a function
     */
    public void add(double value){
        if(this.size() < this.capacity()){
            array[this.size()] = value;
        }
        else{
            this.ExpandIfExceed();
            array[this.capacity() - 1] = value;
        }
        elements++;
    }

    
    /** 
     * place the <code>value</code> at <code>i</code>-th index of the array and defer  
     * the subsequent values. If the array has elements that fewer than <code>i</code>,
     * adding that value to the end of array. Able to Expand the array if needed. 
     * @param i index in array
     * @param value value passed to function
     */
    public void insert(int i, double value){
        if(this.size() < this.capacity()){
            if(this.size() >= i){
                if (this.size() - i >= 0) System.arraycopy(array, i, array, i + 1, this.size() - i);
                array[i] = value;
                elements++;
            }
            else {
                this.add(value);
            }
        }
        else{
            this.ExpandIfExceed();
            if (this.size() - i >= 0) System.arraycopy(array, i, array, i + 1, this.size() - i);
            array[this.capacity() - 1] = value;
            elements++;
        }   
    }

    
    /** 
     * Remove the <code>i</code>-th element and advance all elements behind. 
     * Do nothing if the <code>i</code>-th element does not exist.
     * @param i index of array
     */
    public void remove(int i){
        if(i >= 0){
            if(this.capacity() > 0 && this.size() > (i - 1)) {
                /*
                  Although the last element exist in the array, the decrement of elements restricts
                  one's access. That element would possibly be overwritten in the future.
                 */
                if (this.size() - 1 - i >= 0) System.arraycopy(array, i + 1, array, i, this.size() - 1 - i);
                elements--;
                capacity--;
            }
        }
    }

    
    /** 
     * Determining whether <code>value</code> exist in array. Return <code>true</code> if exists.
     * @param value value that passed to function
     * @return ValueExist
     */
    public boolean contains(double value){
        for(int i = 0; i < this.size(); i++){
            try {
                if(this.lookup(i) == value){
                    return true;
                }
            } catch (NotValidIndexException e) {
                System.out.println("This index is not valid");
            }
        }
        return false;
    }

    
    /** 
     * Return <code>i</code>-th element in the array. <code>i</code> start from 0. Throw
     * NotValidIndexException if <code>i + 1</code> is less than the number of elements.
     * @param i index
     * @return <code>i</code>-th element
     * @throws NotValidIndexException index passed to function is out of bound
     */
    public double lookup(int i) throws NotValidIndexException{
        if(this.size() < (i + 1)){
            throw new NotValidIndexException("This index is not valid");
        }
        else{
            return array[i];
        }
    }

    
    /** 
     * Determining whether two <code>NumArrayList</code> is equal. Two <code>NumArrayList</code>
     * is equal if every element with same index are equal in both lists, and both of their capacity
     * and elements are equal. 
     * @param otherList NumArrayList passed to function that used to compare
     * @return TwoListEqual
     */
    public boolean equals(NumList otherList){
        if(otherList.size() == 0 && this.size() == 0){
            return true;
        }
        else if(otherList.size() != this.size()){
            return false;
        }
        for(int i = 0; i < this.size() - 1; i++){
            try {
                if(otherList.lookup(i) != this.lookup(i)){
                    return false;
                }
            } catch (NotValidIndexException e) {
                System.out.println("This index is not valid");
            }
        }
        return true;

    }
    /**
     * Remove duplicates of elements in array. An element 'A' is said duplicate if another element exists
     * in array that equal to A, but has lower index.
     */
    public void removeDuplicates() throws NotValidIndexException {
        NumArrayList newArray = new NumArrayList();
        for(int i = 0; i < this.size() - 1; i++){
			for(int j = i + 1; j < this.size(); j++){
				if(this.lookup(i) == this.lookup(j)){
					this.remove(j);
				}
			}
			newArray.add(array[i]);
        }
    }

    
    /** 
     * Print the array to String. Each element would be separated by a single space. Return <code>""</code>
     * if the array is empty. 
     * @return String
     */
    public String toString(){
        if(this.size() == 0){
            return "";
        }
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < this.size(); i++){
            result.append(array[i]);
            result.append(" ");
        }
        return result.toString();
    }

    public boolean isSorted(){
        if(this.size() > 1){
            for(int i = 0; i < this.size() - 1; i++){
                try {
                    if(this.lookup(i) > this.lookup(i + 1)) return false;
                } catch (NotValidIndexException e) {
                    System.out.println("This index is not valid.");
                }

            }
        }
        return true;
    }

    public void reverse(){
        int j = this.size() - 1;
        Double temp;
        for(int i = 0; i < this.size() / 2; i++){
            temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            j--;
        }
    }

}
