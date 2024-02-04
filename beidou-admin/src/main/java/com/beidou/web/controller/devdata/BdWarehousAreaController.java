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
import com.beidou.devData.domain.BdWarehousArea;
import com.beidou.devData.service.IBdWarehousAreaService;
import com.beidou.common.utils.poi.ExcelUtil;
import com.beidou.common.core.page.TableDataInfo;

/**
 * 仓库区域Controller
 * 
 * @author beidou
 * @date 2024-01-21
 */
@RestController
@RequestMapping("/devData/wareHouseArea")
public class BdWarehousAreaController extends BaseController
{
    @Autowired
    private IBdWarehousAreaService bdWarehousAreaService;

    /**
     * 查询仓库区域列表
     */
    @PreAuthorize("@ss.hasPermi('devData:wareHouseArea:list')")
    @GetMapping("/list")
    public TableDataInfo list(BdWarehousArea bdWarehousArea)
    {
        startPage();
        List<BdWarehousArea> list = bdWarehousAreaService.selectBdWarehousAreaList(bdWarehousArea);
        return getDataTable(list);
    }

    /**
     * 导出仓库区域列表
     */
    @PreAuthorize("@ss.hasPermi('devData:wareHouseArea:export')")
    @Log(title = "仓库区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BdWarehousArea bdWarehousArea)
    {
        List<BdWarehousArea> list = bdWarehousAreaService.selectBdWarehousAreaList(bdWarehousArea);
        ExcelUtil<BdWarehousArea> util = new ExcelUtil<BdWarehousArea>(BdWarehousArea.class);
        util.exportExcel(response, list, "仓库区域数据");
    }

    /**
     * 获取仓库区域详细信息
     */
    @PreAuthorize("@ss.hasPermi('devData:wareHouseArea:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bdWarehousAreaService.selectBdWarehousAreaById(id));
    }

    /**
     * 新增仓库区域
     */
    @PreAuthorize("@ss.hasPermi('devData:wareHouseArea:add')")
    @Log(title = "仓库区域", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BdWarehousArea bdWarehousArea)
    {
        return toAjax(bdWarehousAreaService.insertBdWarehousArea(bdWarehousArea));
    }

    /**
     * 修改仓库区域
     */
    @PreAuthorize("@ss.hasPermi('devData:wareHouseArea:edit')")
    @Log(title = "仓库区域", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BdWarehousArea bdWarehousArea)
    {
        return toAjax(bdWarehousAreaService.updateBdWarehousArea(bdWarehousArea));
    }

    /**
     * 删除仓库区域
     */
    @PreAuthorize("@ss.hasPermi('devData:wareHouseArea:remove')")
    @Log(title = "仓库区域", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bdWarehousAreaService.deleteBdWarehousAreaByIds(ids));
    }
}
