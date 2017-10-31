package com.ssm.maven.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.maven.core.dao.ScenictypeDao;
import com.ssm.maven.core.entity.Scenictype;
import com.ssm.maven.core.service.ScenictypeService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("scenictypeservice")
public class ScenictypeServiceImpl implements ScenictypeService {

    @Resource
    private ScenictypeDao scenictypeDao;

    @Override
    public List<Scenictype> selectScenictypeAll(Scenictype scenictype) throws Exception {
        return scenictypeDao.selectScenictypeAll(scenictype);
    }

    @Override
    public List<Scenictype> selectScenictypeAll(Integer pageNum, Integer pageSize, Scenictype scenictype) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Scenictype> list = scenictypeDao.selectScenictypeAll(scenictype);
        return list;
    }

    @Override
    public List<Scenictype> selectScenictypeById(Integer pageNum, Integer pageSize, Scenictype scenictype) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Scenictype> list = scenictypeDao.selectScenictypeById(scenictype);
        return list;
    }

    @Override
    public List<Scenictype> selectScenictypeByName(Integer pageNum, Integer pageSize, Scenictype scenictype) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        List<Scenictype> list = scenictypeDao.selectScenictypeByName(scenictype);
        return list;
    }

    @Override
    public void insertSelective(Scenictype scenictype) throws Exception {
        scenictypeDao.insertSelective(scenictype);
    }

    @Override
    public void updateByID(Scenictype scenictype) throws Exception {
        scenictypeDao.updateByID(scenictype);
    }

    @Override
    public void deleteScenicById(Scenictype scenictype) throws Exception {
        scenictypeDao.deleteScenicById(scenictype);
    }

}
