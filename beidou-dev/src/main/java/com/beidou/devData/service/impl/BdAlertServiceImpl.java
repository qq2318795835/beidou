package com.beidou.devData.service.impl;

import java.util.List;
import com.beidou.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beidou.devData.mapper.BdAlertMapper;
import com.beidou.devData.domain.BdAlert;
import com.beidou.devData.service.IBdAlertService;

/**
 * 告警信息Service业务层处理
 * 
 * @author beidou
 * @date 2024-01-21
 */
@Service
public class BdAlertServiceImpl implements IBdAlertService 
{
    @Autowired
    private BdAlertMapper bdAlertMapper;

    /**
     * 查询告警信息
     * 
     * @param id 告警信息主键
     * @return 告警信息
     */
    @Override
    public BdAlert selectBdAlertById(String id)
    {
        return bdAlertMapper.selectBdAlertById(id);
    }

    /**
     * 查询告警信息列表
     * 
     * @param bdAlert 告警信息
     * @return 告警信息
     */
    @Override
    public List<BdAlert> selectBdAlertList(BdAlert bdAlert)
    {
        return bdAlertMapper.selectBdAlertList(bdAlert);
    }

    /**
     * 新增告警信息
     * 
     * @param bdAlert 告警信息
     * @return 结果
     */
    @Override
    public int insertBdAlert(BdAlert bdAlert)
    {
        bdAlert.setCreateTime(DateUtils.getNowDate());
        return bdAlertMapper.insertBdAlert(bdAlert);
    }

    /**
     * 修改告警信息
     * 
     * @param bdAlert 告警信息
     * @return 结果
     */
    @Override
    public int updateBdAlert(BdAlert bdAlert)
    {
        return bdAlertMapper.updateBdAlert(bdAlert);
    }

    /**
     * 批量删除告警信息
     * 
     * @param ids 需要删除的告警信息主键
     * @return 结果
     */
    @Override
    public int deleteBdAlertByIds(String[] ids)
    {
        return bdAlertMapper.deleteBdAlertByIds(ids);
    }

    /**
     * 删除告警信息信息
     * 
     * @param id 告警信息主键
     * @return 结果
     */
    @Override
    public int deleteBdAlertById(String id)
    {
        return bdAlertMapper.deleteBdAlertById(id);
    }
}
