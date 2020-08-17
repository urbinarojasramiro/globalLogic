package com.globalExcercise.exception;

public class GlobalBusinessException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCode(){
        return ErrorCode.BASE;
    }

    public GlobalBusinessException()
    {
        super(ErrorMessage.BASE);
    }

    public GlobalBusinessException(String message)
    {
        super(message);
    }

    public GlobalBusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public GlobalBusinessException(Throwable cause)
    {
        super(cause);
    }

}
