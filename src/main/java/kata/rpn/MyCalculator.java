package kata.rpn;

import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class MyCalculator implements Calculator {

    private static final String ADD = "+";
    private static final String SUB = "-";
    private static final String MULT = "x";
    private static final String DIV = "/";
    private static final String OPERATORS = ADD + SUB + MULT + DIV;
    private static final String SPACE = " ";

    @Override
    public BigDecimal compute(String operation) {
        String[] ops = operation.split(SPACE);
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
        checkZeroDivide(right, operation);
        return getOperation(operation).apply(left, right);
    }

    private void checkZeroDivide(BigDecimal right, String operation) {
        if (BigDecimal.ZERO.equals(right) && DIV.equals(operation)) {
            throw new IllegalArgumentException("Opération inconnue " + operation);
        }
    }

    private BiFunction<BigDecimal, BigDecimal, BigDecimal> getOperation(String operationStr) {
        switch (operationStr) {
            case ADD:
                return BigDecimal::add;
            case SUB:
                return BigDecimal::subtract;
            case MULT:
                return BigDecimal::multiply;
            case DIV:
                return BigDecimal::divide;
            default:
                throw new IllegalArgumentException("Opération inconnue " + operationStr);
        }
    }

    private boolean isOperator(String s) {
        return OPERATORS.contains(s);
    }
}
