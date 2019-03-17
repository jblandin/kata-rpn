package kata.rpn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CalculatorTest {

    private Calculator myCalculator = new MyCalculator();
    @Test
    public void additionSimple() {
        evalOperation("5 3 +", "8");
    }

    @Test
    public void soustractionSimple() {
        evalOperation("5 2 -", "3");
    }

    @Test
    public void multiplicationSimple() {
        evalOperation("5 3 x", "15");
    }

    @Test
    public void divisionSimple() {
        evalOperation("6 2 /", "3");
    }

    @Test
    public void operationComposee() {
        evalOperation("3 15 8 x 7 + x", "381");
    }

    @Test
    public void operationComposee2() {
        evalOperation("1 2 + 4 x 5 + 3 -", "14");
    }

    @Test
    public void operationComposee3() {
        evalOperation("5 4 1 2 + x +", "17");
    }

    @Test
    public void operationComposee4() {
        evalOperation("3 4 2 1 + x + 2 /", "7.5");
    }

    @Test()
    public void divisionParZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> myCalculator.compute("6 0 /"));
    }

    private void evalOperation(String inputOperation, String expectedStringVal) {
        assertEquals(new BigDecimal(expectedStringVal), myCalculator.compute(inputOperation));
    }
}
