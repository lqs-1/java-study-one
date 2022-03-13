package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.CheckItemService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.entity.Result;
import com.lqs.pojo.CheckItem;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "checkItem")
public class CheckItemController {

    @Reference(version = "1.0")
    private CheckItemService checkItemService;

    // 添加检查项
    @PostMapping("add.do")
    public Result addCheckItem(@RequestBody CheckItem checkItem){
        try{
            checkItemService.addCheckItem(checkItem);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    // 获取检查项
    @PostMapping("findPage.do")
    public PageResult findCheckItemPage(@RequestBody QueryPageBean queryPageBean){
        return checkItemService.findPage(queryPageBean);
    }

    // 删除检查项
    @GetMapping("delete.do")
    @PreAuthorize("hasAnyAuthority('CHECKITEM_DELETE')") // 权限。没有删除权限的用户删除 的话，就会报错403，如果不处理就会抛出异常
    public Result deleteCheckItem(Integer id){
        // 先查询是否绑定在检查组
        // 方法就是查看中间表中是否有该检查项的id
        int checkItemCheckGroupCount = checkItemService.findCheckItemCheckGroup(id);
        System.out.println(checkItemCheckGroupCount);
        if (checkItemCheckGroupCount > 0){
            // 如果关联个数大于0，那么就表示这个检查项在某个检查组
           return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL_GROUP);
        }
        try{
            checkItemService.deleteCheckItem(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

    // 根据id返回数据
    @GetMapping("findById.do")
    public Result checkItemFindById(Integer id){
        CheckItem checkItem ;
        try{
            checkItem = checkItemService.findById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        System.out.println();
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS, checkItem);
    }

    // 修改检查项
    @PutMapping("edit.do")
    public Result editCheckItem(@RequestBody CheckItem checkItem){
        try{
            checkItemService.editCheckItem(checkItem);
        }catch(Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    // 获取所有的检查项
    @GetMapping("findAll.do")
    public Result findAll(){
        List<CheckItem> checkItemList;
        try{
            checkItemList = checkItemService.findAll();
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
    }



}
