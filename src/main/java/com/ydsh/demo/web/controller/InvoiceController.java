/**
 * @filename:InvoiceController 2019-06-12 06:54:59
 * @project ydsh-saas-service-demo  V1.0
 * Copyright(c) 2020 姚仲杰 Co. Ltd. 
 * All right reserved. 
 */
package com.ydsh.demo.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ydsh.demo.web.controller.base.AbstractController;
import com.ydsh.demo.web.entity.Invoice;
import com.ydsh.demo.web.service.InvoiceService;
import com.ydsh.generator.common.JsonResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * <p>自定义方法写在这里</p>
 * 
 * <p>说明： 表注释API接口层</P>
 * @version: V1.0
 * @author: 姚仲杰
 *
 */
@Api(description = "表注释",value="表注释" )
@RestController
@RequestMapping("/invoice")
@Slf4j
public class InvoiceController extends AbstractController<InvoiceService,Invoice>{

    @RequestMapping(value = "/fuzzyQuery",method = RequestMethod.POST)
    @ApiOperation(value = "模糊查询", notes = "作者：李锴")
    public JsonResult<Invoice> fuzzyQuery(Invoice invoice) {
//        if (null!=invoice ) {
            List<Invoice> fuzzyResult= baseService.fuzzyQuery(invoice);
//            if (null!=fuzzyResult ) {
//                result.success("模糊查询成功");
//            }else {
//                result.error("查询信息不存在！");
//            }
//        }else {
//            result.error("请传正确的参数！");
//        }
        LambdaQueryWrapper<Invoice> wrapper=new LambdaQueryWrapper<>();
        wrapper.likeRight(Invoice::getClientNo,invoice.getClientNo());
        Page<Invoice> page=new Page<Invoice>(1,10);

        super.baseService.getBaseMapper().selectPage(page,wrapper);

        return result;
    }


}