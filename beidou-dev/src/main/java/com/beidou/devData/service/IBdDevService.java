package com.beidou.devData.service;

import java.util.List;
import com.beidou.devData.domain.BdDev;

/**
 * 设备管理Service接口
 * 
 * @author beidou
 * @date 2024-01-21
 */
public interface IBdDevService 
{
    /**
     * 查询设备管理
     * 
     * @param id 设备管理主键
     * @return 设备管理
     */
    public BdDev selectBdDevById(String id);

    /**
     * 查询设备管理列表
     * 
     * @param bdDev 设备管理
     * @return 设备管理集合
     */
    public List<BdDev> selectBdDevList(BdDev bdDev);

    /**
     * 新增设备管理
     * 
     * @param bdDev 设备管理
     * @return 结果
     */
    public int insertBdDev(BdDev bdDev);

    /**
     * 修改设备管理
     * 
     * @param bdDev 设备管理
     * @return 结果
     */
    public int updateBdDev(BdDev bdDev);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的设备管理主键集合
     * @return 结果
     */
    public int deleteBdDevByIds(String[] ids);

    /**
     * 删除设备管理信息
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    public int deleteBdDevById(String id);
}
