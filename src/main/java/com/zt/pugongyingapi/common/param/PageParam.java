package com.zt.pugongyingapi.common.param;

public class PageParam {

    private Long pageNo = 1l;
    private Long pageSize = 10l;
    private Long startIndex;

    public Long getStartIndex() {
        return startIndex;
    }
    public void setStartIndex(Long startIndex){
        this.startIndex = startIndex;
    }
    public Long getPageNo() {
        return pageNo;
    }
    public void setPageNo(Long pageNo) {
        this.pageNo = pageNo;
    }
    public Long getPageSize() {
        return pageSize;
    }
    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }
}
