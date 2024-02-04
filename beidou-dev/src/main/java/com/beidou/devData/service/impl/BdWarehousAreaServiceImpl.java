package com.beidou.devData.service.impl;

import java.util.List;
import com.beidou.common.utils.DateUtils;
import com.beidou.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.beidou.devData.mapper.BdWarehousAreaMapper;
import com.beidou.devData.domain.BdWarehousArea;
import com.beidou.devData.service.IBdWarehousAreaService;

/**
 * 仓库区域Service业务层处理
 * 
 * @author beidou
 * @date 2024-01-21
 */
@Service
public class BdWarehousAreaServiceImpl implements IBdWarehousAreaService 
{
    @Autowired
    private BdWarehousAreaMapper bdWarehousAreaMapper;

    /**
     * 查询仓库区域
     * 
     * @param id 仓库区域主键
     * @return 仓库区域
     */
    @Override
    public BdWarehousArea selectBdWarehousAreaById(String id)
    {
        return bdWarehousAreaMapper.selectBdWarehousAreaById(id);
    }

    /**
     * 查询仓库区域列表
     * 
     * @param bdWarehousArea 仓库区域
     * @return 仓库区域
     */
    @Override
    public List<BdWarehousArea> selectBdWarehousAreaList(BdWarehousArea bdWarehousArea)
    {
        return bdWarehousAreaMapper.selectBdWarehousAreaList(bdWarehousArea);
    }

    /**
     * 新增仓库区域
     * 
     * @param bdWarehousArea 仓库区域
     * @return 结果
     */
    @Override
    public int insertBdWarehousArea(BdWarehousArea bdWarehousArea)
    {
        bdWarehousArea.setId(IdUtils.fastSimpleUUID());
        bdWarehousArea.setIsDelete("0");
        return bdWarehousAreaMapper.insertBdWarehousArea(bdWarehousArea);
    }

    /**
     * 修改仓库区域
     * 
     * @param bdWarehousArea 仓库区域
     * @return 结果
     */
    @Override
    public int updateBdWarehousArea(BdWarehousArea bdWarehousArea)
    {
        bdWarehousArea.setUpdateTime(DateUtils.getNowDate());
        return bdWarehousAreaMapper.updateBdWarehousArea(bdWarehousArea);
    }

    /**
     * 批量删除仓库区域
     * 
     * @param ids 需要删除的仓库区域主键
     * @return 结果
     */
    @Override
    public int deleteBdWarehousAreaByIds(String[] ids)
    {
        return bdWarehousAreaMapper.deleteBdWarehousAreaByIds(ids);
    }

    /**
     * 删除仓库区域信息
     * 
     * @param id 仓库区域主键
     * @return 结果
     */
    @Override
    public int deleteBdWarehousAreaById(String id)
    {
        return bdWarehousAreaMapper.deleteBdWarehousAreaById(id);
    }
}
