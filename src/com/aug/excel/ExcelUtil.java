package com.aug.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Excel读写工具类
 */
public class ExcelUtil {

    private final static String XLS = "xls";
    private final static String XLSX = "xlsx";

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelUtil.class);

    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
        if (excelFileName.toLowerCase().endsWith(".xls")) {
            try {
                return new HSSFWorkbook(is);    //03
            } catch (Exception e) {
                return new XSSFWorkbook(is);    //07
            }
        } else if (excelFileName.toLowerCase().endsWith(".xlsx")) {
            try {
                return new XSSFWorkbook(is);
            } catch (Exception e) {
                return new HSSFWorkbook(is);
            }
        }
        return null;
    }

    public static Sheet getSheet(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(0);
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
                        dataArr[index++] = getCellValue(cell);
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
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //把公式当String来读，合计行可能会有公式
        if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                if (HSSFDateUtil.isCellDateFormatted(cell) ) {
                    double value = cell.getNumericCellValue();
                    Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
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

    /**
     *
     * @param os
     * @param list
     * @param templateExcelName Excel模板名称
     * @param storageUrl 配置文件中云盘路径
     * @param uploadAbsolutePath
     * @param haveSumRow 是否有合计行
     * @param sumRowIndex 合计行行下标（从1开始）
     * @param sumCellIndex 合计行数据列开始下标（从1开始）
     * @param dataStartRow 从第几行开始读取数据（从1开始）
     * @throws Exception
     */
    public static void exportExcelUseTemplate(OutputStream os,List<String[]> list,String templateExcelName,String uploadAbsolutePath,
                                              String storageUrl,boolean haveSumRow,int sumRowIndex,
                                              int sumCellIndex,int dataStartRow) throws  Exception{
        if(list==null || list.size()<1 || os==null || templateExcelName==null || templateExcelName.trim().length()<1){
            return;
        }
        boolean isAbsolutePath = (uploadAbsolutePath != null) && (!uploadAbsolutePath.equalsIgnoreCase("0"));
        String filePath = File.separator + "download" + File.separator + "template";
        File file = openFile(filePath, templateExcelName, isAbsolutePath, storageUrl, true, true);
        if(!file.exists()){
            LOGGER.error("找不到Excel模板，请联系管理员");
            os.write("找不到Excel模板，请联系管理员".getBytes("UTF-8"));
            return;
        }
        HSSFWorkbook workbook = (HSSFWorkbook)getWorkBook(file);//目前仅是03版
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFCellStyle cellStyle = getCellStyle(workbook);
        HSSFFont font = getFont(workbook);
        cellStyle.setFont(font);
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int i = 0; i <list.size(); i++) {
            String[] arr = list.get(i);
            if(haveSumRow && dataStartRow + i == sumRowIndex){
                row = sheet.getRow(sumRowIndex-1);
            }else{
                row = sheet.createRow(dataStartRow + i - 1);
                row.setHeight((short)400);
            }
            if(arr==null || arr.length<1){
                continue;
            }
            for(int j=0;j<arr.length;j++){
                if(haveSumRow && dataStartRow + i == sumRowIndex){
                    if(j<sumCellIndex-1){
                        continue;
                    }
                }
                cell = row.createCell(j);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(arr[j]);
            }
        }
        try {
            workbook.write(os);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw e;
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        //style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        //style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
//        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
//        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        return style;
    }

    public static HSSFFont getFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        //font.setFontHeightInPoints((short) 13);
        return font;
    }


    public static File openFile(String filePath, String fileName, boolean isAbsolutePath, String directoryPath, boolean autoBuildDir, boolean isNeedToFormatPath) throws Exception {
        File file = null;
        if (!isAbsolutePath) {
            filePath = directoryPath + "\\" + filePath;
        }

        if (filePath != null && filePath.trim().length() > 0) {
            if (isNeedToFormatPath) {
                filePath = formatPath(filePath);
            }

            if (autoBuildDir) {
                File dir = new File(filePath);
                if (!dir.exists()) {
                    dir.mkdirs();
                }
            }

            file = new File(filePath + "\\" + fileName);
        }

        return file;
    }

    public static String formatPath(String path) {
        if (path != null && path.trim().length() > 0) {
            path = path.replaceAll("\\\\{1,}", "/");
            path = path.replaceAll("\\/{2,}", "/");
        }

        return path;
    }

}
