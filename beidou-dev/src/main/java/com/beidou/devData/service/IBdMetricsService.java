package com.beidou.devData.service;

import java.util.List;
import com.beidou.devData.domain.BdMetrics;

/**
 * 指标管理Service接口
 * 
 * @author beidou
 * @date 2024-01-21
 */
public interface IBdMetricsService 
{
    /**
     * 查询指标管理
     * 
     * @param id 指标管理主键
     * @return 指标管理
     */
    public BdMetrics selectBdMetricsById(String id);

    /**
     * 查询指标管理列表
     * 
     * @param bdMetrics 指标管理
     * @return 指标管理集合
     */
    public List<BdMetrics> selectBdMetricsList(BdMetrics bdMetrics);

    /**
     * 新增指标管理
     * 
     * @param bdMetrics 指标管理
     * @return 结果
     */
    public int insertBdMetrics(BdMetrics bdMetrics);

    /**
     * 修改指标管理
     * 
     * @param bdMetrics 指标管理
     * @return 结果
     */
    public int updateBdMetrics(BdMetrics bdMetrics);

    /**
     * 批量删除指标管理
     * 
     * @param ids 需要删除的指标管理主键集合
     * @return 结果
     */
    public int deleteBdMetricsByIds(String[] ids);

    /**
     * 删除指标管理信息
     * 
     * @param id 指标管理主键
     * @return 结果
     */
    public int deleteBdMetricsById(String id);
}
