package com.hyq.lpg.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	
//	private static String ip = "192.168.1.108";
	private static String ip = "115.28.176.222";
//	private static String ip = "192.168.1.108";
	private static int port = 6379;
	private static int outTime = 100000;
	@SuppressWarnings("unused")
	private static String pwd = "bjdlh2014";
	private static JedisPool pool = null;
			//new JedisPool(new JedisPoolConfig(), ip, port, outTime, pwd);
	
	
	public static JedisPool getJedisPool(){
		return new JedisPool(new JedisPoolConfig(), ip, port, outTime);
	}
	
	
	public static String hget(String key, String field,int dataNum) {
		String fieldValue = null;
		JedisPool pool = getJedisPool();
		Jedis jedis = pool.getResource();
		jedis.select(dataNum); 
		try {
//			if(jedis.hexists(key, field)){
			fieldValue = jedis.hget(key, field);
//			}
		} finally {
			pool.returnResource(jedis);
		}
		pool.destroy();
		return fieldValue;
	}
	
	
	public static boolean hexists(String key, String field,int dataNum){
		System.out.println(key+","+field);
		boolean isExists = false;
		Jedis jedis = getJedisPool().getResource();
		jedis.select(dataNum); 
		try {
			isExists = jedis.hexists(key, field);
		} finally {
			pool.returnResource(jedis);
		}
		pool.destroy();
		return isExists;
	}

}
