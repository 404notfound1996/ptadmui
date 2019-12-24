package com.goshine.ptadmui.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.goshine.ptadmui.core.model.ExcelDataModel;
import com.goshine.ptadmui.core.utils.ExcelUtils;
/**
 * Excel导出公共接口
 * @author goshine
 */
@Controller
@RequestMapping("/excel")
public class ExcelController{
	/**
	 * 导出Excel
	 * @param response
	 * @throws Exception
	 */
    @RequestMapping(value = "/exportexcel", method = RequestMethod.GET)
    public void excel(HttpServletRequest request,HttpServletResponse response) throws Exception {
        ExcelDataModel data = new ExcelDataModel();
        data.setSheetName("表单1");
        List<String> titles = new ArrayList<String>();
        titles.add("a1");
        titles.add("a2");
        titles.add("a3");
        data.setTitles(titles);

        List<List<Object>> rowList= new ArrayList<List<Object>>();
        List<Object> row1=new ArrayList<Object>();
        row1.add("111");
        row1.add("222");
        row1.add("333");
        rowList.add(row1);
        List<Object> row2=new ArrayList<Object>();
        row2.add("111");
        row2.add("222");
        row2.add("333");
        rowList.add(row2);
        data.setRows(rowList);
        ExcelUtils.exportExcel(response,"hello.xlsx",data);
    }
}
