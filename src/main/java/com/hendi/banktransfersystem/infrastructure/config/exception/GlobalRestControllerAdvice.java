package com.hendi.banktransfersystem.infrastructure.config.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpErrorResponse;
import com.hendi.banktransfersystem.infrastructure.config.web.response.WebHttpResponse;

import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalRestControllerAdvice extends ResponseEntityExceptionHandler {

	/**
	 * Handles validation errors and converts them into a standardized format.
	 *
	 * @param ex The ConstraintViolationException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleValidationError(
			ConstraintViolationException ex) {

		// Convert ConstraintViolation objects into WebHttpErrorResponse objects
		List<WebHttpErrorResponse> messages = ex.getConstraintViolations().stream()
				.map(violation -> new WebHttpErrorResponse(violation.getPropertyPath().toString(),
						violation.getMessage()))
				.collect(Collectors.toList());

		return ResponseEntity.badRequest().body(WebHttpResponse.badRequest(messages));
	}

	/**
	 * Handles generic exceptions and converts them into a standardized format.
	 *
	 * @param ex The Exception instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleGenericException(Exception ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(WebHttpResponse.internalServerError(messages));
	}

	/**
	 * Handles JPA system exceptions and converts them into a standardized format.
	 *
	 * @param ex The JpaSystemException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(PersistenceException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handlePersistenceException(
			PersistenceException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.internalServerError().body(WebHttpResponse.internalServerError(messages));
	}

	/**
	 * Handles data integrity violation exceptions and converts them into a
	 * standardized format.
	 *
	 * @param ex The DataIntegrityViolationException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleDataIntegrityViolationException(
			DataIntegrityViolationException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.internalServerError().body(WebHttpResponse.internalServerError(messages));
	}

	/**
	 * Handles invalid data access API usage exceptions and converts them into a
	 * standardized format.
	 *
	 * @param ex The InvalidDataAccessApiUsageException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(InvalidDataAccessApiUsageException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleInvalidDataAccessApiUsageException(
			InvalidDataAccessApiUsageException ex) {
		List<WebHttpErrorResponse> message = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.internalServerError().body(WebHttpResponse.internalServerError(message));
	}
}
