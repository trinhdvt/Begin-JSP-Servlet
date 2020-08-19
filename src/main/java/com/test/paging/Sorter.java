package com.test.paging;

public class Sorter {
    private final String sortOrder;
    private final String sortBy;

    public Sorter(String sortOrder, String sortBy) {
        this.sortOrder = sortOrder;
        this.sortBy = sortBy;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public String getSortBy() {
        return sortBy;
    }

}
