package com.enjoy.traffic.deal;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.enjoy.traffic.redisManger.RedisFactory;
import com.enjoy.traffic.redisManger.RedisUtil;
import com.enjoy.traffic.util.Common;
import com.enjoy.traffic.util.MqHelper;

import redis.clients.jedis.Jedis;

public class ReceiveGC implements Runnable {
	private RedisUtil redis;
	private ConcurrentLinkedQueue<String> con;
	public void run() {
		redis=RedisFactory.createRedis();
		con = new ConcurrentLinkedQueue<String>();
		
		String RedisKey=(String) Common.getProperties().get(Common.RedisGC);
		int conSizeNum=Integer.parseInt((String)Common.getProperties().get(Common.GcArray));
		int ccThreadNum=Integer.parseInt((String) Common.getProperties().get(Common.GcThread));
		int size=0;
		
		ExecutorService fixThread = Executors.newFixedThreadPool(ccThreadNum);
		for(int i=0;i<ccThreadNum;i++) {
			fixThread.submit(new WaterRunnable(con));
		}
		try {
			int i=0;
			while(true){
				size=con.size();
				if(size>=conSizeNum) {//判断线程队列是否已满
					Thread.sleep(500);
					continue;
				}
				try {
					
					Jedis jedis = redis.getJedis();
					String json = jedis.rpop(RedisKey);
					jedis.close();
					//Map map=(Map)JSONValue.parse(message);
					if(json==null){
						Thread.sleep(Common.delay());//no data wating...
					}else{
						con.add(json);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
