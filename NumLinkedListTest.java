import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This Test file focus on JUnit tests. Most tests follows rules "test zero, one, normal" while
 * rules "test head, body, tail" are also used when needed.
 * @author dxl746
 */
public class NumLinkedListTest {
    // a relative small number for assertequals(double, double, DELTA) method in JUnit test.
    private static final double DELTA = 1E-15;

    @Test
    public void size() {
        NumLinkedList testArray = new NumLinkedList();
        assertEquals(0, testArray.size());                  // zero test. Element = 0
        testArray.add(1.0);
        assertEquals(1, testArray.size());                  // one test. Element = 1
        testArray.add(3.0);
        assertEquals(2, testArray.size());                  // normal test. Element = 2
    }

    @Test
    public void add() {
        NumLinkedList testArray = new NumLinkedList(3);
        testArray.add(3.0);
        assertEquals("3.0 ", testArray.toString());         // zero test. Append 3.0 after null NumList.
        testArray.add(1.0);
        assertEquals("3.0 1.0 ", testArray.toString());     // one test. Append 1.0 after 3.0
        testArray.add(8.0);
        assertEquals("3.0 1.0 8.0 ", testArray.toString()); // normal test. Append 8.0 after 3.0 -> 1.0
    }

    @Test
    public void insert() {
        NumLinkedList testArray0 = new NumLinkedList();
        NumLinkedList testArray1 = new NumLinkedList(3);
        testArray0.insert(1, 1.0);
        assertEquals("1.0 ", testArray0.toString());            // zero test. Append 1.0 after null NumList.(exceed index)
        testArray0.insert(3, 3.0);
        assertEquals("1.0 3.0 ", testArray0.toString());        // one test. Append 3.0 after 1.0 (exceed index)
        testArray0.insert(5, 8.0);
        assertEquals("1.0 3.0 8.0 ", testArray0.toString());    // normal test. Append 8.0 after 1.0 -> 3.0 (exceed index)
        testArray1.insert(0, 3.0);
        testArray1.insert(0, 5.0);
        assertEquals("5.0 3.0 ", testArray1.toString());        // one test. 3.0 become 5.0 -> 3.0.
        testArray1.insert(1, 8.0);
        assertEquals("5.0 8.0 3.0 ", testArray1.toString());    // normal test. 5.0 -> 3.0 become 5.0 -> 8.0 -> 3.0
        testArray1.insert(0, 1.0);
        assertEquals("1.0 5.0 8.0 3.0 ", testArray1.toString());// normal test. 5.0 -> 8.0 -> 3.0 become 1.0 -> 5.0 -> 8.0 -> 3.0
    }

    @Test
    public void remove() {
        NumLinkedList testArray0 = new NumLinkedList(3);
        testArray0.remove(5);
        assertEquals("", testArray0.toString());                // zero test. Nothing happen for exceed index.
        NumLinkedList test1 = new NumLinkedList();
        // one test
        test1.add(1);
        test1.remove(0);
        assertEquals("", test1.toString());                     // one test. Remove one element only.
        testArray0.add(1.0);
        testArray0.add(3.0);
        testArray0.add(5.0);
        testArray0.remove(4);
        assertEquals("1.0 3.0 5.0 ", testArray0.toString());    // normal test. Nothing happen for exceed index.
        testArray0.add(7.0);
        testArray0.add(9.0);
        testArray0.add(11.0);
        testArray0.remove(0);
        assertEquals("3.0 5.0 7.0 9.0 11.0 ", testArray0.toString()); // normal test. Remove head node.
        testArray0.remove(3);
        assertEquals("3.0 5.0 7.0 11.0 ", testArray0.toString());// normal test. Remove body node.
        testArray0.remove(3);
        assertEquals("3.0 5.0 7.0 ", testArray0.toString());     // normal test. Remove tail node.
    }

    @Test
    public void contains() {
        NumLinkedList testArray = new NumLinkedList(3);
        assertFalse(testArray.contains(8.0));           // zero test. Null NumList couldn't contain 8.0
        testArray.add(1.0);
        assertTrue(testArray.contains(1.0));            // one test. 1.0 does contain 1.0
        assertFalse(testArray.contains(8.0));           // one test. 1.0 doesn't contain 8.0
        testArray.add(3.0);
        testArray.add(5.0);
        assertTrue(testArray.contains(1.0));            // normal test on head. 1.0 -> 3.0 -> 5.0 does contain 1.0
        assertTrue(testArray.contains(3.0));            // normal test on body. 1.0 -> 3.0 -> 5.0 does contain 3.0
        assertTrue(testArray.contains(5.0));            // normal test on tail. 1.0 -> 3.0 -> 5.0 does contain 5.0
        assertFalse(testArray.contains(8.0));           // normal test. 1.0 -> 3.0 -> 5.0 doesn't contain 8.0
    }

    @Test(expected = NotValidIndexException.class)
    public void lookup() throws NotValidIndexException {
        NumLinkedList testArray = new NumLinkedList(3);
        testArray.lookup(0);                                        // zero test. Throw NotValidIndexException.
        testArray.add(3.0);
        assertEquals(3.0, testArray.lookup(0), DELTA);    // one test. 3.0 does contain 3.0
        testArray.add(0.0);
        testArray.add(8.0);
        assertEquals(3.0, testArray.lookup(0), DELTA);    // normal test on head. lookup(0) on 3.0 -> 0.0 -> 8.0 will return 3.0
        assertEquals(0.0, testArray.lookup(1), DELTA);    // normal test on body. lookup(1) on 3.0 -> 0.0 -> 8.0 will return 0.0
        assertEquals(8.0, testArray.lookup(2), DELTA);    // normal test on tail. lookup(2) on 3.0 -> 0.0 -> 8.0 will return 8.0
        testArray.lookup(3);                                        // normal test for NotValidIndexException.
    }

    @Test
    public void testEquals() {
        NumLinkedList testArray = new NumLinkedList();
        NumLinkedList otherList0 = new NumLinkedList();
        NumLinkedList otherList1 = new NumLinkedList(1);
        assertTrue(testArray.equals(otherList0));               // condition test and zero test. two lists with same size are equal
        otherList0.add(3.0);
        assertFalse(testArray.equals(otherList0));              // condition test. two lists with different size are equal
        testArray.add(3.0);
        otherList1.add(3.0);
        otherList0.add(999.0);
        assertTrue(testArray.equals(otherList1));               // one test. 3.0 are equal to 3.0
        assertFalse(testArray.equals(otherList0));              // one test. 3.0 are not equal to 999.0
        testArray.add(5.0);
        testArray.add(8.0);
        otherList1.add(5.0);
        otherList1.add(8.0);
        otherList0.add(999.0);
        otherList0.add(999.0);
        assertTrue(testArray.equals(otherList1));               // normal test. 3.0 -> 5.0 -> 8.0 are equal to 3.0 -> 5.0 -> 8.0
        assertFalse(testArray.equals(otherList0));              // normal test. 3.0 -> 5.0 -> 8.0 are not equal to 999.0 -> 999.0 -> 999.0
    }

    @Test
    public void removeDuplicates() {
        NumLinkedList testArray = new NumLinkedList(6);
        testArray.removeDuplicates();
        assertEquals("", testArray.toString());                     // zero test. null List have no duplicates
        testArray.add(1.0);
        testArray.removeDuplicates();
        assertEquals("1.0 ", testArray.toString());                 // one test. 1.0 have no duplicates.
        testArray.add(3.0);
        testArray.add(5.0);
        testArray.removeDuplicates();
        assertEquals("1.0 3.0 5.0 ", testArray.toString());         // normal test. 1.0 -> 3.0 -> 5.0 have no duplicates
        testArray.add(3.0);
        testArray.add(7.0);
        testArray.add(5.0);
        testArray.add(9.0);
        // normal test for body
        testArray.removeDuplicates();
        assertEquals("1.0 3.0 5.0 7.0 9.0 ", testArray.toString()); // normal test. 1.0 -> 3.0 - 5.0 -> 3.0 -> 7.0 -> 5.0 -> 9.0
                                                                              // would become 1.0 -> 3.0 -> 5.0 -> 7.0 ->9.0
    }

    @Test
    public void testToString() {
        NumLinkedList testArray = new NumLinkedList(3);
        assertEquals("", testArray.toString());                     // zero test. null NumList would return ""
        testArray.add(1.0);
        assertEquals("1.0 ", testArray.toString());                 // one test. 1.0 would return "1.0 "
        testArray.add(3.0);
        testArray.add(5.0);
        assertEquals("1.0 3.0 5.0 ", testArray.toString());         // normal test. 1.0 -> 3.0 -> 5.0 would return "1.0 3.0 5.0 "
    }

    @Test
    public void testIsSorted(){
        NumLinkedList test = new NumLinkedList();
        assertTrue(test.isSorted());            // zero test. null NumList is sorted.
        test.add(1.0);
        assertTrue(test.isSorted());            // one test. NumList contains one element is sorted.
        test.add(3.0);
        assertTrue(test.isSorted());            // normal test. 1.0 -> 3.0 is sorted.
        test.add(2.0);
        assertFalse(test.isSorted());           // normal test. 1.0 -> 3.0 -> 2.0 is sorted.
    }

    @Test
    public void testReverse(){
        NumLinkedList test = new NumLinkedList();
        test.reverse();
        assertEquals("", test.toString());                                      // zero test. Nothing happen to a null List.
        test.add(3.0);
        NumLinkedList test2 = new NumLinkedList();
        test.reverse();
        assertEquals("3.0 ", test.toString());                                  // one test. 3.0 would still become 3.0 after reverse
        test2.add(4.0);
        test2.add(5.0);
        test2.add(6.0);
        test2.reverse();
        assertEquals("6.0 5.0 4.0 ", test2.toString());                         // normal test. 4.0 -> 5.0 -> 6.0 would become 6.0 -> 5.0 -> 4.0 (odd elements)
        NumLinkedList test3 = new NumLinkedList();
        test3.add(3.0);
        test3.add(4.0);
        test3.add(5.0);
        test3.add(6.0);
        test3.reverse();
        assertEquals("6.0 5.0 4.0 3.0 ", test3.toString());                     // normal test. 3.0 -> 4.0 -> 5.0 -> 6.0 would become 6.0 -> 5.0 -> 4.0 -> 3.0(even elements)
        test3.add(7.0);
        test3.reverse();
        assertEquals("7.0 3.0 4.0 5.0 6.0 ", test3.toString());                 // normal test for verifying correct setting of head/tail node.
    }

    @Test
    public void testUnion(){
        NumLinkedList test1 = new NumLinkedList();
        NumLinkedList test2 = new NumLinkedList();
        NumLinkedList test3 = NumList.union(test1, test2);
        NumArrayList test4 = new NumArrayList();
        assertEquals("", test3.toString());                                     // zero test. two null NumList would union a null NumList.
        test1.add(3.0);
        test2.add(2.0);
        test3 = NumList.union(test1, test2);
        assertEquals("2.0 3.0 ", test3.toString());                             // one test. 3.0 and 2.0 would union 2.0 -> 3.0.
        test1.add(5.0);
        test1.add(7.0);
        test1.add(8.0);
        test2.add(4.0);
        test2.add(5.0);
        test3 = NumList.union(test1, test2);
        assertEquals("2.0 3.0 4.0 5.0 7.0 8.0 ", test3.toString());             // normal test for sorted. 3.0 -> 5.0 -> 7.0 -> 8.0 and 2.0 -> 4.0 -> 5.0
                                                                                          // would union 2.0 -> 3.0 -> 4.0 -> 5.0 -> 7.0 -> 8.0
        test1.add(4.0);
        test3 = NumList.union(test1, test2);
        assertEquals("3.0 2.0 5.0 4.0 7.0 8.0 ", test3.toString());             // normal test for unsorted. 3.0 -> 5.0 -> 7.0 -> 8.0 -> 4.0 and 2.0 -> 4.0
                                                                                          // -> 5.0 would union 3.0 -> 2.0 -> 5.0 -> 4.0 -> 7.0 -> 8.0
        test4.add(1.0);
        test4.add(3.0);
        test4.add(5.0);
        // test3: 3.0 -> 1.0 -> 2.0 -> 5.0 -> 4.0 -> 7.0 -> 8.0
        // test4: [1.0 3.0 5.0]
        NumList test5 = NumList.union(test3, test4);
        assertEquals("3.0 1.0 2.0 5.0 4.0 7.0 8.0 ", test5.toString());        // normal test for unsorted arrayList and LinkedList
        // test2: 2.0 -> 4.0 -> 5.0
        // test4: [1.0 3.0 5.0]
        NumList test6 = NumList.union(test2, test4);
        assertEquals("1.0 2.0 3.0 4.0 5.0 ", test6.toString());                // normal test for sorted LinkedList and arrayList.
    }
}