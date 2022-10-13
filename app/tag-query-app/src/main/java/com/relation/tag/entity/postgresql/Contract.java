package com.relation.tag.entity.postgresql;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("contract")
@ApiModel(value = "Contract对象", description = "contract table")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "unique id")
    private Long id;

    @ApiModelProperty(value = "contract address")
    private String contractAddress;

    @ApiModelProperty(value = "symbol")
    private String symbol;

    @ApiModelProperty(value = "full name")
    private String fullName;

    @ApiModelProperty(value = "decimal")
    private Integer decimals;

    @ApiModelProperty(value = "type ERC20 OTHER")
    private String type;

    @ApiModelProperty(value = "status ACTIVE INACTIVE")
    private String status;

    @ApiModelProperty(value = "creator")
    private String owner;

    private String creator;

    @ApiModelProperty(value = "record create time")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "record update time")
    private LocalDateTime updatedAt;

    @ApiModelProperty(value = "record delete")
    private Boolean removed;


}
