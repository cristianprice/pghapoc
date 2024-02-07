package org.pg.ha.poc.wrappers;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * <p>
 * Runs commands.
 * </p>
 *
 * @author cristianp
 */
public class CmdExecutor
{

    private static Logger logger = LogManager.getLogger(CmdExecutor.class);

    private CmdExecutor()
    {

    }

    public static int execute(final Collection<String> params, File workDirectory, Map<String, String> env)
                    throws IOException, InterruptedException
    {
        return execute(params, workDirectory, env, null);
    }

    public static int execute(final Collection<String> params, File workDirectory, Map<String, String> env,
                              File outFile)
                    throws IOException, InterruptedException
    {
        logger.info("Provided out file: {}", outFile);
        ProcessBuilder pb = new ProcessBuilder(params.toArray(new String[params.size()])).inheritIO();
        if (outFile != null)
        {
            pb.redirectOutput(outFile);
        }

        pb.directory(workDirectory);
        pb.environment().putAll(env);

        Process p = pb.start();
        return p.waitFor();
    }
}
