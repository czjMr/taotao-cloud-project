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
package com.taotao.cloud.common.support.blockchain;

import com.taotao.cloud.common.utils.date.DateUtils;
import com.taotao.cloud.common.utils.log.LogUtils;
import com.taotao.cloud.common.utils.secure.SHAUtils;

/**
 * Block
 *
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-02 19:39:21
 */
public class Block {

	/**
	 * hash
	 */
	public String hash;
	/**
	 * previousHash
	 */
	public String previousHash;

	/**
	 * 区块链数据，基本的数据
	 */
	private String data;
	/**
	 * timestamp
	 */
	private long timestamp;
	/**
	 * nonce
	 */
	private int nonce;

	/**
	 * 区块链构造方法
	 */
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timestamp = DateUtils.getTimestamp();
		// 确保hash值的来源
		this.hash = calculateHash();
	}

	/**
	 * 使用 sha256 算法让一个输入转变成256位的hash值
	 *
	 * @return {@link java.lang.String }
	 * @since 2021-09-02 19:39:49
	 */
	public String calculateHash() {
		return SHAUtils.encrypt256(previousHash +
			timestamp +
			nonce +
			data);
	}

	/**
	 * Increases nonce value until hash target is reached
	 *
	 * @param difficulty difficulty
	 * @return {@link java.lang.String }
	 * @since 2021-09-02 19:39:56
	 */
	public String mineBlock(int difficulty) {
		// Create a string with difficulty * "0"
		String target = getDificultyString(difficulty);
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce++;
			hash = calculateHash();
		}
		LogUtils.info("Block Mined: " + hash);
		return hash;
	}

	/**
	 * Returns difficulty string target, to compare to hash. eg difficulty of 5 will return "00000"
	 *
	 * @param difficulty difficulty
	 * @return {@link java.lang.String }
	 * @author shuigedeng
	 * @since 2021-09-02 19:40:08
	 */
	private static String getDificultyString(int difficulty) {
		return new String(new char[difficulty]).replace('\0', '0');
	}

	public String getData() {
		return data;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public int getNonce() {
		return nonce;
	}
}
