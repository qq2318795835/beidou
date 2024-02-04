package com.beidou.web.controller.devdata;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.beidou.devData.domain.BdCompany;
import com.beidou.devData.service.IBdCompanyService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.beidou.common.annotation.Log;
import com.beidou.common.core.controller.BaseController;
import com.beidou.common.core.domain.AjaxResult;
import com.beidou.common.enums.BusinessType;
import com.beidou.common.utils.poi.ExcelUtil;
import com.beidou.common.core.page.TableDataInfo;

/**
 * 企业信息Controller
 * 
 * @author beidou
 * @date 2024-01-18
 */
@RestController
@RequestMapping("/devDate/company")
public class BdCompanyController extends BaseController
{
    @Autowired
    private IBdCompanyService bdCompanyService;

    /**
     * 查询企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('devDate:company:list')")
    @GetMapping("/list")
    public TableDataInfo list(BdCompany bdCompany)
    {
        startPage();
        List<BdCompany> list = bdCompanyService.selectBdCompanyList(bdCompany);
        return getDataTable(list);
    }

    /**
     * 导出企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('devDate:company:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BdCompany bdCompany)
    {
        List<BdCompany> list = bdCompanyService.selectBdCompanyList(bdCompany);
        ExcelUtil<BdCompany> util = new ExcelUtil<BdCompany>(BdCompany.class);
        util.exportExcel(response, list, "企业信息数据");
    }

    /**
     * 获取企业信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('devDate:company:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bdCompanyService.selectBdCompanyById(id));
    }

    /**
     * 新增企业信息
     */
    @PreAuthorize("@ss.hasPermi('devDate:company:add')")
    @Log(title = "企业信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BdCompany bdCompany)
    {
        return toAjax(bdCompanyService.insertBdCompany(bdCompany));
    }

    /**
     * 修改企业信息
     */
    @PreAuthorize("@ss.hasPermi('devDate:company:edit')")
    @Log(title = "企业信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BdCompany bdCompany)
    {
        return toAjax(bdCompanyService.updateBdCompany(bdCompany));
    }

    /**
     * 删除企业信息
     */
    @PreAuthorize("@ss.hasPermi('devDate:company:remove')")
    @Log(title = "企业信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bdCompanyService.deleteBdCompanyByIds(ids));
    }
}
