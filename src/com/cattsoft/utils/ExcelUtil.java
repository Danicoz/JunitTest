package com.cattsoft.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class ExcelUtil {

//	public static void CreateExcel(String[] head, List<String[]> dataset, String sheetName) {
//		// 第一步创建workbook
//		HSSFWorkbook wb = new HSSFWorkbook();
//
//		// 第二步创建sheet
//		HSSFSheet sheet = wb.createSheet(sheetName);
//
//		// 第三步创建行row:添加表头0行
//		HSSFRow row = sheet.createRow(0);
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
//
//		List<String> headList = Arrays.asList(head);
//		int i = 0;
//		for (String str : headList) {
//			HSSFCell cell = row.createCell((short) i); // 第一个单元格
//			cell.setCellValue(new HSSFRichTextString(str)); // 设定值
//			cell.setCellStyle(style);
//			i++;
//		}
//		int i1 = 0;
//		for (String[] value : dataset) {
//
//			row = sheet.createRow(i1 + 1);
//			int i2 = 0;
//			for (String value1 : value) {
//				row.createCell((short) i2).setCellValue(
//						new HSSFRichTextString(value1));
//				i2++;
//			}
//			i1++;
//		}
//
//		// 第六步将生成excel文件保存到指定路径下
//		try {
//			FileOutputStream fout = new FileOutputStream("./file/Customer.xls", true);
//			wb.write(fout);
//			fout.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("创建成功");
//	}
//
//	public static void main(String[] args) {
//		String[] head = { "姓名", "身份证", "错误状态", "错误信息", "空" };
//		List<String[]> dataset = new ArrayList<String[]>();
//		String[] str1 = { "张三", "43068112133421989021611", "L", "长度错误" };
//		String[] str2 = { "李四", "322323232423112343234323232", "X", "校验错误" };
//		String[] str3 = { "王五", "", "N", "身份证信息为空" };
//		dataset.add(str1);
//		dataset.add(str2);
//		dataset.add(str3);
//		CreateExcel(head, dataset, "网络纠错流程");
//	}

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
        if (excelFileName.toLowerCase().endsWith(".xls")) {
            try {
                return  new HSSFWorkbook(is);    //03
            } catch (Exception e) {
                return new XSSFWorkbook(is);    //07
            }
        } else if (excelFileName.toLowerCase().endsWith(".xlsx")) {
            try {
                return new XSSFWorkbook(is);
            } catch (Exception e) {
                return  new HSSFWorkbook(is);
            }
        }
        return null;
    }

    public static Sheet getSheet(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(sheetIndex);
    }

    public static Sheet getSheetByIndex(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(sheetIndex);
    }

    /**
     *
     * @param is
     * @param excelFileName
     * @param dataStartRow 从第几行开始读取数据（从1开始）
     * @return
     */
    public static List<String[]> importExcel(InputStream is,String excelFileName,int dataStartRow) throws Exception{
        List<String[]> list = new ArrayList<String[]>();
        try{
            if(dataStartRow<1){
                dataStartRow = 1;
            }
            // 创建工作簿
            Workbook workbook = createWorkbook(is, excelFileName);
            LOGGER.info("创建工作簿完成");
            // 创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);
            LOGGER.info("创建工作表完成");
            // 获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            // 获取表头单元格个数
            int cells = sheet.getRow((dataStartRow-2)<0 ? 0 : (dataStartRow-2)).getPhysicalNumberOfCells();
            List<String> headList = new ArrayList<String>();
            int rowIndex = 0;//第rowIndex行
            for (int i = dataStartRow-1; i < rows; i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                    String[] dataArr = new String[cells];
                    int index = 0;
                    while (index < cells) {
                        Cell cell = row.getCell(index);
                        if (null == cell) {
                            cell = row.createCell(index);
                        }

                        HashMap<String, Object> isMergedMap = isMergedRegion(sheet, i, index);
                        Boolean isMerged = (Boolean) isMergedMap.get("isMerged");
                        String value = null;
                        if(isMerged){
                            value = getMergedRegionValue(sheet, i, index);
                        }else{
                            value = getCellValue(cell);
                        }
                        dataArr[index++] = value;
                        //获取表头信息，相当于过滤合并的列
                        //index = (Integer) isMergedMap.get("lastColumn") + 1;
                    }
                    list.add(dataArr);
                }else{
                    break;
                }
            }
        }catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw e;
        } finally {
            try {
                is.close();
            } catch (Exception e2) {
                LOGGER.error(e2.getMessage());
                e2.printStackTrace();
            }
        }
        return list;
    }

    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return null;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
//        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
//            cell.setCellType(Cell.CELL_TYPE_STRING);
//        }
        //把公式当String来读，合计行可能会有公式
        if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                if (HSSFDateUtil.isCellDateFormatted( cell) ) {
                    double value = cell.getNumericCellValue();
                    Date date = DateUtil.getJavaDate(value);
                    SimpleDateFormat sdf = null;
                    if (value < 1) { //时间类型
                        sdf = new SimpleDateFormat("HH:mm:ss");
                    } else if (date.getHours() == 0 && date.getMinutes() == 0 && date.getSeconds() == 0){
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    } else {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }
                    return sdf.format(date);
                }else{
                    if (cell.getCellType() != Cell.CELL_TYPE_STRING) {
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    cellValue = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();
                }
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    public static Workbook getWorkBook(File file) {
        //获得文件名
        String fileName = file.getName();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = new FileInputStream(file);
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(XLS)){
                //03版
                //workbook = new HSSFWorkbook(is);
                POIFSFileSystem poifsFileSystem = new POIFSFileSystem(is);
                workbook = WorkbookFactory.create(poifsFileSystem);
            }else if(fileName.endsWith(XLSX)){
                //07版
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return workbook;
    }

    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    public static HSSFFont getFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        return font;
    }

    /**
     * 判断是否为合并单元格
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static HashMap<String, Object> isMergedRegion(Sheet sheet, int row, int column) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("isMerged", false);
        resultMap.put("firstColumn", column);
        resultMap.put("lastColumn", column);
        resultMap.put("firstRow", row);
        resultMap.put("lastRow", row);
        int sheetMergeCount = sheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    resultMap.put("isMerged", true);
                    resultMap.put("firstColumn", firstColumn);
                    resultMap.put("lastColumn", lastColumn);
                    resultMap.put("firstRow", firstRow);
                    resultMap.put("lastRow", lastRow);
                    return resultMap;
                }
            }
        }
        return resultMap;
    }


    /**
     * 获取合并单元格的值
     *
     * @param sheet
     * @param row
     * @param column
     * @return
     */
    public static String getMergedRegionValue(Sheet sheet, int row, int column) {
        int sheetMergeCount = sheet.getNumMergedRegions();

        for (int i = 0; i < sheetMergeCount; i++) {
            CellRangeAddress ca = sheet.getMergedRegion(i);
            int firstColumn = ca.getFirstColumn();
            int lastColumn = ca.getLastColumn();
            int firstRow = ca.getFirstRow();
            int lastRow = ca.getLastRow();

            if (row >= firstRow && row <= lastRow) {
                if (column >= firstColumn && column <= lastColumn) {
                    Row fRow = sheet.getRow(firstRow);
                    Cell fCell = fRow.getCell(firstColumn);
                    return getCellValue(fCell);
                }
            }
        }

        return null;
    }

    /**
     * @param cellNum  冻结的列数
     * @param rowNum   冻结的行数
     * @param firstCellNum  被固定列右边第一列的列号
     * @param firstRollNum  被固定行下边第一列的行号
     * sheet.createFreezePane(1,0,1,0); 固定首列，列号的显示为:A,BCDEF...
     * sheet.createFreezePane(0,3,0,4); 固定前三行，行号显示位:123, 4567....
     */
    public static void createFreezePane(Sheet sheet, int cellNum,int rowNum,int firstCellNum,int firstRollNum ){
        sheet.createFreezePane(cellNum, rowNum, firstCellNum, firstRollNum);
    }



}
