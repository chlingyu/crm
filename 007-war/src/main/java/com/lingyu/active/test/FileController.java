package com.lingyu.active.test;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

    @RestController
    @RequestMapping("file")
    @Slf4j
    public class FileController {
        @Value("${file.upload.url}")
        private String uploadFilePath;

        @RequestMapping("/upload")
        public Map httpUpload(@RequestParam("files") MultipartFile files[]){
            Map<String,Object> map=new HashMap<>();
            JSONObject object=new JSONObject();
            for(int i=0;i<files.length;i++){
                String fileNameOrigin = files[i].getOriginalFilename();  // 文件名
                String fileName=fileNameOrigin.substring(0, fileNameOrigin.lastIndexOf("."))+"_"+UUID.randomUUID()+".jpg";
                System.out.println(fileName);
                File dest = new File(uploadFilePath +'/'+ fileName);
                if (!dest.getParentFile().exists()) {
                    dest.getParentFile().mkdirs();
                }
                try {
                    files[i].transferTo(dest);
                } catch (Exception e) {
                    log.error("{}",e);
                    map.put("success",2);
                    map.put("result","程序错误，请重新上传");
                    return map;
                }
            }
            map.put("success",1);
            map.put("result","文件上传成功");
            return map;
        }

}
