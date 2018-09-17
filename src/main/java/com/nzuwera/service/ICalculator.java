package com.nzuwera.service;

public interface ICalculator {
    enum operations {
        ADD,
        SUBSTRACT,
        MULTIPLY,
        DIVIDE
    }

    String run(operations operations, int a,  int b);
}
