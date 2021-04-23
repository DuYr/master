package com.school.master.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.school.master.admin.service.LogAbsentService;
import com.school.master.mapper.LogAbsentDao;
import com.school.master.model.LogAbsent;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (LogAbsent)表服务实现类
 *
 * @author makejava
 * @since 2021-01-31 16:19:03
 */
@Service("tpLogAbsentService")
public class LogAbsentServiceImpl implements LogAbsentService {
    @Resource
    private LogAbsentDao logAbsentDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public LogAbsent queryById(Integer id) {
        return this.logAbsentDao.queryById(id);
    }


    /**
     * 新增数据
     *
     * @param LogAbsent 实例对象
     * @return 实例对象
     */
    @Override
    public LogAbsent insert(LogAbsent LogAbsent) {
        this.logAbsentDao.insert(LogAbsent);
        return LogAbsent;
    }


    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.logAbsentDao.deleteById(id) > 0;
    }

    @Override
    public List<LogAbsent> getLogsByCriteria(Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<LogAbsent> logAbsents = logAbsentDao.queryAll();
        return logAbsents;
    }
}