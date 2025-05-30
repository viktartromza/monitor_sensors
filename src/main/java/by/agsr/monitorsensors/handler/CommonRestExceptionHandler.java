package by.agsr.monitorsensors.handler;

import by.agsr.monitorsensors.exception.SensorNotFoundException;
import by.agsr.monitorsensors.exception.TypeNotFoundException;
import by.agsr.monitorsensors.exception.UnitNoMatchingException;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CommonRestExceptionHandler {

    @ExceptionHandler(value = {
            TypeNotFoundException.class,
            SensorNotFoundException.class,
            NoSuchElementException.class})
    public ResponseEntity<ErrorResponseDto> handleNotFoundException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.createErrorDto(e));
    }

    @ExceptionHandler(value = {
            UnitNoMatchingException.class,
            IllegalArgumentException.class,
            TypeMismatchException.class,
            BadRequestException.class,
            TypeMismatchException.class})
    public ResponseEntity<ErrorResponseDto> handleBadRequestException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.createErrorDto(e));
    }

    @ExceptionHandler(value = {
            HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorResponseDto> handleRevocationDocumentServiceNotAvailableException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ErrorResponseDto.createErrorDto(e));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.createErrorDto(ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "))
        ));
    }
}

