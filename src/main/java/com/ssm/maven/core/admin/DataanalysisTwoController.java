package com.ssm.maven.core.admin;


import com.ssm.maven.core.entity.*;
import com.ssm.maven.core.service.CarService;
import com.ssm.maven.core.service.ScenicService;
import com.ssm.maven.core.service.TemperatureService;
import com.ssm.maven.core.service.TouristService;
import com.ssm.maven.core.util.DIUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("DataTwo")
public class DataanalysisTwoController {
    @Resource
    private ScenicService scenicService;
    @Resource
    private TemperatureService temperatureService;
    @Resource
    private TouristService touristService;
    @Resource
    private CarService carService;
    @RequestMapping("getCDTable")
    public ModelAndView getDITable(ModelMap model, String scenic_id, String enter_time) throws Exception {
        System.out.println("获得数据" + scenic_id + " " + enter_time + "1111111111");
        ModelAndView mv = new ModelAndView();
        model.put("scenic_id", scenic_id);
        model.put("enter_day", enter_time);
        mv.setViewName("views/table/CDTable");
        return mv;
    }

    /*
    \
    获得总舒适度的信息
     */
    @RequestMapping("getCDInfor")
    public @ResponseBody
    List<CDBean> getCDInfor(String enter_time, String scenic_id) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        DiTable diTable = new DiTable();
        List<DiTable> diTables = new ArrayList<>();
        TouristCustom touristCustom = new TouristCustom();
        List<TouristCustom> touristCustoms = new ArrayList<>();
        ParkingCar parkingCar = new ParkingCar();
        List<ParkingCar> parkingCars = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date day = sdf.parse(enter_time);
        Integer id = Integer.parseInt(scenic_id);
        //天气的信息
        diTable.setScenic_id(id);
        diTable.setTime_date(day);
        diTables = temperatureService.getAllTemperature(diTable);
        System.out.println(diTables.size() + "天气");
        //人流的信息
        touristCustom.setScience_id(id);
        touristCustom.setEnter_day(day);
        touristCustoms = touristService.getTouristList(touristCustom);
        System.out.println(touristCustoms.size() + "人流");
        //车流的信息
        parkingCar.setScience_id(id);
        parkingCar.setDay(day);
        parkingCars = carService.findAllCarByCodeAndTime(parkingCar);
        System.out.println(parkingCars.size() + "车辆");
        double CD = 0.0;
        double celsius = 0.0;
        double relative_humidity = 0.0;
        int people = 0;
        int car = 0;
        Double DI = 0.0;
        double HLC = 0.0;
        double VLC = 0.0;
        int numpeople = 0;
        int numcar = 0;
        List<CDBean> cdBeanList = new ArrayList<CDBean>();
        String datetime1 = enter_time;
        Scenicspot scenicspot = new Scenicspot();
        scenicspot = carService.getNumberByCode(id);
        System.out.println("最大车流量" + scenicspot.getMax_car() + "人流" + scenicspot.getMax_people());
        for (int i = 0; i < 24; i++) {
            //天气CD
            for (int j = 0; j < diTables.size(); j++) {
                if (diTables.get(j).getTime_hour() == i) {
                    celsius = diTables.get(j).getCelsius();
                    relative_humidity = diTables.get(j).getRelative_humidity();
                    DI = DIUtil.returnDI(celsius, relative_humidity) / 100;

                }
            }

            if (i < 10) {
                datetime1 = enter_time + " 0" + i + ":00:00";
            } else {
                datetime1 = enter_time + " " + i + ":00:00";
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date datetime = sdf1.parse(datetime1);
            //人流
            Tourist tourist1 = new Tourist();
            tourist1.setEnter_time(datetime);
            tourist1.setScience_id(id);
            numpeople = touristService.getNumByCodeAndTime(tourist1);
            HLC = numpeople / scenicspot.getMax_people();

            //车流
            ParkingCar parkingCar1 = new ParkingCar();
            parkingCar1.setEnter_time(datetime);
            parkingCar1.setScience_id(id);
            numcar = carService.getNumByCodeAndTime(parkingCar1);
            VLC = numcar / scenicspot.getMax_car();

            CD = DIUtil.retrunCD(DI, HLC, VLC);

            CDBean cdBean = new CDBean();
            cdBean.setHour(i);
            cdBean.setCd(CD);
            cdBeanList.add(cdBean);
            CD = 0.0;
            celsius = 0.0;
            relative_humidity = 0.0;
            people = 0;
            car = 0;
            DI = 0.0;
            HLC = 0.0;
            VLC = 0.0;
            numpeople = 0;
            numcar = 0;
            datetime1 = enter_time;
        }
        return cdBeanList;
    }

    /**
     * 车辆信息
     */
    @RequestMapping("getVLCTable")
    public ModelAndView getVLCTable(ModelMap model, String scenic_id, String enter_time) throws Exception {
        ModelAndView mv = new ModelAndView();
        model.put("scenic_id", scenic_id);
        model.put("enter_day", enter_time);
        mv.setViewName("views/table/VLCTable");
        return mv;
    }

    @RequestMapping("getVLCInfo")
    public @ResponseBody
    List<VLCBean> getVLCInfor(String enter_time, String scenic_id) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date day = sdf.parse(enter_time);
        Integer id = Integer.parseInt(scenic_id);
        List<ParkingCar> parkingCarList = new ArrayList<>();
        ParkingCar parkingCar = new ParkingCar();
        parkingCarList = carService.findAllCarByCodeAndTime(parkingCar);
        Scenicspot scenicspot = new Scenicspot();
        scenicspot = carService.getNumberByCode(id);
        int carMax = 0;
        carMax = scenicspot.getMax_car();
        List<VLCBean> vlcBeanList = new ArrayList<VLCBean>();
        VLCBean vlcBean = null;
        String datetime1 = enter_time;
        int carnum = 0;
        float vlc = 0.0f;
        for (int i = 0; i < 24; i++) {
            vlcBean = new VLCBean();
            if (i < 10) {
                datetime1 = enter_time + " 0" + i + ":00:00";
            } else {
                datetime1 = enter_time + " " + i + ":00:00";
            }
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date datetime = sdf1.parse(datetime1);
            ParkingCar parkingCar1 = new ParkingCar();
            parkingCar1.setEnter_time(datetime);
            parkingCar1.setScience_id(id);
            carnum = carService.getNumByCodeAndTime(parkingCar1);
            vlc = (float) carnum / carMax;
            BigDecimal b = new BigDecimal(vlc);
            float f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            vlcBean.setVf(carnum);
            vlcBean.setTime_hour(String.valueOf(i));
            vlcBean.setVlc(f1);
            vlcBean.setMq_v(carMax);
            vlcBeanList.add(vlcBean);
            datetime1 = enter_time;
            carnum = 0;
        }
        return vlcBeanList;
    }
}
