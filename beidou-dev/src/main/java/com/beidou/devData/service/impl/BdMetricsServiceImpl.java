package com.beidou.devData.service.impl;

import java.util.List;

import com.beidou.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beidou.devData.mapper.BdMetricsMapper;
import com.beidou.devData.domain.BdMetrics;
import com.beidou.devData.service.IBdMetricsService;

/**
 * 指标管理Service业务层处理
 * 
 * @author beidou
 * @date 2024-01-21
 */
@Service
public class BdMetricsServiceImpl implements IBdMetricsService 
{
    @Autowired
    private BdMetricsMapper bdMetricsMapper;

    /**
     * 查询指标管理
     * 
     * @param id 指标管理主键
     * @return 指标管理
     */
    @Override
    public BdMetrics selectBdMetricsById(String id)
    {
        return bdMetricsMapper.selectBdMetricsById(id);
    }

    /**
     * 查询指标管理列表
     * 
     * @param bdMetrics 指标管理
     * @return 指标管理
     */
    @Override
    public List<BdMetrics> selectBdMetricsList(BdMetrics bdMetrics)
    {
        return bdMetricsMapper.selectBdMetricsList(bdMetrics);
    }

    /**
     * 新增指标管理
     * 
     * @param bdMetrics 指标管理
     * @return 结果
     */
    @Override
    public int insertBdMetrics(BdMetrics bdMetrics)
    {
        bdMetrics.setId(IdUtils.fastSimpleUUID());
        bdMetrics.setIsDelete("0");
        return bdMetricsMapper.insertBdMetrics(bdMetrics);
    }

    /**
     * 修改指标管理
     * 
     * @param bdMetrics 指标管理
     * @return 结果
     */
    @Override
    public int updateBdMetrics(BdMetrics bdMetrics)
    {
        return bdMetricsMapper.updateBdMetrics(bdMetrics);
    }

    /**
     * 批量删除指标管理
     * 
     * @param ids 需要删除的指标管理主键
     * @return 结果
     */
    @Override
    public int deleteBdMetricsByIds(String[] ids)
    {
        return bdMetricsMapper.deleteBdMetricsByIds(ids);
    }

    /**
     * 删除指标管理信息
     * 
     * @param id 指标管理主键
     * @return 结果
     */
    @Override
    public int deleteBdMetricsById(String id)
    {
        return bdMetricsMapper.deleteBdMetricsById(id);
    }
}
