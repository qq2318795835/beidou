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
import com.beidou.devData.domain.BdMetrics;
import com.beidou.devData.service.IBdMetricsService;
import com.beidou.common.utils.poi.ExcelUtil;
import com.beidou.common.core.page.TableDataInfo;

/**
 * 指标管理Controller
 * 
 * @author beidou
 * @date 2024-01-21
 */
@RestController
@RequestMapping("/devData/metrics")
public class BdMetricsController extends BaseController
{
    @Autowired
    private IBdMetricsService bdMetricsService;

    /**
     * 查询指标管理列表
     */
    @PreAuthorize("@ss.hasPermi('devData:metrics:list')")
    @GetMapping("/list")
    public TableDataInfo list(BdMetrics bdMetrics)
    {
        startPage();
        List<BdMetrics> list = bdMetricsService.selectBdMetricsList(bdMetrics);
        return getDataTable(list);
    }

    /**
     * 导出指标管理列表
     */
    @PreAuthorize("@ss.hasPermi('devData:metrics:export')")
    @Log(title = "指标管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BdMetrics bdMetrics)
    {
        List<BdMetrics> list = bdMetricsService.selectBdMetricsList(bdMetrics);
        ExcelUtil<BdMetrics> util = new ExcelUtil<BdMetrics>(BdMetrics.class);
        util.exportExcel(response, list, "指标管理数据");
    }

    /**
     * 获取指标管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('devData:metrics:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bdMetricsService.selectBdMetricsById(id));
    }

    /**
     * 新增指标管理
     */
    @PreAuthorize("@ss.hasPermi('devData:metrics:add')")
    @Log(title = "指标管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BdMetrics bdMetrics)
    {
        return toAjax(bdMetricsService.insertBdMetrics(bdMetrics));
    }

    /**
     * 修改指标管理
     */
    @PreAuthorize("@ss.hasPermi('devData:metrics:edit')")
    @Log(title = "指标管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BdMetrics bdMetrics)
    {
        return toAjax(bdMetricsService.updateBdMetrics(bdMetrics));
    }

    /**
     * 删除指标管理
     */
    @PreAuthorize("@ss.hasPermi('devData:metrics:remove')")
    @Log(title = "指标管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bdMetricsService.deleteBdMetricsByIds(ids));
    }
}
