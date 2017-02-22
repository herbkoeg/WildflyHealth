package de.hk.wfh.rs;

//import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Created by palmherby on 19.02.17.
 */
public class LogfileExtractorTest {
    private LogfileExtractor cut ;


    @Before
    public void setUp() throws Exception {
        cut = new LogfileExtractor();
    }

    @Ignore
    @Test
    public void createFilterContext() throws Exception {

/**
        FilterContext filterContext = cut.createFilterContext(getJsonStringList("[bla,paul]", "fasl"), null, "anfang", "ende");

        Assert.assertEquals("ende",filterContext.getEndId());
        Assert.assertEquals("anfang",filterContext.getStartId());

        Assert.assertEquals(2,filterContext.getFilterList().size());
        Assert.assertEquals(2,filterContext.getIgnoreList().size());
**/
        // hamcrest
//        Assert.assertEquals("[bla, fasl]",lineAttributes.getFilterList());
//        Assert.assertThat(listUnderTest,
//                IsIterableContainingInOrder.contains(expectedList.toArray()));

    }


    @Test
    public void add() throws Exception {

    }

    @Test
    public void internalTest() throws IOException {
//        Set<List> properties = fromJSON(new TypeReference<Set<List>>() {}, json);

        ObjectMapper mapper = new ObjectMapper();
//        mapper.readValue(new TypeReference<Set<List>>() {},"[bla;fasl]");
//        String[] asArray = mapper.readValue("[bla,fasl]", String[].class);
        mapper.readValue("[\"bla\",\"fasl\"]", List.class);



    }



}