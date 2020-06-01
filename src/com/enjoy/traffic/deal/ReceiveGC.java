package com.enjoy.traffic.deal;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import com.enjoy.traffic.redisManger.RedisFactory;
import com.enjoy.traffic.redisManger.RedisUtil;
import com.enjoy.traffic.util.Common;
import com.enjoy.traffic.util.DbHelper;

import redis.clients.jedis.Jedis;

public class ReceiveGC implements Runnable {
	ServletContextEvent arg0;
	public ReceiveGC(ServletContextEvent arg0) {
		this.arg0=arg0;
		// TODO Auto-generated constructor stub
	}
	private RedisUtil redis;
	private ConcurrentLinkedQueue<String> con;
	public void run() {
		redis=RedisFactory.createRedis();
		Jedis jedis = redis.getJedis();
		con = new ConcurrentLinkedQueue<String>();
		
		String RedisKey=(String) Common.getProperties().get(Common.RedisGC);
		int conSizeNum=Integer.parseInt((String)Common.getProperties().get(Common.GcArray));
		int ccThreadNum=Integer.parseInt((String) Common.getProperties().get(Common.GcThread));
		int size=0;
		
		ExecutorService fixThread = Executors.newFixedThreadPool(ccThreadNum);
		for(int i=0;i<ccThreadNum;i++) {
			fixThread.submit(new WaterRunnable(con));
		}
		//全局变量
		ServletContext context = arg0.getServletContext();
		//获取当前时间
		String date = Common.getFormatDate(Common.TEMPLATE_DAY,Calendar.getInstance());
		//获取当前数据库数据
		int i=Integer.parseInt(DbHelper.readGC(date));
		context.setAttribute(Common.GCNUM, i);
		
		try {
			while(true){
				System.out.println(i);
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
						i++;
						context.setAttribute(Common.GCNUM, i);
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
