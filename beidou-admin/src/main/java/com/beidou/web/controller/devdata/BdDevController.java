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
import com.beidou.devData.domain.BdDev;
import com.beidou.devData.service.IBdDevService;
import com.beidou.common.utils.poi.ExcelUtil;
import com.beidou.common.core.page.TableDataInfo;

/**
 * 设备管理Controller
 * 
 * @author beidou
 * @date 2024-01-21
 */
@RestController
@RequestMapping("/devData/dev")
public class BdDevController extends BaseController
{
    @Autowired
    private IBdDevService bdDevService;

    /**
     * 查询设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('devData:dev:list')")
    @GetMapping("/list")
    public TableDataInfo list(BdDev bdDev)
    {
        startPage();
        List<BdDev> list = bdDevService.selectBdDevList(bdDev);
        return getDataTable(list);
    }

    /**
     * 导出设备管理列表
     */
    @PreAuthorize("@ss.hasPermi('devData:dev:export')")
    @Log(title = "设备管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BdDev bdDev)
    {
        List<BdDev> list = bdDevService.selectBdDevList(bdDev);
        ExcelUtil<BdDev> util = new ExcelUtil<BdDev>(BdDev.class);
        util.exportExcel(response, list, "设备管理数据");
    }

    /**
     * 获取设备管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('devData:dev:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bdDevService.selectBdDevById(id));
    }

    /**
     * 新增设备管理
     */
    @PreAuthorize("@ss.hasPermi('devData:dev:add')")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BdDev bdDev)
    {
        return toAjax(bdDevService.insertBdDev(bdDev));
    }

    /**
     * 修改设备管理
     */
    @PreAuthorize("@ss.hasPermi('devData:dev:edit')")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BdDev bdDev)
    {
        return toAjax(bdDevService.updateBdDev(bdDev));
    }

    /**
     * 删除设备管理
     */
    @PreAuthorize("@ss.hasPermi('devData:dev:remove')")
    @Log(title = "设备管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bdDevService.deleteBdDevByIds(ids));
    }
}
