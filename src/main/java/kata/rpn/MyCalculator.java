package kata.rpn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class MyCalculator implements Calculator {

    @Override
    public BigDecimal compute(String operation) {
        String[] ops = operation.split(" ");

        List<BigDecimal> numbers = new ArrayList<>();
        Stream.of(ops).forEach(s -> {
            if (isOperator(s)) {
                BigDecimal right = numbers.remove(numbers.size() - 1);
                BigDecimal left = numbers.remove(numbers.size() - 1);
                numbers.add(calc(left, right, s));
            } else {
                numbers.add(new BigDecimal(s));
            }
        });

        return numbers.get(0);
    }



    private BigDecimal calc(BigDecimal left, BigDecimal right, String s) {
        switch (s) {
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
                throw new IllegalArgumentException("Opération inconnue " + s);
        }
    }

    private boolean isOperator(String s) {
        return "+-x/".contains(s);
    }
}
