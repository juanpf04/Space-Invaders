package tp1.exception;

public class NotAllowedMoveException extends GameModelException {

	public NotAllowedMoveException() { 
		super();
	}
	
	public NotAllowedMoveException(String message){ 
		super(message);
	}
	
	public NotAllowedMoveException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotAllowedMoveException(Throwable cause){ 
		super(cause); 
	}
	
	protected NotAllowedMoveException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
