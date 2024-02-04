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
import com.beidou.devData.domain.BdAlert;
import com.beidou.devData.service.IBdAlertService;
import com.beidou.common.utils.poi.ExcelUtil;
import com.beidou.common.core.page.TableDataInfo;

/**
 * 告警信息Controller
 * 
 * @author beidou
 * @date 2024-01-21
 */
@RestController
@RequestMapping("/devData/alert")
public class BdAlertController extends BaseController
{
    @Autowired
    private IBdAlertService bdAlertService;

    /**
     * 查询告警信息列表
     */
    @PreAuthorize("@ss.hasPermi('devData:alert:list')")
    @GetMapping("/list")
    public TableDataInfo list(BdAlert bdAlert)
    {
        startPage();
        List<BdAlert> list = bdAlertService.selectBdAlertList(bdAlert);
        return getDataTable(list);
    }

    /**
     * 导出告警信息列表
     */
    @PreAuthorize("@ss.hasPermi('devData:alert:export')")
    @Log(title = "告警信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BdAlert bdAlert)
    {
        List<BdAlert> list = bdAlertService.selectBdAlertList(bdAlert);
        ExcelUtil<BdAlert> util = new ExcelUtil<BdAlert>(BdAlert.class);
        util.exportExcel(response, list, "告警信息数据");
    }

    /**
     * 获取告警信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('devData:alert:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(bdAlertService.selectBdAlertById(id));
    }

    /**
     * 新增告警信息
     */
    @PreAuthorize("@ss.hasPermi('devData:alert:add')")
    @Log(title = "告警信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BdAlert bdAlert)
    {
        return toAjax(bdAlertService.insertBdAlert(bdAlert));
    }

    /**
     * 修改告警信息
     */
    @PreAuthorize("@ss.hasPermi('devData:alert:edit')")
    @Log(title = "告警信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BdAlert bdAlert)
    {
        return toAjax(bdAlertService.updateBdAlert(bdAlert));
    }

    /**
     * 删除告警信息
     */
    @PreAuthorize("@ss.hasPermi('devData:alert:remove')")
    @Log(title = "告警信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(bdAlertService.deleteBdAlertByIds(ids));
    }
}
