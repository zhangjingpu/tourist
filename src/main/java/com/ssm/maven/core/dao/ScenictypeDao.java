package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.Scenictype;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenictypeDao {

    public List<Scenictype> selectScenictypeAll(Scenictype scenictype) throws Exception;

    public List<Scenictype> selectScenictypeById(Scenictype scenictype) throws Exception;

    public List<Scenictype> selectScenictypeByName(Scenictype scenictype) throws Exception;

    public void insertSelective(Scenictype scenictype) throws Exception;

    public void updateByID(Scenictype scenictype) throws Exception;

    public void deleteScenicById(Scenictype scenictype) throws Exception;





}
