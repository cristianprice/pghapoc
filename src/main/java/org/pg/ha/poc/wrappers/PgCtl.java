package org.pg.ha.poc.wrappers;

import static java.lang.String.format;
import static org.pg.ha.poc.validation.Assert.notNull;
import static org.pg.ha.poc.wrappers.PgEnv.PG_CTL_EXE;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.pg.ha.poc.validation.NotImplemented;

/**
 * <p>
 * Postgres service controller.
 * </p>
 *
 * @author cristianp
 */
public class PgCtl extends PgBaseWrapper
{

    public static final String PROMOTE = "promote";
    public static final String STOP    = "stop";
    public static final String START   = "start";

    private String             serviceName;
    private String             startType;
    private String             command;
    private File               dataDir;

    public String startType()
    {
        return startType;
    }

    public PgCtl withStartType(String startType)
    {
        this.startType = startType;
        return this;
    }

    public String serviceName()
    {
        return serviceName;
    }

    public PgCtl withServiceName(String serviceName)
    {
        this.serviceName = serviceName;
        return this;
    }

    public String command()
    {
        return command;
    }

    public PgCtl withCommand(String command)
    {
        this.command = command;
        return this;
    }

    @Override
    public PgCtl withDbName(String dbName)
    {
        throw new NotImplemented("No need for db.");
    }

    @Override
    public PgCtl withHost(String host)
    {
        throw new NotImplemented("No need for host.");
    }

    @Override
    public PgCtl withPassword(String password)
    {
        return (PgCtl) super.withPassword(password);
    }

    @Override
    public PgCtl withPort(Integer port)
    {
        return (PgCtl) super.withPort(port);
    }

    @Override
    public PgCtl withUserName(String userName)
    {
        return (PgCtl) super.withUserName(userName);
    }

    public File dataDir()
    {
        return dataDir;
    }

    public PgCtl withDataDir(File dataDir)
    {
        this.dataDir = dataDir;
        return this;
    }

    @Override
    public int execute() throws IOException, InterruptedException
    {

        final File bin = new File(new File(PgEnv.PG_BIN), PG_CTL_EXE);

        switch (command())
        {
            case START:
                return startOp(bin);
            case STOP:
                return stopOp(bin);
            case "status":
                return -1;
            case PROMOTE:
                return promoteOp(bin);
            case "register":
            case "unregister":
                return 0;
            default:
                throw new NotImplemented(format("Command: %s not implemented.", command()));
        }
    }

    private int promoteOp(File bin) throws IOException, InterruptedException
    {
        final List<String> args = new ArrayList<>();
        args.add(bin.getAbsolutePath());
        args.add(PROMOTE);
        args.add("-D");
        args.add(notNull(dataDir).getAbsolutePath());

        return CmdExecutor.execute(args, bin.getParentFile(), new HashMap<String, String>());
    }

    private int stopOp(File bin) throws IOException, InterruptedException
    {
        final List<String> args = new ArrayList<>();
        args.add(bin.getAbsolutePath());
        args.add(STOP);
        args.add("-D");
        args.add(notNull(dataDir).getAbsolutePath());

        return CmdExecutor.execute(args, bin.getParentFile(), new HashMap<String, String>());
    }

    private int startOp(File bin) throws IOException, InterruptedException
    {
        final List<String> args = new ArrayList<>();
        args.add(bin.getAbsolutePath());
        args.add("-o");
        args.add(format("\" -p %s\"", notNull(port())));
        args.add(START);
        args.add("-D");
        args.add(notNull(dataDir).getAbsolutePath());

        return CmdExecutor.execute(args, bin.getParentFile(), new HashMap<String, String>());
    }

}
