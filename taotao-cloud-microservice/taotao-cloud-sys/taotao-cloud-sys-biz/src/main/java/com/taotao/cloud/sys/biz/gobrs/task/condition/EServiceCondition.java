package com.taotao.cloud.sys.biz.gobrs.task.condition;

import com.gobrs.async.TaskSupport;
import com.gobrs.async.task.AsyncTask;
import org.springframework.stereotype.Component;

/**
 * The type D service.
 *
 * @program: gobrs -async-starter
 * @ClassName DService
 * @description:
 * @author: sizegang
 * @create: 2022 -03-20
 */
@Component
public class EServiceCondition extends AsyncTask<Object, Boolean> {

	/**
	 * The .
	 */
	int i = 10000;

	@Override
	public void prepare(Object o) {

	}

	@Override
	public Boolean task(Object o, TaskSupport support) {
		System.out.println("EServiceCondition Begin");
		for (int i1 = 0; i1 < i; i1++) {
			i1 += i1;
		}
		System.out.println("EServiceCondition Finish");
		return true;
	}

	@Override
	public boolean nessary(Object o, TaskSupport support) {
		return true;
	}


	@Override
	public void onSuccess(TaskSupport support) {

	}

	@Override
	public void onFail(TaskSupport support) {

	}
}
