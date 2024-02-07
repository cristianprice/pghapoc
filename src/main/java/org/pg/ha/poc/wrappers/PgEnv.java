package org.pg.ha.poc.wrappers;

/**
 * <p>
 * Constants and env variables.
 * </p>
 *
 * @author cristianp
 */
public class PgEnv
{
    private PgEnv()
    {

    }

    public static final String  PSQL_EXE          = "psql.exe";
    public static final String  PG_BASEBACKUP_EXE = "pg_basebackup.exe";
    public static final String  PG_CTL_EXE        = "pg_ctl.exe";

    private static final String PG_BIN_KEY        = "pg.bin.dir";
    public static final String  PG_BIN            = System.getProperty(PG_BIN_KEY);

}
