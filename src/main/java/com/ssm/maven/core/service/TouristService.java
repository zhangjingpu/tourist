package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.ScenicspotCustom;
import com.ssm.maven.core.entity.Tourist;
import com.ssm.maven.core.entity.TouristCustom;

import java.util.List;

public interface TouristService {

    public void deleteTouristByCode(ScenicspotCustom scenicspotCustom) throws Exception;

    public List<TouristCustom> getTouristList(TouristCustom touristCustom) throws Exception;

    public List<TouristCustom> getTouristList(Integer pageIndex, Integer pageSize, TouristCustom touristCustom) throws Exception;

    public List<TouristCustom> searchTouristInfor(Integer pageIndex, Integer pageSize, TouristCustom touristCustom) throws Exception;

    public List<TouristCustom> searchTouristInfor(TouristCustom touristCustom) throws Exception;

    public void deleteByTouristCode(TouristCustom touristCustom) throws Exception;

    public void updateTouristInfor(Tourist tourist) throws Exception;

    public void insertTouristBatch(List<Tourist> touristlist) throws Exception;

    public Integer getCount(TouristCustom touristCustom) throws Exception;

    public int getNumByCodeAndTime(Tourist tourist);

}
