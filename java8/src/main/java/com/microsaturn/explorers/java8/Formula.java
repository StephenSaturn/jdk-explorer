package com.microsaturn.explorers.java8;

/**
 *
 * @author A14532
 * @date 2018-01-22
 */
public interface Formula {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }
}
