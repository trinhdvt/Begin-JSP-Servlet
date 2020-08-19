package com.test.paging;

public interface Pageable {
    Integer getCurrentPage();

    Integer getOffset();

    Integer getLimit();

    Sorter getSorter();
}
