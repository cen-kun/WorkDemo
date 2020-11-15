package com.kunc.uploaddemo.controller;

import net.lingala.zip4j.ZipFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@RestController
@RequestMapping("/upload")
public class uploadController {

    private static final Logger LOGGER = LoggerFactory.getLogger(uploadController.class);


    @GetMapping("/test")
    public String test(){
        return "success";
    }


    /**
     * 上传单个文件
     * @param file
     * @return
     */
    @PostMapping("/file")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "G:\\Download";   //这个最后应该定义成相对的路径 在服务器硬盘上保存实际上的文件
        File dest = new File(filePath +"\\"+ fileName);
        if(!dest.getParentFile().exists()){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);

            //将zip解压
            ZipFile zipFile = new ZipFile(dest);
            String destDir = "G:\\Download\\extra";
            File newDestDir = new File(destDir);
            zipFile.extractAll(destDir);

            LOGGER.info("上传成功");
            return "上传成功";
        } catch (IOException e) {
            LOGGER.error(e.toString(), e);
        }
        return "上传失败！";
    }


    /**
     * 多文件上传
     */
    @PostMapping("/files")
    public String multiUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        String filePath = "G:\\Download";
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            if (file.isEmpty()) {
                return "上传第" + (i++) + "个文件失败";
            }
            String fileName = file.getOriginalFilename();


            File dest = new File(filePath + fileName);
            try {
                file.transferTo(dest);
                LOGGER.info("第" + (i + 1) + "个文件上传成功");
            } catch (IOException e) {
                LOGGER.error(e.toString(), e);
                return "上传第" + (i++) + "个文件失败";
            }
        }
        return "上传成功";
    }






}
