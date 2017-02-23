package de.hk.wfh.rs;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Path("/")
public class LogfileExtractor {
    private Logger logger;

    public LogfileExtractor() {
        logger = Logger.getLogger("REQUESTID");
    }

    @GET
    @Path("/")
    @Produces({"application/json"})
    public String getFilteredFile(
            @QueryParam("startId") String startId,
            @QueryParam("endId") String endId,
            @QueryParam("filterJsonList") String filterList,
            @QueryParam("ignoreJsonList") String ignoreList,
            @QueryParam("logfile") String logfile) throws Exception {
/**
 * Examples:
    http://localhost:8080/wildflyhealth/?logfile=server.log&startId=123&endId=456&filterJsonList=["Unregistered"]&startId=06:41:20,457&endId=06:41:22
    http://localhost:8080/wildflyhealth/?logfile=server.log&startId=06:41:20,457&endId=06:41:22
    http://localhost:8080/wildflyhealth/?logfile=server.log&startId=06:41:20,457&endId=06:41:22&filterJsonList=["Unregistered"]
**/
        try {
            FilterContext filterContext = createFilterContext(filterList, ignoreList, startId, endId);
            return getFilteredContent(logfile, filterContext);
        } catch (IllegalArgumentException ex) {
            return ex.getMessage();
        }
    }

    private void writeSomeLog() {
        for (int i = 0; i < 10; i++) {
            logger.info("hallo " + i);
        }
    }

    String getFilteredContent(String filename, FilterContext filterContext) throws IOException {
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        FileReader fr = null;
        boolean startIdFound = false;
        boolean endIdFound = false;

        String logDir = System.getProperty("user.dir") + "/../standalone/log/";
        String fileNameAbsPath = logDir + filename;

        fr = new FileReader(fileNameAbsPath);
        File file = new File(filename);

        String sCurrentLine;
        br = new BufferedReader(new FileReader(fileNameAbsPath));

        while ((sCurrentLine = br.readLine()) != null) {
            if (sCurrentLine.contains(filterContext.getStartId())) {
                startIdFound = true;
            }
            if (sCurrentLine.contains(filterContext.getEndId())) {
                endIdFound = true;
            }
            if(startIdFound) {
                sb.append(add(filterContext, sCurrentLine));
            }
            if (startIdFound && endIdFound) {
                break;
            }
        }
        return sb.toString();
    }

    boolean containsPattern(String line, List<String> patternList) {
        if (patternList == null) return false;
        for (String pattern : patternList) {
            if (line.contains(pattern)) {
                return true;
            }
        }
        return false;
    }

    FilterContext createFilterContext(String filterJsonList, String ignoreJsonList, String startId, String endId)
            throws IOException, IllegalArgumentException {
        FilterContext filterContext = new FilterContext();
        ObjectMapper mapper = new ObjectMapper();
        if (filterJsonList != null) {
            filterContext.setFilterList(mapper.readValue(filterJsonList, List.class));
        }
        if (ignoreJsonList != null) {
            filterContext.setIgnoreList(mapper.readValue(ignoreJsonList, List.class));
        }
        filterContext.setEndId(endId);
        filterContext.setStartId(startId);

        if (filterContext.getFilterList().size() > 0 && filterContext.getIgnoreList().size() > 0) {
            throw new IllegalArgumentException("Filter and Ignore Parameter not possible");
        }

        return filterContext;
    }

    String add(FilterContext filterContext, String line) {
        boolean containsFilter = containsPattern(line, filterContext.getFilterList());
        boolean containsIgnore = containsPattern(line, filterContext.getIgnoreList());

        if ( noFilterSet(filterContext)) {
            return line + "\n";
        }

        if ( (filterContext.getFilterList().size() > 0) && containsFilter) {
                return line + "\n";
        }
        if ( (filterContext.getIgnoreList().size() > 0) && !containsIgnore) {
                return line + "\n";
        }

        return "";
    }

    boolean noFilterSet(FilterContext filterContext){
        return filterContext.getFilterList().size() == 0 &&
                filterContext.getIgnoreList().size() == 0;
    }
}
