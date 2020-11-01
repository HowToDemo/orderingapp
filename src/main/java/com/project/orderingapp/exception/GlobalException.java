package com.project.orderingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.orderingapp.constant.ExceptionStatusConstant;
import com.project.orderingapp.model.ErrorResponse;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiResponseException.class)
	public ResponseEntity<ErrorResponse> handleApiResponseException(ApiResponseException are,WebRequest request) {
        return new ResponseEntity<>(new ErrorResponse(ExceptionStatusConstant.INVALID_REQUEST),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidParameterException.class)
	public ResponseEntity<ErrorResponse> handleInvalidParameterException(InvalidParameterException ipe,WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ExceptionStatusConstant.INVALID_PARAMETER),HttpStatus.METHOD_NOT_ALLOWED);
	}
	
	@ExceptionHandler(OrderIdNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleOrderIdNotFound(OrderIdNotFoundException oinf,WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ExceptionStatusConstant.ID_NOT_FOUND),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(OrderAlreadyTakenException.class)
	public ResponseEntity<ErrorResponse> handleOrderAlreadyTakenException(OrderAlreadyTakenException oate,WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ExceptionStatusConstant.ORDER_ALREADY_TAKEN),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotIntegerException.class)
	public ResponseEntity<ErrorResponse> handleNotIntegerException(NotIntegerException nie,WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ExceptionStatusConstant.NOT_A_INTEGER),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(InvalidIntegerValueException.class)
	public ResponseEntity<ErrorResponse> handleInvalidIntegerValueException(InvalidIntegerValueException iive,WebRequest request) {
		return new ResponseEntity<>(new ErrorResponse(ExceptionStatusConstant.INVALID_INTEGER_VALUE),HttpStatus.NOT_ACCEPTABLE);
	}
}
