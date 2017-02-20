package de.hk.wfh.rs;

//import junit.framework.Assert;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by palmherby on 19.02.17.
 */
public class LogfileExtractorTest {
    private LogfileExtractor cut ;


    @Before
    public void setUp() throws Exception {
        cut = new LogfileExtractor();
    }

    @Test
    public void createLineAttributes() throws Exception {


        FilterContext lineAttributes = cut.createFilterContext(getJsonStringList("bla", "fasl"), getJsonStringList("bli", "blub"), "anfang", "ende");

        Assert.assertEquals("ende",lineAttributes.getEndId());
        Assert.assertEquals("anfang",lineAttributes.getStartId());

        // hamcrest
//        Assert.assertEquals("[bla, fasl]",lineAttributes.getFilterList());
//        Assert.assertThat(listUnderTest,
//                IsIterableContainingInOrder.contains(expectedList.toArray()));


    }

    @Test
    public void add() throws Exception {

    }

    private String getJsonStringList(String value1, String value2) throws IOException {
        List<String> myStringList = new ArrayList<>();

        myStringList.add(value1);
        myStringList.add(value2);

        ObjectMapper mapper = new ObjectMapper();

        SomePojo somePojo = new SomePojo();
        return mapper.writeValueAsString(myStringList);
    }


}