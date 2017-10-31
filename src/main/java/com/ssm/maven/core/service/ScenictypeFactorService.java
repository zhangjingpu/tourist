package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.ScenictypeFactor;

import java.util.List;

public interface ScenictypeFactorService {

    public List<ScenictypeFactor> getScenictypeFactorById(Integer pageNum, Integer pageSize,
                                                          ScenictypeFactor scenictypeFactor) throws Exception;

    public void insertScenictypeFactor(ScenictypeFactor scenictypeFactor) throws Exception;

    public ScenictypeFactor getScenictypeFactorByName(ScenictypeFactor scenictypeFactor) throws Exception;

    public void deleteScenicFactorById(ScenictypeFactor scenictypeFactor) throws Exception;

    public void deleteScenicFactorByScenicid(ScenictypeFactor scenictypeFactor) throws Exception;

    public void updateByPrimaryKeySelective(ScenictypeFactor scenictypeFactor) throws Exception;
}
