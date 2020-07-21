package com.ruoyi.api.service;

import com.ruoyi.api.db.model.SysNurse;
import com.ruoyi.api.dto.SysNurseDto;
import org.apache.ibatis.annotations.Options;

import java.text.ParseException;
import java.util.List;

public interface SysNurseService {
    List<SysNurseDto> selectSysNurseList(String userId,String queryType);

    boolean checkAdd(SysNurse sysNurse);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    int insertSysNurse(SysNurse sysNurse, String dateTime) throws ParseException;

    SysNurseDto querySysNurseById(int i);
}
