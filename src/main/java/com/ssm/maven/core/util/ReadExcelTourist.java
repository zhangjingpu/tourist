package com.ssm.maven.core.util;

import com.ssm.maven.core.entity.Tourist;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wkr
 * @Date 2016-11-18
 * 工具类读取Excel类中内容
 */
public class ReadExcelTourist {
    //总行数
    private int totalRows = 0;
    //总条数
    private int totalCells = 0;
    //错误信息接收器
    private String errorMsg;

    //构造方法
    public ReadExcelTourist() {
    }

    //获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    //获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    //获取错误信息-暂时未用到暂时留着
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 读EXCEL文件，获取客户信息集合
     *
     * @param /fielName
     * @return
     */
    public List<Tourist> getExcelInfo(MultipartFile Mfile) {

        //把spring文件上传的MultipartFile转换成CommonsMultipartFile类型
        CommonsMultipartFile cf = (CommonsMultipartFile) Mfile; //获取本地存储路径
        File file = new File("F:\\fileupload");
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）
        if (!file.exists()) file.mkdirs();
        //新建一个文件
        File file1 = new File("F:\\fileupload\\" + new Date().getTime() + ".xls");
        //将上传的文件写入新建的文件中
        try {
            cf.getFileItem().write(file1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //初始化客户信息的集合
        List<Tourist> touristList = new ArrayList<Tourist>();
        //初始化输入流
        FileInputStream is = null;
        Workbook wb = null;
        try {
            //根据新建的文件实例化输入流
            is = new FileInputStream(file1);
            //根据excel里面的内容读取客户信息

            //当excel是2003时
            wb = new HSSFWorkbook(is);
            //当excel是2007时
            //wb = new XSSFWorkbook(is);

            //读取Excel里面客户的信息
            touristList = readExcelValue(wb);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    is = null;
                    e.printStackTrace();
                }
            }
        }
        return touristList;
    }

    /**
     * 读取Excel里面客户的信息
     *
     * @param wb
     * @return
     */
    private List<Tourist> readExcelValue(Workbook wb) throws ParseException {
        //得到第一个shell
        Sheet sheet = wb.getSheetAt(0);

        //得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();

        //得到Excel的列数(前提是有行数)
        if (totalRows >= 1 && sheet.getRow(0) != null) {//判断行数大于一，并且第一行必须有标题（这里有bug若文件第一行没值就完了）
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        } else {
            return null;
        }

        List<Tourist> touristList = new ArrayList<Tourist>();//声明一个对象集合
        Tourist tourist;//声明一个对象

        //循环Excel行数,从第二行开始。标题不入库
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) continue;
            tourist = new Tourist();

            //循环Excel的列
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat simpleDateFormat_2 = new SimpleDateFormat("yyyy-MM-dd");
            for (int c = 0; c < this.totalCells; c++) {
                Cell cell = row.getCell(c);
                if (null != cell) {
                    if (c == 0) {
                        tourist.setTourist_type(Integer.parseInt(getValue(cell)));//得到行中第一个值
                    } else if (c == 1) {
                        //System.out.println(getValue(cell) instanceof String);
                        tourist.setEnter_day(simpleDateFormat_2.parse(getValue(cell)));//得到行中第二个值
                    } else if (c == 2) {
                        tourist.setScience_id(Integer.parseInt(getValue(cell)));//得到行中第二个值
                    } else if (c == 3) {
                        tourist.setEnter_time(simpleDateFormat.parse(getValue(cell)));//得到行中第三个值
                    } else if (c == 4) {
                        tourist.setLeave_time(simpleDateFormat.parse(getValue(cell)));//得到行中第四个值
                    } else if (c == 5) {
                        tourist.setTime_hour(Integer.parseInt(getValue(cell)));//得到行中第五个值
                    }
                }
            }
            //添加对象到集合中
            touristList.add(tourist);
        }
        return touristList;
    }

    /**
     * 得到Excel表中的值
     *
     * @param cell Excel中的每一个格子
     * @return Excel中每一个格子中的值
     */
    @SuppressWarnings({"static-access", "unused"})
    private String getValue(Cell cell) {
        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            HSSFDataFormatter dataFormatter = new HSSFDataFormatter();
            String cellFormatted = dataFormatter.formatCellValue(cell);
            return cellFormatted.trim();
        } else {
            // 返回字符串类型的值
            return String.valueOf(cell.getStringCellValue());
        }
    }

}