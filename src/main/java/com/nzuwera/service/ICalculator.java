package com.nzuwera.service;

public interface ICalculator {
    enum operations {
        A,
        S,
        M,
        D,
        U
    }

    String run(String operation, int a, int b);

    boolean isOperation(String ops);

    operations getOperation(String operation);
}