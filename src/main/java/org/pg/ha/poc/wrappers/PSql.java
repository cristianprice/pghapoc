package org.pg.ha.poc.wrappers;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.pg.ha.poc.validation.Assert.notNull;
import static org.pg.ha.poc.wrappers.PgEnv.PSQL_EXE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * Send commands to server.
 * </p>
 *
 * @author cristianp
 */
public class PSql extends PgBaseWrapper
{

    private static Logger logger = LogManager.getLogger(PSql.class);

    private String        command;
    private File          outFile;

    public String command()
    {
        return command;
    }

    public PSql withCommand(String command)
    {
        this.command = command;
        return this;
    }

    @Override
    public PSql withDbName(String dbName)
    {
        return (PSql) super.withDbName(dbName);
    }

    @Override
    public PSql withHost(String host)
    {
        return (PSql) super.withHost(host);
    }

    @Override
    public PSql withPassword(String password)
    {
        return (PSql) super.withPassword(password);
    }

    @Override
    public PSql withPort(Integer port)
    {
        return (PSql) super.withPort(port);
    }

    @Override
    public PSql withUserName(String userName)
    {
        return (PSql) super.withUserName(userName);
    }

    public File outFile()
    {
        return outFile;
    }

    public PSql withOutFile(File outFile)
    {
        this.outFile = outFile;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int execute() throws IOException, InterruptedException
    {

        final File bin = new File(new File(PgEnv.PG_BIN), PSQL_EXE);

        final List<String> args = new ArrayList<>();
        args.add(bin.getAbsolutePath());
        args.add("--csv");
        args.add("-c");
        args.add(format("\"%s\"", notNull(command())));

        final Map<String, String> env = new HashMap<>();
        env.put("PGHOST", notNull(host));
        env.put("PGPORT", String.valueOf(notNull(port)));
        env.put("PGDATABASE", notNull(dbName));
        env.put("PGUSER", notNull(userName));
        env.put("PGPASSWORD", notNull(password));

        logger.info("Executing command: {}", args.stream().collect(joining(" ")));
        return CmdExecutor.execute(args, bin.getParentFile(), env, outFile);
    }

}
