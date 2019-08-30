package com.zt.pugongyingapi.common.result;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageResult extends Result {
    private Long pageNo;
    private Long pageSize;
    private Integer totalNum;
}
