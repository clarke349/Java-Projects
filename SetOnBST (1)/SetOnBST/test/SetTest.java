import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Malik Clarke, Drew Jackson, and Ethan Hunter
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /*
     * Constructor Test Cases
     */

    /**
     * Tests no args constructor for Set3a
     */
    @Test
    public final void noArgsConstructorTest1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> actual = this.constructorTest();
        Set<String> expected = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /*
     * Kernel Method Tests
     */

    /**
     * Tests add with adding a string to a set of size 2
     */
    @Test
    public final void addTest1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> expected = this.createFromArgsRef("abc", "def", "ghi");
        Set<String> result = this.createFromArgsTest("abc", "def");

        result.add("ghi");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Tests add with adding a string to an empty set
     */
    @Test
    public final void addTest2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> expected = this.createFromArgsRef("abc");
        Set<String> result = this.createFromArgsTest();

        result.add("abc");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Tests add with adding a string to a set with 3 elements
     */
    @Test
    public final void addTest3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> expected = this.createFromArgsRef("a", "b", "c", "d");
        Set<String> result = this.createFromArgsTest("a", "b", "c");

        result.add("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Tests add with adding an empty String
     */
    @Test
    public final void addTest4() {
        /*
         * Set up variables and call method under test
         */
        Set<String> expected = this.createFromArgsRef("a", "b", "c", "");
        Set<String> result = this.createFromArgsTest("a", "b", "c");

        result.add("");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Tests add with adding an a String with empty space
     */
    @Test
    public final void addTest5() {
        /*
         * Set up variables and call method under test
         */
        Set<String> expected = this.createFromArgsRef("a", "b", "c", " ");
        Set<String> result = this.createFromArgsTest("a", "b", "c");

        result.add(" ");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Tests add with adding an a String to the left branch
     */
    @Test
    public final void addTest6() {
        /*
         * Set up variables and call method under test
         */
        Set<String> expected = this.createFromArgsRef("b", "a", "c");
        Set<String> result = this.createFromArgsTest("b", "c");

        result.add("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Tests add with adding an a String to the right branch
     */
    @Test
    public final void addTest7() {
        /*
         * Set up variables and call method under test
         */
        Set<String> expected = this.createFromArgsRef("b", "a", "c");
        Set<String> result = this.createFromArgsTest("b", "a");

        result.add("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Tests remove by removing a String with empty space
     */
    @Test
    public final void removeTest1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("a", "b", "c", " ");
        Set<String> expected = this.createFromArgsTest("a", "b", "c");
        String s = " ";
        int expectedSize = 3;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests remove by removing an empty String
     */
    @Test
    public final void removeTest2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("a", "b", "c", "");
        Set<String> expected = this.createFromArgsTest("a", "b", "c");
        String s = "";
        int expectedSize = 3;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests remove with removing a String from the middle of the set
     */
    @Test
    public final void removeTest3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("a", "b", "c", "d", "e");
        Set<String> expected = this.createFromArgsTest("a", "b", "d", "e");
        String s = "c";
        int expectedSize = 4;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests remove with removing a String from the beginning of the set
     */
    @Test
    public final void removeTest4() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("a", "b", "c", "d", "e");
        Set<String> expected = this.createFromArgsTest("b", "c", "d", "e");
        String s = "a";
        int expectedSize = 4;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests remove with removing a String from the end of the set
     */
    @Test
    public final void removeTest5() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("a", "b", "c", "d", "e");
        Set<String> expected = this.createFromArgsTest("a", "b", "c", "d");
        String s = "e";
        int expectedSize = 4;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests remove with removing a String from a set with only one element
     */
    @Test
    public final void removeTest6() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("abc");
        Set<String> expected = this.createFromArgsTest();
        String s = "abc";
        int expectedSize = 0;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests to remove root
     */
    @Test
    public final void removeTest7() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("b", "a", "c");
        Set<String> expected = this.createFromArgsTest("a", "c");

        String s = "b";
        int expectedSize = 2;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests to remove element left of root
     */
    @Test
    public final void removeTest8() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("b", "a", "c");
        Set<String> expected = this.createFromArgsTest("b", "c");

        String s = "a";
        int expectedSize = 2;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests to remove element right of root
     */
    @Test
    public final void removeTest9() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("b", "a", "c");
        Set<String> expected = this.createFromArgsTest("b", "a");

        String s = "c";
        int expectedSize = 2;

        String removed = result.remove(s);
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(s, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests removeAny with a set of five elements and when the smallest element
     * is in the front.
     */
    @Test
    public final void removeAnyTest1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("a", "b", "c", "d", "e");
        String expected = "a";
        int expectedSize = 4;

        String removed = result.removeAny();
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests removeAny with a set of 2 elements and when the smallest element is
     * in the front.
     */
    @Test
    public final void removeAnyTest2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("a", "b");
        String expected = "a";
        int expectedSize = 1;

        String removed = result.removeAny();
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests removeAny with a set of 1 element
     */
    @Test
    public final void removeAnyTest3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("b");
        String expected = "b";
        int expectedSize = 0;

        String removed = result.removeAny();
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests removeAny with a set of five elements and when the smallest element
     * is in the middle.
     */
    @Test
    public final void removeAnyTest4() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("c", "b", "a", "d", "e");
        String expected = "a";
        int expectedSize = 4;

        String removed = result.removeAny();
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests removeAny with a set of five elements and when the smallest element
     * is at the end.
     */
    @Test
    public final void removeAnyTest5() {
        /*
         * Set up variables and call method under test
         */
        Set<String> result = this.createFromArgsRef("e", "b", "c", "d", "a");
        String expected = "a";
        int expectedSize = 4;

        String removed = result.removeAny();
        int actualSize = result.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, removed);
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Tests contains with by searching for an element at the beginning of the
     * set.
     */
    @Test
    public final void containsTest1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsRef("a", "b", "c", "d", "e");
        boolean expected = true;
        String s = "a";

        boolean result = set.contains(s);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests contains with by searching for an element in the middle of the set.
     */
    @Test
    public final void containsTest2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsRef("a", "b", "c", "d", "e");
        boolean expected = true;
        String s = "c";

        boolean result = set.contains(s);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests contains with by searching for an element at the end of the set.
     */
    @Test
    public final void containsTest3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsRef("a", "b", "c", "d", "e");
        boolean expected = true;
        String s = "e";

        boolean result = set.contains(s);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests contains with by searching for an element not within the set.
     */
    @Test
    public final void containsTest4() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsRef("a", "b", "c", "d", "e");
        boolean expected = false;
        String s = "abc";

        boolean result = set.contains(s);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests contains with by searching for an element inside of an empty set
     */
    @Test
    public final void containsTest5() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsRef();
        boolean expected = false;
        String s = "abc";

        boolean result = set.contains(s);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests contains with by searching for an empty String inside of a set
     */
    @Test
    public final void containsTest6() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsRef("a", "b", "c");
        boolean expected = false;
        String s = "";

        boolean result = set.contains(s);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests contains by searching for root of a set
     */
    @Test
    public final void containsTest7() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsRef("b", "a", "c");
        boolean expected = true;
        String s = "b";

        boolean result = set.contains(s);

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests size with a set with 3 elements
     */
    @Test
    public final void sizeTest1() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("a", "b", "c");
        int expected = 3;

        int result = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests size with a set with 2 elements
     */
    @Test
    public final void sizeTest2() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("a", "b");
        int expected = 2;

        int result = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests size with a set with 1 element
     */
    @Test
    public final void sizeTest3() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("a");
        int expected = 1;

        int result = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests size with a set with 0 elements
     */
    @Test
    public final void sizeTest4() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest();
        int expected = 0;

        int result = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests size with a set with an empty String as an element
     */
    @Test
    public final void sizeTest5() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("");
        int expected = 1;

        int result = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

    /**
     * Tests size with a set with 5 elements
     */
    @Test
    public final void sizeTest6() {
        /*
         * Set up variables and call method under test
         */
        Set<String> set = this.createFromArgsTest("a", "b", "c", "d", "e");
        int expected = 5;

        int result = set.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);

    }

}
