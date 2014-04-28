package com.hyq.lpg;
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
import org.json.JSONException;
import org.json.JSONObject;
public class MessageSender  extends java.util.TimerTask{
	private static ConnectionFactory connectionFactory;
	private static Connection connection = null;
	private static Destination destination;
	static Session session;
	static int index=10000;
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
	        destination = session.createQueue("fill_record");
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
	   timer.schedule(new MessageSender(), 1000, 2000);
		
	}

	private String get(){

		//String czkssj =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((new java.util.Date()));
		
    //	String czkssj = RandomDate.randomStringDate("2014-03-30 00:00:00", "2014-03-31 00:00:00","yyyy-MM-dd HH:mm:ss");
		Integer cjqbm = new java.util.Random().nextInt(10);
		Integer recid = new java.util.Random().nextInt(999999999);
		
		Integer tenacode = new java.util.Random().nextInt(99999);

		
		Integer gpbm = index++;
		String rfidcode = "XB"+gpbm;
		try {
			JSONObject j = new JSONObject();
			
			j.put("tenantcode", "44018");
			j.put("officecode", "4401801");
			j.put("rfidcode", rfidcode);
			j.put("recid", recid);
			j.put("gpbm", "BD"+gpbm);
			j.put("cjqbm",cjqbm);
			j.put("zdbm", "0");
			//j.put("czkssj", czkssj);
			j.put("czyxsj", "2");
			j.put("cszl", "2255");
			j.put("czjz", "590");
			j.put("mbzl", "1400");
			j.put("czzt", "1");

			return j.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
		
	}
}
