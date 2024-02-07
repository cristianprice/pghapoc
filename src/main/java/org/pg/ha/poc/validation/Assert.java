package org.pg.ha.poc.validation;

/**
 * <p>
 * Validates stuff.
 * </p>
 *
 * @author cristianp
 */
public class Assert
{
    private Assert()
    {

    }

    public static <T> T notNull(T object)
    {
        if (object == null)
        {
            throw new AssertionException("Object cannot be null.");
        }

        return object;
    }
}
