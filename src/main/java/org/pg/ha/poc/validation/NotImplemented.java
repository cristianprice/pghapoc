package org.pg.ha.poc.validation;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author cristianp
 */
public class NotImplemented extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public NotImplemented()
    {
        super();
    }

    public NotImplemented(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
    {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public NotImplemented(String message, Throwable cause)
    {
        super(message, cause);
    }

    public NotImplemented(String message)
    {
        super(message);
    }

    public NotImplemented(Throwable cause)
    {
        super(cause);
    }

}
