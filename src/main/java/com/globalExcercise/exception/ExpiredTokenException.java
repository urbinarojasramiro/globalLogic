package com.globalExcercise.exception;

public class ExpiredTokenException extends AuthorizationBusinessException
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExpiredTokenException(Exception error)
    {
        super(ErrorMessage.AUTH, error);
    }

    public String getCode()
    {
        return ErrorCode.AUTH_EXCEPTION;

    }
}
