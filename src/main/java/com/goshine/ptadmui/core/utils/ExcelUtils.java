package com.goshine.ptadmui.core.utils;

import java.awt.Color;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
//import org.apache.poi.xssf.usermodel.DefaultIndexedColorMap;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder.BorderSide;
import com.goshine.ptadmui.core.model.ExcelDataModel;
 /**
  * Excel公共导出工具类
  * @author goshine
  */
public class ExcelUtils{
	/**
	 * 导出Excel
	 * @param response
	 * @param fileName
	 * @param model
	 * @throws Exception
	 */
    public static void exportExcel(HttpServletResponse response,String fileName,ExcelDataModel model) throws Exception {
        //告诉浏览器用什么软件可以打开此文件
        response.setHeader("content-Type", "application/vnd.ms-excel");
        //下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fileName, "utf-8"));
        exportExcel(model,response.getOutputStream());
    }

    public static void exportExcel(ExcelDataModel model, OutputStream os) throws Exception {
        XSSFWorkbook wb = new XSSFWorkbook();
        try{
            String sheetName=model.getSheetName();
            if(null==sheetName) {
                sheetName="Sheet1";
            }
            XSSFSheet sheet = wb.createSheet(sheetName);
            writeExcel(wb, sheet,model);
            wb.write(os);
        }finally{
            wb.close();
        }
    }

    private static void writeExcel(XSSFWorkbook wb,Sheet sheet,ExcelDataModel model) {
        int rowIndex = 0;
        rowIndex = writeTitlesToExcel(wb, sheet,model.getTitles());
        writeRowsToExcel(wb, sheet,model.getRows(), rowIndex);
        autoSizeColumns(sheet,model.getTitles().size() + 1);
    }
    
    /**
     * 写表头到Excel
     * @param wb
     * @param sheet
     * @param titles
     * @return
     */
	private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
        int rowIndex = 0;
        int colIndex = 0;

        Font titleFont = wb.createFont();
        titleFont.setFontName("simsun");
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short)14);
        titleFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle titleStyle = wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        titleStyle.setFont(titleFont);
        //设置边框
        //setBorder(titleStyle,BorderStyle.THIN,new XSSFColor(new Color(0,0,0),new DefaultIndexedColorMap()));
        
        Row titleRow = sheet.createRow(rowIndex);
        //titleRow.setHeightInPoints(20);
        colIndex = 0;

        for (String field : titles) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(titleStyle);
            colIndex++;
        }
        rowIndex++;
        return rowIndex;
    }
    
    /**
     * 写数据到Excel
     * @param wb
     * @param sheet
     * @param rows
     * @param rowIndex
     * @return
     */
	private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
        int colIndex = 0;
        Font dataFont = wb.createFont();
        dataFont.setFontName("simsun");
        //dataFont.setFontHeightInPoints((short) 14);
        dataFont.setColor(IndexedColors.BLACK.index);

        XSSFCellStyle dataStyle = wb.createCellStyle();
        dataStyle.setAlignment(HorizontalAlignment.CENTER);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        dataStyle.setFont(dataFont);
        //设置边框
        //setBorder(dataStyle,BorderStyle.THIN,new XSSFColor(new Color(0,0,0),new DefaultIndexedColorMap()));
        for (List<Object> rowData : rows) {
            Row dataRow = sheet.createRow(rowIndex);
            //dataRow.setHeightInPoints(20);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                cell.setCellStyle(dataStyle);
                colIndex++;
            }
            rowIndex++;
        }
        return rowIndex;
    }
    /**
     * 设置单元格列宽
     * @param sheet
     * @param columnNumber
     */
    private static void autoSizeColumns(Sheet sheet, int columnNumber) {
        for (int i = 0; i < columnNumber; i++) {
            int orgWidth = sheet.getColumnWidth(i);
            sheet.autoSizeColumn(i, true);
            int newWidth = (int) (sheet.getColumnWidth(i)+100);
            if (newWidth > orgWidth) {
                sheet.setColumnWidth(i, newWidth);
            } else {
                sheet.setColumnWidth(i, orgWidth);
            }
        }
    }
    /**
     * 设置单元格样式
     * @param style
     * @param border
     * @param color
     */
    private static void setBorder(XSSFCellStyle style,BorderStyle border,XSSFColor color) {
        style.setBorderTop(border);
        style.setBorderLeft(border);
        style.setBorderRight(border);
        style.setBorderBottom(border);
        style.setBorderColor(BorderSide.TOP, color);
        style.setBorderColor(BorderSide.LEFT, color);
        style.setBorderColor(BorderSide.RIGHT, color);
        style.setBorderColor(BorderSide.BOTTOM, color);
    }
}