package ca.sheridancollege.pate5749.Handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class errorhandling {
	@ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(Exception ex) {
        return "error/404"; // Refers to error/404.html
    }
}