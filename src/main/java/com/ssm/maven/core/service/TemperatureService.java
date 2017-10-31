package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.DiCustom;
import com.ssm.maven.core.entity.DiTable;

import java.util.List;

public interface TemperatureService {
    public List<DiCustom> getAllScinceDI();

    public List<DiCustom> getCurrentPage(Integer pageNum, Integer pageSize, DiCustom diCustom);

    public List<DiCustom> findScenicAndTime(Integer pageNum, Integer pageSize, DiCustom diCustom);

    public List<DiTable> getAllTemperature(DiTable diTable);

    public List<DiTable> getTemPage(Integer pageNum, Integer pageSize, DiTable diTable);

    public DiTable getTemByTimeAndId(DiTable diTable);

    public void editTem(DiTable diTable);

    public void addTep(DiTable diTable);

    public List<DiTable> selectTemp(DiTable diTable);

    public void delTem(DiTable diTable);

    public void deleteScenic(DiTable diTable);

    public void insertWeatherBatch(List<DiTable> diTables);

    public List<DiCustom> getAllDI();

}
