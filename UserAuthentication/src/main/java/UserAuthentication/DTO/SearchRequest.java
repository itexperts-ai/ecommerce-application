package UserAuthentication.DTO;

import java.util.List;

public class SearchRequest {
    private List<FilterRequest> filters;
    private List<SortRequest> sort;
    private int page = 0;
    private int size = 10;

    public List<FilterRequest> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterRequest> filters) {
        this.filters = filters;
    }

    public List<SortRequest> getSort() {
        return sort;
    }

    public void setSort(List<SortRequest> sort) {
        this.sort = sort;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
