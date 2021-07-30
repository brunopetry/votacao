package com.example.votacao.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.votacao.dto.ErroDTO;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleAnyException(Exception e, WebRequest request) {

		String descricaoErro = e.getLocalizedMessage();
		if (descricaoErro == null) {
			descricaoErro = e.toString();
		}

		return new ResponseEntity<>(new ErroDTO(descricaoErro), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}