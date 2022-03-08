package com.lqs.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lqs.api.SetmealService;
import com.lqs.constant.RedisConstant;
import com.lqs.entity.PageResult;
import com.lqs.entity.QueryPageBean;
import com.lqs.entity.Result;
import com.lqs.mapper.SetmealMapper;
import com.lqs.pojo.Setmeal;
import com.lqs.utils.OssUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = SetmealService.class, version = "1.0")
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private JedisPool jedisPool;


    // 上传文件服务
    @Override
    public Result uploadFile(String originalFilename, byte[] fileBytes) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
        return OssUtils.UpfileToOss(originalFilename, byteArrayInputStream);

    }

    @Override
    public void addSetmeal(Integer[] checkgroupIds, Setmeal setmeal) {
        /*
        * 分两步，第一步添加套餐，第二步添加套餐和检查组的对应关系
        * */
        // 添加套餐
        setmealMapper.addSetmeal(setmeal);
        // 获取添加后的id

        if (checkgroupIds.length > 0){

            Integer setMealId = setmeal.getId();

            for (Integer checkGroupId : checkgroupIds) {
                Map<String, Integer> setMealCheckGroup = new HashMap<>();
                setMealCheckGroup.put("setMealId", setMealId);
                setMealCheckGroup.put("checkGroupId", checkGroupId);
                setmealMapper.addSetmealCheckGroup(setMealCheckGroup);
            }
        }
        // 这里真正保存到数据库的时候，在记录一下,在定时任务里面这两个redis列表相减就可以得到垃圾图片
        String resultUrl = setmeal.getImg();
        String fileUrl = resultUrl.substring(resultUrl.lastIndexOf("/"));
        String fileName = fileUrl.replace("/", "");
//        System.out.println(fileName);
        jedisPool.getResource().sadd(RedisConstant.SETMEAL_PIC_DB_RESOURCES, fileName);

    }

    @Override
    public PageResult findBy(QueryPageBean queryPageBean) {
        // 分页
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);

        Page<Setmeal> setmeals = setmealMapper.findBy(queryString);

        return new PageResult(setmeals.getTotal(), setmeals.getResult());
    }

    // 给移动端返回所有的套餐
    @Override
    public List<Setmeal> findAllSetmeal() {
        List<Setmeal> setmealList = setmealMapper.findALlSetmeal();
        return setmealList;
    }

    @Override
    public Setmeal findById(Integer id) {
        return setmealMapper.findById(id);
    }
}
