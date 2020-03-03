package com.enjoy.traffic.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.enjoy.traffic.deal.ReceiveWF;
public class ServerStart implements ServletContextListener{
	private final Logger logger= LogManager.getLogger(this.getClass());
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ReceiveWF rf = new ReceiveWF();
		Thread tf=new Thread(rf);
		tf.start();
	}
}
