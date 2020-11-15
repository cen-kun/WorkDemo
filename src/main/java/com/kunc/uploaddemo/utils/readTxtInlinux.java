package com.kunc.uploaddemo.utils;

public class readTxtInlinux {

//    import com.huixiaoer.joy.api.common.config.EnvConfig;
//import com.huixiaoer.joy.api.service.FileService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.io.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.stereotype.Service;
//import org.springframework.util.ResourceUtils;
//
//import java.io.*;
//
//
//    @Slf4j
//    @Service
//    public class FileServiceImpl implements FileService {
//
//        @Override
//        public String readTxtFile() {
//            try {
//                byte[] bytes;
//
//                ClassPathResource classPathResource = new ClassPathResource("file/du.txt");
//                //获取文件流
//                InputStream keyStream = classPathResource.getInputStream();
//                bytes = IOUtils.toByteArray(keyStream);
//                keyStream.read(bytes);
//                keyStream.close();
//
//                ByteArrayInputStream certBis = new ByteArrayInputStream(bytes);
//                InputStreamReader input = new InputStreamReader(certBis);
//                BufferedReader bf = new BufferedReader(input);
//                String line = null;
//                StringBuilder sb = new StringBuilder();
//                while((line=bf.readLine()) != null){
//                    sb.append(line);
//                }
//
//                return sb.toString();
//            } catch (IOException e) {
//                log.error("读取文件错误",e);
//            }
//            return "";
//        }
//
//    }

}
