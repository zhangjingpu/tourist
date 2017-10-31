package dao.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.maven.core.dao.CarDao;
import com.ssm.maven.core.entity.CarCustom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration("classpath:spring-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//默认回滚,即此类中的方法即使执行成功,数据也并不会真正的修改,方法执行后会回滚.
public class CarDaoTest {
    @Resource
    private CarDao carDao;

    @Test
    public void testCarDao() {

        try {
            PageHelper.startPage(1, 1);//页数 每页的数量
            List<CarCustom> list = carDao.getAllSpotCar();
            PageInfo<CarCustom> pages = new PageInfo<>(list);
            System.out.println(pages.getList().get(0).getScenicname());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
