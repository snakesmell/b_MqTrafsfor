package com.enjoy.traffic.deal;

import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.enjoy.traffic.util.MqHelper;

public class WaterRunnable implements Runnable {
	ConcurrentLinkedQueue<String> con;
//	private AutoUpload autoUpload_water;
//	private ActiveMQUtil activeMQUtil;
	private MqHelper mqHelper;
	
	public WaterRunnable(ConcurrentLinkedQueue<String> con ) {
		this.con=con;
		//本地MQ
//		activeMQUtil=new ActiveMQUtil(MQ_LOCAL_NAME,MQ_LOCAL_PW,MQ_LOCAL_IP,MQ_LOCAL_PORT);
//		activeMQUtil.setDelayTime(delayTime);
		//海信MQ上传
		mqHelper=new MqHelper("admin", "admin","tcp://192.168.10.223:61616");
//		autoUpload_water = new AutoUpload(activeMQUtil_water,ipList,isIPConvert,isFtp);			
	}
	@Override
	public void run() {
		String json=null;
		boolean flag=true;
		while(true) {
			try {
				json = con.poll();
				if(json==null||json.trim().equals("")) {
					Thread.sleep(100);
				}else {
					//MQ send
					mqHelper.sendTopicMessage("topic_1", json);
				}
			} catch (Exception e) {
				flag=false;
				e.printStackTrace();
			}
		}
	}
}
