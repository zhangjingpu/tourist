package com.ssm.maven.core.admin;

import com.ssm.maven.core.entity.PageBean;
import com.ssm.maven.core.entity.Scenicspot;
import com.ssm.maven.core.entity.Scenictype;
import com.ssm.maven.core.entity.ScenictypeFactor;
import com.ssm.maven.core.service.ScenictypeFactorService;
import com.ssm.maven.core.service.ScenictypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("scenictype")
public class scenictypeController {
    @Resource
    private ScenictypeService scenictypeService;
    @Resource
    private ScenictypeFactorService scenictypeFactorService;

    @RequestMapping("getScenictypeMenu")
    public ModelAndView getScenicMenu(ModelMap model) throws Exception {
        ModelAndView mv = new ModelAndView();
        Scenictype scenictype = new Scenictype();
        List<Scenictype> list = scenictypeService.selectScenictypeAll(scenictype);
        model.put("list", list);
        mv.setViewName("views/scenictype");
        return mv;
    }


    @RequestMapping("getScenictypeAdd")
    public ModelAndView getScenicAdd() throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/scenictype/scenictype_add");
        return mv;
    }


    @RequestMapping("getScenictypeAll")
    public @ResponseBody
    PageBean<Scenictype> getScenicAll(Integer pageIndex, Integer pageSize) throws Exception {
        //System.out.println("页数"+pageIndex+pageSize);
        Scenictype scenictype = new Scenictype();
        List<Scenictype> list = scenictypeService.selectScenictypeAll(pageIndex, pageSize, scenictype);
        PageBean<Scenictype> pb = new PageBean<Scenictype>(list);
        //System.out.println(pb.getCount());
        return pb;
    }

    @RequestMapping("getScenicById")
    public @ResponseBody
    PageBean<Scenicspot> getScenictypeById(@RequestParam("id") String id, Integer pageIndex, Integer pageSize) throws Exception {
        System.out.println("id" + id);
        Scenictype scenictype = new Scenictype();
        scenictype.setId(Integer.parseInt(id));
        List list = scenictypeService.selectScenictypeById(pageIndex, pageSize, scenictype);
        PageBean<Scenicspot> pb = new PageBean<Scenicspot>(list);
        System.out.println(pb.toString());
        return pb;
    }

    @RequestMapping("insertScenictype")
    public ModelAndView getScenicAdd(String name, ModelMap modelmap) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/scenictype/scenictype_add");
        String str = "error";
        //System.out.println("进入插入!");
        //System.out.println(scenicspot.toString());
        Scenictype scenictype = new Scenictype();
        //System.out.println("得到名字" + name);
        scenictype.setScenictype_name(name);
        List<Scenictype> list = scenictypeService.selectScenictypeByName(1, 1, scenictype);
        if (list.isEmpty()) {
            try {
                scenictypeService.insertSelective(scenictype);
                str = "插入景区信息成功！";
                modelmap.put("scenictype", scenictype);
            } catch (Exception e) {
                e.printStackTrace();
                //System.out.println("插入景区信息错误");
                str = "插入信息失败！";
            }
        } else {
            System.out.println("failed");
            str = "景区类型已经存在！";
        }
        modelmap.put("errorMsg", str);
        return mv;
    }

    @RequestMapping("geteditScenictypeMenu")
    public ModelAndView geteditScenicMenu(ModelMap model, String id, String name) throws Exception {
        //System.out.println("进入修改!");
        ModelAndView mv = new ModelAndView();
        model.put("id", id);
        model.put("scenictypename", name);
        mv.setViewName("views/scenictype/scenictype_edit");
        return mv;
    }

    @RequestMapping("editScenictypeMenu")
    public ModelAndView editScenicMenu(ModelMap model, String id, String name) throws Exception {
        // System.out.println("修改!");
        String Msg = "修改景区类型成功！";
        ModelAndView mv = new ModelAndView();
        Scenictype scenictype = new Scenictype();
        scenictype.setId(Integer.parseInt(id));
        scenictype.setScenictype_name(name);
        List<Scenictype> list = scenictypeService.selectScenictypeByName(1, 1, scenictype);
        if (list.isEmpty()) {
            scenictypeService.updateByID(scenictype);
            model.put("Msg", Msg);
        } else {
            Msg = "景区类型重复!";
            model.put("Msg", Msg);
        }
        mv.setViewName("views/scenictype/scenictype_edit");
        return mv;
    }

    @RequestMapping("getScenictypeInfor")
    public ModelAndView getScenictypeInfor(ModelMap model, String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        System.out.println("进入因子xin" + id);
        model.put("id", id);
        mv.setViewName("views/scenictype/scenictypefactor");
        return mv;
    }


    @RequestMapping("getScenictypeFactorAll")
    public @ResponseBody
    PageBean<ScenictypeFactor> getScenicFactorAll(Integer pageIndex, Integer pageSize, String id) throws Exception {
        //System.out.println("页数"+pageIndex+pageSize);
        ScenictypeFactor scenictypeFactor = new ScenictypeFactor();
        scenictypeFactor.setScenictype_id(Integer.parseInt(id));
        List<ScenictypeFactor> list = scenictypeFactorService.getScenictypeFactorById(pageIndex, pageSize, scenictypeFactor);
        PageBean<ScenictypeFactor> pb = new PageBean<ScenictypeFactor>(list);
        //System.out.println(pb.getCount());
        return pb;
    }

    @RequestMapping("getScenictypeFactorAdd")
    public ModelAndView getScenictypeFactorAdd(ModelMap model, String id) throws Exception {
        ModelAndView mv = new ModelAndView();
        System.out.println("进入因子" + id);
        model.put("id", id);
        mv.setViewName("views/scenictype/scenictypefactor_add");
        return mv;
    }

    @RequestMapping("insertScenictypeFactor")
    public ModelAndView insertScenictypeFactor(ModelMap modelmap, ScenictypeFactor scenictypeFactor) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("views/scenictype/scenictypefactor_add");
        String str = "error";
        //System.out.println("进入插入!");
        //System.out.println(scenicspot.toString());
        System.out.println(scenictypeFactor.toString());
        ScenictypeFactor s2 = null;
        s2 = scenictypeFactorService.getScenictypeFactorByName(scenictypeFactor);
        if (s2 == null) {
            try {
                scenictypeFactorService.insertScenictypeFactor(scenictypeFactor);
                System.out.println("插入成功!");
                str = "插入景区信息因子成功！";
            } catch (Exception e) {
                e.printStackTrace();
                //System.out.println("插入景区信息错误");
                str = "插入信息因子失败！";
            }
        } else {
            System.out.println("failed");
            str = "景区类型因子已经存在！";
        }
        modelmap.put("errorMsg", str);
        return mv;
    }

    @RequestMapping("deleteScenictypeById")
    public @ResponseBody
    String deleteScenicById(String id) {
        String str = "success";
        try {
            System.out.println("进入删除");
            Scenictype scenictype = new Scenictype();
            scenictype.setId(Integer.parseInt(id));
            ScenictypeFactor scenictypeFactor = new ScenictypeFactor();
            scenictypeFactor.setScenictype_id(scenictype.getId());
            scenictypeService.deleteScenicById(scenictype);
            scenictypeFactorService.deleteScenicFactorByScenicid(scenictypeFactor);
        } catch (Exception e) {
            e.printStackTrace();
            str = "error";
        }
        return str;
    }

    @RequestMapping("deleteScenictypeByScenicId")
    public @ResponseBody
    String deleteScenictypeByScenicId(String id) {
        String str = "success";
        try {
            System.out.println("进入删除");
            ScenictypeFactor scenictypeFactor = new ScenictypeFactor();
            scenictypeFactor.setId(Integer.parseInt(id));
            scenictypeFactorService.deleteScenicFactorById(scenictypeFactor);
        } catch (Exception e) {
            e.printStackTrace();
            str = "error";
        }
        return str;
    }

    @RequestMapping("getScenictypeFactoredit")
    public ModelAndView getScenictypeFactoredit(ModelMap model, ScenictypeFactor scenictypeFactor) throws Exception {
        ModelAndView mv = new ModelAndView();
        System.out.println("进入因子" + scenictypeFactor.toString());
        model.put("id", scenictypeFactor.getId());
        model.put("name", scenictypeFactor.getScenictype_name());
        model.put("value", scenictypeFactor.getScenictype_value());
        mv.setViewName("views/scenictype/scenictypefactor_edit");
        return mv;
    }

    @RequestMapping("editScenictypeFactor")
    public ModelAndView editScenictypeFactor(ModelMap model, ScenictypeFactor scenictypeFactor) throws Exception {
        // System.out.println("修改!");
        String Msg = "修改景区类型成功！";
        System.out.println("进入因子2" + scenictypeFactor.toString());
        ModelAndView mv = new ModelAndView();
        ScenictypeFactor s2 = new ScenictypeFactor();
        s2 = scenictypeFactorService.getScenictypeFactorByName(scenictypeFactor);
        if (s2 == null || s2.getId() == scenictypeFactor.getId()) {
            scenictypeFactorService.updateByPrimaryKeySelective(scenictypeFactor);
            model.put("Msg", Msg);
        } else {
            Msg = "景区已经存在！";
            model.put("Msg", Msg);
        }
        mv.setViewName("views/scenictype/scenictypefactor_edit");
        return mv;
    }






}
