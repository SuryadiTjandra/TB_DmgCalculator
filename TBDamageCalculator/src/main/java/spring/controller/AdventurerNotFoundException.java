package spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdventurerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public AdventurerNotFoundException(String message) {
		super(message);
	}
}
