package com.clients.app.config;

import com.clients.commons.api.ApiError;
import com.clients.commons.api.constants.ErrorMsg;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return buildResponseEntity(new ApiError(ErrorMsg.VALIDATE_REQUEST.getCod(), ErrorMsg.VALIDATE_REQUEST.getMsj(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        return buildResponseEntity(new ApiError(ErrorMsg.VALIDATE_REQUEST.getCod(), ErrorMsg.VALIDATE_REQUEST.getMsj(), HttpStatus.BAD_REQUEST));
    }
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<Object> handleMissingRequestHeaderException(MissingRequestHeaderException ex) {
        return buildResponseEntity(new ApiError(ErrorMsg.VALIDATE_REQUEST.getCod(), ErrorMsg.VALIDATE_REQUEST.getMsj(), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<Object> handleMethodFeign(FeignException ex) {
        return buildResponseEntity(new ApiError(ErrorMsg.VALIDATE_REQUEST.getCod(), ErrorMsg.VALIDATE_REQUEST.getMsj(), HttpStatus.valueOf(ex.status())));
    }

/*    @ExceptionHandler(ProviderException.class)
    protected ResponseEntity<Object> handleMethodProviderException(ProviderException ex) {
        logPrinter.write(ex);
        return buildResponseEntity(new ApiError(ex.getMensaje(),ex.getMensajeTec(), ex.getCodigo(), ex.getStatus()));
    }*/

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleMethodBadException(Exception ex) {
        return buildResponseEntity(new ApiError(ErrorMsg.INTER_ERROR.getCod(), ErrorMsg.INTER_ERROR.getMsj(), HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
