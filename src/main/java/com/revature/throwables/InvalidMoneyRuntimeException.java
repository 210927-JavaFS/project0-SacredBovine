package com.revature.throwables;

public class InvalidMoneyRuntimeException extends RuntimeException{

		public InvalidMoneyRuntimeException() {
			super();
			System.out.println("InvalidMoneyRuntimeException!");
			
		}
		public InvalidMoneyRuntimeException(String message) {
			super(message);
		}
}