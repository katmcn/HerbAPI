package com.example.demo.exceptions;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Herb Not Found")
public class HerbNotFound extends EntityNotFoundException{

	private static final long serialVersionUID = 6683881482045597047L;

	
}
	

