package com.example.demo.controller;

import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HTTPLinkManipulator {
	static Pattern pattern = Pattern.compile("(https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|www\\.[a-zA-Z0-9][a-zA-Z0-9-]+[a-zA-Z0-9]\\.[^\\s]{2,}|https?:\\/\\/(?:www\\.|(?!www))[a-zA-Z0-9]+\\.[^\\s]{2,}|www\\.[a-zA-Z0-9]+\\.[^\\s]{2,})", Pattern.CASE_INSENSITIVE);
	
	public static String convertStringToHTTPSForm(String s) {
		if (!s.startsWith("https://") && !s.startsWith("http://")) {
			return "https://" + s;
		}
		else {
			return s;
		}
	}
	
	public static Boolean validate(String s) {
		return pattern.matcher(s).find();
	}
	
	
	@Test
	public void testValidate() {
		assertTrue(validate("https://www.google.com"));
		assertTrue(validate("www.google.com"));
		assertTrue(validate("https://google.com"));
		assertTrue(validate("www.google.com"));
		
		assertFalse(validate("https://www.google"));
		assertFalse(validate("https://googlecom"));
	}
	
	@Test
	public void testConvertStringToHTTPSForm() {
		assertTrue(convertStringToHTTPSForm("www.google.com").equals("https://www.google.com"));
		assertTrue(convertStringToHTTPSForm("https://www.google.com").equals("https://www.google.com"));
		assertTrue(convertStringToHTTPSForm("http://www.google.com").equals("http://www.google.com"));
	}
}
