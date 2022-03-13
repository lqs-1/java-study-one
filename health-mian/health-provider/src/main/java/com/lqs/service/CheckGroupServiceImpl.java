package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lqs.api.CheckGroupService;
import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.mapper.CheckGroupMapper;
import com.lqs.pojo.CheckGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = CheckGroupService.class, version = "1.0")
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupMapper checkGroupMapper;

    @Override
    public void addCheckGroup(Integer[] checkitemIds, CheckGroup checkGroup) {
        // 保存检查组
        checkGroupMapper.addCheckGroup(checkGroup);
        // 保存检查组检查项对应关系，多对多
        for (Integer checkitemId : checkitemIds) {
            Map<String, Integer> checkGroupCheckItem = new HashMap<>();
            checkGroupCheckItem.put("checKGroupId", checkGroup.getId());
            checkGroupCheckItem.put("checkItemId", checkitemId);
            checkGroupMapper.addCheckGroupCheckItem(checkGroupCheckItem);
        }

    }

    @Override
    public PageResult findCheckGroupBy(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        // 开始分页
        PageHelper.startPage(currentPage, pageSize);
        // 开始条件查询
        Page<CheckGroup> checkGroupList = checkGroupMapper.findCheckGroupBy(queryString);
        return new PageResult(checkGroupList.getTotal(),checkGroupList.getResult());
    }

    @Override
    public void deleteCheckGroup(Integer id) {
        // 分为两次，第一次删除检查组，然后删除检查组和检查组的对应关系
        checkGroupMapper.deleteCheckGroupCheckItem(id);
        checkGroupMapper.deleteCheckGroup(id);
    }

    @Override
    public Map<String, Object> findCheckGroupById(Integer id) {
        // 查询有两步，查检查组和查检查项列表
        CheckGroup checkgroup = checkGroupMapper.findCheckGroupById(id);
        List<Integer> checkitemidlist = checkGroupMapper.findCheckGroupCheckItemById(id);
        Map<String, Object> queryResult = new HashMap<>();
        queryResult.put("checkgroup", checkgroup);
        queryResult.put("checkitemidlist", checkitemidlist);
        return queryResult;
    }

    @Override
    public void editCheckGroup(CheckGroup checkGroup, Integer[] checkitemIds) {
        //  修改有两步，修改基本信息和修改与检查项的对应关系
        checkGroupMapper.editCheckGroup(checkGroup);
        if(checkitemIds.length > 0){
            checkGroupMapper.deleteCheckGroupCheckItem(checkGroup.getId());
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> checkGroupCheckItem = new HashMap<>();
                checkGroupCheckItem.put("checKGroupId", checkGroup.getId());
                checkGroupCheckItem.put("checkItemId", checkitemId);
                checkGroupMapper.addCheckGroupCheckItem(checkGroupCheckItem);
            }
        }

    }

    @Override
    public List<CheckGroup> findAllCheckGroup() {
        // 查询所有的检查组，只需要查一次
        return checkGroupMapper.findAllCheckGroup();
    }
}
