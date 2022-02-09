/**
 * This Test file focus on JUnit tests. Most tests follows rules "test zero, one, normal" while
 * rules "test head, body, tail" are also used when needed. 
 * @author dxl746
 */
import org.junit.Test;

import static org.junit.Assert.*;

public class NumArrayListTest {
    // a relative small number for assertequals(double, double, DELTA) method in JUnit test.
    private static final double DELTA = 1E-15;

    @Test
    public void size() {
        NumArrayList testArray = new NumArrayList();
        // zero test.
        assertEquals(0, testArray.size());
        // normal test.
        testArray.add(1.0);
        assertEquals(1, testArray.size());
        testArray.add(3.0);
        assertEquals(2, testArray.size());
    }

    @Test
    public void capacity() {
        NumArrayList testArray0 = new NumArrayList();
        NumArrayList testArray1 = new NumArrayList(1);
        NumArrayList testArray2 = new NumArrayList(3);
        // zero test
        assertEquals(0, testArray0.capacity());
        // normal test
        assertEquals(1, testArray1.capacity());
        assertEquals(3, testArray2.capacity());
    }

    @Test
    public void add() {
        NumArrayList testArray = new NumArrayList(3);
        testArray.add(3.0);
        testArray.add(0.0);
        testArray.add(8.0);
        // normal test
        assertEquals("3.0 0.0 8.0 ", testArray.toString());
    }

    @Test
    public void insert() {
        NumArrayList testArray0 = new NumArrayList();
        NumArrayList testArray1 = new NumArrayList(3);
        // zero test for empty NumArrayList.
        testArray0.insert(1, 1.0);
        assertEquals("1.0 ", testArray0.toString());
        // normal test for exceed index
        testArray0.insert(3, 3.0);
        assertEquals("1.0 3.0 ", testArray0.toString());
        testArray0.insert(5, 8.0);
        assertEquals("1.0 3.0 8.0 ", testArray0.toString());
        // normal test
        testArray1.insert(0, 3.0);
        testArray1.insert(2, 5.0);
        assertEquals("3.0 5.0 ", testArray1.toString());
        testArray1.insert(1, 8.0);
        assertEquals("3.0 8.0 5.0 ", testArray1.toString());
    }

    @Test
    public void remove() {
        NumArrayList testArray0 = new NumArrayList(3);
        // zero test
        testArray0.remove(5);
        assertEquals("", testArray0.toString());
        testArray0.add(1.0);
        testArray0.add(3.0);
        testArray0.add(5.0);
        // normal test for exceed index
        assertEquals("1.0 3.0 5.0 ", testArray0.toString());
        testArray0.remove(4);
        assertEquals("1.0 3.0 5.0 ", testArray0.toString());
        testArray0.add(7.0);
        testArray0.add(9.0);
        testArray0.add(11.0);
        // normal test for head
        testArray0.remove(0);
        assertEquals("3.0 5.0 7.0 9.0 11.0 ", testArray0.toString());
        // normal test for body
        testArray0.remove(3);
        assertEquals("3.0 5.0 7.0 11.0 ", testArray0.toString());
        //normal test for tail
        testArray0.remove(3);
        assertEquals("3.0 5.0 7.0 ", testArray0.toString());
    }

    @Test
    public void contains() {
        NumArrayList testArray = new NumArrayList(3);
        // zero test
        assertFalse(testArray.contains(8.0));
        testArray.add(1.0);
        testArray.add(3.0);
        testArray.add(5.0);
        // normal test on head, body, and end
        assertTrue(testArray.contains(1.0));
        assertTrue(testArray.contains(3.0));
        assertTrue(testArray.contains(5.0));
        // normal test when not found
        assertFalse(testArray.contains(8.0));
    }

    @Test(expected = NotValidIndexException.class)
    public void lookup() throws NotValidIndexException {
        NumArrayList testArray = new NumArrayList(3);
        // zero test for NotValidIndexException
        testArray.lookup(0);
        testArray.add(3.0);
        testArray.add(0.0);
        testArray.add(8.0);
        // normal test
        assertEquals(3.0, testArray.lookup(0), DELTA);
        assertEquals(0.0, testArray.lookup(1), DELTA);
        assertEquals(8.0, testArray.lookup(2), DELTA);
        // normal test for NotValidIndexException
        testArray.lookup(3);
    }

    @Test
    public void testEquals() {
        NumArrayList testArray = new NumArrayList();
        NumArrayList otherList0 = new NumArrayList();
        NumArrayList otherList1 = new NumArrayList(1);
        // zero test
        assertTrue(testArray.equals(otherList0));
        otherList0.add(3.0);
        // condition test: size not match
        assertFalse(testArray.equals(otherList0));
        testArray.add(3.0);
        otherList1.add(3.0);
        otherList0.add(999.0);
        // normal test for one element
        assertTrue(testArray.equals(otherList1));
        assertFalse(testArray.equals(otherList0));
        testArray.add(5.0);
        testArray.add(8.0);
        otherList1.add(5.0);
        otherList1.add(8.0);
        otherList0.add(999.0);
        otherList0.add(999.0);
        // normal test for many element
        assertTrue(testArray.equals(otherList1));
        assertFalse(testArray.equals(otherList0));
    }

    @Test
    public void removeDuplicates() throws NotValidIndexException {
        NumArrayList testArray = new NumArrayList(6);
        testArray.add(1.0);
        testArray.add(3.0);
        testArray.add(5.0);
        testArray.removeDuplicates();
        // zero test
        assertEquals("1.0 3.0 5.0 ", testArray.toString());
        testArray.add(3.0);
        testArray.add(7.0);
        testArray.add(5.0);
        testArray.add(9.0);
        // normal test for body
        testArray.removeDuplicates();
        assertEquals("1.0 3.0 5.0 7.0 9.0 ", testArray.toString());
    }

    @Test
    public void testToString() {
        NumArrayList testArray = new NumArrayList(3);
        // zero test
        assertEquals("", testArray.toString());
        testArray.add(1.0);
        testArray.add(3.0);
        testArray.add(5.0);
        // normal test
        assertEquals("1.0 3.0 5.0 ", testArray.toString());
    }
}