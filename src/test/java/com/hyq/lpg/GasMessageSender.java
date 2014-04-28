package com.hyq.lpg;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Timer;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springside.modules.mapper.JsonMapper;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hyq.lpg.entity.GasTank;
public class GasMessageSender  extends java.util.TimerTask{
	private static  ConnectionFactory connectionFactory;
	private static Connection connection = null;
	private static  Destination destination;
	
	private static  int index=20000;
	static Session session;
	static MessageProducer producer;
	static {
        connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://localhost:61616");
       
        try {
        	
        	System.out.println(1111111);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(Boolean.TRUE,
	                Session.AUTO_ACKNOWLEDGE);
			
	        // 获取操作连接
	        
	        // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
	        destination = session.createQueue("gas_tank");
	        // 得到消息生成者【发送者】
	        producer = session.createProducer(destination);
	        // 设置不持久化，此处学习，实际根据项目决定
	        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
	        // 构造消息，此处写死，项目就是参数，或者方法获取
	        //sendMessage(session,producer);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
			//	connection.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
    }

   
   
	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 构造从工厂得到连接对象
        try {
			//connection = connectionFactory.createConnection();
		
	        // 启动
        	
	        
	       
	        TextMessage message = session.createTextMessage(get());
			 // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息");
            producer.send(message);
            session.commit();
	      //  Timer timer = new Timer();
	        
           
            if(index==10100){
            	this.cancel();
            }
	      //  timer.schedule(new MyMessageTime(connection,session, producer), 1000, 2000);
        } catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null!=connection){
				try {
					//connection.stop();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args){
		Timer timer = new Timer();
	    timer.schedule(new GasMessageSender(), 1000, 2000);
	   
		
	}

	private String get(){

		//String czkssj =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((new java.util.Date()));
		 
    	Date czsj  = RandomDate.randomDate("2013-11-01 00:00:00", "2014-04-11 00:00:00","yyyy-MM-dd HH:mm:ss");
    	Date jdsj  = RandomDate.randomDate("2013-11-01 00:00:00", "2014-04-11 00:00:00","yyyy-MM-dd HH:mm:ss");
    	Date scrq  = RandomDate.randomDate("2013-11", "2014-04","yyyy-MM");
		Integer gpbm = index++;
		String rfidcode = "XB"+gpbm;
			
		
			GasTank gas = new GasTank();
			
			
			gas.setBarcode(new java.util.Random().nextInt(999999)+"");
			
			gas.setGpbm("BD"+gpbm);
			gas.setGpid("37018BD"+gpbm);
			gas.setJdfs(0);
			gas.setXpbm(rfidcode);
			gas.setJdsj(jdsj);   //建档时间
			gas.setScrq(scrq);  //生产日期
			gas.setLastCheckTime(scrq);//
			GregorianCalendar gc=new GregorianCalendar(); 
			gc.setTime(scrq); gc.add(1, 4);
			gas.setNextCheckTime(gc.getTime());
			gas.setMedium(0+"");
			//gas.setKhbm(khbm);
			gas.setYwzt(0);
			gas.setGpzt(0);  //正常使用
			gas.setOfficecode(3701801+"");
			gas.setTenantcode(37018+"");
			gas.setGplx(1);  //15KG
			gas.setPz(16.5);
			gas.setSynx(8);   //默认钢瓶8年报废
			gas.setZzcs(new java.util.Random().nextInt(100));  //充装次数
			gas.setQjzq(4);   //默认使用四年
			gas.setSccj(new java.util.Random().nextInt(10)+"");
			gas.setCzsj(czsj);  //最后一次充时间
			gas.setFpr("fangji");
		/*	JSONObject j = new JSONObject(gas);
			
			*/
			JsonMapper jm = new JsonMapper(Include.NON_DEFAULT);
			return jm.toJson(gas);
			
		
		
	}
	
	
	
	
	
	
}
