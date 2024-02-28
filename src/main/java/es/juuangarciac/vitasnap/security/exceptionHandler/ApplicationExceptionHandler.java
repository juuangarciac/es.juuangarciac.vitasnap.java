package es.juuangarciac.vitasnap.security.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ExceptionHandler(ServletRequestBindingException.class)
    public final ResponseEntity<Object> handleHeaderException(Exception ex, WebRequest request) {

        String detail = ex.getLocalizedMessage();
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, detail);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        String detail = ex.getLocalizedMessage();
        ErrorResponse error = ErrorResponse.create(ex, HttpStatus.INTERNAL_SERVER_ERROR, detail);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}