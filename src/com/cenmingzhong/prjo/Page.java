package com.cenmingzhong.prjo;

import java.util.List;

/**
 * page是分页的模型对象
 * @param <T> 是具体的模块的javaBean类
 */
public class Page<T> {

    public static final Integer PAGE_SIZE = 4;

    //当前页码
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //当前页显示数量
    private Integer pageSize= PAGE_SIZE;
    //总记录数
    private Integer pageTotalCout;
    //当前页数据
    private List<T> items;
    //分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static Integer getPageSize() {
        return PAGE_SIZE;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageTotalCout() {
        return pageTotalCout;
    }

    public void setPageTotalCout(Integer pageTotalCout) {
        this.pageTotalCout = pageTotalCout;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", pageSize=" + pageSize +
                ", pageTotalCout=" + pageTotalCout +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
