package com.taotao.cloud.quartz;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest(classes = QuartzTest.App.class,
	properties = {
		"spring.application.name=taotao-cloud-quartz-test",
		"taotao.cloud.quartz.enabled=true"
	},
	webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
//@AutoConfigureMockMvc
class QuartzTest {

	@SpringBootApplication
	public static class App {

	}

	// @Autowired
	// private QuartzManager quartzManager;

	@Test
	void contextLoads() throws InterruptedException {
		// QuartzJob model = new QuartzJob();
		// model.setId(125L);
		// model.setBeanName("jobTest");
		// model.setCronExpression("0 0/2 * * * ?");
		// // model.setPause(false);
		// model.setJobName("jobTestJobName");
		// model.setMethodName("jobTest");
		// model.setCreateTime(LocalDateTime.now());
		//
		// quartzManager.addJob(model);


		Thread.sleep(1000000000);
	}

}
