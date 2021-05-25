package com.fernandes.bethel.bucket;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //So that spring instantiates this class
public class AmazonConfig {

    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
          "AKIAIGVRMBHRGQ3KDPWA",
          "atSkUyx4Btq9QiJop791TVan2Sb6gAsPxFiU8dUr"
        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .withRegion("ap-south-1")
                .build();
    }
}
