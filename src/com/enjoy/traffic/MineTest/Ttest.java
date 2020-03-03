package com.enjoy.traffic.MineTest;

import com.enjoy.traffic.redisManger.RedisUtil;
import com.enjoy.traffic.util.Common;

import redis.clients.jedis.Jedis;

public class Ttest {
	public static void main(String[] args) {
		/*MqHelper mq_gc = new MqHelper("admin", "admin","tcp://192.168.10.223:61616");
		
		Jedis r = redis.getJedis();
		r.lpush(Common.KEY_DAHUA_ILLEGAL_REDIS_LIST, json);
		r.close();*/
		RedisUtil redis=new RedisUtil();
		for(int i=0;i<600000;i++){
			Jedis r = redis.getJedis();
			r.lpush("WFMQ", "MqHelper mq_gc = new MqHelper(adminadmintcp://192.168.10.223:61616)"+i);
			r.close();
		}
	}
}
