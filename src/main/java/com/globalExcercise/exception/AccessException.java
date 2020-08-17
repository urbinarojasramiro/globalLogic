package com.globalExcercise.exception;


public class AccessException extends AuthorizationBusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9206080276562193190L;

	public AccessException(Exception error)
    {
        super(ErrorMessage.AUTH, error);
    }

    public String getCode()
    {
        return ErrorCode.AUTH_EXCEPTION;

    }

}
