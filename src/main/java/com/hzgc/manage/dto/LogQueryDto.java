package com.hzgc.manage.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;

/**
 * 日志列表页参数校验 (JSR303参数校验)
 * created by liang on 2018/11/16
 */
@Data
public class LogQueryDto implements Serializable {

    /**
     * 账号名称
     */
    private String username;

    /**
     * 用户创建时间
     */
    @Field( type = FieldType.Date, format = DateFormat.custom,pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createtime;

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