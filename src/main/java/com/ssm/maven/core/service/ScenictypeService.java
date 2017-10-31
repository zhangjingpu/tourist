package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.Scenictype;

import java.util.List;

public interface ScenictypeService {

    public List<Scenictype> selectScenictypeAll(Scenictype scenictype) throws Exception;

    public List<Scenictype> selectScenictypeAll(Integer pageNum, Integer pageSize, Scenictype scenictype) throws Exception;

    public List<Scenictype> selectScenictypeById(Integer pageNum, Integer pageSize, Scenictype scenictype) throws Exception;

    public List<Scenictype> selectScenictypeByName(Integer pageNum, Integer pageSize, Scenictype scenictype) throws Exception;

    public void insertSelective(Scenictype scenictype) throws Exception;

    public void updateByID(Scenictype scenictype) throws Exception;

    public void deleteScenicById(Scenictype scenictype) throws Exception;


}
