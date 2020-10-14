import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map2;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Malik Clarke
 * @author Drew Jackson
 * @author Ethan Hunter
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /*
     * Constructor test cases
     */

    /**
     * Tests no args constructor for Map4
     */
    @Test
    public final void noArgsConstructorTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> actual = this.constructorTest();
        Map<String, String> expected = this.constructorRef();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /*
     * Constructor test cases
     */

    /**
     * Tests constructor with one pair
     */
    @Test
    public final void constructorTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> actual = this.createFromArgsTest("test", "one");
        Map<String, String> expected = this.createFromArgsTest("test", "one");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /**
     * Tests constructor with two pairs
     */
    @Test
    public final void constructorTest2() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> actual = this.createFromArgsTest("test", "one",
                "is", "working");
        Map<String, String> expected = this.createFromArgsTest("test", "one",
                "is", "working");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /**
     * Tests constructor with keys = values
     */
    @Test
    public final void constructorTest3() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> actual = this.createFromArgsTest("key", "key",
                "value", "value");
        Map<String, String> expected = this.createFromArgsTest("key", "key",
                "value", "value");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /*
     * Kernel Method Test cases
     */

    /**
     * Test add with adding one <key, value> to an empty map
     */
    @Test
    public final void addTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> expected = this.createFromArgsRef("Malik",
                "Clarke");
        Map<String, String> result = this.createFromArgsTest();

        result.add("Malik", "Clarke");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Test add with adding one <key, value> to a map of size 1
     */
    @Test
    public final void addTest2() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> expected = this.createFromArgsRef("Malik", "Clarke",
                "CSE", "2231");
        Map<String, String> result = this.createFromArgsTest();

        result.add("Malik", "Clarke");
        result.add("CSE", "2231");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
    }

    /**
     * Test remove with with a map of size 1
     */
    @Test
    public final void removeTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> result = this.createFromArgsRef("Malik", "Clarke");
        Map<String, String> expected = this.createFromArgsTest();

        Map<String, String> map2 = new Map2();
        map2.add("Malik", "Clarke");
        Map.Pair<String, String> expectedPair = map2.remove("Malik");

        Map.Pair<String, String> pair = result.remove("Malik");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(expectedPair, pair);
    }

    /**
     * Test remove with with a map of size 2
     */
    @Test
    public final void removeTest2() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> result = this.createFromArgsRef("Malik", "Clarke",
                "CSE", "2231");
        Map<String, String> expected = this.createFromArgsTest("Malik",
                "Clarke");

        Map<String, String> map2 = new Map2();
        map2.add("CSE", "2231");
        Map.Pair<String, String> expectedPair = map2.remove("CSE");

        Map.Pair<String, String> pair = result.remove("CSE");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(expectedPair, pair);
    }

    /**
     * Test remove with with a map of size 3
     */
    @Test
    public final void removeTest3() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> result = this.createFromArgsRef("Malik", "Clarke",
                "CSE", "2231", "Programming", "Java");
        Map<String, String> expected = this.createFromArgsTest("Malik",
                "Clarke", "CSE", "2231");

        Map<String, String> map2 = new Map2();
        map2.add("Programming", "Java");
        Map.Pair<String, String> expectedPair = map2.remove("Programming");

        Map.Pair<String, String> pair = result.remove("Programming");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(expectedPair, pair);
    }

    /**
     * Test remove with removing a <key, value> from the middle of the map
     */
    @Test
    public final void removeTest4() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> result = this.createFromArgsRef("Malik", "Clarke",
                "CSE", "2231", "Programming", "Java");
        Map<String, String> expected = this.createFromArgsTest("Malik",
                "Clarke", "Programming", "Java");

        Map<String, String> map2 = new Map2();
        map2.add("CSE", "2231");
        Map.Pair<String, String> expectedPair = map2.remove("CSE");

        Map.Pair<String, String> pair = result.remove("CSE");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(expectedPair, pair);
    }

    /**
     * Test remove with removing a <key, value> from the beginning of the map
     */
    @Test
    public final void removeTest5() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> result = this.createFromArgsRef("Malik", "Clarke",
                "CSE", "2231", "Programming", "Java");
        Map<String, String> expected = this.createFromArgsTest("CSE", "2231",
                "Programming", "Java");

        Map<String, String> map2 = new Map2();
        map2.add("Malik", "Clarke");
        Map.Pair<String, String> expectedPair = map2.remove("Malik");

        Map.Pair<String, String> pair = result.remove("Malik");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, result);
        assertEquals(expectedPair, pair);
    }

    /**
     * Test removeAny with a map of size 1
     */
    @Test
    public final void removeAnyTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231");

        int expectedSize = 0;

        testMap.removeAny();

        int resultSize = testMap.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test removeAny with a map of size 2
     */
    @Test
    public final void removeAnyTest2() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b");

        int expectedSize = 1;

        testMap.removeAny();

        int resultSize = testMap.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test removeAny with a map of size 3
     */
    @Test
    public final void removeAnyTest3() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        int expectedSize = 2;

        testMap.removeAny();

        int resultSize = testMap.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSize, resultSize);
    }

    /**
     * Test value with a map of size 1
     */
    @Test
    public final void valueTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231");
        String expectedValue = "2231";

        String actualValue = testMap.value("CSE");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Test value with a map of size 2
     */
    @Test
    public final void valueTest2() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b");

        String expectedValue = "b";

        String actualValue = testMap.value("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Test value with a map of size 3
     */
    @Test
    public final void valueTest3() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        String expectedValue = "d";

        String actualValue = testMap.value("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Test value when finding a value in the middle of the map
     */
    @Test
    public final void valueTest4() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        String expectedValue = "b";

        String actualValue = testMap.value("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Test value when finding a value at the beginning of the map
     */
    @Test
    public final void valueTest5() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        String expectedValue = "2231";

        String actualValue = testMap.value("CSE");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedValue, actualValue);
    }

    /**
     * Test hasKey when finding a key at the beginning of the map
     */
    @Test
    public final void hasKeyTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        boolean expected = true;

        boolean actual = testMap.hasKey("CSE");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /**
     * Test hasKey when finding a key in the middle of the map
     */
    @Test
    public final void hasKeyTest2() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        boolean expected = true;

        boolean actual = testMap.hasKey("a");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /**
     * Test hasKey when finding a key at the end of the map
     */
    @Test
    public final void hasKeyTest3() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        boolean expected = true;

        boolean actual = testMap.hasKey("c");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /**
     * Test hasKey when the key is not in the map
     */
    @Test
    public final void hasKeyTest4() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("CSE", "2231",
                "a", "b", "c", "d");

        boolean expected = false;

        boolean actual = testMap.hasKey("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /**
     * Test hasKey when the key with an empty map
     */
    @Test
    public final void hasKeyTest5() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest();

        boolean expected = false;

        boolean actual = testMap.hasKey("d");

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expected, actual);
    }

    /**
     * Test size with an empty map
     */
    @Test
    public final void sizeTest1() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest();

        int expectedSize = 0;

        int actualSize = testMap.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Test size with a map with one pair
     */
    @Test
    public final void sizeTest2() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("Malik",
                "Clarke");

        int expectedSize = 1;

        int actualSize = testMap.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Test size with a map with two pairs
     */
    @Test
    public final void sizeTest3() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("Malik", "Clarke",
                "a", "b");

        int expectedSize = 2;

        int actualSize = testMap.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSize, actualSize);
    }

    /**
     * Test size with a map with three pairs
     */
    @Test
    public final void sizeTest4() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> testMap = this.createFromArgsTest("Malik", "Clarke",
                "a", "b", "c", "d");

        int expectedSize = 3;

        int actualSize = testMap.size();

        /*
         * Assert that values of variables match expectations
         */
        assertEquals(expectedSize, actualSize);
    }

}
