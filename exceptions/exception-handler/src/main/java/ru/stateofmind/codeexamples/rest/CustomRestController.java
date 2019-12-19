package ru.stateofmind.codeexamples.rest;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stateofmind.codeexamples.exception.CustomException;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * @author Alexey Dvoryaninov
 * @since 19/12/2019
 */
@RestController
@RequestMapping("/api")
public class CustomRestController {

    private static final Logger LOG = getLogger(CustomRestController.class.getName());

    /**
     * Uncomment different exceptions and make GET request in browser to see how handlers work.
     */
    @GetMapping("/process")
    public void process() {
        // some code
        LOG.info("Process is initialized!");
        //throw new IllegalArgumentException();
        //throw new CustomException("Secret message");
        throw new NullPointerException("NPE");
    }

    /**
     * This exceptionHandler handles standard {@link IllegalArgumentException} exception.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public void customBadRequestHandler() {
        // some code
        LOG.info("To LOG::: customBadRequestHandler is reached!");
    }

    /**
     * This exceptionHandler handles custom {@link CustomException} exception.
     * @param ex {@link CustomException}
     */
    @ExceptionHandler(CustomException.class)
    public void customExceptionHandler(CustomException ex) {
        // some code
        LOG.info("To LOG::: customExceptionHandler is reached! \nMessage: " + ex.getMessage());
    }

    /**
     * This exceptionHandler handles {@link CustomException} exception and return ResponseEntity.
     * @param ex {@link NullPointerException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity customHandler(NullPointerException ex) {
        // some code
        LOG.info("To LOG::: customHandler with ResponseEntity is reached! \nMessage: " + ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
