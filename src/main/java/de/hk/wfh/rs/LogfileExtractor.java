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

@Path("/")
public class LogfileExtractor {
    private Logger logger;

    public LogfileExtractor() {
        logger = Logger.getLogger("REQUESTID");
    }

    @GET
    @Path("/json")
    @Produces({ "application/json" })
    public String getHelloWorldJSON() {
//        http://localhost:8080/wildflyhealth/rest/json
        return "hallo";
    }

    private void writeSomeLog() {

        for (int i= 0 ; i< 10; i++) {
            logger.info("hallo " + i);
        }
    }


    private String getFileContent(String filename) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        FileReader fr = null;
        //String filename = "/Users/palmherby/Entwickeln/jBoss/wildfly-10.1.0.Final/standalone/log/server.log";

        fr = new FileReader(filename);
        br = new BufferedReader(fr);

        File file = new File(filename);
        System.err.println("filesize: " + file.length() );


        String sCurrentLine;
        br = new BufferedReader(new FileReader(filename));

        while ((sCurrentLine = br.readLine()) != null) {
            //        if(sCurrentLine.contains("org.jboss.as.server.deployment"))
            sb.append(sCurrentLine).append("\n");
        }
        return sb.toString();
    }

}
