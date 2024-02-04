package com.beidou.devData.service.impl;

import java.util.List;
import com.beidou.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beidou.devData.mapper.BdDevMapper;
import com.beidou.devData.domain.BdDev;
import com.beidou.devData.service.IBdDevService;

/**
 * 设备管理Service业务层处理
 * 
 * @author beidou
 * @date 2024-01-21
 */
@Service
public class BdDevServiceImpl implements IBdDevService 
{
    @Autowired
    private BdDevMapper bdDevMapper;

    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    @Override
    public BdDev selectBdDevById(String id)
    {
        return bdDevMapper.selectBdDevById(id);
    }

    /**
     * 查询设备管理列表
     * 
     * @param bdDev 设备管理
     * @return 设备管理
     */
    @Override
    public List<BdDev> selectBdDevList(BdDev bdDev)
    {
        return bdDevMapper.selectBdDevList(bdDev);
    }

    /**
     * 新增设备管理
     * 
     * @param bdDev 设备管理
     * @return 结果
     */
    @Override
    public int insertBdDev(BdDev bdDev)
    {
        return bdDevMapper.insertBdDev(bdDev);
    }

    /**
     * 修改设备管理
     * 
     * @param bdDev 设备管理
     * @return 结果
     */
    @Override
    public int updateBdDev(BdDev bdDev)
    {
        bdDev.setUpdateTime(DateUtils.getNowDate());
        return bdDevMapper.updateBdDev(bdDev);
    }

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键
     * @return 结果
     */
    @Override
    public int deleteBdDevByIds(String[] ids)
    {
        return bdDevMapper.deleteBdDevByIds(ids);
    }

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    @Override
    public int deleteBdDevById(String id)
    {
        return bdDevMapper.deleteBdDevById(id);
    }
}
