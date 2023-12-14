package tp1.exception;

public class LaserInFlightException extends GameModelException {

	public LaserInFlightException() { 
		super();
	}
	
	public LaserInFlightException(String message){ 
		super(message);
	}
	
	public LaserInFlightException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LaserInFlightException(Throwable cause){ 
		super(cause); 
	}
	
	public LaserInFlightException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
