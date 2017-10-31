package com.ssm.maven.core.service;

import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.entity.ScenicspotCustom;

import java.util.List;

public interface ScenicService {

    /**
     * 查询景区信息
     */
    public List<Scenicspot> getScenicspotAll(Integer pageNum, Integer pageSize, Scenicspot scenicspot);

    public List<Scenicspot> getScenicspotAll(Scenicspot scenicspot);

    public List<Scenicspot> getScenicspotByName(Integer pageNum, Integer pageSize, String scenicname);

    /*
插入景区信息
*/
    public void insertSelective(Scenicspot scenicspot) throws Exception;

    /*
根据code找到景区信息
 */
    public Scenicspot getScenicspotByCode(String str) throws Exception;

    public List<Scenicspot> getScenicspotlistByCode(Integer pageNum, Integer pageSize, String code) throws Exception;

        /*
    根据code更新用户信息
     */

    public void updateByCode(Scenicspot scenicspot) throws Exception;


    /*
    根据code删除景区信息
     */

    public void deleteByCode(String code) throws Exception;


    /*
    查询所有景区的信息和日期
     */

    public List<ScenicspotCustom> getScenicspotAndDay(Integer pageIndex, Integer pageSize) throws Exception;

    public List<ScenicspotCustom> getScenicspotAndDay() throws Exception;

     /*
    查询景区的信息和日期
     */

    public List<ScenicspotCustom> getpeopleInfor(Integer pageIndex, Integer pageSize, ScenicspotCustom scenicspotCustom) throws Exception;

    public void insertScenicspotList(List<Scenicspot> scenicspotlist) throws Exception;

    public Scenicspot getScenicspotById(Integer id) throws Exception;



}
