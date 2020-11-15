package com.kunc.uploaddemo.service;

import com.kunc.uploaddemo.utils.AnalysisUtils;

import java.io.File;
import java.io.IOException;

/**
 * 解析excel
 */
public class excelService {

    /**
     * 通用的解析文件
     */
    public void analysisFile(String filePath) throws IOException {

        //1.读入文件
        File file = new File(filePath);
        String fileName = file.getName();
//        if(".xls".equals(filetype)) {
//            book = new HSSFWorkbook(in);
//        } else if (".xlsx".equals(filetype)) {
//            book = new XSSFWorkbook(in);
//        } else {
//            throw new Exception("请上传excel文件！");
//        }
        //2.判断文件的类型
        //保存的是文件的格式
        String fileType = fileName.substring(fileName.lastIndexOf("."));

        //3.根据文件的后缀解析文件
        if(".txt".equals(fileType)){
            System.out.println("读入的是txt文件");
            AnalysisUtils.txtAnalysis(file);
        }else if(".xls".equals(fileType)) {
            System.out.println("读入的是xls文件");
            AnalysisUtils.importExcel(file);
        } else if (".xlsx".equals(fileType)) {
            AnalysisUtils.importExcel(file);
            System.out.println("读入的是xlsx文件");
        }else if(".csv".equals(fileType)){
            System.out.println("读入的是csv文件");
            AnalysisUtils.readCSV(file,5);
        }else {
            System.out.println("文件格式不正确");
        }

    }


}
