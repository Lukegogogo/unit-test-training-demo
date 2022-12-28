package com.cainiao.training.infra;

import com.cainiao.training.util.DemoLandlordClient;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DemoLandlordClient.class)
public class ADemoTairClientTest {

    @InjectMocks
    DemoTairClient demoTairClient;

    @Before
    public void setup() {
        PowerMockito.mockStatic(DemoLandlordClient.class);
        PowerMockito.when(DemoLandlordClient.getTenantId())
                .thenReturn("TAOBAO_HK");
    }


    @Test
    public void testGetCache() throws Exception {

        String actual = demoTairClient.getCache("key");
        Assertions.assertThat(actual)
                .isEqualTo("key TAOBAO_HK");
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme