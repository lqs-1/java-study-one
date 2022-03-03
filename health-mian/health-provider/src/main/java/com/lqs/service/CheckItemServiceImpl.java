package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lqs.api.CheckItemService;
import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.mapper.CheckItemMapper;
import com.lqs.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service(interfaceClass = CheckItemService.class, version = "1.0")
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    @Autowired
    private CheckItemMapper checkItemMapper;



    @Override
    public Boolean addCheckItem(CheckItem checkItem) {
        return checkItemMapper.addCheckItem(checkItem);
    }

    @Override
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        // 完成分页查询，基于mybatis框架提供的分页插件
        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemMapper.findBy(queryString);
        long total = page.getTotal();
        List<CheckItem> rows = page.getResult();
        return new PageResult(total, rows);
    }

    @Override
    public int findCheckItemCheckGroup(int id) {
        return checkItemMapper.findCheckItemCheckGroup(id);
    }

    @Override
    public void deleteCheckItem(int id) {
        checkItemMapper.deleteCheckItem(id);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemMapper.findById(id);
    }

    @Override
    public void editCheckItem(CheckItem checkItem) {
        checkItemMapper.editCheckItem(checkItem);
    }
}
