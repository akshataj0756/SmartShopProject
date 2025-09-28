package com.smartshop.userinterface;

public class InvalidInputException extends RuntimeException {
	
		private static final long serialVersionUID = 1L;
		public String message;

		public InvalidInputException(String message) {
			super(message);

		}
	}


