package com.beidou.devData.mapper;

import java.util.List;
import com.beidou.devData.domain.BdWarehousArea;
import org.springframework.stereotype.Repository;

/**
 * 仓库区域Mapper接口
 * 
 * @author beidou
 * @date 2024-01-21
 */
@Repository
public interface BdWarehousAreaMapper
{
    /**
     * 查询仓库区域
     * 
     * @param id 仓库区域主键
     * @return 仓库区域
     */
    public BdWarehousArea selectBdWarehousAreaById(String id);

    /**
     * 查询仓库区域列表
     * 
     * @param bdWarehousArea 仓库区域
     * @return 仓库区域集合
     */
    public List<BdWarehousArea> selectBdWarehousAreaList(BdWarehousArea bdWarehousArea);

    /**
     * 新增仓库区域
     * 
     * @param bdWarehousArea 仓库区域
     * @return 结果
     */
    public int insertBdWarehousArea(BdWarehousArea bdWarehousArea);

    /**
     * 修改仓库区域
     * 
     * @param bdWarehousArea 仓库区域
     * @return 结果
     */
    public int updateBdWarehousArea(BdWarehousArea bdWarehousArea);

    /**
     * 删除仓库区域
     * 
     * @param id 仓库区域主键
     * @return 结果
     */
    public int deleteBdWarehousAreaById(String id);

    /**
     * 批量删除仓库区域
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBdWarehousAreaByIds(String[] ids);
}
