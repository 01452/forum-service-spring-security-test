package telran.java2022.accounting.dto.exceptions;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordUpdateException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6585119053472239314L;

	public PasswordUpdateException(LocalDate localDate) {
		super("Last password update was: " + localDate + ". Update your password.");
	}
}
