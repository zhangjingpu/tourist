package com.ssm.maven.core.admin;


import com.ssm.maven.core.entity.CarCustom;
import com.ssm.maven.core.entity.PageBean;
import com.ssm.maven.core.entity.ParkingCar;
import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {
    @Resource
    private CarService carService;

    /**
     * 车流量信息页面
     */
    @RequestMapping("getAllScenicspotCar")
    @ResponseBody
    public ModelAndView getAllScenicspot() {
        ModelAndView modelAndView = new ModelAndView();
        List<Scenicspot> scenicspot = carService.getAllScenicspot();
        List<CarCustom> carCustom = carService.getAllSpotCar();
        modelAndView.addObject("carCustom", carCustom);
        modelAndView.addObject("scenicspot", scenicspot);

        modelAndView.setViewName("views/car");
        return modelAndView;
    }

    /**
     * 获取当前页面信息
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("getCurrentCarPage")
    @ResponseBody
    public PageBean<CarCustom> getScenicAll(Integer pageIndex, Integer pageSize, CarCustom carCustom) throws Exception {
        List list = carService.getCurrentPage(pageIndex, pageSize, carCustom);
        PageBean<CarCustom> pb = new PageBean<CarCustom>(list);
        return pb;
    }

    @RequestMapping("findSpotCarByNameOrTime")
    public @ResponseBody
    PageBean<CarCustom> findSpotCarByNameOrTime(Integer pageIndex, Integer pageSize, String scenicname, String start_day, String end_day) throws Exception {
        CarCustom carCustom = new CarCustom();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        if (start_day != null && start_day != "") {
            java.util.Date enter_time1 = sdf.parse(start_day); //转换为util.date
            carCustom.setStart_day(enter_time1);
        }
        if (end_day != null && end_day != "") {
            java.util.Date leave_time1 = sdf.parse(end_day); //转换为util.date
            carCustom.setEnd_day(leave_time1);
        }
        carCustom.setScenicname(scenicname);
        List list = carService.findSpotCarByNameOrTime(pageIndex, pageSize, carCustom);
        PageBean<CarCustom> pb = new PageBean<CarCustom>(list);
        return pb;
    }

    /**
     * 获取指定的景区某一天的车辆信息(详情)
     */
    @RequestMapping("getCarInfoByCodeAndTime")
    public @ResponseBody
    ModelAndView getCarInfoByCodeAndTime(String code, String day, String name) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        ParkingCar parkingCar = new ParkingCar();
        int co = Integer.parseInt(code);
        parkingCar.setScience_id(co);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date_util = sdf.parse(day); //转换为util.date
        parkingCar.setDay(date_util);
        List<ParkingCar> park = carService.findAllCarByCodeAndTime(parkingCar);
        String science_id = "";
        if (park.size() > 0) {
            science_id = String.valueOf(park.get(0).getScience_id());
        }
        int number = carService.getAllCarNumber(parkingCar);
        System.out.println("总数量：" + number);
        modelAndView.addObject("park", park);
        modelAndView.addObject("number", number);
        modelAndView.addObject("scenicname", name);
        modelAndView.addObject("time", day);
        modelAndView.addObject("science_id", science_id);
        modelAndView.setViewName("views/car/carList");
        return modelAndView;
    }

    /**
     * 获取车辆当第一页车辆信息
     *
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping("getCarPage")
    public @ResponseBody
    PageBean<ParkingCar> getFirstCar(Integer pageIndex, Integer pageSize, String science_id, String day) throws Exception {
        ParkingCar parkingCar = new ParkingCar();
        int i = Integer.parseInt(science_id);
        parkingCar.setScience_id(i);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date_util = sdf.parse(day); //转换为util.date
        parkingCar.setDay(date_util);
        List list = carService.getFirstCar(pageIndex, pageSize, parkingCar);
        PageBean<ParkingCar> pb = new PageBean<ParkingCar>(list);
        return pb;
    }

    /**
     * 搜索车辆
     */
    @RequestMapping("findCar")
    public @ResponseBody
    PageBean<ParkingCar> findCar(Integer pageIndex, Integer pageSize, String keyword, String day, String code) throws Exception {
        ParkingCar parkingCar = new ParkingCar();
        parkingCar.setLicense_car(keyword);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date_util = sdf.parse(day); //转换为util.date
        parkingCar.setDay(date_util);
        int science_id = Integer.parseInt(code);
        parkingCar.setScience_id(science_id);
        List list = carService.findCar(pageIndex, pageSize, parkingCar);
        PageBean<ParkingCar> pb = new PageBean<ParkingCar>(list);
        return pb;
    }

    /**
     * 根据景区的code和时间删除当天的车辆信息
     */
    @RequestMapping("deleteByCodeAndTime")
    public @ResponseBody
    String deleteByCodeAndTime(String code, String day) throws Exception {
        String str = "success";
        ParkingCar parkingCar = new ParkingCar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date time = sdf.parse(day); //转换为util.date
        parkingCar.setDay(time);
        int i = Integer.parseInt(code);
        parkingCar.setScience_id(i);
        carService.deleteByCodeAndTime(parkingCar);
        return str;
    }
}
