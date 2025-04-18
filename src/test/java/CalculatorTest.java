import Calculator.src.main.java.Calculator;
import Calculator.src.main.java.command.CalculatorStack;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    void testPushAndPrintCommands() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testPushAndPrintCommands.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("5", output, "Expected output '5', but got: " + output);
    }

    @Test
    void testPopCommand() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testPopCommand.txt");
        System.setOut(System.out);

        // Проверяем, что вывод содержит ожидаемое значение
        String output = outContent.toString().trim();
        assertEquals("4", output, "Expected output 4 \nbut got: " + output);
    }

    @Test
    void testEmptyStack() throws Exception {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Calculator calculator = new Calculator();
        calculator.run("/test/testEmptyStack.txt");

        System.setErr(System.err);

        String output = errContent.toString().trim();
        assertTrue(output.contains("The command failed: POP - Stack is empty"),
                "Expected error message not found in output: " + output);
    }

    @Test
    void testEmptyStack1() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testEmptyStack1.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("Stack is empty", output, "Expected output 'Stack is empty', but got: " + output);
    }

    @Test
    void testDivisionByZero() throws Exception {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Calculator calculator = new Calculator();
        calculator.run("/test/testDivisionByZero.txt");

        System.setErr(System.err);

        String output = errContent.toString().trim();
        assertTrue(output.contains("The command failed: / - Division by zero!"),
                "Expected error message not found in output: " + output);
    }

    @Test
    void testComment() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testComment.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("5", output, "Expected output '5' \nbut got: " + output);
    }

    @Test
    void testAdd() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testAdd.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("20.0", output, "Expected output '20' \nbut got: " + output);
    }

    @Test
    void testDiv() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testDiv.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("3.0", output, "Expected output '3' \nbut got: " + output);
    }

    @Test
    void testMult() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testMult.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("75.0", output, "Expected output '75' \nbut got: " + output);
    }

    @Test
    void testSQRT() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testSQRT.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("5.0", output, "Expected output '5' \nbut got: " + output);
    }

    @Test
    void testSub() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testSub.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("10.0", output, "Expected output '10' \nbut got: " + output);
    }

    @Test
    void testInfinity() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        calculator.run("/test/testInfinity.txt");
        System.setOut(System.out);

        String output = outContent.toString().trim();
        assertEquals("Infinity", output, "Expected output 'Infinity' \nbut got: " + output);
    }

    @Test
    void testNotNumberAdd() throws Exception {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Calculator calculator = new Calculator();
        calculator.run("/test/testNotNumberAdd.txt");

        System.setErr(System.err);

        String output = errContent.toString().trim();
        assertTrue(output.contains("The command failed: + - Argument is not a number! (For input string: \"a\")"),
                "Expected error message not found in output: " + output);
    }

    @Test
    void testNotNumberDiv() throws Exception {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Calculator calculator = new Calculator();
        calculator.run("/test/testNotNumberDiv.txt");

        System.setErr(System.err);

        String output = errContent.toString().trim();
        assertTrue(output.contains("The command failed: / - Argument is not a number! (For input string: \"a\")"),
                "Expected error message not found in output: " + output);
    }

    @Test
    void testNotNumberMult() throws Exception {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Calculator calculator = new Calculator();
        calculator.run("/test/testNotNumberMult.txt");

        System.setErr(System.err);

        String output = errContent.toString().trim();
        assertTrue(output.contains("The command failed: * - Argument is not a number! (For input string: \"a\")"),
                "Expected error message not found in output: " + output);
    }

    @Test
    void testNotNumberSQRT() throws Exception {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Calculator calculator = new Calculator();
        calculator.run("/test/testNotNumberSQRT.txt");

        System.setErr(System.err);

        String output = errContent.toString().trim();
        assertTrue(output.contains("The command failed: SQRT - Argument is not a number! (For input string: \"a\")"),
                "Expected error message not found in output: " + output);
    }

    @Test
    void testNotNumberSub() throws Exception {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        Calculator calculator = new Calculator();
        calculator.run("/test/testNotNumberSub.txt");

        System.setErr(System.err);

        String output = errContent.toString().trim();
        assertTrue(output.contains("The command failed: - - Argument is not a number! (For input string: \"a\")"),
                "Expected error message not found in output: " + output);
    }
}