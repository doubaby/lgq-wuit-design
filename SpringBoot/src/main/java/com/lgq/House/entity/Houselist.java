package com.lgq.House.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author testjava
 * @since 2022-10-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Houselist对象", description="")
public class Houselist implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "house_id", type = IdType.AUTO)
    private Integer houseId;

    @ApiModelProperty("房屋所在地址")
    private String address;

    @ApiModelProperty("房屋出租类型")
    private String type;

    @ApiModelProperty("房屋价格")
    private Double price;

    @ApiModelProperty("房屋出租状态")
    private String status;

    @ApiModelProperty("房屋内状况")
    private String detail;

    @ApiModelProperty("房屋所在城市")
    private String city;

    @ApiModelProperty("租户的id")
    private Integer userlistId;

    @ApiModelProperty("房屋所在城市")
    private String userlistName;

    @ApiModelProperty("逻辑删除")
    private Integer isDeleted;

    @ApiModelProperty("房屋的图片")
    private String houseUrl;

    @ApiModelProperty("房屋的实际面积")
    private String roomDetail;

    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

}
