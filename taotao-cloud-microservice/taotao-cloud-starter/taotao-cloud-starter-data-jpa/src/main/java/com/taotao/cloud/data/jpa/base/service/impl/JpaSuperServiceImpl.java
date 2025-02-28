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
package com.taotao.cloud.data.jpa.base.service.impl;

import cn.hutool.core.util.StrUtil;
import com.querydsl.core.types.Predicate;
import com.taotao.cloud.common.exception.LockException;
import com.taotao.cloud.data.jpa.base.entity.JpaSuperEntity;
import com.taotao.cloud.data.jpa.base.repository.JpaSuperRepository;
import com.taotao.cloud.data.jpa.base.service.JpaSuperService;
import com.taotao.cloud.lock.support.DistributedLock;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * service实现父类
 *
 * @author shuigedeng
 * @version 2022.03
 * @since 2020/4/30 10:27
 */
public class JpaSuperServiceImpl<M extends JpaSuperRepository<T, I>, T extends JpaSuperEntity<I>, I
	extends Serializable> implements JpaSuperService<T, I> {

	@Autowired
	protected M jpaRepository;

	/**
	 * 幂等性新增记录 例子如下：
	 * <p>
	 * String username = sysUser.getUsername();
	 * <p>
	 * boolean result = super.saveIdempotency( sysUser , lock , LOCK_KEY_USERNAME+username , new
	 * QueryWrapper<SysUser>().eq("username", username) );
	 *
	 * @param entity    实体对象
	 * @param lock      锁实例
	 * @param lockKey   锁的key
	 * @param predicate 判断是否存在的条件
	 * @param msg       对象已存在提示信息
	 */
	@Override
	public boolean saveIdempotency(T entity, DistributedLock lock, String lockKey,
								   Predicate predicate, String msg) {
		if (lock == null) {
			throw new LockException("分布式锁为空");
		}
		if (StrUtil.isEmpty(lockKey)) {
			throw new LockException("锁的key为空");
		}

		//try {
		//	boolean isLock = lock.lock(lockKey);
		//	if (isLock) {
		//		long count = jpaRepository.count(predicate);
		//		if (count == 0) {
		//			jpaRepository.save(entity);
		//			return true;
		//		} else {
		//			throw new IdempotencyException(StrUtil.isEmpty(msg) ? "数据已存在" : msg);
		//		}
		//	} else {
		//		throw new LockException("锁等待超时");
		//	}
		//} catch (Exception e) {
		// e.printStackTrace();
		//} finally {
		// try {
		//	 lock.unlock(lockKey);
		// } catch (Exception e) {
		//	 e.printStackTrace();
		// }
		//}
		return true;
	}

	/**
	 * 幂等性新增记录
	 *
	 * @param entity    实体对象
	 * @param lock      锁实例
	 * @param lockKey   锁的key
	 * @param predicate 判断是否存在的条件
	 */
	@Override
	public boolean saveIdempotency(T entity, DistributedLock lock, String lockKey,
								   Predicate predicate) {
		return saveIdempotency(entity, lock, lockKey, predicate, null);
	}

	/**
	 * 幂等性新增或更新记录 例子如下： String username = sysUser.getUsername(); boolean result =
	 * super.saveOrUpdateIdempotency(sysUser, lock , LOCK_KEY_USERNAME+username , new
	 * QueryWrapper<SysUser>().eq("username", username));
	 *
	 * @param entity    实体对象
	 * @param lock      锁实例
	 * @param lockKey   锁的key
	 * @param predicate 判断是否存在的条件
	 * @param msg       对象已存在提示信息
	 */
	@Override
	public boolean saveOrUpdateIdempotency(T entity, DistributedLock lock, String lockKey,
										   Predicate predicate, String msg) {
		// if (null != entity) {
		// 	Class<?> cls = entity.getClass();
		// 	TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
		// 	if (null != tableInfo && StrUtil.isNotEmpty(tableInfo.getKeyProperty())) {
		// 		Object idVal = ReflectionKit.getFieldValue(entity, tableInfo.getKeyProperty());
		// 		if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) {
		// 			return this.saveIdempotency(entity, lock, lockKey, countWrapper, StrUtil.isEmpty(msg) ? "数据已存在" : msg);
		// 		} else {
		// 			return updateById(entity);
		// 		}
		// 	} else {
		// 		throw ExceptionUtils.mpe("执行错误,未找到@TableId.");
		// 	}
		// }
		return false;
	}

	/**
	 * 幂等性新增或更新记录 例子如下： String username = sysUser.getUsername(); boolean result =
	 * super.saveOrUpdateIdempotency(sysUser, lock , LOCK_KEY_USERNAME+username , new
	 * QueryWrapper<SysUser>().eq("username", username));
	 *
	 * @param entity    实体对象
	 * @param lock      锁实例
	 * @param lockKey   锁的key
	 * @param predicate 判断是否存在的条件
	 */
	@Override
	public boolean saveOrUpdateIdempotency(T entity, DistributedLock lock, String lockKey,
										   Predicate predicate) {
		return this.saveOrUpdateIdempotency(entity, lock, lockKey, predicate, null);
	}
}
