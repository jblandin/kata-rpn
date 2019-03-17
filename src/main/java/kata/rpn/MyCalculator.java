package kata.rpn;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.Stream;

public class MyCalculator implements Calculator {

    @Override
    public BigDecimal compute(String operation) {
        String[] ops = operation.split(" ");
        Deque<BigDecimal> numbers = new ArrayDeque<>();
        Stream.of(ops).forEach(s -> {
            if (isOperator(s)) {
                BigDecimal right = numbers.pop();
                BigDecimal left = numbers.pop();
                numbers.push(calc(left, right, s));
            } else {
                numbers.push(new BigDecimal(s));
            }
        });

        return numbers.getFirst();
    }

    private BigDecimal calc(BigDecimal left, BigDecimal right, String operation) {
        switch (operation) {
            case "+":
                return left.add(right);
            case "-":
                return left.subtract(right);
            case "x":
                return left.multiply(right);
            case "/":
                if (BigDecimal.ZERO.equals(right)) {
                    throw new IllegalArgumentException("Division par Zéro !");
                }
                return left.divide(right);
            default:
                throw new IllegalArgumentException("Opération inconnue " + operation);
        }
    }

    private boolean isOperator(String s) {
        return "+-x/".contains(s);
    }
}
