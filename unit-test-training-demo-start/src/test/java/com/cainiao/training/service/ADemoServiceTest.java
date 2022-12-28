package com.cainiao.training.service;

import com.cainiao.training.infra.DemoDBMapper;
import com.cainiao.training.infra.DemoTairClient;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class ADemoServiceTest {
    @Mock
    DemoTairClient mockTairClient;
    @Mock
    DemoDBMapper mockDBMapper;
    @InjectMocks
    DemoService demoService;

    @Test
    public void testGetResult_succeed_getFromCache() throws Exception {
        when(mockTairClient.getCache(anyString())).thenReturn("getCacheResponse");

        String result = demoService.getResult("request");
        Assert.assertEquals("getCacheResponse", result);
        verify(mockTairClient, times(1)).getCache(anyString());
        verifyNoMoreInteractions(mockTairClient);
        verifyNoInteractions(mockDBMapper);
    }

    @Test
    //TODO void 返回如何断言 -- Argument Captor 的使用
    public void testDeleteByKey_succeed_givenValidKey() {

        demoService.deleteByKey("KEY");
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        verify(mockDBMapper).deleteData(argumentCaptor.capture());

        String actual = argumentCaptor.getValue();
        assertThat(actual).contains("Delete from", "KEY");

        verifyNoMoreInteractions(mockDBMapper);
        verifyNoInteractions(mockTairClient);

    }

    @Test
    //TODO 依赖里面的 Lambda 表达式内的逻辑怎么执行？
    public void testGetResults_succeed_getFromDB() {
        List<String> keys = Collections.nCopies(2, "KEY");

        doAnswer((Answer<List<String>>) invocationOnMock -> {
            Supplier<List<String>> loader = (Supplier<List<String>>) invocationOnMock.getArguments()[1];
            List dataFromSource = loader.get();
            return new ArrayList<>(dataFromSource);
        }).when(mockTairClient)
                .batchGet(any(), any());

        when(mockDBMapper.queryData(anyList())).thenReturn(Collections.singletonList("EXPECTED"));

        List<String> actuals = demoService.getResults(keys);

        assertThat(actuals).containsExactly("EXPECTED");

        verify(mockTairClient).batchGet(any(), any());
        verify(mockDBMapper).queryData(anyList());
        verifyNoMoreInteractions(mockTairClient, mockDBMapper);


    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme