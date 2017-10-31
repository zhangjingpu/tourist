package com.ssm.maven.core.entity;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

/**
 * @author 1034683568@qq.com
 * @project_name ssm-maven
 * @date 2017-3-1
 */
public class PageBean<T> implements Serializable {
    private String rel;
    private String msg;
    private List<T> list;    //结果集
    private Long count;


    /**
     * 包装Page对象，因为直接返回Page对象，在JSON处理以及其他情况下会被当成List来处理，
     * 而出现一些问题。
     *
     * @param list page结果
     *             navigatePages 页码数量
     */
    public PageBean(List<T> list) {
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.rel = "true";
            this.list = page;
            this.msg = "获取成功";
            this.count = page.getTotal();
        }
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "rel='" + rel + '\'' +
                ", msg='" + msg + '\'' +
                ", list=" + list +
                ", count=" + count +
                '}';
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}

