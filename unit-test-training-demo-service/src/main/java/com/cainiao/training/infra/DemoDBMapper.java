package com.cainiao.training.infra;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class DemoDBMapper {

    public String queryData(String sql) {
        return "ABC";
    }

    public List<String> queryData(List<String> sqls){
        return Collections.singletonList("ABC");
    }

    public void deleteData(String sql) {
        // delete
    }
}
