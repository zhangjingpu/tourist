package com.ssm.maven.core.admin;

import com.ssm.maven.core.entity.*;
import com.ssm.maven.core.service.ScenicService;
import com.ssm.maven.core.service.ScenictypeService;
import com.ssm.maven.core.util.UUIDTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("/scenic")
@Controller
public class ScenicController {

    @Resource
    private ScenicService scenicService;
    @Resource
    private Scenicspot scenicspot;
    @Resource
    private ScenictypeService scenictypeService;

    @RequestMapping("getScenicAll")
    public @ResponseBody
    PageBean<ScenicspotCustom> getScenicAll(Integer pageIndex, Integer pageSize, String name) throws Exception {
        //System.out.println("页数"+pageIndex+pageSize);
        scenicspot.setScenicname(name);
        List list = scenicService.getScenicspotAll(pageIndex, pageSize, scenicspot);
        PageBean<ScenicspotCustom> pb = new PageBean<ScenicspotCustom>(list);
        System.out.println(name);
        //System.out.println(pb.getCount());
        return pb;
    }

    @RequestMapping("getScenicAdd")
    public ModelAndView getScenicAdd(ModelMap model) throws Exception {
        ModelAndView mv = new ModelAndView();
        Scenictype scenictype = new Scenictype();
        List<Scenictype> list = scenictypeService.selectScenictypeAll(scenictype);
        model.put("list", list);
        mv.setViewName("views/scenic/scenic_add");
        return mv;
    }

    @RequestMapping("getScenicedit")
    public ModelAndView getScenicedit(ModelMap model, String code) throws Exception {
        System.out.println(code);
        ModelAndView mv = new ModelAndView();
        scenicspot = scenicService.getScenicspotByCode(code);
        System.out.println(scenicspot.toString());
        Scenictype scenictype = new Scenictype();
        List<Scenictype> list1 = scenictypeService.selectScenictypeAll(scenictype);
        model.put("list", list1);
        model.put("sc", scenicspot);
        mv.setViewName("views/scenic/scenic_edit");
        return mv;
    }

    @RequestMapping("getScenicMenu")
    public ModelAndView getScenicMenu(Model model) throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Scenicspot> list = scenicService.getScenicspotAll(scenicspot);
        model.addAttribute("list", list);
        mv.setViewName("views/scenicarea");
        return mv;
    }

    @RequestMapping("getScenicByName")
    public @ResponseBody
    PageBean<Scenicspot> getScenicByName(@RequestParam("scenicname") String scenicname, Integer pageIndex, Integer pageSize) throws Exception {
        //System.out.println(scenicname);
        List list = scenicService.getScenicspotByName(pageIndex, pageSize, scenicname);
        PageBean<Scenicspot> pb = new PageBean<Scenicspot>(list);
        return pb;
    }

    @RequestMapping("getScenicByCode")
    public @ResponseBody
    PageBean<Scenicspot> getScenicByCode(@RequestParam("code") String code, Integer pageIndex, Integer pageSize) throws Exception {
        System.out.println("code" + code);
        List list = scenicService.getScenicspotlistByCode(pageIndex, pageSize, code);
        PageBean<Scenicspot> pb = new PageBean<Scenicspot>(list);
        System.out.println(pb.toString());
        return pb;
    }

    @RequestMapping("insertScenic")
    public ModelAndView getScenicAdd(Scenicspot scenicspot, ModelMap modelmap, HttpServletRequest request) throws Exception {
        System.out.println(scenicspot.toString());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/scenic/scenic_add");
        String str = "error";
        HttpSession session = request.getSession();
        SysUser user = (SysUser) session.getAttribute("currentUser");
        //System.out.println(user.toString());
        scenicspot.setCreator(user.getId());
        scenicspot.setUpdator(user.getId());
        String uuid = UUIDTool.getUUID();
        // System.out.println(uuid);
        scenicspot.setCode(uuid);
        //System.out.println(scenicspot.getCode());
        scenicspot.setCreate_time(new Date());
        scenicspot.setUpdate_time(new Date());
        scenicspot.setDel_flag(1);
        //System.out.println("进入插入!");
        //System.out.println(scenicspot.toString());
        List<Scenicspot> list = scenicService.getScenicspotByName(1, 10, scenicspot.getScenicname());
        if (list.isEmpty()) {
            try {
                scenicService.insertSelective(scenicspot);
                str = "插入景区信息成功！";
                Scenictype scenictype = new Scenictype();
                List<Scenictype> list1 = scenictypeService.selectScenictypeAll(scenictype);
                modelmap.put("list", list1);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("插入景区信息错误");
                str = "插入信息失败！";
            }
        } else {
            System.out.println("failed");
            str = "景区已经存在！";
        }
        modelmap.put("sc", scenicspot);
        modelmap.put("errorMsg", str);
        return mv;
    }

    @RequestMapping("editScenicMenu")
    public ModelAndView editScenicMenu(ModelMap model, Scenicspot scenicspot, HttpServletRequest request) throws Exception {
        System.out.println("进入修改!");
        //String a = request.getParameter("address");
        // System.out.println(a + "sada");
        System.out.println(scenicspot.toString());
        ModelAndView mv = new ModelAndView();
        String str = "error";

        try {
            scenicService.updateByCode(scenicspot);
            scenicspot = scenicService.getScenicspotByCode(scenicspot.getCode());
            System.out.println("修改成功！");
            str = "景区信息更新成功！";
        } catch (Exception e) {
            e.printStackTrace();
            str = "景区信息更新失败!";
        }

        model.put("Msg", str);
        model.put("sc", scenicspot);

        mv.setViewName("views/scenic/scenic_edit");
        return mv;
    }

    @RequestMapping("deleteScenicByCode")
    public @ResponseBody
    String deleteScenicByCode(String code) {
        String str = code;
        try {
            scenicService.deleteByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
            str = "error";
        }
        return str;
    }
}
