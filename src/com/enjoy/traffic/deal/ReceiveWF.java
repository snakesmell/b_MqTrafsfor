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

public class ReceiveWF implements Runnable {
	private RedisUtil redis;
	private ConcurrentLinkedQueue<String> con;
	public void run() {
		redis=RedisFactory.createRedis();
		con = new ConcurrentLinkedQueue<String>();
		Jedis jedis = redis.getJedis();
		
		String RedisKey=(String) Common.getProperties().get(Common.RedisWF);
		int conSizeNum=Integer.parseInt((String)Common.getProperties().get(Common.WfArray));
		int ccThreadNum=Integer.parseInt((String) Common.getProperties().get(Common.WfThread));
		int size=0;
		
		ExecutorService fixThread = Executors.newFixedThreadPool(ccThreadNum);
		for(int i=0;i<ccThreadNum;i++) {
			fixThread.submit(new IllegalRunnable(con));
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
					String json = jedis.rpop(RedisKey);
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
