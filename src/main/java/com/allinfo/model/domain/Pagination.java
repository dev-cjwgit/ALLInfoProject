package com.allinfo.model.domain;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Pagination {
    @ApiParam(defaultValue = "1")
    private Integer page = 1;

    @ApiParam(defaultValue = "10")
    private Integer range = 10;

    public Integer getPage() {
        return page = (page - 1) * range;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }
}
