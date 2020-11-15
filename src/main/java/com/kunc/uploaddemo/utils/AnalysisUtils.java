package com.kunc.uploaddemo.utils;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 解析四种基本的文本格式的工具类
 */
public class AnalysisUtils {

    private static Logger logger = LoggerFactory.getLogger(CSVUtils.class);
    //行尾分隔符定义
    private final static String NEW_LINE_SEPARATOR = "\n";
    //上传文件的存储位置
    private final static URL PATH = Thread.currentThread().getContextClassLoader().getResource("");


    /**
     * 解析txt
     * @param file
     */
    public static void txtAnalysis(File file) throws IOException {

        String s = "";
        InputStreamReader in = new InputStreamReader(new FileInputStream(file),"UTF-8");
        BufferedReader br = new BufferedReader(in);
        StringBuffer content = new StringBuffer();
        while ((s=br.readLine())!=null){

            //这里面要对每一行的文本进行解析把 得到相应的内容 进行操作
            content = content.append(s); //这个content 是所有的文本内容
        }


        return ;
    }

    /**
     * 解析CSV格式文件
     * @param file
     */
    /**
     * @return List<List<String>>
     * @Description 读取CSV文件的内容（不含表头）
     * @Param filePath 文件存储路径，colNum 列数  这个列数是不是已知的啊
     **/
    public static List<List<String>> readCSV(File file, int colNum) {
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(file);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            CSVParser parser = CSVFormat.DEFAULT.parse(bufferedReader);
//          表内容集合，外层List为行的集合，内层List为字段集合
            List<List<String>> values = new ArrayList<>();
            int rowIndex = 0;

            for (CSVRecord record : parser.getRecords()) {
//              跳过表头
                if (rowIndex == 0) {
                    rowIndex++;
                    continue;
                }
//              每行的内容
                List<String> value = new ArrayList<>(colNum + 1);
                for (int i = 0; i < colNum; i++) {
                    value.add(record.get(i));
                }
                values.add(value);
                rowIndex++;
            }
            return values;
        } catch (IOException e) {
            logger.error("解析CSV内容失败" + e.getMessage(), e);
        }finally {
            //关闭流
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 解析xls文件格式的excel（2003年之前的excel）
     * @param file
     */
    public static void xlsAnalysis(File file){

    }

    /**
     * 解析xlsx格式文件（比较新的excel文件）
     * @param file
     */
    public static void xlsxAnalysis(File file){

    }

    /**
     * 导入并解析excel
     * @param file
     * @return
     */
    public static List<Object[]> importExcel(File file) {
        logger.info("导入解析开始，fileName:{}",file.getName());
        try {
            List<Object[]> list = new ArrayList<>();
            InputStream inputStream = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            //获取sheet的行数
            int rows = sheet.getPhysicalNumberOfRows();
            for (int i = 0; i < rows; i++) {
                //过滤表头行
                if (i == 0) {
                    continue;
                }
                //获取当前行的数据
                Row row = sheet.getRow(i);
                Object[] objects = new Object[row.getPhysicalNumberOfCells()];
                int index = 0;
                for (Cell cell : row) {

                    /**
                     * CellTypeEnum        类型        值
                     *
                     * NUMERIC             数值型        0
                     *
                     * STRING              字符串型      1
                     *
                     * FORMULA            公式型        2
                     *
                     * BLANK                    空值         3
                     *
                     * BOOLEAN             布尔型       4
                     *
                     * ERROR                   错误         5
                     */
                    if (cell.getCellTypeEnum().equals(0)) {
                        objects[index] = (int) cell.getNumericCellValue();
                    }
                    if (cell.getCellTypeEnum().equals(1)) {
                        objects[index] = cell.getStringCellValue();
                    }
                    if (cell.getCellTypeEnum().equals(4)) {
                        objects[index] = cell.getBooleanCellValue();
                    }
                    if (cell.getCellTypeEnum().equals(5)) {
                        objects[index] = cell.getErrorCellValue();
                    }
                    index++;
                }
                list.add(objects);
            }
            logger.info("导入文件解析成功！");
            return list;
        }catch (Exception e){
            logger.info("导入文件解析失败！");
            e.printStackTrace();
        }
        return null;
    }


}
