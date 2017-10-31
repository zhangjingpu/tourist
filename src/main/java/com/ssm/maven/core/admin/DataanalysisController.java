package com.ssm.maven.core.admin;


import com.ssm.maven.core.entity.DiTable;
import com.ssm.maven.core.entity.HLCTable;
import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.entity.TouristCustom;
import com.ssm.maven.core.service.DI_TableService;
import com.ssm.maven.core.service.ScenicService;
import com.ssm.maven.core.service.TouristService;
import com.ssm.maven.core.util.DIUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("Data")
public class DataanalysisController {

    @Resource
    private ScenicService scenicService;
    @Resource
    Scenicspot scenicspot;
    @Resource
    private DI_TableService di_tableService;
    @Resource
    private TouristService touristService;


    @RequestMapping("getScenicMenu")
    public ModelAndView getScenicMenu(Model model) throws Exception {
        System.out.println("进入页面");
        ModelAndView mv = new ModelAndView();
        List<Scenicspot> list = scenicService.getScenicspotAll(scenicspot);
        model.addAttribute("list", list);
        mv.setViewName("views/Dataanalysis");
        return mv;
    }

    @RequestMapping("getDITable")
    public ModelAndView getDITable(ModelMap model, String scenic_id, String enter_time, String scenic_name) throws Exception {
        System.out.println("进入页面");
        System.out.println("获得数据" + scenic_id + " " + enter_time + " " + scenic_name);
        ModelAndView mv = new ModelAndView();
        model.put("scenic_id", scenic_id);
        model.put("enter_day", enter_time);
        model.put("scenic_name", scenic_name);
        mv.setViewName("views/table/DITable");
        return mv;
    }

    @RequestMapping("getHLCTable")
    public ModelAndView getHLCTable(ModelMap model, String scenic_id, String enter_time, String scenic_name) throws Exception {
        //System.out.println("进入页面");
        System.out.println("获得数据 " + scenic_id + "啦啦 " + enter_time + " " + scenic_name);
        ModelAndView mv = new ModelAndView();
        model.put("scenic_id", scenic_id);
        model.put("enter_day", enter_time);
        model.put("scenic_name", scenic_name);
        mv.setViewName("views/table/HLCTable");
        return mv;
    }

    @RequestMapping(value = "getDiInfor")
    public @ResponseBody
    List<DiTable> getDi(String enter_time, String scenic_id) {
        System.out.println("进入di" + enter_time + " " + scenic_id);
        List<DiTable> list = new ArrayList<>();
        DiTable diTable = new DiTable();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date();
        try {
            if (enter_time != null) {
                d1 = simpleDateFormat.parse(enter_time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        diTable.setTime_date(d1);
        diTable.setScenic_id(Integer.valueOf(scenic_id));
        try {
            list = di_tableService.selectByScenic(diTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (DiTable d2 : list) {
            Double di = DIUtil.returnDI(d2.getCelsius(), d2.getRelative_humidity());
            d2.setDi(di);
        }
        return list;
    }

    @RequestMapping(value = "getHLCInfor")
    public @ResponseBody
    List<HLCTable> getHLCInfor(String enter_time, String scenic_id) throws Exception {
        System.out.println("进入HLC" + enter_time + " " + scenic_id);
        List<HLCTable> list = new ArrayList<>();
        List<TouristCustom> list2 = new ArrayList<>();
        TouristCustom touristCustom = new TouristCustom();
        Scenicspot scenicspot = scenicService.getScenicspotById(Integer.parseInt(scenic_id));
        int max_people = scenicspot.getMax_people();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = null;
        try {
            d1 = simpleDateFormat.parse(enter_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        touristCustom.setScience_id(Integer.parseInt(scenic_id));
        touristCustom.setEnter_day(d1);
        try {
            list2 = touristService.searchTouristInfor(touristCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<Integer, HLCTable> map = new HashMap<>();
        for (TouristCustom tc : list2) {

            if (map.get(tc.getTime_hour()) == null) {
                HLCTable hlcTable = new HLCTable(max_people, 1);
                hlcTable.setTime_hour(tc.getTime_hour());
                float HLC = (float) hlcTable.getHf() / (float) hlcTable.getMq_h();
                hlcTable.setHlc(HLC);
                map.put(tc.getTime_hour(), hlcTable);
            } else {
                HLCTable hlcTable = map.get(tc.getTime_hour());
                int count = hlcTable.getHf();
                count++;
                hlcTable.setHf(count);
                float HLC = (float) hlcTable.getHf() / (float) hlcTable.getMq_h();
                hlcTable.setHlc(HLC);
                map.put(tc.getTime_hour(), hlcTable);
                System.out.println("修改");
            }

        }

        Set<Integer> ks = map.keySet();
        Iterator<Integer> it = ks.iterator();
        while (it.hasNext()) {
            Integer key = it.next();
            HLCTable hlcTable = map.get(key);
            list.add(hlcTable);
        }
        Collections.sort(list, new Comparator<HLCTable>() {
            @Override
            public int compare(HLCTable o1, HLCTable o2) {
                int a = o1.getTime_hour();
                int b = o2.getTime_hour();
                if ((a - b) == 0) {
                    return 0;
                }
                return (a - b);
            }
        });


        return list;
    }

}
