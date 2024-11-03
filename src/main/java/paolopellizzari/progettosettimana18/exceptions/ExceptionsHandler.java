package paolopellizzari.progettosettimana18.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import paolopellizzari.progettosettimana18.payloads.ExceptionResponseDTO;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponseDTO handleNotFound (NotFoundException nfe){
        return new ExceptionResponseDTO(nfe.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponseDTO handleBadRequest (BadRequestException bre){
        return new ExceptionResponseDTO(bre.getMessage(), LocalDateTime.now());
    }

}
