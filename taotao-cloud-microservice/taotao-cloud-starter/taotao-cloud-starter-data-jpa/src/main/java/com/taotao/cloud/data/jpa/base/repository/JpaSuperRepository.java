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
package com.taotao.cloud.data.jpa.base.repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaPredicateExecutor;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * 基础jpa Repository
 *
 * @param <T> the type of the entity to handle
 * @param <I> the type of the entity's identifier
 * @author shuigedeng
 * @version 2021.9
 * @since 2021-09-04 07:32:26
 */
public abstract class JpaSuperRepository<T, I extends Serializable> extends
	SimpleJpaRepository<T, I> {

	protected final JPAQueryFactory jpaQueryFactory;
	protected final QuerydslJpaPredicateExecutor<T> jpaPredicateExecutor;
	protected final EntityManager em;
	private final EntityPath<T> path;
	protected final Querydsl querydsl;

	public JpaSuperRepository(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.em = em;
		this.jpaPredicateExecutor = new QuerydslJpaPredicateExecutor<>(
			JpaEntityInformationSupport.getEntityInformation(domainClass, em), em,
			SimpleEntityPathResolver.INSTANCE, getRepositoryMethodMetadata());
		this.jpaQueryFactory = new JPAQueryFactory(em);
		this.path = SimpleEntityPathResolver.INSTANCE.createPath(domainClass);
		this.querydsl = new Querydsl(em, new PathBuilder<T>(path.getType(), path.getMetadata()));
	}

	/**
	 * findPageable
	 *
	 * @param predicate predicate
	 * @param pageable  pageable
	 * @param orders    orders
	 * @return {@link Page }<{@link T }>
	 * @since 2022-09-02 08:28:43
	 */
	public Page<T> findPageable(Predicate predicate, Pageable pageable,
								OrderSpecifier<?>... orders) {
		final JPAQuery<T> countQuery = jpaQueryFactory.selectFrom(path);
		countQuery.where(predicate);
		JPQLQuery<T> query = querydsl.applyPagination(pageable, countQuery);
		query.orderBy(orders);
		return PageableExecutionUtils.getPage(query.fetch(), pageable,
			() -> countQuery.fetch().size());
	}

	/**
	 * count
	 *
	 * @param predicate predicate
	 * @return int
	 * @since 2022-09-02 08:28:54
	 */
	public int count(Predicate predicate) {
		return jpaQueryFactory.selectFrom(path)
			.where(predicate)
			.fetch()
			.size();
	}

	/**
	 * exists
	 *
	 * @param predicate predicate
	 * @return {@link Boolean }
	 * @since 2022-09-02 08:29:00
	 */
	public Boolean exists(Predicate predicate) {
		return jpaPredicateExecutor.exists(predicate);
	}

	/**
	 * fetch
	 *
	 * @param predicate predicate
	 * @return {@link List }<{@link T }>
	 * @since 2022-09-02 08:29:03
	 */
	public List<T> fetch(Predicate predicate) {
		return jpaQueryFactory.selectFrom(path)
			.where(predicate)
			.fetch();
	}

	/**
	 * fetchOne
	 *
	 * @param predicate predicate
	 * @return {@link T }
	 * @since 2021-10-09 20:30:50
	 */
	public T fetchOne(Predicate predicate) {
		return jpaQueryFactory.selectFrom(path)
			.where(predicate)
			.fetchOne();
	}

	/**
	 * fetchCount
	 *
	 * @param predicate predicate
	 * @return int
	 * @since 2022-09-02 08:29:08
	 */
	public int fetchCount(Predicate predicate) {
		return jpaQueryFactory.selectFrom(path)
			.where(predicate)
			.fetch()
			.size();
	}

	/**
	 * find
	 *
	 * @param predicate predicate
	 * @param expr      expr
	 * @param o         o
	 * @return {@link List }<{@link ? }>
	 * @since 2022-09-02 08:29:12
	 */
	public List<?> find(Predicate predicate, Expression<?> expr, OrderSpecifier<?>... o) {
		return jpaQueryFactory
			.select(expr)
			.from(path)
			.where(predicate)
			.orderBy(o)
			.fetch();
	}
}
