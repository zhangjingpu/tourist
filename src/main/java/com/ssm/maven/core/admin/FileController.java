package com.ssm.maven.core.admin;

import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import com.ssm.maven.core.entity.*;
import com.ssm.maven.core.service.ScenicService;
import com.ssm.maven.core.service.TouristService;
import com.ssm.maven.core.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("File")
public class FileController {

    private static Log log = LogFactory.getLog(FileController.class);
    @Resource
    private ScenicService scenicService;
    @Resource
    private TouristService touristService;

    @RequestMapping("importfile")
    public @ResponseBody
    String batchimport(MultipartFile file1,
                       HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        log.info("ClientController ..batchimport() start");
        System.out.println("进入！");
        SysUser sysUser = (SysUser) session.getAttribute("currentUser");
        String Msg = null;
        boolean b = false;
        //System.out.println(file1.isEmpty());
        //判断文件是否为空
        if (file1 == null) {
            Msg = "文件是为空！";
            request.getSession().setAttribute("Msg", Msg);
            return "views/scenicarea";
        }

        //获取文件名
        String name = file1.getOriginalFilename();
        System.out.println(name);
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）验证文件名是否合格
        long size = file1.getSize();
        if (name == null || ("").equals(name) && size == 0 && !WDWUtil.validateExcel(name)) {
            Msg = "文件格式不正确！请使用.xls或.xlsx后缀文档。";
            request.getSession().setAttribute("msg", Msg);
            return "views/scenicarea";
        }

        //创建处理EXCEL
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取客户信息集合。
        List<Scenicspot> ScenicspotList = readExcel.getExcelInfo(file1);
        //System.out.println(ScenicspotList.isEmpty());
        if (ScenicspotList != null && !ScenicspotList.toString().equals("[]") && ScenicspotList.size() >= 1) {
            b = true;
        }

        if (b) {
            //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            for (Scenicspot scenicspot : ScenicspotList) {
                //这里可以做添加数据库的功能
                System.out.println(scenicspot.toString());
                scenicspot.setCode(UUIDTool.getUUID());
                scenicspot.setCreate_time(new Date());
                scenicspot.setUpdate_time(new Date());
                scenicspot.setCreator(sysUser.getId());
                scenicspot.setUpdator(sysUser.getId());
            }
            try {
                scenicService.insertScenicspotList(ScenicspotList);
                System.out.println("插入！");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Msg = "批量导入EXCEL成功！";
            request.getSession().setAttribute("Msg", Msg);
        } else {
            Msg = "批量导入EXCEL失败！";
            request.getSession().setAttribute("Msg", Msg);
        }
        return "success";
    }

    @RequestMapping("exportScenicExcel")
    public void exportScenicExcel(HttpServletResponse response) {
        Map<String, Object> conditionMap = null;
        List<Scenicspot> dataSet = null;
        LeadingOutExcel leadingOutExcel = null;    //工具类

        //配置信息
        String fileName = "所有景区信息" + new Date();
        String format = "yyyy-MM-dd";
        String title = "景区信息";
        String[] rowName = {"景区编号", "景区名称", "地址", "联系电话", "最大承载量", "创建时间", "状态", "舒适度阈值", "景区类型"};


        Scenicspot scenicspot = new Scenicspot();
        dataSet = scenicService.getScenicspotAll(scenicspot);

        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < dataSet.size(); i++) {
            Scenicspot sc = dataSet.get(i);
            objs = new Object[rowName.length];
            objs[0] = sc.getCode();
            objs[1] = sc.getScenicname();
            objs[2] = sc.getAddress();
            objs[3] = sc.getTelephone();
            //日期类型处理
            Date date = sc.getCreate_time();
            String dateStr = "";
            if (date != null) {
                SimpleDateFormat df = new SimpleDateFormat(format);
                dateStr = df.format(date);
            }
            objs[5] = dateStr;
            objs[4] = sc.getMax_people();
            Integer status = sc.getStatus();
            if (status.equals(1)) {
                objs[6] = "开放";
            } else {
                objs[6] = "关闭";
            }
            objs[7] = sc.getMax_di();
            objs[8] = sc.getScenictype();
            dataList.add(objs);
        }
        System.out.println("进入文件导出");
        leadingOutExcel = new LeadingOutExcel(fileName, title, rowName, dataList, response);
        try {
            leadingOutExcel.export();
        } catch (Exception e) {
            log.error("写入Excle出错！", e);
        }
    }

    @RequestMapping("importfilePeople")
    public @ResponseBody
    String importfilePeople(MultipartFile file1,
                            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        log.info("ClientController ..batchimport() start");
        System.out.println("进入游客！");
        SysUser sysUser = (SysUser) session.getAttribute("currentUser");
        String Msg = null;
        boolean b = false;
        System.out.println(file1.isEmpty());
        //判断文件是否为空
        if (file1 == null) {
            Msg = "文件是为空！";
            request.getSession().setAttribute("Msg", Msg);
            return "views/people";
        }

        //获取文件名
        String name = file1.getOriginalFilename();
        System.out.println(name);
        //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）验证文件名是否合格
        long size = file1.getSize();
        if (name == null || ("").equals(name) && size == 0 && !WDWUtil.validateExcel(name)) {
            Msg = "文件格式不正确！请使用.xls或.xlsx后缀文档。";
            request.getSession().setAttribute("msg", Msg);
            return "views/scenicarea";
        }

        //创建处理EXCEL
        ReadExcelTourist readExcel = new ReadExcelTourist();
        //解析excel，获取客户信息集合。
        List<Tourist> TouristList = readExcel.getExcelInfo(file1);
        System.out.println(TouristList.isEmpty());
        if (TouristList != null && !TouristList.toString().equals("[]") && TouristList.size() >= 1) {
            b = true;
        }

        if (b) {
            //迭代添加客户信息（注：实际上这里也可以直接将customerList集合作为参数，在Mybatis的相应映射文件中使用foreach标签进行批量添加。）
            for (Tourist ts : TouristList) {
                //这里可以做添加数据库的功能
                System.out.println(ts.toString());
                ts.setTourist_code(UUIDTool.getUUID());
                ts.setDel_flag(1);
            }
            try {
                touristService.insertTouristBatch(TouristList);
                System.out.println("插入！");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Msg = "批量导入EXCEL成功！";
            request.getSession().setAttribute("Msg", Msg);
        } else {
            Msg = "批量导入EXCEL失败！";
            request.getSession().setAttribute("Msg", Msg);
        }
        return "success";
    }

    @RequestMapping("exportTouristExcelAll")
    public void exportTouristExcelAll(HttpServletResponse response) {
        Map<String, Object> conditionMap = null;
        List<TouristCustom> dataSet = null;
        LeadingOutExcel leadingOutExcel = null;    //工具类

        //配置信息
        String fileName = "人流详细信息" + new Date();
        String format = "yyyy-MM-dd";
        String format_1 = "yyyy-MM-dd hh:mm:ss";
        String title = "人流信息";
        String[] rowName = {"人流编号", "人流类型", "日期", "景区编号", "景区名称", "进入时间", "离开时间"};
        TouristCustom touristCustom = new TouristCustom();
        try {
            dataSet = touristService.getTouristList(touristCustom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < dataSet.size(); i++) {
            TouristCustom sc = dataSet.get(i);
            objs = new Object[rowName.length];
            objs[0] = sc.getTourist_code();
            objs[1] = sc.getTourist_type();
            //日期类型处理
            Date date = sc.getEnter_day();
            String dateStr = "";
            if (date != null) {
                SimpleDateFormat df = new SimpleDateFormat(format);
                dateStr = df.format(date);
            }
            objs[2] = dateStr;
            objs[4] = sc.getScenicname();
            objs[3] = sc.getCode();
            Date date_1 = sc.getEnter_time();
            String dateStr_1 = "";
            if (date_1 != null) {
                SimpleDateFormat df = new SimpleDateFormat(format_1);
                dateStr_1 = df.format(date);
            }
            objs[5] = dateStr_1;
            date_1 = sc.getLeave_time();
            dateStr_1 = " ";
            if (date_1 != null) {
                SimpleDateFormat df = new SimpleDateFormat(format_1);
                dateStr_1 = df.format(date);
            }
            objs[6] = dateStr_1;
            dataList.add(objs);

        }
        System.out.println("人流信息文件导出");
        leadingOutExcel = new LeadingOutExcel(fileName, title, rowName, dataList, response);
        try {
            leadingOutExcel.export();
        } catch (Exception e) {
            log.error("写入Excle出错！", e);
        }
    }

    @RequestMapping("exportTouristExcel")
    public void exportTouristExcel(HttpServletResponse response) {
        Map<String, Object> conditionMap = null;
        List<ScenicspotCustom> dataSet = null;
        LeadingOutExcel leadingOutExcel = null;    //工具类

        //配置信息
        String fileName = "人流汇总信息" + new Date();
        String format = "yyyy-MM-dd";
        String format_1 = "yyyy-MM-dd hh:mm:ss";
        String title = "人流信息";
        String[] rowName = {"景区编号", "景区名称", "地址", "日期", "承载量"};
        ScenicspotCustom touristCustom = new ScenicspotCustom();
        try {
            dataSet = scenicService.getScenicspotAndDay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < dataSet.size(); i++) {
            ScenicspotCustom sc = dataSet.get(i);
            objs = new Object[rowName.length];
            objs[0] = sc.getCode();
            objs[1] = sc.getScenicname();
            objs[2] = sc.getAddress();
            //日期类型处理
            Date date = sc.getEnter_day();
            String dateStr = "";
            if (date != null) {
                SimpleDateFormat df = new SimpleDateFormat(format);
                dateStr = df.format(date);
            }
            objs[3] = dateStr;
            objs[4] = sc.getMax_people();
            dataList.add(objs);
        }
        System.out.println("人流信息文件汇总导出");
        leadingOutExcel = new LeadingOutExcel(fileName, title, rowName, dataList, response);
        try {
            leadingOutExcel.export();
        } catch (Exception e) {
            log.error("写入Excle出错！", e);
        }
    }


    @RequestMapping("exportScenicTypeExcel")
    public void exportScenicTypeExcel(HttpServletResponse response) {
        Map<String, Object> conditionMap = null;
        List<ScenicspotCustom> dataSet = null;
        LeadingOutExcel leadingOutExcel = null;    //工具类

        //配置信息
        String fileName = "景区类型信息" + new Date();
        String format = "yyyy-MM-dd";
        String format_1 = "yyyy-MM-dd hh:mm:ss";
        String title = "人流信息";
        String[] rowName = {"景区编号", "景区类型名称", "因子元素", "因子元素值"};
        ScenicspotCustom touristCustom = new ScenicspotCustom();
        try {
            dataSet = scenicService.getScenicspotAndDay();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Object[]> dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        for (int i = 0; i < dataSet.size(); i++) {
            ScenicspotCustom sc = dataSet.get(i);
            objs = new Object[rowName.length];
            objs[0] = sc.getCode();
            objs[1] = sc.getScenicname();
            objs[2] = sc.getAddress();
            //日期类型处理
            Date date = sc.getEnter_day();
            String dateStr = "";
            if (date != null) {
                SimpleDateFormat df = new SimpleDateFormat(format);
                dateStr = df.format(date);
            }
            objs[3] = dateStr;
            objs[4] = sc.getMax_people();
            dataList.add(objs);
        }
        System.out.println("人流信息文件汇总导出");
        leadingOutExcel = new LeadingOutExcel(fileName, title, rowName, dataList, response);
        try {
            leadingOutExcel.export();
        } catch (Exception e) {
            log.error("写入Excle出错！", e);
        }
    }

}
