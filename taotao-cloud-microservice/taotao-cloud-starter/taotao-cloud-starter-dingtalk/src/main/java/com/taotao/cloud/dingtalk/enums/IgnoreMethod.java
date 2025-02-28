/*
 * Copyright (c) ©2015-2021 Jaemon. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.dingtalk.enums;


import com.taotao.cloud.dingtalk.model.DingerInvocationHandler;

/**
 * Ignore Method
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:20:59
 */
public enum IgnoreMethod {

	EQUALS("equals") {
		@Override
		public Boolean execute(DingerInvocationHandler invocationHandler, Object[] args) {
			return invocationHandler.equals(args[0]);
		}
	},
	CLONE("clone") {
		@Override
		public Object execute(DingerInvocationHandler invocationHandler, Object[] args)
			throws CloneNotSupportedException {
			return invocationHandler.clone();
		}
	},
	HASH_CODE("hashCode") {
		@Override
		public Integer execute(DingerInvocationHandler invocationHandler, Object[] args)
			throws Exception {
			return invocationHandler.hashCode();
		}
	},
	TO_STRING("toString") {
		@Override
		public String execute(DingerInvocationHandler invocationHandler, Object[] args)
			throws Exception {
			return invocationHandler.toString();
		}
	};

	private String methodName;

	IgnoreMethod(String methodName) {
		this.methodName = methodName;
	}


	public abstract Object execute(DingerInvocationHandler invocationHandler, Object[] args)
		throws Exception;

	public String getMethodName() {
		return methodName;
	}
}
