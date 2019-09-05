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
		// ��һ������workbook
		HSSFWorkbook wb = new HSSFWorkbook();

		// �ڶ�������sheet
		HSSFSheet sheet = wb.createSheet(sheetName);

		// ������������row:��ӱ�ͷ0��
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // ����

		List<String> headList = Arrays.asList(head);
		int i = 0;
		for (String str : headList) {
			HSSFCell cell = row.createCell((short) i); // ��һ����Ԫ��
			cell.setCellValue(new HSSFRichTextString(str)); // �趨ֵ
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

		// ������������excel�ļ����浽ָ��·����
		try {
			FileOutputStream fout = new FileOutputStream("./file/Customer.xls", true);
			wb.write(fout);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("�����ɹ�");
	}

	public static void main(String[] args) {
		String[] head = { "����", "���֤", "����״̬", "������Ϣ", "��" };
		List<String[]> dataset = new ArrayList<String[]>();
		String[] str1 = { "����", "43068112133421989021611", "L", "���ȴ���" };
		String[] str2 = { "����", "322323232423112343234323232", "X", "У�����" };
		String[] str3 = { "����", "", "N", "���֤��ϢΪ��" };
		dataset.add(str1);
		dataset.add(str2);
		dataset.add(str3);
		CreateExcel(head, dataset, "�����������");
	}
}
