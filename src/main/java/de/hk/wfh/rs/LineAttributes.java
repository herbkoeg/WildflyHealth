package de.hk.wfh.rs;

import java.util.List;

public class LineAttributes {
    private List<String> filterList;
    private List<String> ingoreList;
    private String startId;
    private String endId;

    public List<String> getFilterList() {
        return filterList;
    }

    public void setFilterList(List<String> filterList) {
        this.filterList = filterList;
    }

    public List<String> getIngoreList() {
        return ingoreList;
    }

    public void setIngoreList(List<String> ingoreList) {
        this.ingoreList = ingoreList;
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
}
