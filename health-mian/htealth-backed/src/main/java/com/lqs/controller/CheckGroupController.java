package com.lqs.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lqs.api.CheckGroupService;
import com.lqs.constant.MessageConstant;
import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.entity.Result;
import com.lqs.pojo.CheckGroup;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "checkGroup")
public class CheckGroupController {

    @Reference(version = "1.0")
    private CheckGroupService checkGroupService;

    // 添加检查组
    @PostMapping(value = "add.do")
    public Result addCheckGroup(@RequestBody CheckGroup checkGroup,Integer[] checkitemIds){
        try{
            checkGroupService.addCheckGroup(checkitemIds, checkGroup);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }


    // 分页获取页面数据
    @PostMapping(value = "findPage.do")
    public PageResult findBy(@RequestBody QueryPageBean queryPageBean){
        return checkGroupService.findCheckGroupBy(queryPageBean);
    }

    // 删除检查组
    @GetMapping(value = "delete.do")
    public Result deleteCheckGroup(Integer id){
        try{
            checkGroupService.deleteCheckGroup(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }

    // 编辑检查组的时候的回显数据查询
    @GetMapping(value = "findById.do")
    public Result findCheckGroupById(Integer id){
        Map<String, Object> queryResult;
        try{
            queryResult = checkGroupService.findCheckGroupById(id);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, queryResult);
    }


    // 编辑检查组
    @PutMapping(value = "editCheckGroup.do")
    public Result editCheckGroup(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup){
        try{
            checkGroupService.editCheckGroup(checkGroup, checkitemIds);
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

}
