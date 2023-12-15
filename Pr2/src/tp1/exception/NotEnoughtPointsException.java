package tp1.exception;

public class NotEnoughtPointsException extends GameModelException {

	public NotEnoughtPointsException() { 
		super();
	}
	
	public NotEnoughtPointsException(String message){ 
		super(message);
	}
	
	public NotEnoughtPointsException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotEnoughtPointsException(Throwable cause){ 
		super(cause); 
	}
	
	protected NotEnoughtPointsException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
