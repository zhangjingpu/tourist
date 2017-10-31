package com.ssm.maven.core.admin;

import com.ssm.maven.core.entity.DiCustom;
import com.ssm.maven.core.entity.DiTable;
import com.ssm.maven.core.entity.PageBean;
import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.service.CarService;
import com.ssm.maven.core.service.TemperatureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/temperature")
public class TemperatureController {
    @Resource
    private TemperatureService temperatureService;
    @Resource
    private CarService carService;

    /**
     * 显示所有的景区Di
     *
     * @return
     */
    @RequestMapping("getAllScinceDI")
    @ResponseBody
    public ModelAndView getAllScinceDI() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<Scenicspot> scenicspot = carService.getAllScenicspot();
        List<DiCustom> diCustoms = temperatureService.getAllScinceDI();
        System.out.println("总共数据量：" + diCustoms.size());
        modelAndView.addObject("scenicspot", scenicspot);
        modelAndView.addObject("diCustoms", diCustoms);
        modelAndView.setViewName("views/weather");
        return modelAndView;
    }

    /**
     * 第一页
     */
    @RequestMapping("getCurrentTempPage")
    @ResponseBody
    public PageBean<DiCustom> getScenicAll(Integer pageIndex, Integer pageSize, DiCustom diCustom) throws Exception {
        List list = temperatureService.getCurrentPage(pageIndex, pageSize, diCustom);
        PageBean<DiCustom> pb = new PageBean<DiCustom>(list);
        return pb;
    }

    /**
     * 查询页表
     */
    @RequestMapping("findScenicAndTime")
    @ResponseBody
    public PageBean<DiCustom> findScenicAndTime(Integer pageIndex, Integer pageSize, String scenicname, String start_time, String end_time) throws Exception {
        DiCustom diCustom = new DiCustom();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (start_time != null && start_time != "") {
            java.util.Date start_time1 = sdf.parse(start_time);
            diCustom.setStart_time(start_time1);
        }
        if (end_time != null && end_time != "") {
            java.util.Date end_time1 = sdf.parse(end_time);
            diCustom.setEnd_time(end_time1);
        }
        diCustom.setScenicname(scenicname);

        List list = temperatureService.findScenicAndTime(pageIndex, pageSize, diCustom);
        PageBean<DiCustom> pb = new PageBean<DiCustom>(list);
        return pb;
    }

    /**
     * 获取指定景区当天的气温信息
     */
    @RequestMapping("getAllTemperature")
    @ResponseBody
    public ModelAndView getAllTemperature(String name, String time, String code) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        DiTable diTable = new DiTable();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time1 = sdf.parse(time);
        diTable.setTime_date(time1);
        int num = Integer.parseInt(code);
        diTable.setScenic_id(num);
        System.out.print(diTable.getScenic_id() + " " + diTable.getTime_date());
        List<DiTable> diTable1 = temperatureService.getAllTemperature(diTable);
        String scenic_id = "";
        if (diTable1.size() > 0) {
            scenic_id = String.valueOf(diTable1.get(0).getScenic_id());

        }
        modelAndView.addObject("scenic_id", scenic_id);
        modelAndView.addObject("scincename", name);
        modelAndView.addObject("time", time);
        modelAndView.addObject("diTable", diTable1);
        modelAndView.setViewName("views/weather/temperature");
        return modelAndView;
    }

    /**
     * 气温第一页
     */
    @RequestMapping("getTemCuPage")
    public @ResponseBody
    PageBean<DiTable> getTemCuPage(Integer pageIndex, Integer pageSize, String scenic_id, String time) throws Exception {
        DiTable diTable = new DiTable();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time1 = sdf.parse(time);
        diTable.setTime_date(time1);
        int i = Integer.parseInt(scenic_id);
        diTable.setScenic_id(i);
        List list = temperatureService.getTemPage(pageIndex, pageSize, diTable);
        PageBean<DiTable> pb = new PageBean<DiTable>(list);
        return pb;
    }

    /**
     * 气温编辑
     */
    @RequestMapping("getTemByTimeAndId")
    public @ResponseBody
    ModelAndView getTemByTimeAndId(String code, String day, String hour) throws Exception {
        System.out.println(code + " " + day + " " + hour);
        ModelAndView modelAndView = new ModelAndView();
        DiTable diTable = new DiTable();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time1 = sdf.parse(day);
        diTable.setTime_date(time1);
        int i = Integer.parseInt(hour);
        diTable.setTime_hour(i);
        int j = Integer.parseInt(code);
        diTable.setScenic_id(j);
        DiTable diTable1 = temperatureService.getTemByTimeAndId(diTable);
        modelAndView.addObject("diTable", diTable1);
        modelAndView.setViewName("views/weather/temEdit");
        return modelAndView;
    }

    @RequestMapping("editTem")
    public @ResponseBody
    ModelAndView editTem(ModelMap model, DiTable diTable) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        temperatureService.editTem(diTable);
        String str = "编辑成功";
        model.put("Msg", str);
        modelAndView.setViewName("views/weather/temEdit");
        return modelAndView;
    }

    /**
     * 跳转到添加页面
     */
    @RequestMapping("addForm")
    public @ResponseBody
    ModelAndView addForm(String code, String day) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        int i = Integer.parseInt(code);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date time = sdf.parse(day);
        modelAndView.addObject("scenic_id", i);
        modelAndView.addObject("time_date", time);
        modelAndView.setViewName("views/weather/temAdd");
        return modelAndView;
    }

    /**
     * 添加时间天气
     */
    @RequestMapping("addTem")
    public @ResponseBody
    ModelAndView addTem(ModelMap model, DiTable diTable) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println("信息1");
        List<DiTable> diTable1 = temperatureService.selectTemp(diTable);
        String result = "error";
        if (diTable1.size() == 0) {
            temperatureService.addTep(diTable);
            result = "success";
        }
        model.put("Msg", result);
        modelAndView.setViewName("views/weather/temAdd");
        return modelAndView;
    }

    /**
     * 删除时间天气
     */
    @RequestMapping("delTem")
    public @ResponseBody
    String delTem(int id) throws Exception {
        DiTable diTable = new DiTable();
        diTable.setId(id);
        temperatureService.delTem(diTable);
        String str = "success";
        return str;
    }

    /**
     * 删除景区某天的时间所有天气数据
     */
    @RequestMapping("deleletScenic")
    public @ResponseBody
    String deleletScenic(String code, String time) throws Exception {
        String str = "success";
        DiTable diTable = new DiTable();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time1 = sdf.parse(time); //转换为util.date
        diTable.setTime_date(time1);
        int i = Integer.parseInt(code);
        diTable.setScenic_id(i);
        temperatureService.deleteScenic(diTable);
        System.out.println("山吹成功");
        return str;
    }
}
