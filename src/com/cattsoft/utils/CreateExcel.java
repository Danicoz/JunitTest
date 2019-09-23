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
//		// ��һ������workbook
//		HSSFWorkbook wb = new HSSFWorkbook();
//
//		// �ڶ�������sheet
//		HSSFSheet sheet = wb.createSheet("���֤������Ϣ");
//
//		// ������������row:��ӱ�ͷ0��
//		HSSFRow row = sheet.createRow(0);
//		HSSFCellStyle style = wb.createCellStyle();
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����
//
//		String[] head = { "����", "���֤", "����״̬", "������Ϣ","��" };
//		List<String> headList = Arrays.asList(head);
//		int j = 0;
//		for (String row1 : headList) {
//			HSSFCell cell = row.createCell((short) j); // ��һ����Ԫ��
//			cell.setCellValue(new HSSFRichTextString(row1)); // �趨ֵ
//			cell.setCellStyle(style);
//			j++;
//		}
//
//		// ���岽��������
//		List<Customer> list = CreateExcel.getCustomer();
//		for (int i = 0; i < list.size(); i++) {
//			Customer Customer = list.get(i);
//			// ������
//			row = sheet.createRow(i + 1);
//			// ������Ԫ�����������
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
//		// ������������excel�ļ����浽ָ��·����
//		try {
//			FileOutputStream fout = new FileOutputStream("c:/test/Customer.xls");
//			wb.write(fout);
//			fout.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("Excel�ļ����ɳɹ�...");
//	}
//
//	public static List<Customer> getCustomer() {
//		List<Customer> list = new ArrayList<Customer>();
//
//		Customer r1 = new Customer("����", "43068112133421989021611", "L", "���ȴ���");
//		Customer r2 = new Customer("����", "322323232423112343234323232", "X",
//				"У�����");
//		Customer r3 = new Customer("����", "", "N", "���֤��ϢΪ��");
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
