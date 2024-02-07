package org.pg.ha.poc;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pg.ha.poc.wrappers.PgBaseBackup;
import org.pg.ha.poc.wrappers.PgCtl;

/**
 * Hello world!
 *
 */
public class Main
{

    public static final File   DESTINATION = new File("c:\\tmp\\data");
    public static final String USER_PASS   = "postgres";
    private static Logger      logger      = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, InterruptedException
    {
        PgBaseBackup backup = new PgBaseBackup().withDbName(USER_PASS).withDestination(DESTINATION)
                        .withHost("localhost").withUserName(USER_PASS).withPassword(USER_PASS).withPort(5432)
                        .withCreateStreamReplSlot(true, "crazy_slot");
        logger.info(backup.execute());

        PgCtl pgCtl = new PgCtl().withCommand(PgCtl.START).withDataDir(DESTINATION).withPort(5433);
        logger.info(pgCtl.execute());

        // PSql psql = new PSql().withDbName("postgres")
        // .withHost("localhost").withUserName("postgres").withPassword("postgres").withPort(5432)
        // .withCommand("select * from nms_crazy.state_common_resource")
        // .withOutFile(new File("c:\\tmp.dir\\result.csv"));
        // logger.info(psql.execute());
    }
}
