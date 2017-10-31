package com.ssm.maven.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.maven.core.dao.ScenicDao;
import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.entity.ScenicspotCustom;
import com.ssm.maven.core.service.ScenicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("sceniservice")
public class ScenicServiceImpl implements ScenicService {

    @Resource
    private ScenicDao scenicDao;
    @Resource
    private Scenicspot scenicspot;

    @Override
    public List<Scenicspot> getScenicspotAll(Integer pageNum, Integer pageSize, Scenicspot scenicspot) {

        List<Scenicspot> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = scenicDao.getScenicspotAll(scenicspot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Scenicspot> getScenicspotAll(Scenicspot scenicspot) {

        List<Scenicspot> list = new ArrayList<>();
        try {
            list = scenicDao.getScenicspotAll(scenicspot);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Scenicspot> getScenicspotByName(Integer pageNum, Integer pageSize, String scenicname) {
        List<Scenicspot> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = scenicDao.getScenicspotByName(scenicname);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void insertSelective(Scenicspot scenicspot) throws Exception {
        scenicDao.insertSelective(scenicspot);
    }

    @Override
    public Scenicspot getScenicspotByCode(String str) throws Exception {
        return scenicDao.getScenicspotByCode(str);
    }

    @Override
    public List<Scenicspot> getScenicspotlistByCode(Integer pageNum, Integer pageSize, String code) throws Exception {
        List<Scenicspot> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = scenicDao.getScenicspotlistByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void updateByCode(Scenicspot scenicspot) throws Exception {
        scenicDao.updateByCode(scenicspot);
    }

    @Override
    public void deleteByCode(String code) throws Exception {
        scenicDao.deleteByCode(code);
    }

    @Override
    public List<ScenicspotCustom> getScenicspotAndDay(Integer pageIndex, Integer pageSize) throws Exception {
        PageHelper.startPage(pageIndex, pageSize);
        List<ScenicspotCustom> list = scenicDao.getScenicspotAndDay();
        return list;
    }

    @Override
    public List<ScenicspotCustom> getScenicspotAndDay() throws Exception {
        List<ScenicspotCustom> list = scenicDao.getScenicspotAndDay();
        return list;
    }

    @Override
    public List<ScenicspotCustom> getpeopleInfor(Integer pageIndex, Integer pageSize, ScenicspotCustom scenicspotCustom) throws Exception {

        PageHelper.startPage(pageIndex, pageSize);
        List<ScenicspotCustom> list = scenicDao.getpeopleInfor(scenicspotCustom);
        return list;
    }

    @Override
    public void insertScenicspotList(List<Scenicspot> scenicspotlist) throws Exception {
        scenicDao.insertScenicspotList(scenicspotlist);
    }

    @Override
    public Scenicspot getScenicspotById(Integer id) throws Exception {
        return scenicDao.getScenicspotById(id);
    }


}
