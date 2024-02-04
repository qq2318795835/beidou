package com.beidou.devData.domain;

import java.util.Date;

import com.beidou.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.beidou.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 企业信息对象 bd_company
 * 
 * @author beidou
 * @date 2024-01-18
 */
public class BdCompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private String id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String company;

    /** 公司简称 */
    @Excel(name = "公司简称")
    private String abbreviation;

    /** 企业编号 */
    @Excel(name = "企业编号")
    private String companyNumber;

    /** 企业地址 */
    @Excel(name = "企业地址")
    private String companyAddress;

    /** 公司电话 */
    @Excel(name = "公司电话")
    private String companyPhone;

    /** 第二负责人 */
    @Excel(name = "第二负责人")
    private String managementName;

    /** 第二负责人联系电话 */
    @Excel(name = "第二负责人联系电话")
    private String managementPhone;

    /** 第一负责人姓名 */
    @Excel(name = "第一负责人姓名")
    private String leader;

    /** 第一负责人电话 */
    @Excel(name = "第一负责人电话")
    private String leaderPhone;

    /** 备注 */
    @Excel(name = "备注")
    private String message;

    /** 网站 */
    @Excel(name = "网站")
    private String webAddress;

    /** 企业类型 */
    @Excel(name = "企业类型")
    private String companyType;

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
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setAbbreviation(String abbreviation) 
    {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() 
    {
        return abbreviation;
    }
    public void setCompanyNumber(String companyNumber) 
    {
        this.companyNumber = companyNumber;
    }

    public String getCompanyNumber() 
    {
        return companyNumber;
    }
    public void setCompanyAddress(String companyAddress) 
    {
        this.companyAddress = companyAddress;
    }

    public String getCompanyAddress() 
    {
        return companyAddress;
    }
    public void setCompanyPhone(String companyPhone) 
    {
        this.companyPhone = companyPhone;
    }

    public String getCompanyPhone() 
    {
        return companyPhone;
    }
    public void setManagementName(String managementName) 
    {
        this.managementName = managementName;
    }

    public String getManagementName() 
    {
        return managementName;
    }
    public void setManagementPhone(String managementPhone) 
    {
        this.managementPhone = managementPhone;
    }

    public String getManagementPhone() 
    {
        return managementPhone;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setLeaderPhone(String leaderPhone) 
    {
        this.leaderPhone = leaderPhone;
    }

    public String getLeaderPhone() 
    {
        return leaderPhone;
    }
    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getMessage() 
    {
        return message;
    }
    public void setWebAddress(String webAddress) 
    {
        this.webAddress = webAddress;
    }

    public String getWebAddress() 
    {
        return webAddress;
    }
    public void setCompanyType(String companyType) 
    {
        this.companyType = companyType;
    }

    public String getCompanyType() 
    {
        return companyType;
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
            .append("company", getCompany())
            .append("abbreviation", getAbbreviation())
            .append("companyNumber", getCompanyNumber())
            .append("companyAddress", getCompanyAddress())
            .append("companyPhone", getCompanyPhone())
            .append("managementName", getManagementName())
            .append("managementPhone", getManagementPhone())
            .append("leader", getLeader())
            .append("leaderPhone", getLeaderPhone())
            .append("message", getMessage())
            .append("webAddress", getWebAddress())
            .append("companyType", getCompanyType())
            .append("createdTime", getCreatedTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
