package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.DiCustom;
import com.ssm.maven.core.entity.DiTable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureDao {
    /**
     * 查询所有的DI表（以日期为标准）
     */
    public List<DiCustom> getAllScinceDI() throws Exception;

    /**
     * 查询
     *
     * @return
     * @throws Exception
     */
    public List<DiCustom> findScenicAndTime(DiCustom diCustom) throws Exception;

    /**
     * 获取指定景区当天的气温信息
     */
    public List<DiTable> getAllTemperatures(DiTable diTable) throws Exception;

    /**
     * 获取指定小时的温度信息
     */
    public DiTable getTemByTimeAndId(DiTable diTable) throws Exception;

    /**
     * 修改指定的小时温度信息
     */
    public void editTem(DiTable diTable) throws Exception;

    /**
     * 添加时间天气
     */
    public void addTep(DiTable diTable) throws Exception;

    public List<DiTable> selectTemp(DiTable diTable) throws Exception;

    /**
     * 删除时间天气
     */
    public void delTem(DiTable diTable) throws Exception;

    /**
     * 删除景区天气
     */
    public void deleletScenic(DiTable diTable) throws Exception;

    public void insertCarBatch(List<DiTable> diTables) throws Exception;

    public List<DiCustom> getAllDI() throws Exception;
}