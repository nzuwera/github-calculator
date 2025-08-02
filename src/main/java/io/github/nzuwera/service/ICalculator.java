package io.github.nzuwera.service;

import java.util.List;

public interface ICalculator {
    enum operations {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        UNKNOWN
    }

    String run(String operation, List<Double> numbers);

    boolean isOperation(String ops);

    operations getOperation(String operation);
}
