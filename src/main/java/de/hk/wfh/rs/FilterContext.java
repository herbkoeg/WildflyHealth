package de.hk.wfh.rs;

import java.util.ArrayList;
import java.util.List;

public class FilterContext {
    private List<String> filterList;
    private List<String> ignoreList;
    private String startId;
    private String endId;
    private String generated;

    public List<String> getFilterList() {
        if(filterList != null)
            return filterList;
        return new ArrayList<String>();
    }

    public void setFilterList(List<String> filterList) {
        this.filterList = filterList;
    }

    public List<String> getIgnoreList() {

        if(ignoreList != null)
            return ignoreList;
        return new ArrayList<String>();
    }

    public void setIgnoreList(List<String> ignoreList) {
        this.ignoreList = ignoreList;
    }

    public String getStartId() {
        return startId;
    }

    public void setStartId(String startId) {
        this.startId = startId;
    }

    public String getEndId() {
        return endId;
    }

    public void setEndId(String endId) {
        this.endId = endId;
    }

    @Override
    public String toString() {
        return "FilterContext{" +
                "filterList=" + filterList +
                ", ignoreList=" + ignoreList +
                ", startId='" + startId + '\'' +
                ", endId='" + endId + '\'' +
                '}';
    }
}
