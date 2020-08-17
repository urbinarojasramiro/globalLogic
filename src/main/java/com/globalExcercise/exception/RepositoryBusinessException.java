package com.globalExcercise.exception;

public class RepositoryBusinessException extends GlobalBusinessException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getCode(){
        return ErrorCode.AUTH_EXCEPTION;
    }

    public RepositoryBusinessException()
    {
        super(ErrorMessage.REPO_MESSAGE);
    }

    public RepositoryBusinessException(String message)
    {
        super(message);
    }

    public RepositoryBusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public RepositoryBusinessException(Throwable cause)
    {
        super(cause);
    }
}
