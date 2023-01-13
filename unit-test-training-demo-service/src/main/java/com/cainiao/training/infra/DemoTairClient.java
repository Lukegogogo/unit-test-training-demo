package com.cainiao.training.infra;

import com.cainiao.training.util.DemoLandlordClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Demo tair client for training
 *
 * @author
 */
@Component
public class DemoTairClient {


    public String getCache(String key) {

        String tenantId = DemoLandlordClient.getTenantId();

        return String.format("%s %s", key, tenantId);
    }

    public List<String> batchGet(List<String> keys, Supplier<List<String>> loader) {

        if (CollectionUtils.isEmpty(keys)) {
            return new ArrayList<>();
        }

        List<String> cacheList = doBatchGet(keys);

        if (cacheList.size() != keys.size()) {
            List<String> dbList = loader.get();
            return dbList;

        } else {
            return cacheList;
        }


    }

    private List<String> doBatchGet(List<String> keys) {
        return new ArrayList<>(keys);
    }
}
