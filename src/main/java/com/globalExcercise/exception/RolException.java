package com.globalExcercise.exception;

public class RolException extends RepositoryBusinessException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8198898141231600335L;

	public RolException(Exception error)
    {
        super(ErrorMessage.REPO_MESSAGE, error);
    }

    public String getCode()
    {
        return ErrorCode.REPO_EXCEPTION;

    }
}
