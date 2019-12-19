package ru.stateofmind.codeexamples.exception;

/**
 * Custom exception.
 *
 * @author Alexey Dvoryaninov
 * @since 19/12/2019
 */
public class CustomException extends RuntimeException {

    public CustomException(String message) {
        // put here some code
        super(message);
    }
}
