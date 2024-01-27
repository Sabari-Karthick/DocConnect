package com.Batman.advice;

import java.time.LocalDateTime;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Batman.exceptions.AlreadyExistsException;
import com.Batman.exceptions.DetailsNotFoundException;
import com.Batman.exceptions.InputFieldException;
import com.Batman.exceptions.JwtAuthenticationException;
import com.Batman.exceptions.SlotNotAvailableException;
import com.Batman.exceptions.UserNotFoundException;

@RestControllerAdvice	
public class ExceptionInterceptor {

	
	@ExceptionHandler(InputFieldException.class)
	public ResponseEntity<Error> handleInputFieldException(InputFieldException e){
		
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"Unprocessable_Request");
		return new ResponseEntity<Error>(error,HttpStatus.UNPROCESSABLE_ENTITY);
		
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Error> handleUserNotFoundException(UserNotFoundException e){
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"User_Not_Found");
		return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(DetailsNotFoundException.class)
	public ResponseEntity<Error> handleDetailsNotFoundException(DetailsNotFoundException e){
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"Details_Not_Found");
		return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Error> handleConstraintViolationException(DataIntegrityViolationException e){
		Error error = new Error(LocalDateTime.now(),"Given Details are invlaid , try with valid details","INVALID_DETAILS");
		return new ResponseEntity<Error>(error,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity<Error> handleAlreadyExistsFoundException(AlreadyExistsException e){
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"Already_Exists");
		return new ResponseEntity<Error>(error,HttpStatus.TOO_MANY_REQUESTS);
	}
	
	@ExceptionHandler(SlotNotAvailableException.class)
	public ResponseEntity<Error> handleSlotNotAvailableException(SlotNotAvailableException e){
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"SLOT_NOT_AVAILABLE");
		return new ResponseEntity<Error>(error,HttpStatus.TOO_MANY_REQUESTS);
	}
	
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<Error> handleUsernameNotFoundException(UsernameNotFoundException e){
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"USERNAME_NOT_FOUND");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(JwtAuthenticationException.class)
	public ResponseEntity<Error> handleJwtAuthenticationException(JwtAuthenticationException e){
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"AUTHENTICATION_FAILED");
		return new ResponseEntity<Error>(error,e.getHttpStatus());
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<Error> handleAuthenticationException(AuthenticationException e){
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"AUTHENTICATION_FAILED");
		return new ResponseEntity<Error>(error,HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleException(Exception e){
		System.out.println(e.getClass());
		Error error = new Error(LocalDateTime.now(),e.getMessage(),"Processing_Error");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
}
