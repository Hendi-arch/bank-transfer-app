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

import com.hendi.banktransfersystem.entity.transaction.exception.InsufficientBalanceException;
import com.hendi.banktransfersystem.entity.transaction.exception.TransactionNotFoundException;
import com.hendi.banktransfersystem.entity.user.exception.PasswordNotMatchException;
import com.hendi.banktransfersystem.entity.user.exception.UserNotFoundException;
import com.hendi.banktransfersystem.entity.usertoken.exception.UserTokenNotFoundException;
import com.hendi.banktransfersystem.entity.usertoken.exception.UserTokenRevokedException;
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
	 * Handles when the sender's balance is insufficient for a transaction.
	 *
	 * @param ex The InsufficientBalanceException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(InsufficientBalanceException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleInsufficientBalanceException(
			InsufficientBalanceException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse("sender_balance", ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WebHttpResponse.badRequest(messages));
	}

	/**
	 * Handles when a transaction is not found.
	 *
	 * @param ex The TransactionNotFoundException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(TransactionNotFoundException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleTransactionNotFoundException(
			TransactionNotFoundException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebHttpResponse.notFound(messages));
	}

	/**
	 * Handles when a user is not found.
	 *
	 * @param ex The UserNotFoundException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleUserNotFoundException(
			UserNotFoundException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebHttpResponse.notFound(messages));
	}

	/**
	 * Handles when a password does not match.
	 *
	 * @param ex The PasswordNotMatchException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(PasswordNotMatchException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handlePasswordNotMatchException(
			PasswordNotMatchException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(WebHttpResponse.badRequest(messages));
	}

	/**
	 * Handles when a user token is not found.
	 *
	 * @param ex The UserTokenNotFoundException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(UserTokenNotFoundException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleUserTokenNotFoundException(
			UserTokenNotFoundException ex) {
		List<WebHttpErrorResponse> messages = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(WebHttpResponse.notFound(messages));
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

	/**
	 * Handles when authentication fails, usually due to revoked credentials.
	 *
	 * @param ex The UserTokenRevokedException instance
	 * @return ResponseEntity containing the error response
	 */
	@ExceptionHandler(UserTokenRevokedException.class)
	public ResponseEntity<WebHttpResponse<List<WebHttpErrorResponse>>> handleUserTokenRevokedException(
			UserTokenRevokedException ex) {
		List<WebHttpErrorResponse> message = List.of(new WebHttpErrorResponse(null, ex.getMessage()));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(WebHttpResponse.unauthorized(message));
	}

}
