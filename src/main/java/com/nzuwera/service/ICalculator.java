package com.nzuwera.service;

import java.util.List;

public interface ICalculator {
    enum operations {
        A,
        S,
        M,
        D,
        U
    }

    String run(String operation, List<Double> numbers);

    boolean isOperation(String ops);

    operations getOperation(String operation);
}
