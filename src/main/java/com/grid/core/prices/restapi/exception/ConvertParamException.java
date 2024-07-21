package com.grid.core.prices.restapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class ConvertParamException extends RuntimeException {
	
	private static final long serialVersionUID = -6988956542221472211L;
	
	public ConvertParamException(String message) {
        super(message);
    }
	
}