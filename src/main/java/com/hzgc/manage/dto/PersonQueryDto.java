package com.hzgc.manage.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 人口实体对象 (JSR303参数校验)
 * created by liang on 2018/11/16
 */
@Data
public class PersonQueryDto implements Serializable {

    /**
     * 身份证（sfz）
     */
    private String sfz;

    /**
     * 姓名（xm）
     */
    private String xm;

    /**
     * 当前页
     */
    @NotEmpty(message = "页码不能为空")
    private int page;

    /**
     * 每页大小
     */
    @NotEmpty(message = "每页大小不能为空")
    private int size;
}
