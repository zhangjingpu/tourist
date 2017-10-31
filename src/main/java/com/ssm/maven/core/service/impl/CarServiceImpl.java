package com.ssm.maven.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.maven.core.dao.CarDao;
import com.ssm.maven.core.entity.CarCustom;
import com.ssm.maven.core.entity.ParkingCar;
import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.service.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("CarService")
public class CarServiceImpl implements CarService {
    @Resource
    CarDao carDao;

    @Override
    public List<Scenicspot> getAllScenicspot() {
        List<Scenicspot> s = carDao.getAllScenicspot();
        return s;
    }

    @Override
    public List<CarCustom> getAllSpotCar() {
        return carDao.getAllSpotCar();
    }

    /**
     * 获取当前页面信息
     */
    @Override
    public List<CarCustom> getCurrentPage(Integer pageNum, Integer pageSize, CarCustom carCustom) {
        List<CarCustom> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = carDao.getAllSpotCar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 查询
     */
    @Override
    public List<CarCustom> findSpotCarByNameOrTime(Integer pageNum, Integer pageSize, CarCustom carCustom) {
        List<CarCustom> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = carDao.findSpotCarByNameOrTime(carCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ParkingCar> findAllCarByCodeAndTime(ParkingCar parkingCar) throws Exception {
        return carDao.findAllCarByCodeAndTime(parkingCar);
    }

    @Override
    public Integer getAllCarNumber(ParkingCar parkingCar) throws Exception {
        return carDao.getAllCarNumber(parkingCar);
    }

    /**
     * 获取车辆第一页页面信息
     */
    @Override
    public List<ParkingCar> getFirstCar(Integer pageNum, Integer pageSize, ParkingCar parkingCar) {
        List<ParkingCar> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = carDao.findAllCarByCodeAndTime(parkingCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 模糊查询车辆
     */
    public List<ParkingCar> findCar(Integer pageNum, Integer pageSize, ParkingCar parkingCar) {
        List<ParkingCar> list = new ArrayList<>();
        try {
            PageHelper.startPage(pageNum, pageSize);
            list = carDao.findCar(parkingCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * 删除
     *
     * @param parkingCar
     */
    public void deleteByCodeAndTime(ParkingCar parkingCar) {
        try {
            carDao.deleteByCodeAndTime(parkingCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertCarBatch(List<ParkingCar> parkingCarList) {
        try {
            carDao.insertCarBatch(parkingCarList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CarCustom> getAllCar() {
        List<CarCustom> carCustomList = null;
        try {
            carCustomList = carDao.getAllCar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return carCustomList;
    }

    public Scenicspot getNumberByCode(int code) {
        Scenicspot scenicspot = new Scenicspot();
        try {
            scenicspot = carDao.getNumberByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scenicspot;
    }

    public int getNumByCodeAndTime(ParkingCar parkingCar) {
        int i = 0;
        try {
            i = carDao.getNumByCodeAndTime(parkingCar);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }
}
