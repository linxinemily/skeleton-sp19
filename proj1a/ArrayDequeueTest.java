public class ArrayDequeueTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        LinkedListDeque<String> AList = new LinkedListDeque<String>();

        boolean passed = checkEmpty(true, AList.isEmpty());

        AList.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, AList.size()) && passed;
        passed = checkEmpty(false, AList.isEmpty()) && passed;

        AList.addLast("middle");
        passed = checkSize(2, AList.size()) && passed;

        AList.addLast("back");
        passed = checkSize(3, AList.size()) && passed;

        System.out.println("Printing out deque: ");
        AList.printDeque();

        printTestStatus(passed);
    }

    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> AList = new ArrayDeque<Integer>();
        // should be empty
        boolean passed = checkEmpty(true, AList.isEmpty());

        AList.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, AList.isEmpty()) && passed;

        AList.removeFirst();
        // should be empty
        passed = checkEmpty(true, AList.isEmpty()) && passed;

        printTestStatus(passed);
    }

    public static void addGetTest() {
        System.out.println("Running add/get test.");
        ArrayDeque<String> AList = new ArrayDeque<String>();
        AList.addLast("apple");
        AList.addLast("banana");
        AList.addLast("cherry");
        AList.addLast("tomato");
        AList.addLast("mango");

        System.out.println("Printing out deque: ");
        AList.printDeque();

        boolean passed = AList.get(3).equals("tomato");
        passed = AList.get(4).equals("mango") && passed;
        printTestStatus(passed);
    }

    public static void constructorTest() {
        ArrayDeque<String> AList_1 = new ArrayDeque<String>();
        AList_1.addLast("a");
        AList_1.addLast("b");
        AList_1.addLast("c");
        AList_1.addLast("d");
        AList_1.addLast("e");
        AList_1.addLast("f");
        AList_1.addLast("g");
        AList_1.addLast("h");

        System.out.println("[List 1] Printing out deque: ");
        AList_1.printDeque();

        ArrayDeque<String> AList_2 = new ArrayDeque<String>(AList_1);
        System.out.println("[List 2] Printing out deque: ");
        AList_2.printDeque();

        boolean passed = checkSize(AList_1.size(), AList_2.size());
        printTestStatus(passed);
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        addGetTest();
        constructorTest();
    }
}
