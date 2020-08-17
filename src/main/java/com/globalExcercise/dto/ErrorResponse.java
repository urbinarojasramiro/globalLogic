package com.globalExcercise.dto;

import java.io.Serializable;
import java.util.List;

public class ErrorResponse implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5986823227666074143L;
	private String code;
    private String message;
    private List<String> errors;

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public List<String> getErrors()
    {
        return errors;
    }

    public void setErrors(List<String> errors)
    {
        this.errors = errors;
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append(", errors=").append(errors);
        sb.append('}');
        return sb.toString();
    }
}
