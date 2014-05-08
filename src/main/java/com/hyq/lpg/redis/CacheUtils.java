
package com.hyq.lpg.redis;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class CacheUtils {
	
	private  CacheManager cacheManager = null;

	private static final String SYS_CACHE = "sysCache";

	public  Object get(String key) {
		return get(SYS_CACHE, key);
	}

	public  void put(String key, Object value) {
		put(SYS_CACHE, key, value);
	}

	public  void remove(String key) {
		remove(SYS_CACHE, key);
	}
	
	public  Object get(String cacheName, String key) {
		Element element = getCache(cacheName).get(key);
		return element==null?null:element.getObjectValue();
	}

	public  void put(String cacheName, String key, Object value) {
		Element element = new Element(key, value);
		getCache(cacheName).put(element);
	}

	public  void remove(String cacheName, String key) {
		getCache(cacheName).remove(key);
	}
	
	/**
	 * 获得一个Cache，没有则创建一个。
	 * @param cacheName
	 * @return
	 */
	private  Cache getCache(String cacheName){
		Cache cache = this.cacheManager.getCache(cacheName);
		if (cache == null){
			cacheManager.addCache(cacheName);
			cache = cacheManager.getCache(cacheName);
			cache.getCacheConfiguration().setEternal(true);
		}
		return cache;
	}

	public  CacheManager getCacheManager() {
		return cacheManager;
	}
	
}
