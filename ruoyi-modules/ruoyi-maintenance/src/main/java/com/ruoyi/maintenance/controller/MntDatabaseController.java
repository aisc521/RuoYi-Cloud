package com.ruoyi.maintenance.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import cn.hutool.core.io.FileUtil;
import com.ruoyi.common.core.utils.BadRequestException;
import com.ruoyi.common.core.utils.SqlUtils;
import com.ruoyi.common.core.utils.poi.ExcelUtil;
import com.ruoyi.common.core.web.controller.BaseController;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.core.web.page.TableDataInfo;
import com.ruoyi.common.log.annotation.Log;
import com.ruoyi.common.log.enums.BusinessType;
import com.ruoyi.common.security.annotation.Inner;
import com.ruoyi.maintenance.domain.MntDatabase;
import com.ruoyi.maintenance.service.IMntDatabaseService;
import io.swagger.annotations.ApiOperation;
import net.sf.jsqlparser.schema.Database;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 数据库管理Controller
 * 
 * @author ruoyi
 * @date 2020-07-20
 */
@RestController
@RequestMapping("/database")
public class MntDatabaseController extends BaseController


{
    @Autowired
    private IMntDatabaseService mntDatabaseService;
    private final String fileSavePath = FileUtil.getTmpDirPath()+"/";

    /**
     * 查询数据库管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:database:list')")
    @GetMapping("/list")
    public TableDataInfo list(MntDatabase mntDatabase)
    {
        startPage();
        List<MntDatabase> list = mntDatabaseService.selectMntDatabaseList(mntDatabase);
        return getDataTable(list);
    }

    /**
     * 导出数据库管理列表
     */
    @PreAuthorize("@ss.hasPermi('maintenance:database:export')")
    @Log(title = "数据库管理", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response,MntDatabase mntDatabase) throws IOException {
        List<MntDatabase> list = mntDatabaseService.selectMntDatabaseList(mntDatabase);
        ExcelUtil<MntDatabase> util = new ExcelUtil<MntDatabase>(MntDatabase.class);
        util.exportExcel(response,list, "database");
    }

    /**
     * 获取数据库管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('maintenance:database:query')")
    @GetMapping(value = "/{dbId}")
    public AjaxResult getInfo(@PathVariable("dbId") String dbId)
    {
        return AjaxResult.success(mntDatabaseService.selectMntDatabaseById(dbId));
    }

    /**
     * 新增数据库管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:database:add')")
    @Log(title = "数据库管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MntDatabase mntDatabase)
    {
        return toAjax(mntDatabaseService.insertMntDatabase(mntDatabase));
    }

    /**
     * 修改数据库管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:database:edit')")
    @Log(title = "数据库管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MntDatabase mntDatabase)
    {
        return toAjax(mntDatabaseService.updateMntDatabase(mntDatabase));
    }

    /**
     * 删除数据库管理
     */
    @PreAuthorize("@ss.hasPermi('maintenance:database:remove')")
    @Log(title = "数据库管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{dbIds}")
    public AjaxResult remove(@PathVariable String[] dbIds)
    {
        return toAjax(mntDatabaseService.deleteMntDatabaseByIds(dbIds));
    }

    @Log(title = "执行SQL脚本")
    @ApiOperation(value = "执行SQL脚本")
    @PostMapping(value = "/upload")
    //@PreAuthorize("@ss.hasPermi('maintenance:database:sql')")
    @Inner(false)
    public ResponseEntity<Object> upload(@RequestBody MultipartFile file, HttpServletRequest request)throws Exception{
        String id = request.getParameter("db_id");
        String id1 = request.getParameter("projectId");
        System.out.println(id1);
        MntDatabase database = mntDatabaseService.findById(id);
        String fileName;
        if(database != null){
            fileName = file.getOriginalFilename();
            File executeFile = new File(fileSavePath+fileName);
            FileUtil.del(executeFile);
            file.transferTo(executeFile);
            String result = SqlUtils.executeFile(database.getJdbcUrl(), database.getUserName(), database.getPwd(), executeFile);
            return new ResponseEntity<>(result,HttpStatus.OK);
        }else{
            throw new BadRequestException("Database not exist");
        }
    }

    @Log(title = "测试数据库链接")
    @ApiOperation(value = "测试数据库链接")
    @PostMapping("/testConnect")
    @PreAuthorize("@ss.hasPermi('maintenance:database:testConnect')")

    public AjaxResult testConnect(@Validated @RequestBody MntDatabase resources){
        if(mntDatabaseService.testConnection(resources)){
            return AjaxResult.success("连接成功");
        }else{
            return AjaxResult.success("连接失败");
        }

    }

}
