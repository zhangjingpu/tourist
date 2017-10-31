package com.ssm.maven.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.maven.core.dao.ScenictypeFactorDao;
import com.ssm.maven.core.entity.ScenictypeFactor;
import com.ssm.maven.core.service.ScenictypeFactorService;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("scenictypefactorservice")
public class ScenictypeFactorServiceImpl implements ScenictypeFactorService {
    @Resource
    private ScenictypeFactorDao scenictypeFactorDao;

    @Override
    public List<ScenictypeFactor> getScenictypeFactorById(Integer pageNum, Integer pageSize, ScenictypeFactor scenictypeFactor) throws Exception {

        PageHelper.startPage(pageNum, pageSize);
        List<ScenictypeFactor> list = scenictypeFactorDao.getScenictypeFactorById(scenictypeFactor);
        return list;
    }

    @Override
    public void insertScenictypeFactor(ScenictypeFactor scenictypeFactor) throws Exception {
        scenictypeFactorDao.insertScenictypeFactor(scenictypeFactor);
    }

    @Override
    public ScenictypeFactor getScenictypeFactorByName(ScenictypeFactor scenictypeFactor) throws Exception {
        return scenictypeFactorDao.getScenictypeFactorByName(scenictypeFactor);
    }

    @Override
    public void deleteScenicFactorById(ScenictypeFactor scenictypeFactor) throws Exception {
        scenictypeFactorDao.deleteScenicFactorById(scenictypeFactor);
    }

    @Override
    public void deleteScenicFactorByScenicid(ScenictypeFactor scenictypeFactor) throws Exception {
        scenictypeFactorDao.deleteScenicFactorByScenicid(scenictypeFactor);
    }

    @Override
    public void updateByPrimaryKeySelective(ScenictypeFactor scenictypeFactor) throws Exception {
        scenictypeFactorDao.updateByPrimaryKeySelective(scenictypeFactor);
    }
}
