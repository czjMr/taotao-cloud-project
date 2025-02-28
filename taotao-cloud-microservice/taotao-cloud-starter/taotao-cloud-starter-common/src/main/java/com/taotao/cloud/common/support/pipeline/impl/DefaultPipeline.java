package com.taotao.cloud.common.support.pipeline.impl;


import com.taotao.cloud.common.support.pipeline.Pipeline;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 默认的管道实现
 *
 * @author shuigedeng
 * @version 2022.04
 * @since 2022-04-27 17:10:57
 */
public class DefaultPipeline<T> implements Pipeline<T> {

	/**
	 * 创建一个内部的链表
	 */
	private final LinkedList<T> list = new LinkedList<>();

	@Override
	public Pipeline<T> addLast(T t) {
		list.addLast(t);
		return this;
	}

	@Override
	public Pipeline<T> addFirst(T t) {
		list.addFirst(t);
		return this;
	}

	@Override
	public Pipeline<T> set(int index, T t) {
		list.set(index, t);
		return this;
	}

	@Override
	public Pipeline<T> removeLast() {
		list.removeLast();
		return this;
	}

	@Override
	public Pipeline<T> removeFirst() {
		list.removeFirst();
		return this;
	}

	@Override
	public Pipeline<T> remove(int index) {
		list.remove(index);
		return this;
	}

	@Override
	public T get(int index) {
		return list.get(index);
	}

	@Override
	public T getFirst() {
		return list.getFirst();
	}

	@Override
	public T getLast() {
		return list.getLast();
	}

	@Override
	public List<T> list() {
		return Collections.unmodifiableList(list);
	}

	@Override
	public List<T> slice(int startIndex, int endIndex) {
		return list.subList(startIndex, endIndex);
	}
}
