package com.relation.tag.entity.postgresql;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("user_info")
@ApiModel(value="UserInfo对象", description="")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String address;

    private String name;

    private String introduction;

    private String twitter;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean removed;


}
