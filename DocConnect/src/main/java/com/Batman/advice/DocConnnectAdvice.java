package com.Batman.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Batman.error.Error;
import com.Batman.exception.AlreadyBookedException;
import com.Batman.exception.DoctorNotFoundException;
import com.Batman.exception.ProcessingException;

@RestControllerAdvice
public class DocConnnectAdvice {

	@ExceptionHandler(DoctorNotFoundException.class)
	public ResponseEntity<Error> handleDoctorNotFoundException(DoctorNotFoundException doctorNotFoundException){
		Error error = new Error(doctorNotFoundException.getMessage(), LocalDateTime.now(), "404_Not_Found");
		
		return new ResponseEntity<Error>(error,HttpStatus.NOT_FOUND);
	}
	
	
	@ExceptionHandler(ProcessingException.class)
	public ResponseEntity<Error> handleProcessingError(ProcessingException processingException){
		Error error = new Error(processingException.getMessage(), LocalDateTime.now(), "500_Mail_Processing_Error");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}	
	
	@ExceptionHandler(AlreadyBookedException.class)
	public ResponseEntity<Error> handleAlreadyBookedException(AlreadyBookedException bookedException){
	        Error error = new Error(bookedException.getMessage(), LocalDateTime.now(), "400_BAD_Request_For_Booking");
	        return new ResponseEntity<Error>(error, HttpStatus.BAD_REQUEST);
	        
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error> handleOtherException(Exception e){
		Error error = new Error(e.getMessage(), LocalDateTime.now(), "500_Internal_Server_Error");
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
	
}
