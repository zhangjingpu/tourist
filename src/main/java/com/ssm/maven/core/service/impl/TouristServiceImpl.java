package com.ssm.maven.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.maven.core.dao.TouristDao;
import com.ssm.maven.core.entity.ScenicspotCustom;
import com.ssm.maven.core.entity.Tourist;
import com.ssm.maven.core.entity.TouristCustom;
import com.ssm.maven.core.service.TouristService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("touristservice")
public class TouristServiceImpl implements TouristService {

    @Resource
    private TouristDao touristDao;

    @Override
    public void deleteTouristByCode(ScenicspotCustom scenicspotCustom) throws Exception {
        touristDao.deleteTouristByCode(scenicspotCustom);
    }

    @Override
    public List<TouristCustom> getTouristList(TouristCustom touristCustom) throws Exception {
        return touristDao.getTouristList(touristCustom);
    }

    @Override
    public List<TouristCustom> getTouristList(Integer pageIndex, Integer pageSize, TouristCustom touristCustom) throws Exception {
        PageHelper.startPage(pageIndex, pageSize);
        List<TouristCustom> list = touristDao.getTouristList(touristCustom);
        return list;
    }

    @Override
    public List<TouristCustom> searchTouristInfor(Integer pageIndex, Integer pageSize, TouristCustom touristCustom) throws Exception {
        PageHelper.startPage(pageIndex, pageSize);
        List<TouristCustom> list = touristDao.searchTouristInfor(touristCustom);
        return list;
    }

    @Override
    public List<TouristCustom> searchTouristInfor(TouristCustom touristCustom) throws Exception {
        List<TouristCustom> list = touristDao.searchTouristInfor(touristCustom);
        return list;
    }

    @Override
    public void deleteByTouristCode(TouristCustom touristCustom) throws Exception {
        touristDao.deleteByTouristCode(touristCustom);
    }

    @Override
    public void updateTouristInfor(Tourist tourist) throws Exception {
        touristDao.updateTouristInfor(tourist);
    }

    @Override
    public void insertTouristBatch(List<Tourist> touristlist) throws Exception {
        touristDao.insertTouristBatch(touristlist);
    }

    @Override
    public Integer getCount(TouristCustom touristCustom) throws Exception {
        return touristDao.getCount(touristCustom);
    }

    @Override
    public int getNumByCodeAndTime(Tourist tourist) {
        int i = 0;
        try {
            i = touristDao.getNumByCodeAndTime(tourist);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

}
