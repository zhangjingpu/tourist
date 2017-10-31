package dao.test;

import com.ssm.maven.core.dao.TouristDao;
import com.ssm.maven.core.entity.ScenicspotCustom;
import com.ssm.maven.core.entity.TouristCustom;
import com.ssm.maven.core.service.TouristService;
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
public class TouristDaoTest {

    @Resource
    private ScenicspotCustom scenicspotCustom;
    @Resource
    private TouristDao touristDao;
    @Resource
    private TouristService touristService;
    @Test
    public void deleteTouristByCode() {
        String str = "2017-07-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date a1 = simpleDateFormat.parse(str);
            scenicspotCustom.setEnter_day(a1);
            scenicspotCustom.setCode("1");
            touristDao.deleteTouristByCode(scenicspotCustom);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void deleteByTouristCode() {


        try {
            TouristCustom touristCustom = new TouristCustom();
            touristCustom.setTourist_code("1");
            touristDao.deleteByTouristCode(touristCustom);
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getTouristList() {
        TouristCustom touristCustom = new TouristCustom();
        String code = "1";
        String str = "2017-07-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date a1 = simpleDateFormat.parse(str);
            // touristCustom.setCode(code);
            // touristCustom.setEnter_day(a1);
            List<TouristCustom> list = touristDao.getTouristList(touristCustom);
            for (TouristCustom tc : list) {
                System.out.println(tc.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTouristInfor() {
        TouristCustom touristCustom = new TouristCustom();
        String tourist_code = "1";
        Integer scenic_id = 1;
        String enter_time = "2017-07-01 00:00:00";
        String leave_time = "2017-07-18 00:00:00";
        touristCustom.setScience_id(scenic_id);
        touristCustom.setTourist_code(tourist_code);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date a1 = simpleDateFormat.parse(enter_time);
            Date a2 = simpleDateFormat.parse(leave_time);
            //touristCustom.setTourist_code(tourist_code);
            //touristCustom.setEnter_day(a1);
            //touristCustom.setLeave_time(a2);
            List<TouristCustom> list = touristDao.searchTouristInfor(touristCustom);
            for (TouristCustom tc : list) {
                System.out.println(tc.toString());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void searchTouristInfor() throws Exception {
        TouristCustom touristCustom = new TouristCustom();
        touristCustom.setTourist_code("1");
        List<TouristCustom> list = touristService.searchTouristInfor(1, 1, touristCustom);
        System.out.println(list.get(0).toString());
    }

    @Test
    public void getCount() throws Exception {
        TouristCustom touristCustom = new TouristCustom();
        touristCustom.setScience_id(2);
        String str = "2017-07-01";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = simpleDateFormat.parse(str);
        touristCustom.setEnter_day(d1);
        Integer a = touristDao.getCount(touristCustom);
        System.out.println(a);
    }


}
