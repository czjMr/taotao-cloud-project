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
package com.taotao.cloud.dingtalk.entity;


import com.taotao.cloud.dingtalk.model.DingerConfig;
import com.taotao.cloud.dingtalk.multi.AlgorithmHandler;
import java.util.List;

/**
 * MultiDingerConfig
 *
 * @author shuigedeng
 * @version 2022.07
 * @since 2022-07-06 15:20:15
 */
public class MultiDingerConfig {

	private AlgorithmHandler algorithmHandler;
	private List<DingerConfig> dingerConfigs;

	public MultiDingerConfig(AlgorithmHandler algorithmHandler, List<DingerConfig> dingerConfigs) {
		this.algorithmHandler = algorithmHandler;
		this.dingerConfigs = dingerConfigs;
	}

	public AlgorithmHandler getAlgorithmHandler() {
		return algorithmHandler;
	}

	public void setAlgorithmHandler(AlgorithmHandler algorithmHandler) {
		this.algorithmHandler = algorithmHandler;
	}

	public List<DingerConfig> getDingerConfigs() {
		return dingerConfigs;
	}

	public void setDingerConfigs(List<DingerConfig> dingerConfigs) {
		this.dingerConfigs = dingerConfigs;
	}
}
