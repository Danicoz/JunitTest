package com.cattsoft.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	public static void CreateExcel(String[] head, List<String[]> dataset, String sheetName) {
		// 第一步创建workbook
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步创建sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// 第三步创建行row:添加表头0行
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中

		List<String> headList = Arrays.asList(head);
		int i = 0;
		for (String str : headList) {
			HSSFCell cell = row.createCell((short) i); // 第一个单元格
			cell.setCellValue(new HSSFRichTextString(str)); // 设定值
			cell.setCellStyle(style);
			i++;
		}
		int i1 = 0;
		for (String[] value : dataset) {

			row = sheet.createRow(i1 + 1);
			int i2 = 0;
			for (String value1 : value) {
				row.createCell((short) i2).setCellValue(
						new HSSFRichTextString(value1));
				i2++;
			}
			i1++;
		}

		// 第六步将生成excel文件保存到指定路径下
		try {
			FileOutputStream fout = new FileOutputStream("./file/Customer.xls", true);
			wb.write(fout);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("创建成功");
	}

	public static void main(String[] args) {
		String[] head = { "姓名", "身份证", "错误状态", "错误信息", "空" };
		List<String[]> dataset = new ArrayList<String[]>();
		String[] str1 = { "张三", "43068112133421989021611", "L", "长度错误" };
		String[] str2 = { "李四", "322323232423112343234323232", "X", "校验错误" };
		String[] str3 = { "王五", "", "N", "身份证信息为空" };
		dataset.add(str1);
		dataset.add(str2);
		dataset.add(str3);
		CreateExcel(head, dataset, "网络纠错流程");
	}
}
