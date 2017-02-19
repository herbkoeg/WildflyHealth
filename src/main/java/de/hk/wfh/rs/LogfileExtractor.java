package de.hk.wfh.rs;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.security.auth.Subject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
            @QueryParam("startId") String startid,
            @QueryParam("endId") String endId,
            @QueryParam("filterJsonList") String filterList,
            @QueryParam("ignoreJsonList") String ignoreList,
            @QueryParam("logfile") String logfile) throws Exception {
//   http://localhost:8080/wildflyhealth/rest/json
//   http://localhost:8080/wildflyhealth/rest/herbert/json?logfile=server.log

        //   String baseDir = System.getProperty("user.dir");
//        see  http://stackoverflow.com/questions/2602043/rest-api-best-practice-how-to-accept-list-of-parameter-values-as-input

        String retVal = getFileContent(logfile,filterList);

//        System.out.println("ignoreList: " + ignoreList.get(0));

//        writeSomeLog();

        ObjectMapper mapper = new ObjectMapper();
        List<String> myStringList = new ArrayList<>();
        myStringList.add("herbert");
        myStringList.add("koegi");

        SomePojo somePojo = new SomePojo();
        System.out.println(mapper.writeValueAsString(myStringList));

        // ["herbert","koegi"]

        return retVal;
    }

    private void writeSomeLog() {

        for (int i = 0; i < 10; i++) {
            logger.info("hallo " + i);
        }
    }

     String getFileContent(String filename, String pattern) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        FileReader fr = null;
        boolean startIdFound = false;
        boolean endIdFound = false;

        String logDir = System.getProperty("user.dir") + "/../standalone/log/";

//        http://localhost:8080/wildflyhealth/rest/herbert/json?logfile=server.log&pattern=org.jboss

        String fileNameAbsPath = logDir + filename;

//        System.out.println(fileNameAbsPath);

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

    LineAttributes createLineAttributes(String filterJsonList, String ignoreJsonList, String startId, String endId) throws IOException {
        LineAttributes lineAttributes = new LineAttributes();
        ObjectMapper mapper = new ObjectMapper();
        lineAttributes.setFilterList(mapper.readValue(filterJsonList, List.class));
        lineAttributes.setIngoreList(mapper.readValue(ignoreJsonList, List.class));
        lineAttributes.setEndId(endId);
        lineAttributes.setStartId(startId);

        return lineAttributes;
    }

    String add(List<String> ignoreList, List<String> filterList, String line) {



        return null;
    }
}
