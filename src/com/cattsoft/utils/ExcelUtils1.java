package com.cattsoft.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExcelUtils1 {


    /**
     * 设置斜线
     * @param wb
     * @param sheet
     */
    public static void setAnchor(Workbook wb, Sheet sheet) {
        CreationHelper helper = wb.getCreationHelper();
        /*HXSSFDrawing drawing = HSSFPatriarch*/
        //HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();

        ClientAnchor anchor = helper.createClientAnchor();

        //设置斜线的开始位置
        anchor.setCol1(0);
        anchor.setRow1(1);

        //设置斜线的结束位置
        anchor.setCol2(1);
        anchor.setRow2(3);

        XSSFSimpleShape shape = drawing.createSimpleShape((XSSFClientAnchor) anchor);

        //设置形状类型为线型
//        shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
//        shape.setLineStyle(HSSFSimpleShape.LINESTYLE_SOLID);

        // 设置形状类型为线型
        shape.setShapeType(ShapeTypes.LINE);

        // 设置线宽
        shape.setLineWidth(0.5);

        // 设置线的风格
        shape.setLineStyle(0);

        // 设置线的颜色
        shape.setLineStyleColor(0, 0, 0);

        // 设置线宽
        //shape.setLineWidth(1);
        // 设置线的颜色
        // shape.setLineStyleColor(0, 0, 0);

    }


    /**
     * 添加单元格
     * @param cellIndex
     * @param style
     * @param valueObject
     */
    public static void addHSSFCell(Sheet sheet, int rowIndex, int cellIndex, CellStyle style, Object valueObject) {
        Cell cell = sheet.getRow(rowIndex).getCell(cellIndex);
        if (style != null){
            cell.setCellStyle(style);
        }
        if(valueObject == null) {
            cell.setCellValue("");
        } else if (valueObject instanceof BigDecimal) {
            cell.setCellValue(Double.parseDouble(valueObject.toString()));
        } else if (valueObject instanceof String) {
            cell.setCellValue((String)valueObject);
        }else if (valueObject instanceof Integer) {
            cell.setCellValue(Double.parseDouble(valueObject.toString()));
        }else if (valueObject instanceof BigInteger) {
            cell.setCellValue(Double.parseDouble(valueObject.toString()));
        }else if (valueObject instanceof Timestamp) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cell.setCellValue(dateFormat.format((Date)valueObject).replaceAll("00:00:00",""));
        } else {
            cell.setCellValue(valueObject.toString());
        }
    }

    /**
     * 合并单元格
     * @param sheet
     * @param firstRow  起始行
     * @param endRow    终止行
     * @param firstCol  起始列
     * @param endCol    终止列
     */
    public static void addMergedRegion(Sheet sheet, int firstRow, int endRow, int firstCol, int endCol) {
        sheet.addMergedRegion(new CellRangeAddress(firstRow, endRow, firstCol, endCol));
    }


    /**
     * 设置单元格宽度
     * @param sheet
     * @param columnIndex
     * @param width
     */
    public static void setColumnWidth(Sheet sheet,int columnIndex,int width) {
        if (columnIndex == 0){
            sheet.setColumnWidth((short) columnIndex, (short) (18 * width));
        }else {
            sheet.setColumnWidth((short) columnIndex, (short) (37 * width));
        }

    }

    /**
     * 表头
     * @param wb
     * @return
     */
    public CellStyle setTitleCellStyle(Workbook wb)
    {
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setColor((short)8);
        font.setFontHeightInPoints((short)12);
        CellStyle style = wb.createCellStyle();
        style.setAlignment((short)2);
        style.setVerticalAlignment((short)1);
        style.setFillForegroundColor((short)55);
        style.setFillPattern((short)1);
        style.setFont(font);

        return style;
    }

    private CellStyle setDiQuCellStyle(Workbook wb) {
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setColor((short)8);
        font.setFontHeightInPoints((short)12);
        CellStyle style = wb.createCellStyle();
        style.setFillForegroundColor(HSSFColor.PALE_BLUE.index); // 设置填充颜色
        style.setFillPattern((short)1);//设置为1用前景色填充单元格
        style.setFont(font);

        return style;
    }

    /**
     *
     * @param wb
     * @param index  单元格颜色 HSSFColor.PALE_BLUE.index:蓝色
     *               HSSFColor.LIGHT_TURQUOISE.index 浅蓝色
     *               IndexedColors.LEMON_CHIFFON.getIndex() 黄色
     *               HSSFColor.LIGHT_GREEN.index  绿色
     * @return
     */
    public static CellStyle setCellStyle(Workbook wb, short index){
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setColor((short)8);
        font.setFontHeightInPoints((short)12);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平对齐
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直对齐
        style.setFillForegroundColor(index); // 设置填充颜色
        style.setFillPattern((short)1);//设置为1用前景色填充单元格
        style.setBorderBottom((short)1);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);
        style.setFont(font);
        return style;
    }

    /**
     * 指定RGB颜色
     * @param wb
     * @return
     */
    private CellStyle setCellStyle(HSSFWorkbook wb,int R,int G,int B) {
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setColor((short)8);
        font.setFontHeightInPoints((short)12);

        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平对齐
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直对齐
        //拿到palette颜色板
        HSSFPalette palette = wb.getCustomPalette();
        // 在画板上获取对应的rgb颜色
        HSSFColor color=null;
        color = palette.findSimilarColor(R, G, B);
        style.setFillForegroundColor(color.getIndex()); // 设置填充颜色
        style.setFillPattern((short)1);//设置为1用前景色填充单元格
        style.setBorderBottom((short)1);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);

        style.setFont(font);
        return style;
    }

    /**
     * 普通单元格
     * @param wb
     * @return
     */
    private CellStyle setCommomCellStyle(Workbook wb) {
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setColor((short)8);
        font.setFontHeightInPoints((short)12);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 水平对齐
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直对齐

        style.setBorderBottom((short)1);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);

        style.setFont(font);

        return style;
    }

    /**
     * 普通单元格
     * @param wb
     * @return
     */
    private CellStyle setLeftCellStyle(Workbook wb) {
        Font font = wb.createFont();
        font.setFontName("微软雅黑");
        font.setColor((short)8);
        font.setFontHeightInPoints((short)12);

        CellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT); // 水平对齐
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 垂直对齐
        style.setWrapText(true);//自动换行


        style.setBorderBottom((short)1);
        style.setBorderLeft((short)1);
        style.setBorderRight((short)1);
        style.setBorderTop((short)1);

        style.setFont(font);

        return style;
    }

    public static void main(String[] args) {

        SXSSFWorkbook workbook =  null;
        BufferedOutputStream outputStream = null;
        SXSSFRow row =null;
        SXSSFCell cell = null;

        try {
            File fileXlsxPath = new File("E:\\aug\\钉钉\\29.xlsx");
            outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
            workbook = new SXSSFWorkbook();
            SXSSFSheet sheet = (SXSSFSheet) workbook.createSheet("测试Sheet");


            row = (SXSSFRow) sheet.createRow(0);
            cell = (SXSSFCell) row.createCell(0);
            //addHSSFCell(sheet,0,0,null,"办件情况");
            addMergedRegion(sheet, 0, 0, 0, 7);
            CellStyle cellStyle=workbook.createCellStyle(); // 创建单元格样式
            cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  // 设置单元格水平方向对齐方式
            cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER); // 设置单元格垂直方向对齐方式
            addHSSFCell(sheet,0,0,cellStyle,"测试标题");

            row = (SXSSFRow) sheet.createRow(1);
            cell = (SXSSFCell) row.createCell(0);
            addMergedRegion(sheet, 1, 2, 0,0);
            addHSSFCell(sheet,1,0,null,"时间       任务");
            setColumnWidth(sheet, 0, 260);
            row.setHeight((short)230);
            setAnchor(workbook, sheet);

            cell = (SXSSFCell) row.createCell(1);
            addHSSFCell(sheet,1,1,null,"第一任务");
            setColumnWidth(sheet, 1, 180);
            addMergedRegion(sheet, 1, 1, 1, 2);
            CellStyle style = setCellStyle(workbook, HSSFColor.LIGHT_TURQUOISE.index);
            cell.setCellStyle(style);

            cell = (SXSSFCell) row.createCell(3);
            addMergedRegion(sheet, 1, 1, 3, 4);
            addHSSFCell(sheet,1,3,null,"第二任务");
            setColumnWidth(sheet, 3, 180);
            style = setCellStyle(workbook, IndexedColors.LEMON_CHIFFON.getIndex());
            cell.setCellStyle(style);


            cell = (SXSSFCell) row.createCell(5);
            addMergedRegion(sheet, 1, 1, 5, 6);
            addHSSFCell(sheet,1,5,null,"第三任务");
            setColumnWidth(sheet, 5, 180);
            style = setCellStyle(workbook, HSSFColor.LIGHT_GREEN.index);
            cell.setCellStyle(style);

            List<String> rwList = new ArrayList<String>();
            rwList.add("任务1");
            rwList.add("任务2");
            rwList.add("任务3");
            rwList.add("任务4");
            rwList.add("任务5");
            rwList.add("任务6");
            row = (SXSSFRow) sheet.createRow(2);
            boolean flag = true;
            for(int j = 1; j < 7; j++){
                cell = (SXSSFCell) row.createCell(j);
                addHSSFCell(sheet, 2, j, null, rwList.get(j-1));
                setColumnWidth(sheet, j, 80);

                if(flag){
                    style = setCellStyle(workbook, HSSFColor.LIGHT_TURQUOISE.index);
                    cell.setCellStyle(style);
                    if(j%2 == 0){
                        flag = false;
                    }
                }else{
                    style = setCellStyle(workbook, IndexedColors.LEMON_CHIFFON.getIndex());
                    cell.setCellStyle(style);
                    if(j%2 == 0){
                        flag = true;
                    }
                }
            }

            //sheet.createFreezePane(1,0,1,0);
            sheet.createFreezePane(0,3,0,4);

            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //test1();
    }


    public static void test1(){
        XSSFWorkbook workbook =  null;
        BufferedOutputStream outputStream = null;
        XSSFRow row =null;
        XSSFCell cell = null;

        try {
            File fileXlsxPath = new File("E:\\aug\\钉钉\\25.xlsx");
            outputStream = new BufferedOutputStream(new FileOutputStream(fileXlsxPath));
            workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("测试Sheet");


            row = sheet.createRow(0);
            cell =  row.createCell(0);
            //addHSSFCell(sheet,0,0,null,"办件情况");
            addMergedRegion(sheet, 0, 0, 0, 7);
            addHSSFCell(sheet,0,0,null,"测试标题");

            row =  sheet.createRow(1);
            cell =  row.createCell(0);
            addMergedRegion(sheet, 1, 2, 0,0);
            addHSSFCell(sheet,1,0,null,"时间       任务");
            setAnchor(workbook, sheet);

            cell =  row.createCell(1);
            addMergedRegion(sheet, 1, 1, 1, 2);
            addHSSFCell(sheet,1,1,null,"第一任务");

            cell =  row.createCell(3);
            addMergedRegion(sheet, 1, 1, 3, 4);
            addHSSFCell(sheet,1,3,null,"第二任务");

            cell = row.createCell(5);
            addMergedRegion(sheet, 1, 1, 5, 6);
            addHSSFCell(sheet,1,5,null,"第三任务");

            List<String> rwList = new ArrayList<String>();
            rwList.add("任务1");
            rwList.add("任务2");
            rwList.add("任务3");
            rwList.add("任务4");
            rwList.add("任务5");
            rwList.add("任务6");
            row = sheet.createRow(2);
            for(int j = 1; j < 7; j++){
                cell = row.createCell(j);
                addHSSFCell(sheet, 2, j, null, rwList.get(j-1));
            }

           // sheet.createFreezePane(int cellNum,int rowNum,int firstCellNum,int firstRollNum );

            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
