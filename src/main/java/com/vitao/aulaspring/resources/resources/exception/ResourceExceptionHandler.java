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

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
// essa classe de excessão é o cabeçalho das excessões usamos para substituir excessões padrões por nossas personalizadas para serem apresentadas
// na resposta enviada ao navegador
@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){

        StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.NOT_FOUND.value(),"Not found!",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
  
    }
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request){

        StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"Data integrity!",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<StandardError> validation(ConstraintViolationException e, HttpServletRequest request){

       ValidationError err = new ValidationError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(),"Validation Error!",e.getMessage(),request.getRequestURI());
       return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request){

        ValidationError err = new ValidationError(System.currentTimeMillis(),HttpStatus.UNPROCESSABLE_ENTITY.value(),"Validation Error!",e.getMessage(),request.getRequestURI());
        for (FieldError x : e.getBindingResult().getFieldErrors()){
            err.addError(x.getField(),x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(err);

    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> author(AuthorizationException e, HttpServletRequest request){

        StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(),"Acesso negado!", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
  
    }
    
    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request){

        StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.BAD_REQUEST.value(),"File Exception!",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
  
    }

    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request){

        HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
        StandardError err = new StandardError (System.currentTimeMillis(),code.value(),"Amazon Service Exception!",e.getMessage(),request.getRequestURI());

        return ResponseEntity.status(code).body(err);
  
    }

    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request){

        StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(),"Amazon Client Exception!",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
  
    }
    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request){

        StandardError err = new StandardError(System.currentTimeMillis(),HttpStatus.FORBIDDEN.value(),"Amazon S3 Exception!",e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);   

    }
    
}

