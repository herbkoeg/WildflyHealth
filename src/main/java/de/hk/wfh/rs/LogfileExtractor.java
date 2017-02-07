package de.hk.wfh.rs;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Path("/herbert/")
public class LogfileExtractor {
    private Logger logger;

    public LogfileExtractor() {
        logger = Logger.getLogger("REQUESTID");
    }

    @GET
    @Path("/json")
    @Produces({"application/json"})
    public String getHelloWorldJSON(
            @QueryParam("requestId") String requestId,
            @QueryParam("pattern") String pattern,
            @QueryParam("logfile") String logfile) throws Exception {
//   http://localhost:8080/wildflyhealth/rest/json
//   http://localhost:8080/wildflyhealth/rest/herbert/json?logfile=server.log

        //   String baseDir = System.getProperty("user.dir");

        String retVal = getFileContent(logfile,pattern);

        writeSomeLog();
        return retVal;
    }

    private void writeSomeLog() {

        for (int i = 0; i < 10; i++) {
            logger.info("hallo " + i);
        }
    }


    private String getFileContent(String filename, String pattern) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        FileReader fr = null;

        String logDir = System.getProperty("user.dir") + "/../standalone/log/";

        //String filename = "/Users/palmherby/Entwickeln/jBoss/wildfly-10.1.0.Final/standalone/log/server.log";
//        http://localhost:8080/wildflyhealth/rest/herbert/json?logfile=server.log&pattern=org.jboss

        String fileNameAbsPath = logDir + filename;

        System.out.println(fileNameAbsPath);

        fr = new FileReader(fileNameAbsPath);
        File file = new File(filename);
        System.err.println("filesize: " + file.length());

        String sCurrentLine;
        br = new BufferedReader(new FileReader(fileNameAbsPath));

        while ((sCurrentLine = br.readLine()) != null) {
            if(pattern != null) {
                if (sCurrentLine.contains(pattern)) {
                    sb.append(sCurrentLine).append("\n");
                }
            }
            else {
                sb.append(sCurrentLine).append("\n");
            }
        }
        return sb.toString();
    }

}
