package com.beidou.devData.service;

import java.util.List;
import com.beidou.devData.domain.BdWarehouse;

/**
 * 仓库Service接口
 * 
 * @author beidou
 * @date 2024-01-20
 */
public interface IBdWarehouseService 
{
    /**
     * 查询仓库
     * 
     * @param id 仓库主键
     * @return 仓库
     */
    public BdWarehouse selectBdWarehouseById(String id);

    /**
     * 查询仓库列表
     * 
     * @param bdWarehouse 仓库
     * @return 仓库集合
     */
    public List<BdWarehouse> selectBdWarehouseList(BdWarehouse bdWarehouse);

    /**
     * 新增仓库
     * 
     * @param bdWarehouse 仓库
     * @return 结果
     */
    public int insertBdWarehouse(BdWarehouse bdWarehouse);

    /**
     * 修改仓库
     * 
     * @param bdWarehouse 仓库
     * @return 结果
     */
    public int updateBdWarehouse(BdWarehouse bdWarehouse);

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的仓库主键集合
     * @return 结果
     */
    public int deleteBdWarehouseByIds(String[] ids);

    /**
     * 删除仓库信息
     * 
     * @param id 仓库主键
     * @return 结果
     */
    public int deleteBdWarehouseById(String id);
}
