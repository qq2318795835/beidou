package com.beidou.devData.service.impl;

import java.util.List;
import com.beidou.common.utils.DateUtils;
import com.beidou.common.utils.uuid.IdUtils;
import com.beidou.devData.domain.BdCompany;
import com.beidou.devData.mapper.BdCompanyMapper;
import com.beidou.devData.service.IBdCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 企业信息Service业务层处理
 * 
 * @author beidou
 * @date 2024-01-18
 */
@Service
public class BdCompanyServiceImpl implements IBdCompanyService
{
    @Autowired
    private BdCompanyMapper bdCompanyMapper;

    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    @Override
    public BdCompany selectBdCompanyById(String id)
    {
        return bdCompanyMapper.selectBdCompanyById(id);
    }

    /**
     * 查询企业信息列表
     * 
     * @param bdCompany 企业信息
     * @return 企业信息
     */
    @Override
    public List<BdCompany> selectBdCompanyList(BdCompany bdCompany)
    {
        return bdCompanyMapper.selectBdCompanyList(bdCompany);
    }

    /**
     * 新增企业信息
     * 
     * @param bdCompany 企业信息
     * @return 结果
     */
    @Override
    public int insertBdCompany(BdCompany bdCompany)
    {
        bdCompany.setId(IdUtils.fastSimpleUUID());
        bdCompany.setIsDelete("0");
        return bdCompanyMapper.insertBdCompany(bdCompany);
    }

    /**
     * 修改企业信息
     * 
     * @param bdCompany 企业信息
     * @return 结果
     */
    @Override
    public int updateBdCompany(BdCompany bdCompany)
    {
        bdCompany.setUpdateTime(DateUtils.getNowDate());
        return bdCompanyMapper.updateBdCompany(bdCompany);
    }

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息主键
     * @return 结果
     */
    @Override
    public int deleteBdCompanyByIds(String[] ids)
    {
        return bdCompanyMapper.deleteBdCompanyByIds(ids);
    }

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    @Override
    public int deleteBdCompanyById(String id)
    {
        return bdCompanyMapper.deleteBdCompanyById(id);
    }

}

