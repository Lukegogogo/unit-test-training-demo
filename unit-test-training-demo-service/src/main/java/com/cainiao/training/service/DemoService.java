package com.cainiao.training.service;

import com.cainiao.training.infra.DemoDBMapper;
import com.cainiao.training.infra.DemoTairClient;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Component
public class DemoService {

    @Resource
    DemoTairClient tairClient;

    @Resource
    DemoDBMapper dbMapper;

    public String getResult(String request) throws IOException {
        String cacheDate = tairClient.getCache(request);

        if (cacheDate == null) {
            throw new IOException("Client Timeout");
        }

        if (!StringUtils.isEmpty(cacheDate)) {
            return cacheDate;
        } else {
            return dbMapper.queryData(request);
        }
    }


    //Advanced
    public List<String> getResults(List<String> requests) {
        return tairClient.batchGet(requests, () -> {
            List<String> dbResult = dbMapper.queryData(requests);
            if (dbResult.isEmpty()) {
                throw new RuntimeException("Empty DB");
            }
            return dbResult;
        });

    }

    public void deleteByKey(String key) {
        if (StringUtils.isEmpty(key)) {
            return;
        }

        String sql = generateSQL(key);



        dbMapper.deleteData(sql);
    }

    private String generateSQL(String key) {
        return String.format("Delete from table_name where key = %s", key);
    }


}
