package com.aditya.collpolltest.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;


public class ModelDataComment implements Serializable {

    @SerializedName("data")
    @Expose
    private List<DatumComment> data = null;
    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;


    /**
     * No args constructor for use in serialization
     */
    public ModelDataComment() {
    }

    public ModelDataComment(List<DatumComment> data, Integer total, Integer page, Integer limit, Integer offset) {
        super();
        this.data = data;
        this.total = total;
        this.page = page;
        this.limit = limit;
        this.offset = offset;
    }

    public List<DatumComment> getData() {
        return data;
    }

    public void setData(List<DatumComment> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

}

