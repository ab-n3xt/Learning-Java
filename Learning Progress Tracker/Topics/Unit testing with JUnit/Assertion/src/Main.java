class CalculatorTest {
    void testAddition() {
        // implement a test case
        Calculator c = new Calculator();

        int a = 1, b = 2;
        int result = c.add(a, b);
        int expected = 3;

        Assertions.assertEquals(result, expected);
    }
}