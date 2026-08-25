package com.example.demo.utils;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.common.auth.CredentialsProviderFactory;
import com.aliyun.oss.common.auth.EnvironmentVariableCredentialsProvider;
import com.aliyun.oss.common.comm.SignVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class AliyunOSSperator {
//    private String endpoint = "https://oss-cn-beijing.aliyuncs.com";
//    private String bucketName = "java-student-ai";
//    private String region = "cn-beijing";
    @Autowired
    private AliyunOSSproperties aliyunOSSproperties;
    public String uplode(byte[] content, String originalFilename) throws Exception{
        String endpoint = aliyunOSSproperties.getEndpoint();
        String bucketName = aliyunOSSproperties.getBucketName();
        String region = aliyunOSSproperties.getRegion();
//        重环境变量获取访问凭证
        EnvironmentVariableCredentialsProvider credentialsProvider = CredentialsProviderFactory.newEnvironmentVariableCredentialsProvider();
//        /*填写object完整路径*/
//        获取当前系统时间
        String dir = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM"));
//        生成一个新的不重复的文件名
        String newFileName = UUID.randomUUID() +originalFilename.substring(originalFilename.lastIndexOf("."));
        String objectName =dir +"/"+newFileName;
//        /*创建OSSClient*/实例
        ClientBuilderConfiguration clientBuilderConfiguration =new ClientBuilderConfiguration();
        clientBuilderConfiguration.setSignatureVersion(SignVersion.V4);
        OSS ossClient = OSSClientBuilder.create()
                .endpoint(endpoint)
                .credentialsProvider(credentialsProvider)
                .clientConfiguration(clientBuilderConfiguration)
                .region(region)
                .build();
        try {
            ossClient.putObject(bucketName,objectName,new ByteArrayInputStream(content));

        }finally {
            ossClient.shutdown();
        }
        return endpoint.split("//")[0]+"//"+bucketName+"."+endpoint.split("//")[1]+"/"+objectName;
    }
}

