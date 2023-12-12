import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReversePolishNotationCalculatorTest {

    @Test
    public void shouldCalculateAddition() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        int result = calculator.calculatePolishNotation("2 3 +");
        Assertions.assertEquals(5, result);
    }

    @Test
    public void shouldCalculateSubtraction() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        int result = calculator.calculatePolishNotation("5 3 -");
        Assertions.assertEquals(2, result);
    }

    @Test
    public void shouldCalculateMultiplication() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        int result = calculator.calculatePolishNotation("2 4 *");
        Assertions.assertEquals(8, result);
    }

    @Test
    public void shouldCalculateMultipleOperations() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        int result = calculator.calculatePolishNotation("2 3 + 4 *");
        Assertions.assertEquals(20, result);
    }

    @Test
    public void shouldCalculateComplexExpression() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        int result = calculator.calculatePolishNotation("5 2 4 * + 7 -");
        Assertions.assertEquals(6, result);
    }

    @Test
    public void shouldHandleMultipleSpacesBetweenNumbersAndOperators() {
        ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
        int result = calculator.calculatePolishNotation("5       4    -");
        Assertions.assertEquals(1, result);
    }

}

class ReversePolishNotationCalculator {

    public int calculatePolishNotation(String str) {
        String[] parts = str.split(" ");
        Deque<Integer> numbers = new ArrayDeque<>();
        int index = 0;

        while (index != parts.length) {

            if (parts[index].isBlank()) {
                index++;
                continue;
            }

            if (isOperation(parts[index])) {
                int operandOne = numbers.pop();
                int operandTwo = numbers.pop();

                if (parts[index].equals("+")) {
                    numbers.push(operandOne + operandTwo);
                } else if (parts[index].equals("-")) {
                    numbers.push(operandTwo - operandOne);
                } else if (parts[index].equals("*")) {
                    numbers.push(operandOne * operandTwo);
                }

            } else {
                numbers.push(Integer.parseInt(parts[index]));
            }

            index++;
        }

        return numbers.pop();
    }

    private boolean isOperation(String part) {
        if (part.equals("+")
                || part.equals("-")
                || part.equals("*")) {
            return true;
        }

        return false;
    }
}



