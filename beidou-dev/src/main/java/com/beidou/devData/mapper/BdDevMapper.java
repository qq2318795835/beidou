package com.beidou.devData.mapper;

import java.util.List;
import com.beidou.devData.domain.BdDev;
import org.springframework.stereotype.Repository;

/**
 * 设备管理Mapper接口
 * 
 * @author beidou
 * @date 2024-01-21
 */
@Repository
public interface BdDevMapper
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
     * 删除设备管理
     * 
     * @param id 设备管理主键
     * @return 结果
     */
    public int deleteBdDevById(String id);

    /**
     * 批量删除设备管理
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBdDevByIds(String[] ids);
}
