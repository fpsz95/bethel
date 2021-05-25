package com.fernandes.bethel.bucket;

public enum BucketName {

    PROFILE_IMAGE("bethel");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }


    public String getBucketName() {
        return bucketName;
    }
}
