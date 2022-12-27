package com.example.demo.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/*
 * provides a one to one mapping of integer i to a URL string
 */
public class IntegerToURLMapper {
	static String urlCharacters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String convert(int i) {
		StringBuilder returnString = new StringBuilder();
		int base = urlCharacters.length();
		int n = i;
		while (n != 0) {
			int remainder = n % base;
			returnString.append(urlCharacters.charAt(remainder));
			n -= remainder;
			n /= base;
		}
		return returnString.toString();
	}
	
	@Test
	public void testDecimalConvert() {
		urlCharacters="0123456789";
		assertEquals(convert(321), "123");
		assertEquals(convert(3891), "1983");
		
	}
	
	@Test
	public void testHexadecimalConvert() {
		urlCharacters = "0123456789ABCDEF";
		assertEquals(convert(123450), "A32E1");
		assertEquals(convert(11237897), "90A7BA");
	}
}
