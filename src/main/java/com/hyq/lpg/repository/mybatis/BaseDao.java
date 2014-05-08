package com.hyq.lpg.repository.mybatis;

public abstract interface BaseDao<T> {
	public abstract void add(T t);
	public abstract int update(T t);
	public abstract int delete(T t);
	public abstract int delete(Long id);
	public abstract int get(Long id);
}
