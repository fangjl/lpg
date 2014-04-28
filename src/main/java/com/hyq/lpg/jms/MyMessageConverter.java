package com.hyq.lpg.jms;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
public class MyMessageConverter implements MessageConverter {
	/**
	 * 接收端转换
	 */
	@Override
	public Object fromMessage(Message message) throws JMSException,
			MessageConversionException {
		// TODO Auto-generated method stub
		
		if (message instanceof TextMessage) {
			return (TextMessage) message;
		}
		return null;
	}

	
	/**
	 * 发送端转换
	 */
	@Override
	public Message toMessage(Object arg0, Session arg1) throws JMSException,
			MessageConversionException {
		// TODO Auto-generated method stub
		return null;
	}

}
