package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.CarCustom;
import com.ssm.maven.core.entity.ParkingCar;
import com.ssm.maven.core.entity.Scenicspot;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarDao {
    /**
     * 景区查询
     */
    public List<Scenicspot> getAllScenicspot();

    /**
     * 获取所有景区每天车流量的信息
     */
    public List<CarCustom> getAllSpotCar();

    /**
     * 查询
     */
    public List<CarCustom> findSpotCarByNameOrTime(CarCustom carCustom) throws Exception;

    /**
     * 根据景区编号和日期查询所有车辆信息
     */
    public List<ParkingCar> findAllCarByCodeAndTime(ParkingCar parkingCar) throws Exception;

    public Integer getAllCarNumber(ParkingCar parkingCar) throws Exception;

    /**
     * 搜索车辆信息
     */
    public List<ParkingCar> findCar(ParkingCar parkingCar) throws Exception;
    /**
     * 根据车辆的日期和景区code删除
     */
    public void deleteByCodeAndTime(ParkingCar parkingCar) throws Exception;

    public void insertCarBatch(List<ParkingCar> parkingCarList) throws Exception;

    public List<CarCustom> getAllCar() throws Exception;

    //更具景区的id查询最大承载车辆
    public Scenicspot getNumberByCode(int code) throws Exception;

    //根据时间和景区id查询车辆数量
    public int getNumByCodeAndTime(ParkingCar parkingCar) throws Exception;
}
