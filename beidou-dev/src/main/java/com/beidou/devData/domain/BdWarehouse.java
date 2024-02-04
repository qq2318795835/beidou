package com.beidou.devData.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.beidou.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.beidou.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 仓库对象 bd_warehouse
 * 
 * @author beidou
 * @date 2024-01-20
 */
public class BdWarehouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 仓库编码 */
    @Excel(name = "仓库编码")
    private String houseCode;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String houseName;

    /** 仓库地址 */
    @Excel(name = "仓库地址")
    private String houseAddress;

    /** 库房类型：1-高温库， 2-中温库，3-低温库，4-超低温库，5-速冻库，6-气调库温度标准 */
    @Excel(name = "库房类型：1-高温库， 2-中温库，3-低温库，4-超低温库，5-速冻库，6-气调库温度标准")
    private Integer houseType;

    /** 所属公司Id */
    private String companyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 负责人 */
    @Excel(name = "负责人")
    private String principalName;

    /** 负责人电话 */
    @Excel(name = "负责人电话")
    private String principalTel;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal longitude;

    /** 维度 */
    @Excel(name = "维度")
    private BigDecimal latitude;

    /** 库房面积 */
    @Excel(name = "库房面积")
    private Long areaSize;

    /** 状态：1-正常,0-空库 */
    @Excel(name = "状态：1-正常,0-空库")
    private Integer houseStatus;

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
    public void setHouseAddress(String houseAddress) 
    {
        this.houseAddress = houseAddress;
    }

    public String getHouseAddress() 
    {
        return houseAddress;
    }
    public void setHouseType(Integer houseType) 
    {
        this.houseType = houseType;
    }

    public Integer getHouseType() 
    {
        return houseType;
    }
    public void setCompanyId(String companyId) 
    {
        this.companyId = companyId;
    }

    public String getCompanyId() 
    {
        return companyId;
    }
    public void setCompanyName(String companyName) 
    {
        this.companyName = companyName;
    }

    public String getCompanyName() 
    {
        return companyName;
    }
    public void setPrincipalName(String principalName) 
    {
        this.principalName = principalName;
    }

    public String getPrincipalName() 
    {
        return principalName;
    }
    public void setPrincipalTel(String principalTel) 
    {
        this.principalTel = principalTel;
    }

    public String getPrincipalTel() 
    {
        return principalTel;
    }
    public void setLongitude(BigDecimal longitude) 
    {
        this.longitude = longitude;
    }

    public BigDecimal getLongitude() 
    {
        return longitude;
    }
    public void setLatitude(BigDecimal latitude) 
    {
        this.latitude = latitude;
    }

    public BigDecimal getLatitude() 
    {
        return latitude;
    }
    public void setAreaSize(Long areaSize) 
    {
        this.areaSize = areaSize;
    }

    public Long getAreaSize() 
    {
        return areaSize;
    }
    public void setHouseStatus(Integer houseStatus) 
    {
        this.houseStatus = houseStatus;
    }

    public Integer getHouseStatus() 
    {
        return houseStatus;
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
            .append("houseCode", getHouseCode())
            .append("houseName", getHouseName())
            .append("houseAddress", getHouseAddress())
            .append("houseType", getHouseType())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("principalName", getPrincipalName())
            .append("principalTel", getPrincipalTel())
            .append("longitude", getLongitude())
            .append("latitude", getLatitude())
            .append("areaSize", getAreaSize())
            .append("houseStatus", getHouseStatus())
            .append("createdTime", getCreatedTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
