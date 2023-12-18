package com.broadcom.tanzu.solutions.gemfire.hystrix.gemfirehystrix;

import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class GemFireHystrixApplication {

    public static void main(String[] args) {

        SpringApplication.run(GemFireHystrixApplication.class, args);

        ConnectionHelper connectionHelper = new ConnectionHelper();
        connectionHelper.getCluster1Connection();

        RegionHelper regionHelper = new RegionHelper();

        String value = (String)regionHelper.getFromRegionName("DC1_region1","key1");
        System.out.println("value:"+value);
    }

}
