package com.example.demo.service;

@SuppressWarnings("serial")
public class ServiceException extends Exception {
	public ServiceException() {}
	public ServiceException(String message) {
		super(message);
	}
}
