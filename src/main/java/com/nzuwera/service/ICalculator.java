package com.nzuwera.service;

public interface ICalculator {
    enum operations {
        ADD,
        SUBSTRACT,
        MULTIPLY,
        DIVIDE,
        UNKNOWN
    }

    String run(String operation, int a, int b);

    boolean isOperation(String ops);

    operations getOperation(String operation);
}