package com.test.paging;

public class PageRequest implements Pageable {
    private final Integer currentPage;
    private final Integer maxPageItems;
    private final Sorter sorter;

    public PageRequest(Integer currentPage, Integer maxPageItems, Sorter sorter) {
        this.currentPage = currentPage;
        this.maxPageItems = maxPageItems;
        this.sorter = sorter;
    }

    @Override
    public Integer getCurrentPage() {
        return this.currentPage;
    }

    @Override
    public Integer getOffset() {
        if (currentPage != null && maxPageItems != null) {
            return (this.currentPage - 1) * this.maxPageItems;
        }
        return null;
    }

    @Override
    public Integer getLimit() {
        return this.maxPageItems;
    }

    @Override
    public Sorter getSorter() {
        return this.sorter;
    }
}
