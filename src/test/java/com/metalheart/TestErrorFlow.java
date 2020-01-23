package com.metalheart;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
public class TestErrorFlow {


    @Autowired
    private MailGateway gateway;

    @MockBean
    private TestHandler handler;

    @Test
    public void testHappyFlow() throws Exception {

        // Arrange
        Mockito.when(handler.handle(any(), any())).thenReturn(true);

        // act
        boolean res = gateway.send(new PayloadData("some payload"));

        // assert
        Assert.assertTrue(res);
    }

    @Test
    public void testErrorFlow() throws Exception {

        // Arrange
        Mockito.when(handler.handle(any(), any())).thenThrow(new RuntimeException("ERROR!"));

        // act
        boolean res = gateway.send(new PayloadData("some payload"));

        // assert
        Assert.assertFalse(res);
    }
}
