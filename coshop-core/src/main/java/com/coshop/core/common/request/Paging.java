package com.coshop.core.common.request;

import com.alibaba.fastjson.annotation.JSONField;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * @author dixiey
 * @version 1.0
 * @description: 分页
 * @date 2023/11/26 20:47
 */
@ApiModel("分页")
public class Paging<T> implements Serializable {

    private static final long serialVersionUID = -2015870279620981599L;

    @ApiModelProperty("当前页数")
    @JSONField(name = "currentPage")
    @JsonProperty("currentPage")
    private long currentPage = 1;

    @ApiModelProperty("每页多少条数据,默认20")
    @JSONField(name = "pageSize")
    @JsonProperty("pageSize")
    private long pageSize = 20;

    @ApiModelProperty("总行数")
    @JSONField(name = "total")
    @JsonProperty("total")
    private long total = 0;

    @ApiModelProperty("数据列表")
    @JSONField(name = "records")
    @JsonProperty("records")
    private List<T> records = Collections.emptyList();

    public Paging() {
    }

    public Paging(PageInfo page) {
        this.total = page.getTotal();
        this.records = page.getList();
        this.currentPage = page.getPageNum();
        this.pageSize = page.getPageSize();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }


}
