// package com.taotao.cloud.sys.biz.gobrs.task.condition;
//
// import com.gobrs.async.TaskSupport;
// import com.gobrs.async.domain.TaskResult;
// import com.gobrs.async.task.AsyncTask;
// import org.springframework.scheduling.annotation.AsyncResult;
// import org.springframework.stereotype.Component;
//
//
// /**
//  * The type C service.
//  *
//  * @program: gobrs -async-starter
//  * @ClassName CService
//  * @description: 任务依赖类型
//  * AServiceCondition,BServiceCondition,CServiceCondition->DServiceCondition:anyCondition
//  * <p>
//  * 简化配置
//  * <p>
//  * A,B,C->D:anyCondition
//  * <p>
//  * D根据 A,B,C 返回的任务结果中的 AnyCondition 的state状态 进行判断是否继续执行 子任务
//  * @author: sizegang
//  * @create: 2022 -03-20
//  */
// @Component
// public class CServiceCondition extends AsyncTask<String, TaskResult<String>> {
//
// 	/**
// 	 * The .
// 	 */
// 	int i = 10000;
//
//
// 	@Override
// 	public void prepare(String o) {
//
// 	}
//
// 	@Override
// 	public TaskResult<String> task(String o, TaskSupport support) {
// 		AsyncResult.Builder<String> builder = TaskResult.builder();
// 		try {
//
// 			System.out.println("CServiceCondition Begin");
// 			/**
// 			 * 获取 所依赖的父任务的结果
// 			 */
// 			String result = getResult(support, AServiceCondition.class, String.class);
//
// 			/**
// 			 * 获取自身任务的返回结果 这里获取 结果值为 null
// 			 */
// 			TaskResult<AnyConditionResult<String>> tk = getTaskResult(support);
//
// 			/**
// 			 * 尝试获取 AServiceCondition 任务的返回结果
// 			 */
// 			TaskResult<String> taskResult = getTaskResult(support, AServiceCondition.class, String.class);
// 			/**
// 			 *  设置任务返回结果
// 			 */
// 			if (taskResult != null) {
// 				builder.setResult(taskResult.getResult());
// 			} else {
// 				builder.setResult("Mock CServiceCondition Result ");
// 			}
// 			Thread.sleep(5000);
// 			for (int i1 = 0; i1 < i; i1++) {
// 				i1 += i1;
// 			}
// 			System.out.println("CServiceCondition Finish");
// 		} catch (InterruptedException e) {
// 			e.printStackTrace();
// 			builder.setState(false);
// 		}
// 		return builder.build();
//
// 	}
//
// 	@Override
// 	public boolean nessary(String o, TaskSupport support) {
// 		return true;
// 	}
//
//
// 	@Override
// 	public void onSuccess(TaskSupport support) {
// 		/**
// 		 * 获取自身task 执行完成之后的结果 这里会拿到当前任务的返回结果
// 		 * 第二个参数是 anyCondition 类型
// 		 */
// 		AnyConditionResult<String> result = getResult(support, true);
//
// 		/**
// 		 * 获取 任务结果封装 包含执行状态 TaskResult 是任务执行结果的一个封装
// 		 */
// 		TaskResult<AnyConditionResult<String>> taskResult = getTaskResult(support);
// 	}
//
// 	@Override
// 	public void onFail(TaskSupport support) {
// 	}
// }
