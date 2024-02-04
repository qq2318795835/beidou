package com.beidou.devData.service;

import com.beidou.devData.domain.BdCompany;

import java.util.List;

/**
 * 企业信息Service接口
 * 
 * @author beidou
 * @date 2024-01-18
 */
public interface IBdCompanyService 
{
    /**
     * 查询企业信息
     * 
     * @param id 企业信息主键
     * @return 企业信息
     */
    public BdCompany selectBdCompanyById(String id);

    /**
     * 查询企业信息列表
     * 
     * @param bdCompany 企业信息
     * @return 企业信息集合
     */
    public List<BdCompany> selectBdCompanyList(BdCompany bdCompany);

    /**
     * 新增企业信息
     * 
     * @param bdCompany 企业信息
     * @return 结果
     */
    public int insertBdCompany(BdCompany bdCompany);

    /**
     * 修改企业信息
     * 
     * @param bdCompany 企业信息
     * @return 结果
     */
    public int updateBdCompany(BdCompany bdCompany);

    /**
     * 批量删除企业信息
     * 
     * @param ids 需要删除的企业信息主键集合
     * @return 结果
     */
    public int deleteBdCompanyByIds(String[] ids);

    /**
     * 删除企业信息信息
     * 
     * @param id 企业信息主键
     * @return 结果
     */
    public int deleteBdCompanyById(String id);
}
