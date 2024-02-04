package com.beidou.devData.domain;

import java.util.Date;

import com.beidou.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.beidou.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 仓库区域对象 bd_warehous_area
 * 
 * @author beidou
 * @date 2024-01-21
 */
public class BdWarehousArea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 区域编码 */
    @Excel(name = "区域编码")
    private String areaCode;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String houseCode;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String houseName;

    /** 区域类型：1-货架，0-设备区 */
    @Excel(name = "区域类型：1-货架，0-设备区")
    private Integer areaType;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /** 是否删除，0否1是 */
    private String isDelete;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setAreaCode(String areaCode) 
    {
        this.areaCode = areaCode;
    }

    public String getAreaCode() 
    {
        return areaCode;
    }
    public void setHouseCode(String houseCode) 
    {
        this.houseCode = houseCode;
    }

    public String getHouseCode() 
    {
        return houseCode;
    }
    public void setHouseName(String houseName) 
    {
        this.houseName = houseName;
    }

    public String getHouseName() 
    {
        return houseName;
    }
    public void setAreaType(Integer areaType) 
    {
        this.areaType = areaType;
    }

    public Integer getAreaType() 
    {
        return areaType;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setIsDelete(String isDelete) 
    {
        this.isDelete = isDelete;
    }

    public String getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("areaCode", getAreaCode())
            .append("houseCode", getHouseCode())
            .append("houseName", getHouseName())
            .append("areaType", getAreaType())
            .append("createdTime", getCreatedTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
