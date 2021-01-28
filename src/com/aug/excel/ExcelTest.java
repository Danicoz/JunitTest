package com.aug.excel;

import com.cattsoft.utils.ExcelUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class ExcelTest {

    @Test
    public void testImportExcel() throws Exception {
//        MultipartFile multipartFile = attachment;
//        String fileName = multipartFile.getOriginalFilename();
//        InputStream is = multipartFile.getInputStream();
        String path = "./file/3333.xlsx";//./file/1111.xlsx ./file/2222.xls  ./file/3333.xlsx（合并文件）
        InputStream is = new FileInputStream(path);
        List<String[]> resultList = ExcelUtil.importExcel(is,"3333.xlsx",5);
        System.out.println(resultList.size());

    }

    //合并项的导入
    public void testImportExcel1(){



    }

    public static void main(String[] args) {

    }



}
