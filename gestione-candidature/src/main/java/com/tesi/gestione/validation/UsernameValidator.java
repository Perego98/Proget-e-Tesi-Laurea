package com.tesi.gestione.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<ValidUsername, String> {

	private Pattern pattern;
	private Matcher matcher;

	@Override
	public boolean isValid(final String username, final ConstraintValidatorContext context) {

		if (username == null) {
			return false;
		}

		if (username.contains(" ")) {
			return false;
		}

		return true;
	}

}