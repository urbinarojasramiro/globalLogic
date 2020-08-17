package com.globalExcercise.exception;

public class AuthorizationBusinessException extends GlobalBusinessException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7813958680443241440L;

	public String getCode(){
        return ErrorCode.AUTH_EXCEPTION;
    }

    public AuthorizationBusinessException()
    {
        super(ErrorMessage.UNKNOWN);
    }

    public AuthorizationBusinessException(String message)
    {
        super(message);
    }

    public AuthorizationBusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AuthorizationBusinessException(Throwable cause)
    {
        super(cause);
    }
}
