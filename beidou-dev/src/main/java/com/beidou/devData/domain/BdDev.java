package com.beidou.devData.domain;

import java.util.Date;

import com.beidou.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.beidou.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备管理对象 bd_dev
 * 
 * @author beidou
 * @date 2024-01-21
 */
public class BdDev extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String devCode;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String devName;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String houseCode;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String houseName;

    /** 设备类型(0-温度传感器,1-湿度传感器) */
    @Excel(name = "设备类型(0-温度传感器,1-湿度传感器)")
    private Integer devType;

    /** 设备状态(1-在用,0-停用,2-异常) */
    @Excel(name = "设备状态(1-在用,0-停用,2-异常)")
    private Integer devStatus;

    /** 指标类型，指标id */
    @Excel(name = "指标类型，指标id")
    private String metricType;

    /** 备注信息 */
    private String remarks;

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
    public void setDevCode(String devCode) 
    {
        this.devCode = devCode;
    }

    public String getDevCode() 
    {
        return devCode;
    }
    public void setDevName(String devName) 
    {
        this.devName = devName;
    }

    public String getDevName() 
    {
        return devName;
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
    public void setDevType(Integer devType) 
    {
        this.devType = devType;
    }

    public Integer getDevType() 
    {
        return devType;
    }
    public void setDevStatus(Integer devStatus) 
    {
        this.devStatus = devStatus;
    }

    public Integer getDevStatus() 
    {
        return devStatus;
    }
    public void setMetricType(String metricType) 
    {
        this.metricType = metricType;
    }

    public String getMetricType() 
    {
        return metricType;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
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
            .append("devCode", getDevCode())
            .append("devName", getDevName())
            .append("houseCode", getHouseCode())
            .append("houseName", getHouseName())
            .append("devType", getDevType())
            .append("devStatus", getDevStatus())
            .append("metricType", getMetricType())
            .append("remarks", getRemarks())
            .append("createdTime", getCreatedTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
