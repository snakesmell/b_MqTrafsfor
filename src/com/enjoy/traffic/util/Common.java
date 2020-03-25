package com.enjoy.traffic.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public final class Common {
	/**
	 * 车辆号牌号码处理，如果旧系统带有颜色，则去除
	 * @param hphm
	 * @return
	 */
	public static String dealHPHM(String hphm){
		int hphmLen=hphm.length();
		if(hphmLen>7){
			hphm=hphm.substring(1, hphmLen);
		}
		return hphm;
	}
	/**
	 * 违法代码处理，如果旧违法代码则加0矫正
	 * @param wfdm
	 * @return
	 */
	public static String dealWFDM(String wfdm){
		int wfdmLen=wfdm.length();
		if(wfdmLen<5){
			wfdm=wfdm+"0";
		}
		return wfdm;
	}
	/**
	 * 去掉道路代码.0位
	 * @param s
	 * @return
	 */
	public static int transforInt(String s){
		return (int)Float.parseFloat(s);
	}
	public static String stringTransforArray(String date,EnumUtil e) {
		String[] s = date.split(" ");
		String[] a = s[0].split("-");//DAY
		String[] b = s[1].split(":");//HOUR
		switch(e) {
			case YEAR:return a[0].trim();
			case MONTH:return a[1].trim();
			case DAY:return a[2].trim();
			case HOUR:return b[0].trim();
			case MINUTE:return b[1].trim();
			case SECOND:return b[2].trim();
			case YMD:return a[0].trim()+a[1].trim()+a[2].trim();
			case YMDH:return a[0].trim()+a[1].trim()+a[2].trim()+b[0].trim();
			case YMDHM:return a[0].trim()+a[1].trim()+a[2].trim()+b[0].trim()+b[1].trim();
		}
		return date;
	}
	public static String WfArray="wfArray";//每队列容积
	public static String WfThread="wfThread";//线程数
	
	public static String GcArray="gcArray";//每队列容积
	public static String GcThread="gcThread";//线程数
	
	public static boolean True=true;
	public static boolean False=false;
	//MQ配置表
	public static String SQL_MQ=" select * from XT_CK_ZC_FW_MQ ";
	//系统配置信息
	public static String WEB_Param=" select * from XT_CS_JC ";
	//大货车相机
	public static String SQL_DHC=" SELECT cksb.CODE from  (SELECT ID FROM XT_CK_SB_SP_SXJ  where SXJLX=1) sxj LEFT JOIN XT_CK_SB cksb on sxj.id =cksb.id  ";
	//redis list 违法
	public static String KEY_DAHUA_ILLEGAL_REDIS_LIST="DahuaIllegalList";
	//redis list 过车
	public static String KEY_DAHUA_WATER_REDIS_LIST="DahuaWaterList";
	//redis list 视频网过车
	public static String KEY_VIDEO_DAHUA_WATER_REDIS_LIST="VideoDahuaWaterList";
	
	//redis list 违法
	public static String KEY_Hikvison_ILLEGAL_REDIS_LIST="HikvisonIllegalList";
	//redis list 过车
	public static String KEY_Hikvison_WATER_REDIS_LIST="HikvisonWaterList";
	//redis list 视频网过车
	public static String KEY_VIDEO_Hikvison_WATER_REDIS_LIST="VideoHikvisonWaterList";
	
	public static String getNowDate(){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sim.format(new Date());
	}
	public static String TEMPLATE_YEAR="yyyy";
	public static String TEMPLATE_MONTH="yyyyMM";
	public static String TEMPLATE_DAY="yyyyMMdd";
	public static String TEMPLATE_HOUR="yyyyMMddHH";
	public static String TEMPLATE_MINUTE="yyyyMMddHHmm";
	public static String getFormatDate(String Template,Calendar cal){
        SimpleDateFormat sim = new SimpleDateFormat(Template);
        return sim.format(cal.getTime());
	}
	//定时生成表的数量
	public static int TABLE_NUMS=7;
	//定时创建过车表
	public static String NEW_WATER_TABLE="create table MRS_VEH_SNAP_? as select * from MRS_VEH_SNAP where 1=2";
	public static String waterIndex_id="id";//主键
	public static String waterIndex_cjsj="DCOLLECTIONDATE";//采集时间
	public static String waterIndex_hphm="CCARNUMBER";//号牌号码
	public static String waterIndex_hplxid="HPLXID";
	public static String waterIndex_cjdz="CADDRESSCODE";//采集地址
	public static String waterIndex_fxbh="FXBH";
	public static String waterIndex_cdxh="CDXH";
	public static String waterIndex_all="CLICENSETYPE,CDEVICECODE,DCOLLECTIONDATE,CDIRECTION,CLANENUMBER";
	
	//查询过车表
	public static String NEW_WATER_TABLE_NAME="MRS_VEH_SNAP_?";
	//定时创建违法表
	public static String NEW_ILLEGAL_TABLE="create table TR_ZP_TX101ZPWFJL_? as select * from TR_ZP_TX101ZPSYJL where 1=2";
	//查询违法表
	public static String NEW_ILLEGAL_TABLE_NAME="TR_ZP_TX101ZPWFJL_?";
	//过车存储过程
	public static String SP_TR_ZP_TX101ZPSYJL_ADD="SP_TR_ZP_TX101ZPSYJL_ADD";
	//违法存储
	//1 白名单
	public static String SP_TR_ZP_TX101ZPWFJL_BMD_ADD="SP_TR_ZP_TX101ZPWFJL_BMD_ADD";
	//2 大货车
	public static String SP_TR_ZP_TX101ZPWFJL_DHC_ADD="SP_TR_ZP_TX101ZPWFJL_DHC_ADD";
	//3 违法表
	public static String SP_TR_ZP_TX101ZPWFJL_ADD="SP_TR_ZP_TX101ZPWFJL_ADD"; 
	//4缉查布控车辆表
	public static String BLACKLIST="blacklist";
	//5实时报警表
	public static String BLACKLIST_ALERT="blacklist_alert";
	//6缉查布控
	public static String JCBK="jcbk";
	//6实时追踪
	public static String SSZZ="sszz";
	//初始化
	public static int PRO_DEFAULE=0;
	//获取当前时间毫秒计算
	public static long getDateLong(){
		return new Date().getTime();
	}

	/**
	 * MQ通讯Topic
	 */
	public static String HMD="MQ_GCSJ_BKBJ";
	public static String HISENSE_PASS="HIATMP.HISENSE.PASS.PASSINF";
	public static String MQ_DefaultUser="admin";
	public static String MQ_DefaultPassword="admin";
	//配置表字段名
	public static String UPLOAD_MQ_USER="UPLOAD_MQ_USER_GC";
	public static String UPLOAD_MQ_PWD="UPLOAD_MQ_PWD_GC";
	public static String ZP_LSSC_MQDestination="ZP_LSSC_MQDestination";
	public static String ZP_WFSC_MQURL="ZP_WFSC_MQURL";
	//视频网配置表字段名
	public static String UPLOAD_VIDEO_MQ_USER="UPLOAD_VIDEO_MQ_USER";
	public static String UPLOAD_VIDEO_MQ_PWD="UPLOAD_VIDEO_MQ_PWD";
	public static String ZP_LSSC_VIDEO_MQDestination="ZP_LSSC_VIDEO_MQDestination";
	public static String ZP_WFSC_VIDEO_MQURL="ZP_WFSC_VIDEO_MQURL";
	//过车
	public static String GCJK="MQ_JKSJ_YS";
	//获取配置文件
	public static Properties getProperties(){
		Properties pro=null;
		try {
			InputStream file = new FileInputStream(Common.class.getClassLoader().getResource("config.properties").getFile());
			 pro = new Properties();
			 pro.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
         return pro;
	}
	public static String MQURL="mqurl";
	public static String MQNAME="mqname";
	public static String MQPW="mqpw";
	public static String TopicWf="topicWf";
	public static String TopicGc="topicGc";
	
	public static String RedisWF="redis_wf";
	public static String RedisGC="refis_gc";
	public static String RedisAddress="redis_add";
	public static String RedisPort="redis_port";
	public static String RedisNum="redis_num";
	//并发开启线程数
	public static String DahuaWaterThread="dahuaWaterThread";
	public static String DahuaIllegalThread="dahuaIllegalThread";
	public static String VideoDahuaWaterThread="videoDahuaWaterThread";
	
	public static String HikvisonWaterThread="hikvisonWaterThread";
	public static String HikvisonIllegalThread="hikvisonIllegalThread";
	public static String VideoHikvisonWaterThread="videoHikvisonWaterThread";
	
	public static String WAIT="wait";
	//黑名单表明
	public static String Black_Alert_Device="Black_Alert_Device";
	public static String black_config="black_config";
	public static String JCBK_TABLE="blacklist";
	public static String BMD_TABLE="ilg_whitelist";
	public static String DHC_TABLE="XT_CK_SB_SP_SXJ";
	public static String GCMQ_TABLE="TR_ZP_GCMQ";
	public static String XT_SBBH_NO_TRANSFER="XT_SBBH_NO_TRANSFER";
	//redis数据延迟
//	public static int Delay=1000*Integer.parseInt((String)Common.getProperties().get(WAIT));
	public static int delay(){
		try {
			int delay = 1000*Integer.parseInt((String)Common.getProperties().get(WAIT));
			if(delay<=0){
				return 500;
			}else{
				return delay;
			}
		} catch (NumberFormatException e) {
			return 0;
		}
	}
	//黑名单表
	public static String HMD_TABLE_SAVE="INSERT INTO HMD_ALARM_RECORD VALUES(S_HEIMD_ALARM_RECORD.NEXTVAL,?SYSDATE)";
	//过车表
//	public static String WATER_TABLE_SAVE="INSERT INTO TR_ZP_TX101ZPSYJL_? (ID,SJLY,ZPXTID,ZPXTJLID,MSGID,HPLXIDZDSB,HPHMZDSB,HPLXID,HPHM,HPYSID,CSYSID,CLLXID,CBID,CJSJ,CJSJMonth,CJSJDay,CJSJHour,CJDBH,CJDZ,CJJGBH,DeptID,FXBH,CDXH,TDID,CLSD,ZJTP1,ZJTP2,ZJTP3,ZJTP4,ZJTP4INFO,ZJRLTP,ZJSP,ZPLX,ZPMBID,SBBH,SBID,DRSJ,WFBJ,SCZT,HONGBJ,HEIBJ,BAIBJ,Memo) values(S_TR_ZP_TX101ZPSYJL.Nextval,?)";
	public static String getWaterTableSaveSql(String date,String value){
		StringBuffer sb=new StringBuffer();
		sb.append(" INSERT INTO TR_ZP_TX101ZPSYJL_");
		sb.append(date);
		sb.append(" (ID,SJLY,ZPXTID,ZPXTJLID,MSGID,HPLXIDZDSB,HPHMZDSB,HPLXID,HPHM,HPYSID,CSYSID,CLLXID,CBID,CJSJ,CJSJMonth,CJSJDay,CJSJHour,CJDBH,CJDZ,CJJGBH,DeptID,FXBH,CDXH,TDID,CLSD,ZJTP1,ZJTP2,ZJTP3,ZJTP4,ZJTP4INFO,ZJRLTP,ZJSP,ZPLX,ZPMBID,SBBH,SBID,DRSJ,WFBJ,SCZT,HONGBJ,HEIBJ,BAIBJ,Memo) ");
		sb.append(" values(S_TR_ZP_TX101ZPSYJL.Nextval,"+value+")");
		return sb.toString();
	}
	public static String getIllegalTableSaveSql(String date,String value){
		StringBuffer sb=new StringBuffer();
		sb.append(" INSERT INTO TR_ZP_TX101ZPWFJL_");
		sb.append(date);
		sb.append(" (ID,WFLXID,SJLY,WFSJLYLX,ZPXTID,ZPXTJLID,MSGID,HPLXIDZDSB,HPHMZDSB,HPLXID,HPHM,HPYSID,CSYSID,CBID,CJSJ,CJSJMonth,CJSJDay,CJSJHour,WFDMZDSB,WFDM,CJDBH,CJDZ,CJJGBH,DeptID,FXBH,CDXH,TDID,ZJTP1,ZJTP2,ZJTP3,ZJTP4,ZJTP4INFO,ZPLX,SBBH,SBID,DRSJ,WFBJ, SCZT,HONGBJ,HEIBJ,BAIBJ,SXZT,SFZF,Memo,clsd,shortVideoPath) ");
		sb.append(" values(S_TR_ZP_TX101ZPSYJL.Nextval,"+value+")");
		return sb.toString();
	}
	//过车表注销字段
	public static String CANCEL_FIELD[]={"0","1"};
	public static String SIGN_COMMA=",";
	public static String SIGN_QUESTION="?";
	public static String SIGN_NULL="null";
	public static String IllegalTable_MEMO="违章数据导入";
	
	public static String MQ_COUNT_FLAG="mqcountflag";
	//HMD begin
	public static String Sound="sound";
	public static String HMDLX_TABLE="select id ,name as text,sound from tr_md_heimdlx_jc ";
	public static String Mdlxid="mdlxid";
	//HMD end
	public static String LOCAL_MQ="LOCAL_MQ";
}



