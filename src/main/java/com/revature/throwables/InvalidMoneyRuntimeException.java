package com.revature.throwables;

public class InvalidMoneyRuntimeException extends RuntimeException{

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		public InvalidMoneyRuntimeException() {
			super();
			System.out.println("InvalidMoneyRuntimeException!");
			
		}
		public InvalidMoneyRuntimeException(String message) {
			super(message);
		}
}