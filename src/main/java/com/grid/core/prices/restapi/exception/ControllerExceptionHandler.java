package com.grid.core.prices.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.grid.core.prices.restapi.dto.PricesResponseDto;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public PricesResponseDto missingServletRequestParameterExceptionHandlerMethod(
			MissingServletRequestParameterException mSRPEx, WebRequest request) {

		return PricesResponseDto.builder()
				.errorMessage("Error parameter validation")
			.build();
	}

	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public PricesResponseDto constraintViolationExceptionHandlerMethod(ConstraintViolationException cVEx,
			WebRequest request) {

		return PricesResponseDto.builder()
					.errorMessage("Bad request, please type a request params properly")
				.build();
	}
	
	@ExceptionHandler(ConvertParamException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public PricesResponseDto convertParamExceptionHandlerMethod(ConvertParamException cPEx,
			WebRequest request) {

		return PricesResponseDto.builder()
					.errorMessage(cPEx.getMessage())
				.build();
	}
}