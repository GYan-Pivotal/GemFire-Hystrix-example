package com.broadcom.tanzu.solutions.gemfire.hystrix.gemfirehystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.geode.cache.Region;
import org.apache.geode.cache.client.ClientCacheFactory;
import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.stereotype.Component;

@Component
public class RegionHelper {

    public Object getFromRegionName(String regionName, Object key) {
        //try to get value from cluster1
        Region region = ClientCacheFactory.getAnyInstance().getRegion(regionName);
        if (region == null){
            region = ClientCacheFactory.getAnyInstance().createClientRegionFactory(
                    ClientRegionShortcut.PROXY).create(regionName);
        }
        return this.getFromRegion(region, key);
    }


    @HystrixCommand(fallbackMethod = "defaultGetFromRegion")
    public Object getFromRegion(Region region, Object key) {
        //catch NoAvailableServersException
        //catch NoAvailableLocatorsException
        return region.get(key);
    }

    public Object defaultGetFromRegion(Region region, Object key) {
        ClientCacheFactory.getAnyInstance().close();
        ConnectionHelper help = new ConnectionHelper();
        help.getCluster2Connection();
        return region.get(key);
    }

    @HystrixCommand(fallbackMethod = "defaultPutToRegion")
    public Object putToRegion(Region region, Object key, Object value) {
        //catch NoAvailableServersException
        //catch NoAvailableLocatorsException
        if (region == null){
            region = ClientCacheFactory.getAnyInstance().createClientRegionFactory(
                    ClientRegionShortcut.PROXY).create(region.getName());
        }
        return region.put(key, value);
    }

    public Object defaultPutToRegion(Region region, Object key, Object value) {
        ClientCacheFactory.getAnyInstance().close();
        ConnectionHelper help = new ConnectionHelper();
        help.getCluster2Connection();
        return region.put(key, value);
    }
}
