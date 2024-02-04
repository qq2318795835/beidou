package com.beidou.web.controller.devdata;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.beidou.devData.domain.BdWarehouse;
import com.beidou.devData.service.IBdWarehouseService;
import com.beidou.common.utils.poi.ExcelUtil;
import com.beidou.common.core.page.TableDataInfo;

/**
 * 仓库Controller
 * 
 * @author beidou
 * @date 2024-01-20
 */
@RestController
@RequestMapping("/devData/warehouse")
public class BdWarehouseController extends BaseController
{
    @Autowired
    private IBdWarehouseService bdWarehouseService;

    /**
     * 查询仓库列表
     */
    @PreAuthorize("@ss.hasPermi('devData:warehouse:list')")
    @GetMapping("/list")
    public TableDataInfo list(BdWarehouse bdWarehouse)
    {
        startPage();
        List<BdWarehouse> list = bdWarehouseService.selectBdWarehouseList(bdWarehouse);
        return getDataTable(list);
    }

    /**
     * 导出仓库列表
     */
    @PreAuthorize("@ss.hasPermi('devData:warehouse:export')")
    @Log(title = "仓库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BdWarehouse bdWarehouse)
    {
        List<BdWarehouse> list = bdWarehouseService.selectBdWarehouseList(bdWarehouse);
        ExcelUtil<BdWarehouse> util = new ExcelUtil<BdWarehouse>(BdWarehouse.class);
        util.exportExcel(response, list, "仓库数据");
    }

    /**
     * 获取仓库详细信息
     */
    @PreAuthorize("@ss.hasPermi('devData:warehouse:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bdWarehouseService.selectBdWarehouseById(id));
    }

    /**
     * 新增仓库
     */
    @PreAuthorize("@ss.hasPermi('devData:warehouse:add')")
    @Log(title = "仓库", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BdWarehouse bdWarehouse)
    {
        return toAjax(bdWarehouseService.insertBdWarehouse(bdWarehouse));
    }

    /**
     * 修改仓库
     */
    @PreAuthorize("@ss.hasPermi('devData:warehouse:edit')")
    @Log(title = "仓库", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BdWarehouse bdWarehouse)
    {
        return toAjax(bdWarehouseService.updateBdWarehouse(bdWarehouse));
    }

    /**
     * 删除仓库
     */
    @PreAuthorize("@ss.hasPermi('devData:warehouse:remove')")
    @Log(title = "仓库", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bdWarehouseService.deleteBdWarehouseByIds(ids));
    }
}
