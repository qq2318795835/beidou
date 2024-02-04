package com.beidou.devData.domain;

import com.beidou.common.annotation.Excel;
import com.beidou.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 告警信息对象 bd_alert
 * 
 * @author beidou
 * @date 2024-01-21
 */
public class BdAlert extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
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

    /** 公司Id */
    @Excel(name = "公司Id")
    private String companyId;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyName;

    /** 温度 */
    @Excel(name = "温度")
    private Integer tem;

    /** 湿度 */
    @Excel(name = "湿度")
    private Integer hum;

    /** 温度状况： 1：高温， 0： */
    @Excel(name = "温度状况： 1：高温， 0：")
    private Integer temAlert;

    /** 湿度状况： 1：高湿， 0： */
    @Excel(name = "湿度状况： 1：高湿， 0：")
    private Integer humAlert;

    /** 提交时间 */
    @Excel(name = "提交时间")
    private String curtime;

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
    public void setTem(Integer tem) 
    {
        this.tem = tem;
    }

    public Integer getTem() 
    {
        return tem;
    }
    public void setHum(Integer hum) 
    {
        this.hum = hum;
    }

    public Integer getHum() 
    {
        return hum;
    }
    public void setTemAlert(Integer temAlert) 
    {
        this.temAlert = temAlert;
    }

    public Integer getTemAlert() 
    {
        return temAlert;
    }
    public void setHumAlert(Integer humAlert) 
    {
        this.humAlert = humAlert;
    }

    public Integer getHumAlert() 
    {
        return humAlert;
    }
    public void setCurtime(String curtime) 
    {
        this.curtime = curtime;
    }

    public String getCurtime() 
    {
        return curtime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("devCode", getDevCode())
            .append("devName", getDevName())
            .append("houseCode", getHouseCode())
            .append("houseName", getHouseName())
            .append("companyId", getCompanyId())
            .append("companyName", getCompanyName())
            .append("tem", getTem())
            .append("hum", getHum())
            .append("temAlert", getTemAlert())
            .append("humAlert", getHumAlert())
            .append("curtime", getCurtime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
