package com.cattsoft.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * ����CSV�ļ�
 */
public class CSVCrate {
	
	public static void main(String[] args) {
        //����
        List<List<Object>> dataList = new ArrayList<List<Object>>();
        List<Object> rowList = new ArrayList<Object>();
        Person p = new Person();
        p.setAge(11);
        p.setName("11334457899087766665544444445556");
        rowList.add(p);
        Person p1 = new Person();
        p1.setAge(1133445789);
        p1.setName("11334457899087766665544444445556");
        rowList.add(p1);
        Person p3 = new Person();
        p3.setAge(33);
        p3.setName("33");
        rowList.add(p3); 
        dataList.add(rowList);

        // ���ͷ
        Object[] head = { "�ͻ�����", "֤������", };
        List<Object> headList = Arrays.asList(head);
        String fileName = "testCSV.csv";//�ļ�����
        String filePath = "c:/test/"; //�ļ�·��
        createCSV(dataList,headList,fileName,filePath);
	}

    /**
     * ����CSV�ļ�
     */
    @Test
    public static void createCSV(List<List<Object>>dataList, List<Object> headList,String fileName,String filePath) {
        
        File csvFile = null;
        BufferedWriter csvWtriter = null;
        try {
            csvFile = new File(filePath + fileName);
            File parent = csvFile.getParentFile();
            if (parent == null && parent.exists()) {
                return;
            }else{
            parent.mkdirs();
            }
            csvFile.createNewFile();

            // GB2312ʹ��ȷ��ȡ�ָ���","
            csvWtriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csvFile), "GB2312"), 1024);
                 
            int num = headList.size() / 2;
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < num; i++) {
                buffer.append(" ,");
            }
            csvWtriter.write(buffer.toString() + "IP����������" + buffer.toString());
            csvWtriter.newLine();

            // д���ļ�ͷ��
            writeRow(headList, csvWtriter);

            // д���ļ�����
            for (List<Object> row : dataList) {
                writeRow1(row, csvWtriter);
            }
            csvWtriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                csvWtriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * дһ������
     * @param row �����б�
     * @param csvWriter
     * @throws IOException
     */
    private static void writeRow(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for (Object data : row) {
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(data).append("\",").toString();
            csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
    
    private static void writeRow1(List<Object> row, BufferedWriter csvWriter) throws IOException {
        for (Object data : row) {
        	Person p = (Person) data;
            StringBuffer sb = new StringBuffer();
            String rowStr = sb.append("\"").append(p.getName()).append("\",")
            		.append(p.getAge()).append("\n").toString();
           csvWriter.write(rowStr);
        }
        csvWriter.newLine();
    }
    

}




