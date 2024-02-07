package org.pg.ha.poc.wrappers;

import static java.util.stream.Collectors.joining;
import static org.pg.ha.poc.validation.Assert.notNull;
import static org.pg.ha.poc.wrappers.PgEnv.PG_BASEBACKUP_EXE;

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
 * PgBaseBackup Wrapper.
 * </p>
 *
 * @author cristianp
 */
public class PgBaseBackup extends PgBaseWrapper
{

    private static Logger logger               = LogManager.getLogger(PgBaseBackup.class);

    private File          destination;
    private boolean       createStreamReplSlot = false;

    private String        slotName;

    public File destination()
    {
        return destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PgBaseBackup withDbName(String dbName)
    {
        return (PgBaseBackup) super.withDbName(dbName);
    }

    @Override
    public PgBaseBackup withHost(String host)
    {
        return (PgBaseBackup) super.withHost(host);
    }

    @Override
    public PgBaseBackup withPassword(String password)
    {
        return (PgBaseBackup) super.withPassword(password);
    }

    @Override
    public PgBaseBackup withPort(Integer port)
    {
        return (PgBaseBackup) super.withPort(port);
    }

    @Override
    public PgBaseBackup withUserName(String userName)
    {
        return (PgBaseBackup) super.withUserName(userName);
    }

    public PgBaseBackup withDestination(File destination)
    {
        this.destination = destination;
        return this;
    }

    public boolean createStreamReplSlot()
    {
        return createStreamReplSlot;
    }

    public PgBaseBackup withCreateStreamReplSlot(boolean createStreamReplSlot, String slotName)
    {
        this.createStreamReplSlot = createStreamReplSlot;
        this.slotName = slotName;
        return this;
    }

    @Override
    public int execute() throws IOException, InterruptedException
    {

        final File bin = new File(new File(PgEnv.PG_BIN), PG_BASEBACKUP_EXE);

        final List<String> args = new ArrayList<>();
        args.add(bin.getAbsolutePath());
        args.add("-v");
        args.add("-P");
        args.add("-R");
        args.add("-D");
        args.add(notNull(destination).getAbsolutePath());

        if (createStreamReplSlot())
        {
            args.add("-X");
            args.add("stream");
            args.add("--create-slot");
            args.add("--slot");
            args.add(notNull(slotName));
        }

        final Map<String, String> env = new HashMap<>();
        env.put("PGHOST", notNull(host));
        env.put("PGPORT", String.valueOf(notNull(port)));
        env.put("PGDATABASE", notNull(dbName));
        env.put("PGUSER", notNull(userName));
        env.put("PGPASSWORD", notNull(password));

        logger.info("Executing command: {}", args.stream().collect(joining(" ")));
        return CmdExecutor.execute(args, destination.getParentFile(), env);
    }

}
