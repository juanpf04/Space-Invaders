package tp1.exception;

public class NoShockWaveException extends GameModelException {

	public NoShockWaveException() { 
		super();
	}
	
	public NoShockWaveException(String message){ 
		super(message);
	}
	
	public NoShockWaveException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NoShockWaveException(Throwable cause){ 
		super(cause); 
	}
	
	protected NoShockWaveException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
	
}
