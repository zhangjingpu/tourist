package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.CarCustom;
import com.ssm.maven.core.entity.ParkingCar;
import com.ssm.maven.core.entity.Scenicspot;

import java.util.List;

public interface CarService {
    /**
     * 景区查询
     */
    public List<Scenicspot> getAllScenicspot();

    /**
     * 获取所有景区每天车流量的信息
     */
    public List<CarCustom> getAllSpotCar();

    /**
     * 获取当前页面的信息
     */
    public List<CarCustom> getCurrentPage(Integer pageNum, Integer pageSize, CarCustom carCustom);

    /**
     * 查询
     */
    public List<CarCustom> findSpotCarByNameOrTime(Integer pageNum, Integer pageSize, CarCustom carCustom);

    /**
     * 查找
     *
     * @param parkingCar
     * @return
     * @throws Exception
     */
    public List<ParkingCar> findAllCarByCodeAndTime(ParkingCar parkingCar) throws Exception;

    public Integer getAllCarNumber(ParkingCar parkingCar) throws Exception;

    /**
     * 获取当前页面车辆信息
     */
    public List<ParkingCar> getFirstCar(Integer pageNum, Integer pageSize, ParkingCar parkingCar);
    /**
     * 搜索的页面信息
     */
    public List<ParkingCar> findCar(Integer pageNum, Integer pageSize, ParkingCar parkingCar);

    /**
     * 根据车辆的日期和景区code删除
     */
    public void deleteByCodeAndTime(ParkingCar parkingCar);

    public void insertCarBatch(List<ParkingCar> parkingCarList);

    public List<CarCustom> getAllCar();

    public Scenicspot getNumberByCode(int code);

    public int getNumByCodeAndTime(ParkingCar parkingCar);
}
