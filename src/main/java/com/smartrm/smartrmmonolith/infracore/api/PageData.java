package com.smartrm.smartrmmonolith.infracore.api;

import java.util.List;

/**
 * @author: yoda
 * @description: 分页结果
 */
public class PageData<T> {
    
    private int page;
    private int pageSize;
    private int totalPage = 0;
    private int total = 0;
    private List<T> list = null;
    
    public PageData(int pageSize, int page) {
        this.page = page;
        this.pageSize = pageSize;
    }
    
    public void withList(List<T> list) {
        this.list = list;
    }
    
    public void withTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    public void withTotal(int total) {
        this.total = total;
    }
    
}
