/*
 * Copyright (c) 2020-2030, Shuigedeng (981376577@qq.com & https://blog.taotaocloud.top/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.taotao.cloud.core.endpoint.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;

/**
 * MBeanDemo 
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 21:05:36
 */
@ManagedResource(objectName = "com.taotao.cloud.core.endpoint:name=MBeanDemo")
public class MBeanDemo {

	@ManagedAttribute
	public String getName() {
		return "MBean";
	}

	@ManagedOperation
	public void shutdown() {
		System.exit(0);
	}
}
