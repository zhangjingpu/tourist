package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.ScenictypeFactor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenictypeFactorDao {

    public List<ScenictypeFactor> getScenictypeFactorById(ScenictypeFactor scenictypeFactor) throws Exception;

    public void insertScenictypeFactor(ScenictypeFactor scenictypeFactor) throws Exception;

    public ScenictypeFactor getScenictypeFactorByName(ScenictypeFactor scenictypeFactor) throws Exception;

    public void deleteScenicFactorById(ScenictypeFactor scenictypeFactor) throws Exception;

    public void deleteScenicFactorByScenicid(ScenictypeFactor scenictypeFactor) throws Exception;

    public void updateByPrimaryKeySelective(ScenictypeFactor scenictypeFactor) throws Exception;


}
