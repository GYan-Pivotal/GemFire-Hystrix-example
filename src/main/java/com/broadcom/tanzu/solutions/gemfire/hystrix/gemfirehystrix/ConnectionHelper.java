package com.broadcom.tanzu.solutions.gemfire.hystrix.gemfirehystrix;

import org.apache.geode.cache.client.ClientCache;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ConnectionHelper {

    public ClientCache getCluster1Connection() {
        ClientCache cache;
        // configure ClientCache properties
        Properties clientCacheProps = new Properties();
        clientCacheProps.setProperty("log-level", "config");
        clientCacheProps.setProperty("log-file", "client.log");

        cache = new ClientCacheFactory(clientCacheProps).addPoolLocator("192.168.86.42", 30001)
                .create();
            return cache;
    }

    public ClientCache getCluster2Connection() {
        ClientCache cache;
        // configure ClientCache properties
        Properties clientCacheProps = new Properties();
        clientCacheProps.setProperty("log-level", "config");
        clientCacheProps.setProperty("log-file", "client.log");

        cache = new ClientCacheFactory(clientCacheProps).addPoolLocator("192.168.86.42", 40001)
                .create();
        return cache;
    }

}
