//package com.cattsoft.utils;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFRichTextString;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//public class CreateExcel {
//	public static void main(String[] args) {
//		// 第一步创建workbook
//		HSSFWorkbook wb = new HSSFWorkbook();
//
//		// 第二步创建sheet
//		HSSFSheet sheet = wb.createSheet("身份证错误信息");
//
//		// 第三步创建行row:添加表头0行
//		HSSFRow row = sheet.createRow(0);
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中
//
//		String[] head = { "姓名", "身份证", "错误状态", "错误信息","空" };
//		List<String> headList = Arrays.asList(head);
//		int j = 0;
//		for (String row1 : headList) {
//			HSSFCell cell = row.createCell((short) j); // 第一个单元格
//			cell.setCellValue(new HSSFRichTextString(row1)); // 设定值
//			cell.setCellStyle(style);
//			j++;
//		}
//
//		// 第五步插入数据
//		List<Customer> list = CreateExcel.getCustomer();
//		for (int i = 0; i < list.size(); i++) {
//			Customer Customer = list.get(i);
//			// 创建行
//			row = sheet.createRow(i + 1);
//			// 创建单元格并且添加数据
//			row.createCell((short) 0).setCellValue(
//					new HSSFRichTextString(Customer.getName()));
//			row.createCell((short) 1).setCellValue(
//					new HSSFRichTextString(Customer.getIdCard()));
//			row.createCell((short) 2).setCellValue(
//					new HSSFRichTextString(Customer.getStatus()));
//			row.createCell((short) 3).setCellValue(
//					new HSSFRichTextString(Customer.getMessage()));
//		}
//
//		// 第六步将生成excel文件保存到指定路径下
//		try {
//			FileOutputStream fout = new FileOutputStream("c:/test/Customer.xls");
//			wb.write(fout);
//			fout.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Excel文件生成成功...");
//	}
//
//	public static List<Customer> getCustomer() {
//		List<Customer> list = new ArrayList<Customer>();
//
//		Customer r1 = new Customer("张三", "43068112133421989021611", "L", "长度错误");
//		Customer r2 = new Customer("李四", "322323232423112343234323232", "X",
//				"校验错误");
//		Customer r3 = new Customer("王五", "", "N", "身份证信息为空");
//
//		list.add(r1);
//		list.add(r2);
//		list.add(r3);
//
//		return list;
//	}
//
//	// List<String[]> dataset = new ArrayList<String[]>();
//	// for(int i=0;i<bookTypeList.size();i++) {
//	// BookType bookType = bookTypeList.get(i);
//	// dataset.add(new String[]{bookType.getBookTypeId() +
//	// "",bookType.getBookTypeName(),bookType.getDays() + ""});
//	// }
//}
