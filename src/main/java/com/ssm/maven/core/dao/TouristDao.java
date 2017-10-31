package com.ssm.maven.core.dao;


import com.ssm.maven.core.entity.ScenicspotCustom;
import com.ssm.maven.core.entity.Tourist;
import com.ssm.maven.core.entity.TouristCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristDao {

    /*
    查找所有的人流信息
     */
    public List<Tourist> getTouristAll(Tourist tourist) throws Exception;

    public void deleteTouristByCode(ScenicspotCustom scenicspotCustom) throws Exception;

    public List<TouristCustom> getTouristList(TouristCustom touristCustom) throws Exception;

    public List<TouristCustom> searchTouristInfor(TouristCustom touristCustom) throws Exception;

    public void deleteByTouristCode(TouristCustom touristCustom) throws Exception;

    public void updateTouristInfor(Tourist tourist) throws Exception;

    public void insertTouristBatch(List<Tourist> touristlist) throws Exception;

    public Integer getCount(TouristCustom touristCustom) throws Exception;

    public int getNumByCodeAndTime(Tourist tourist) throws Exception;








}
