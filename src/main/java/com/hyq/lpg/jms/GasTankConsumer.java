package com.hyq.lpg.jms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.mapper.JsonMapper;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hyq.lpg.entity.GasTank;
import com.hyq.lpg.service.GasTankService;

/**
 * 钢瓶档案消费者
 * @author fjl
 *
 */
public class GasTankConsumer {
	protected Logger logger = LoggerFactory.getLogger(GasTankConsumer.class);
	private GasTankService gasTankService;
	
	
	
	/**
	 * 消息接收回调函数入口
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private void messageReceive(TextMessage textMessage){
			
			
		try{
		String	data = textMessage.getStringProperty("data").toLowerCase();
			
			
		

		JsonMapper jm = new JsonMapper(Include.NON_NULL);

		
		Map rf = jm.fromJson(data, Map.class);
//		FillRecords gr = (FillRecords) JSONObject.toBean(JSONObject.fromObject(data),FillRecords.class);
//		if (StringUtils.isNotBlank(gr.getTenantcode())) {
//			gr.setCzjz(gr.getCzjz() * 0.01);
//			fillRecordsService.save(gr);
//		}
		GasTank gas = new GasTank();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		
		//gas.setBarcode(new java.util.Random().nextInt(999999)+"");
		
		gas.setGpbm(((String)rf.get("gpbm")).trim().toUpperCase());
		gas.setGpid((String)rf.get("gpid"));
		gas.setJdfs(0);
		gas.setXpbm((String)rf.get("rfidcode"));
		
		/*GregorianCalendar gc=new GregorianCalendar(); 
		gc.setTime(scrq); gc.add(1, 4);*/
		
		try {
			gas.setJdsj(sdf.parse((String)rf.get("jdsj"))	);   //建档时间
			gas.setScrq(sdf.parse((String)rf.get("scrq"))	);  //生产日期
			gas.setLastCheckTime(sdf.parse((String)rf.get("lastchecktime")));//
			gas.setNextCheckTime(sdf.parse((String)rf.get("nextchecktime")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block             NEXTCHECKTIME
			e.printStackTrace();
		}
		gas.setGgxh((String)rf.get("ggxh"));

		
		gas.setJdr((String)rf.get("jdr"));
		gas.setMedium((String)rf.get("medium"));
		//gas.setKhbm(khbm);
		gas.setYwzt(0);
		gas.setGpzt(0);  //正常使用
		String rfs = (String)rf.get("qybm");
		gas.setOfficecode(rfs.split("_")[1]);
		gas.setTenantcode(rfs.split("_")[0]);
		gas.setGplx(Integer.parseInt((String)rf.get("gplx")));  //15KG
		gas.setPz(Double.parseDouble((String)rf.get("pz")));
		gas.setSynx(8);   //默认钢瓶8年报废
		//gas.setZzcs(new java.util.Random().nextInt(100));  //充装次数
		gas.setQjzq(4);   //默认使用四年
		gas.setSccj((String)rf.get("sccj"));
	//	gas.setCzsj(czsj);  //最后一次充时间
		gas.setFpr((String)rf.get("fpr"));
	/*	JSONObject j = new JSONObject(gas);
		
		*/
		gasTankService.saveTenantGasTank(gas);
		
		logger.info("入库 一条建档记录" + gas.getTenantcode());
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Autowired
	public void setGasTankService(GasTankService gasTankService) {
		this.gasTankService = gasTankService;
	}
	
	
	
}
