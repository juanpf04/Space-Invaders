package tp1.exception;

public class GameModelException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GameModelException() { 
		super();
	}
	
	public GameModelException(String message){ 
		super(message);
	}
	
	public GameModelException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public GameModelException(Throwable cause){ 
		super(cause); 
	}
	
	protected GameModelException(String message, Throwable cause, 
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
