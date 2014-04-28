package com.hyq.lpg.redis;

import redis.clients.jedis.Jedis;


public class M {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// JedisPoolConfig jc = new JedisPoolConfig();
		
		 
		//JedisPool pool = new JedisPool(new JedisPoolConfig(), "192.168.1.119");
	
		Jedis jedis =RedisUtil.getJedisPool().getResource();
		jedis.set("44018:7777", "bar");
		//String value = jedis.get("foo");
		
		System.out.println(jedis.keys("44018*"));
		System.out.println(jedis.randomKey());
		
	}

}
