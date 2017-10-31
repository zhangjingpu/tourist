package com.ssm.maven.core.dao;

import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.entity.ScenicspotCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScenicDao {

    /*
    查找所有的景区信息
     */
    public List<Scenicspot> getScenicspotAll(Scenicspot scenicspot) throws Exception;

    /*
    查找所有的景区信息
     */
    public List<Scenicspot> getScenicspotByName(String scenicname) throws Exception;

    /*
    插入景区信息
     */
    public void insertSelective(Scenicspot scenicspot) throws Exception;

    /*
    根据code找到景区信息
     */
    public Scenicspot getScenicspotByCode(String str) throws Exception;

    /*
  根据code找到景区信息
   */
    public List<Scenicspot> getScenicspotlistByCode(String code) throws Exception;

    /*
    根据code更新景区信息
     */

    public void updateByCode(Scenicspot scenicspot) throws Exception;


    /*
    根据code删除景区信息
     */

    public void deleteByCode(String code) throws Exception;

    /*
    查询所有景区的信息和日期
     */

    public List<ScenicspotCustom> getScenicspotAndDay() throws Exception;

      /*
    查询景区的信息和日期
     */

    public List<ScenicspotCustom> getpeopleInfor(ScenicspotCustom scenicspotCustom) throws Exception;

    public void insertScenicspotList(List<Scenicspot> scenicspot) throws Exception;

    public Scenicspot getScenicspotById(Integer id) throws Exception;

}