package org.pg.ha.poc.validation;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author cristianp
 */
public class AssertionException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public AssertionException()
    {
        super();
    }

    public AssertionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public AssertionException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public AssertionException(String message)
    {
        super(message);
    }

    public AssertionException(Throwable cause)
    {
        super(cause);
    }

}
