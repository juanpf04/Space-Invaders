package tp1.exception;

public class NumberFormatException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberFormatException() { 
		super();
	}
	
	public NumberFormatException(String message){ 
		super(message);
	}
	
	public NumberFormatException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NumberFormatException(Throwable cause){ 
		super(cause); 
	}
	
	protected NumberFormatException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
