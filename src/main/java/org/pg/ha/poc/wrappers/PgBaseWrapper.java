package org.pg.ha.poc.wrappers;

import java.io.IOException;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author cristianp
 */
public abstract class PgBaseWrapper
{
    protected String  dbName;
    protected String  host;
    protected Integer port;
    protected String  userName;
    protected String  password;

    public String getDbName()
    {
        return dbName;
    }

    public PgBaseWrapper withDbName(String dbName)
    {
        this.dbName = dbName;
        return this;
    }

    public String host()
    {
        return host;
    }

    public PgBaseWrapper withHost(String host)
    {
        this.host = host;
        return this;
    }

    public Integer port()
    {
        return port;
    }

    public PgBaseWrapper withPort(Integer port)
    {
        this.port = port;
        return this;
    }

    public String userName()
    {
        return userName;
    }

    public PgBaseWrapper withUserName(String userName)
    {
        this.userName = userName;
        return this;
    }

    public String password()
    {
        return password;
    }

    public PgBaseWrapper withPassword(String password)
    {
        this.password = password;
        return this;
    }

    public abstract int execute() throws IOException, InterruptedException;
}
