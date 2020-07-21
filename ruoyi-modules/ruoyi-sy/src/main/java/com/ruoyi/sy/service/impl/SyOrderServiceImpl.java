package com.ruoyi.sy.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.utils.CommonUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.sy.domain.SyOrder;
import com.ruoyi.sy.domain.SyOrderDetile;
import com.ruoyi.sy.domain.SyOrderDto;
import com.ruoyi.sy.mapper.SyOrderDetileMapper;
import com.ruoyi.sy.mapper.SyOrderMapper;
import com.ruoyi.sy.service.ISyOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-21
 */
@Service
public class SyOrderServiceImpl implements ISyOrderService
{

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
    @Autowired
    private SyOrderMapper syOrderMapper;
    @Autowired
    private SyOrderDetileMapper syOrderDetileMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public SyOrder selectSyOrderById(Long id)
    {
        return syOrderMapper.selectSyOrderById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param syOrder 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<SyOrder> selectSyOrderList(SyOrder syOrder)
    {
        return syOrderMapper.selectSyOrderList(syOrder);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param syOrder 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSyOrder(SyOrder syOrder)
    {
        return syOrderMapper.insertSyOrder(syOrder);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param syOrder 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSyOrder(SyOrder syOrder)
    {
        return syOrderMapper.updateSyOrder(syOrder);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSyOrderByIds(Long[] ids)
    {
        return syOrderMapper.deleteSyOrderByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteSyOrderById(Long id)
    {
        return syOrderMapper.deleteSyOrderById(id);
    }

    @Override
    public int insert(SyOrderDto syOrderDto) {
        SyOrder syOrder = new SyOrder();
        syOrder.setCreateDate(new Date());
        syOrder.setGuidanceTeacher("沐含");
        syOrder.setOrderId(CommonUtils.createOrderId("SJWH"));
        syOrder.setServiceTeacher("沐含");
        syOrder.setUserName(syOrderDto.getOrderName());
        if(StringUtils.isNotEmpty(syOrderDto.getSex())){
            syOrder.setSex(Long.valueOf(syOrderDto.getSex()));
        }
        syOrder.setCell(syOrderDto.getCell());
        syOrder.setAddress(syOrderDto.getAddress());
        syOrder.setIdCard(syOrderDto.getIdCard());
        syOrder.setRemark(syOrderDto.getRemark());
        syOrder.setAccountName(syOrderDto.getAccountName());
        syOrder.setAccountNumber(syOrderDto.getAccountNumber());
        syOrderMapper.insertSyOrder(syOrder);

        SyOrderDetile syOrderDetile = new SyOrderDetile();
        if(StringUtils.isNotEmpty(syOrderDto.getCustomTools())){
            syOrderDetile.setTypeId(Long.valueOf(syOrderDto.getCustomTools()));
        }

        syOrderDetile.setOrderName(syOrderDto.getOrderName());
        if(StringUtils.isNotEmpty(syOrderDto.getOrder_sex())){
            syOrderDetile.setOrderSex(Long.valueOf(syOrderDto.getOrder_sex()));
        }

        try {
            syOrderDetile.setOrderBirthday(simpleDateFormat.parse(syOrderDto.getOrder_birthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        syOrderDetile.setOrderZodiac(syOrderDto.getOrder_zodiac());
        syOrderDetile.setOrderTime(syOrderDto.getOrder_time());
        syOrderDetile.setOrderFatherName(syOrderDto.getOrder_father_name());
        syOrderDetile.setOrderMotherName(syOrderDto.getOrder_mother_name());
        syOrderDetile.setOrderBsName(syOrderDto.getOrder_bs_name());
        syOrderDetile.setWordsTao(syOrderDto.getWords_tao());
        syOrderDetile.setRemark(syOrderDto.getRemark());
        if(StringUtils.isNotEmpty(syOrderDto.getLettering_not())){
            syOrderDetile.setLetteringNot(Long.valueOf(syOrderDto.getLettering_not()));
        }

        syOrderDetile.setTypeName(syOrderDto.getType_name());
        syOrderDetile.setCreateDate(new Date());
        syOrderDetile.setJzSize(syOrderDto.getJz_size());
        syOrderDetile.setOrderId(syOrder.getOrderId());

        syOrderDetileMapper.insertSyOrderDetile(syOrderDetile);
        return 1;
    }
}
