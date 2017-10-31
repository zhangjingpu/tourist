package com.ssm.maven.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.maven.core.dao.TemperatureDao;
import com.ssm.maven.core.entity.DiCustom;
import com.ssm.maven.core.entity.DiTable;
import com.ssm.maven.core.service.TemperatureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service("TemperatureService")
public class TemperatureImpl implements TemperatureService {
    @Resource
    TemperatureDao temperatureDao;

    @Override
    public List<DiCustom> getAllScinceDI() {
        List<DiCustom> list = new ArrayList<>();
        try {
            list = temperatureDao.getAllScinceDI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DiCustom> getCurrentPage(Integer pageNum, Integer pageSize, DiCustom diCustom) {
        List<DiCustom> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = temperatureDao.getAllScinceDI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DiCustom> findScenicAndTime(Integer pageNum, Integer pageSize, DiCustom diCustom) {
        List<DiCustom> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = temperatureDao.findScenicAndTime(diCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DiTable> getAllTemperature(DiTable diTable) {
        List<DiTable> list = new ArrayList<>();
        try {
            list = temperatureDao.getAllTemperatures(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DiTable> getTemPage(Integer pageNum, Integer pageSize, DiTable diTable) {
        List<DiTable> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = temperatureDao.getAllTemperatures(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public DiTable getTemByTimeAndId(DiTable diTable) {
        DiTable diTable1 = new DiTable();
        try {
            diTable = temperatureDao.getTemByTimeAndId(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diTable;
    }

    public void editTem(DiTable diTable) {
        try {
            temperatureDao.editTem(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTep(DiTable diTable) {
        try {
            temperatureDao.addTep(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiTable> selectTemp(DiTable diTable) {
        List<DiTable> diTable1 = new ArrayList<>();
        try {
            diTable1 = temperatureDao.selectTemp(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diTable1;
    }

    @Override
    public void delTem(DiTable diTable) {
        try {
            temperatureDao.delTem(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteScenic(DiTable diTable) {
        try {
            temperatureDao.deleletScenic(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertWeatherBatch(List<DiTable> diTables) {
        try {
            temperatureDao.insertCarBatch(diTables);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DiCustom> getAllDI() {
        List<DiCustom> diCustomList = null;
        try {
            diCustomList = temperatureDao.getAllDI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diCustomList;
    }
}
