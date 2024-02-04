package com.beidou.devData.domain;

import com.beidou.common.annotation.Excel;
import com.beidou.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 指标管理对象 bd_metrics
 * 
 * @author beidou
 * @date 2024-01-21
 */
public class BdMetrics extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private String id;

    /**
     * 指标编码
     */
    @Excel(name = "指标编码")
    private String mCode;

    /** 指标名称 */
    @Excel(name = "指标名称")
    private String mName;

    /** 温度上限 */
    @Excel(name = "温度上限")
    private String maxTem;

    /** 温度下限 */
    @Excel(name = "温度下限")
    private String minTem;

    /** 湿度上线 */
    @Excel(name = "湿度上线")
    private String maxHum;

    /** 湿度下线 */
    @Excel(name = "湿度下线")
    private String minHum;

    /** 通风次数 */
    @Excel(name = "通风次数")
    private String ventTimes;

    /** 是否删除，0否1是 */
    private String isDelete;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getMaxTem() {
        return maxTem;
    }

    public void setMaxTem(String maxTem) {
        this.maxTem = maxTem;
    }

    public String getMinTem() {
        return minTem;
    }

    public void setMinTem(String minTem) {
        this.minTem = minTem;
    }

    public String getMaxHum() {
        return maxHum;
    }

    public void setMaxHum(String maxHum) {
        this.maxHum = maxHum;
    }

    public String getMinHum() {
        return minHum;
    }

    public void setMinHum(String minHum) {
        this.minHum = minHum;
    }

    public String getVentTimes() {
        return ventTimes;
    }

    public void setVentTimes(String ventTimes) {
        this.ventTimes = ventTimes;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "BdMetrics{" +
                "id='" + id + '\'' +
                ", mCode='" + mCode + '\'' +
                ", mName='" + mName + '\'' +
                ", maxTem='" + maxTem + '\'' +
                ", minTem='" + minTem + '\'' +
                ", maxHum='" + maxHum + '\'' +
                ", minHum='" + minHum + '\'' +
                ", ventTimes='" + ventTimes + '\'' +
                ", isDelete='" + isDelete + '\'' +
                '}';
    }
}
