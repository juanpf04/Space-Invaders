package tp1.exception;

public class OffWorldException extends GameModelException {

	public OffWorldException() { 
		super();
	}
	
	public OffWorldException(String message){ 
		super(message);
	}
	
	public OffWorldException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public OffWorldException(Throwable cause){ 
		super(cause); 
	}
	
	protected OffWorldException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
