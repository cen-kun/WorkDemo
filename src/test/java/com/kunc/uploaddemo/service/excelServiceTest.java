package com.kunc.uploaddemo.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class excelServiceTest {

    @Test
    void analysisFile() {
        excelService excelService = new excelService();
        excelService.analysisFile("G:\\Download\\ckTest1测试1.xlsx");
        excelService.analysisFile("G:\\Download\\ckTest1测试2.docx");
        excelService.analysisFile("G:\\Download\\ckTest1测试2.csv");
        excelService.analysisFile("G:\\Download\\ckTest1测试3.txt");
        excelService.analysisFile("G:\\Download\\ckTest1测试123456.zip");
        System.out.println("我运行完毕了");
    }
}