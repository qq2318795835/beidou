package com.beidou.devData.service.impl;

import java.util.List;
import com.beidou.common.utils.DateUtils;
import com.beidou.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beidou.devData.mapper.BdWarehouseMapper;
import com.beidou.devData.domain.BdWarehouse;
import com.beidou.devData.service.IBdWarehouseService;

/**
 * 仓库Service业务层处理
 * 
 * @author beidou
 * @date 2024-01-20
 */
@Service
public class BdWarehouseServiceImpl implements IBdWarehouseService 
{
    @Autowired
    private BdWarehouseMapper bdWarehouseMapper;

    /**
     * 查询仓库
     * 
     * @param id 仓库主键
     * @return 仓库
     */
    @Override
    public BdWarehouse selectBdWarehouseById(String id)
    {
        return bdWarehouseMapper.selectBdWarehouseById(id);
    }

    /**
     * 查询仓库列表
     * 
     * @param bdWarehouse 仓库
     * @return 仓库
     */
    @Override
    public List<BdWarehouse> selectBdWarehouseList(BdWarehouse bdWarehouse)
    {
        return bdWarehouseMapper.selectBdWarehouseList(bdWarehouse);
    }

    /**
     * 新增仓库
     * 
     * @param bdWarehouse 仓库
     * @return 结果
     */
    @Override
    public int insertBdWarehouse(BdWarehouse bdWarehouse)
    {
        bdWarehouse.setId(IdUtils.fastSimpleUUID());
        bdWarehouse.setIsDelete("0");
        return bdWarehouseMapper.insertBdWarehouse(bdWarehouse);
    }

    /**
     * 修改仓库
     * 
     * @param bdWarehouse 仓库
     * @return 结果
     */
    @Override
    public int updateBdWarehouse(BdWarehouse bdWarehouse)
    {
        bdWarehouse.setUpdateTime(DateUtils.getNowDate());
        return bdWarehouseMapper.updateBdWarehouse(bdWarehouse);
    }

    /**
     * 批量删除仓库
     * 
     * @param ids 需要删除的仓库主键
     * @return 结果
     */
    @Override
    public int deleteBdWarehouseByIds(String[] ids)
    {
        return bdWarehouseMapper.deleteBdWarehouseByIds(ids);
    }

    /**
     * 删除仓库信息
     * 
     * @param id 仓库主键
     * @return 结果
     */
    @Override
    public int deleteBdWarehouseById(String id)
    {
        return bdWarehouseMapper.deleteBdWarehouseById(id);
    }
}
