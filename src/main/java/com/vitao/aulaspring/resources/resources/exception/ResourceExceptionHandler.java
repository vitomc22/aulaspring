package com.vitao.aulaspring.resources.resources.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.vitao.aulaspring.services.exceptions.AuthorizationException;
import com.vitao.aulaspring.services.exceptions.DataIntegrityException;
import com.vitao.aulaspring.services.exceptions.FileException;
import com.vitao.aulaspring.services.exceptions.ObjectNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// essa classe de excessão é o cabeçalho das excessões usamos para substituir excessões padrões por nossas personalizadas para serem apresentadas
// na resposta enviada ao navegador
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  
    }
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> validation(ConstraintViolationException e, HttpServletRequest request){

        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
        
        for (ConstraintViolation<?> x : e.getConstraintViolations()) {
            err.addError(x.getPropertyPath().toString(), x.getMessage()); //aqui pegamos o nome do atributo e mensagem em forma de lista

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> author(AuthorizationException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
  
    }
    
    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
  
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){

        HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
        StandardError err = new StandardError(code.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(code).body(err);
  
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
  
    }
    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request){

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);   

    }
    
}

