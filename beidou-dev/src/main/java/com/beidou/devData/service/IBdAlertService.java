package com.beidou.devData.service;

import java.util.List;
import com.beidou.devData.domain.BdAlert;

/**
 * 告警信息Service接口
 * 
 * @author beidou
 * @date 2024-01-21
 */
public interface IBdAlertService 
{
    /**
     * 查询告警信息
     * 
     * @param id 告警信息主键
     * @return 告警信息
     */
    public BdAlert selectBdAlertById(String id);

    /**
     * 查询告警信息列表
     * 
     * @param bdAlert 告警信息
     * @return 告警信息集合
     */
    public List<BdAlert> selectBdAlertList(BdAlert bdAlert);

    /**
     * 新增告警信息
     * 
     * @param bdAlert 告警信息
     * @return 结果
     */
    public int insertBdAlert(BdAlert bdAlert);

    /**
     * 修改告警信息
     * 
     * @param bdAlert 告警信息
     * @return 结果
     */
    public int updateBdAlert(BdAlert bdAlert);

    /**
     * 批量删除告警信息
     * 
     * @param ids 需要删除的告警信息主键集合
     * @return 结果
     */
    public int deleteBdAlertByIds(String[] ids);

    /**
     * 删除告警信息信息
     * 
     * @param id 告警信息主键
     * @return 结果
     */
    public int deleteBdAlertById(String id);
}
