package dao.test;

import com.alibaba.fastjson.JSON;
import com.ssm.maven.core.dao.MenuDao;
import com.ssm.maven.core.entity.Menu;
import com.ssm.maven.core.entity.MenuBean;
import com.ssm.maven.core.util.MenuUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) //指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration("classpath:spring-context.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
//默认回滚,即此类中的方法即使执行成功,数据也并不会真正的修改,方法执行后会回滚.
public class MenuDaoTest {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private Menu menu;

    @Test
    public void getMenuFather() {
        List<Menu> menulist = new ArrayList<Menu>();
        try {
            menulist = menuDao.findFatherMenu(menu);
            for (Menu mb : menulist) {
                System.out.println(mb.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMenuChildren() {
        List<Menu> menulist = new ArrayList<Menu>();
        try {
            menulist = menuDao.findChildMenu(menu);
            for (Menu mb : menulist) {
                System.out.println(mb.getParent_id());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJson() {
        List<Menu> menulist = new ArrayList<Menu>();
        try {
            menulist = menuDao.findChildMenu(menu);
            String str = JSON.toJSONString(menulist);
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @Test
    public void testJsonMenu() {
        List<Menu> a1 = new ArrayList<Menu>();
        List<Menu> a2 = new ArrayList<Menu>();
        List<MenuBean> a3 = new ArrayList<MenuBean>();
        try {
            a2 = menuDao.findChildMenu(menu);
            a1 = menuDao.findFatherMenu(menu);
            String str = JSON.toJSONString(MenuUtil.getMenuFormat(a1, a2, a3));
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
