package dao.test;


import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.maven.core.dao.ScenicDao;
import com.ssm.maven.core.entity.*;
import com.ssm.maven.core.service.ScenicService;
import com.ssm.maven.core.service.ScenictypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration("classpath:spring-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//默认回滚,即此类中的方法即使执行成功,数据也并不会真正的修改,方法执行后会回滚.
public class ScenicDaoTest {

    @Resource
    private ScenicDao scenicDao;

    @Resource
    private Scenicspot scenicspot;

    @Resource
    private ScenicspotCustom scenicspotCustom;

    @Resource
    private ScenicService scenicService;
    @Resource
    private ScenictypeService scenictypeService;

    @Test
    public void testScenicDao() {

        try {
            PageHelper.startPage(3, 1);//页数 每页的数量
            List<Scenicspot> list = scenicDao.getScenicspotAll(scenicspot);
            PageInfo<Scenicspot> pages = new PageInfo<>(list);
            System.out.println(pages.getList().get(0).getScenicname());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getAll() {
        try {
            List<Scenicspot> list = scenicDao.getScenicspotAll(scenicspot);
            for (Scenicspot scenicspot : list) {
                System.out.println(scenicspot.getScenicname());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllScenic() {

        List<Scenicspot> list = scenicService.getScenicspotAll(1, 2, scenicspot);
        System.out.println(list.get(0));
        PageBean<Scenicspot> pb = new PageBean<>(list);
        System.out.println(JSON.toJSONString(pb));
    }

    @Test
    public void getScenicByName() {
        List list = scenicService.getScenicspotByName(1, 1, "石象湖");
        PageBean<Scenicspot> pb = new PageBean<Scenicspot>(list);
        System.out.println(JSON.toJSONString(pb));
    }

    @Test
    public void insertScenic() {
        scenicspot.setCode("123");
        scenicspot.setDel_flag(1);
        scenicspot.setStatus(1);
        scenicspot.setUpdate_time(new Date());
        scenicspot.setCreator(1);
        scenicspot.setTelephone("15928588606");
        scenicspot.setMax_car(100);
        scenicspot.setMax_people(100);
        scenicspot.setScenicname("dadong");
        scenicspot.setAddress("sdads");
        scenicspot.setUpdate_time(new Date());
        scenicspot.setUpdator(1);
        scenicspot.setMax_di(100.00);
        scenicspot.setScenictype(1);
        try {
            scenicDao.insertSelective(scenicspot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getScenicBycode() {
        try {
            scenicspot = scenicDao.getScenicspotByCode("1");
            System.out.println(scenicspot.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void updateScenicBycode() {
        try {
            scenicspot.setCode("1");
            scenicspot.setMax_people(200);
            try {
                scenicDao.updateByCode(scenicspot);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                System.out.println(scenicDao.getScenicspotByCode("1"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void deleteScenicBycode() {
        try {
            scenicDao.deleteByCode("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getScenicspotAndDay() {
        try {
            List<ScenicspotCustom> list = scenicDao.getScenicspotAndDay();
            for (ScenicspotCustom sc : list) {
                System.out.println(sc.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getScenicspotAndDay2() {
        try {
            List<ScenicspotCustom> list = scenicService.getScenicspotAndDay(1, 4);
            for (ScenicspotCustom sc : list) {
                System.out.println(sc.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getpeopleInfor() {
        String code = "1";
        String start = "2017-07-01 00:00:00";
        String end = "2017-07-23 00:00:00";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date a1 = simpleDateFormat.parse(start);
            Date a2 = simpleDateFormat.parse(end);
            //System.out.println(a1+" "+a2);
            scenicspotCustom.setCode(code);
            scenicspotCustom.setEnter_day(a1);
            scenicspotCustom.setEnd_day(a2);
            List<ScenicspotCustom> list = scenicDao.getpeopleInfor(scenicspotCustom);
            for (ScenicspotCustom sc : list) {
                System.out.println(sc.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteByID() {
        ScenictypeFactor scenictypeFactor = new ScenictypeFactor();
        Scenictype scenictype = new Scenictype();
        scenictype.setId(9);
        try {
            scenictypeService.deleteScenicById(scenictype);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
