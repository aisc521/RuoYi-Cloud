package com.ruoyi.api.controller;

import com.aliyun.oss.*;
import com.aliyun.oss.model.*;
import com.ruoyi.common.core.web.domain.AjaxResult;
import com.ruoyi.common.security.annotation.Inner;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.logging.SimpleFormatter;

@RestController
public class FileController {

    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String endpoint = "oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIp4QVA8HaNMqY";
    private static String accessKeySecret = "0mlmS5SUXTPAyIFVCpbqTI3TwEFT78";
    private static String bucketName = "crxapp";
    private static String firstKey = "my-crx-key";


    @Inner(false)
    @PostMapping(value = "/addImg")
    public  AjaxResult upload (HttpServletRequest request) throws Exception {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        String ret = "";
        try {
            String fileNames = "";
            if (!ossClient.doesBucketExist(bucketName)) {
                System.out.println("您已经创建Bucket：" + bucketName + "。");
            }
            BucketInfo info = ossClient.getBucketInfo(bucketName);
            String fileKey = "user-img";
            fileNames=formatter.format(new Date());
            StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
            Iterator<String> iterator = req.getFileNames();
            while (iterator.hasNext()) {
                MultipartFile file = req.getFile(iterator.next());
                fileNames = file.getOriginalFilename();
                InputStream input = file.getInputStream();
                // 上传文件流
                PutObjectResult putResult = ossClient.putObject(bucketName, fileKey + fileNames, input);
                ret = getUrl(fileKey + fileNames);
                ossClient.shutdown();
            }
            System.out.println(("图片上传阿里云 name=" + fileKey + fileNames + "地址:" + ret) );
        } catch (OSSException oe) {
            oe.printStackTrace();
        } catch (ClientException ce) {
            ce.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return AjaxResult.success("上传成功",ret);
    }
    public String getUrl(String key) {
        // 设置URL过期时间为10年  3600l* 1000*24*365*10
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        // 生成URL
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
        if (url != null) {
            return url.toString();
        }
        return null;
    }




}
