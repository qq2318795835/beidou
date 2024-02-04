package com.beidou.devData.mapper;

import java.util.List;
import com.beidou.devData.domain.BdMetrics;
import org.springframework.stereotype.Repository;

/**
 * 指标管理Mapper接口
 * 
 * @author beidou
 * @date 2024-01-21
 */
@Repository
public interface BdMetricsMapper
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
     * 删除指标管理
     * 
     * @param id 指标管理主键
     * @return 结果
     */
    public int deleteBdMetricsById(String id);

    /**
     * 批量删除指标管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBdMetricsByIds(String[] ids);
}
