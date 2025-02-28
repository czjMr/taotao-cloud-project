package com.taotao.cloud.threadpoll.dynamictp.apollo.controller;

import com.dtp.core.DtpRegistry;
import com.dtp.core.thread.DtpExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 */
@RestController
@SuppressWarnings("all")
public class TestController {

    @Resource
    private ThreadPoolExecutor dtpExecutor1;

    @GetMapping("/dtp-nacos-example/test")
    public String test() {
        new Thread(() -> {
            try {
                task();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return "success";
    }

    public void task() throws InterruptedException {
        DtpExecutor dtpExecutor2 = DtpRegistry.getDtpExecutor("dtpExecutor2");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(100);
            dtpExecutor1.execute(() -> {
                log.info("i am dynamic-tp-test-1 task");
            });
            dtpExecutor2.execute(() -> {
                log.info("i am dynamic-tp-test-2 task");
            });
        }
    }
}
