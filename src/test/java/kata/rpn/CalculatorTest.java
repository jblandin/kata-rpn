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
        assertEquals(new BigDecimal("8"), myCalculator.compute("5 3 +"));
    }

    @Test
    public void soustractionSimple() {
        assertEquals(new BigDecimal("3"), myCalculator.compute("5 2 -"));
    }

    @Test
    public void multiplicationSimple() {
        assertEquals(new BigDecimal("15"), myCalculator.compute("5 3 x"));
    }

    @Test
    public void divisionSimple() {
        assertEquals(new BigDecimal("3"), myCalculator.compute("6 2 /"));
    }

    @Test
    public void operationComposee() {
        assertEquals(new BigDecimal("381"), myCalculator.compute("3 15 8 x 7 + x"));
    }
    @Test
    public void operationComposee2() {
        assertEquals(new BigDecimal("14"), myCalculator.compute("1 2 + 4 x 5 + 3 -"));
    }
    @Test
    public void operationComposee3() {
        assertEquals(new BigDecimal("17"), myCalculator.compute("5 4 1 2 + x +"));
    }
    @Test
    public void operationComposee4() {
        assertEquals(new BigDecimal("7.5"), myCalculator.compute("3 4 2 1 + x + 2 /"));
    }

    @Test()
    public void divisionParZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> myCalculator.compute("6 0 /"));
    }

}
