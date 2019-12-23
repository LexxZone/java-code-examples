package ru.stateofmind.codeexamples.parametrized;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * You can add custom names to the class or method with @DisplayName.
 */
@DisplayName("Custom Test Name")
class ParametrizedTestsExample {
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

        @Test
        @DisplayName("First Simple Test")
        void firstSimpleTest() {
        }

        @DisplayName("Second Simple Test")
        @Test
        void second_simple_test() {
        }

    }
