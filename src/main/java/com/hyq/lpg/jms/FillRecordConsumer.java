package com.hyq.lpg.jms;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.jms.TextMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.mapper.JsonMapper;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.hyq.lpg.entity.FillRecords;
import com.hyq.lpg.service.FillRecordService;


/**
 * 充装记录消费者
 * @author fjl
 *
 */
public class FillRecordConsumer {
	protected Logger logger = LoggerFactory.getLogger(FillRecordConsumer.class);
	private FillRecordService fillRecordsService;
	
	

	
	





	/**
	 * 消息接收回调函数入口
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private void messageReceive(TextMessage textMessage){
		
		try {
			String	data = textMessage.getStringProperty("data");
			JsonMapper jm = new JsonMapper(Include.NON_NULL);
		
			Map rf = jm.fromJson(data, Map.class);

			if(!StringUtils.isNotBlank((String)rf.get("qybm"))){
				return ;
			}
			/*if(!StringUtils.isNotBlank((String)rf.get("gpbm"))){
				return ;
			}
			
			if(!StringUtils.isNotBlank((String)rf.get("rfidcode"))){
				return ;
			}*/
			
			FillRecords fr = new FillRecords();
			
			String rfs = (String)rf.get("qybm");
			fr.setTenantcode(rfs.split("_")[0]);
			fr.setOfficecode(rfs.split("_")[1]);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			fr.setRfidcode((String)rf.get("rfidcode"));
			fr.setRecid(Long.parseLong((String)rf.get("recid")));
			fr.setGpbm(((String)rf.get("gpbm")).trim());
			fr.setCjqbm(Integer.parseInt((String)rf.get("cjqbm")));
			fr.setZdbm((String)rf.get("zdbm"));
			
			fr.setCzkssj(sdf.parse((String)rf.get("czkssj")));
			fr.setCzyxsj(Integer.parseInt((String)rf.get("czyxsj")));
			fr.setCszl(Double.parseDouble((String)rf.get("cszl")));
			fr.setCzjz(Double.parseDouble((String)rf.get("czjz")));
			fr.setMbzl(Double.parseDouble((String)rf.get("mbzl")));
			fr.setCzzt(Integer.parseInt((String)rf.get("czzt")));
			
	        fillRecordsService.saveTenantFillRecords(fr);
	        logger.info("入库 一条充装记录" + fr.getGpbm());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Autowired
	public void setFillRecordsService(FillRecordService fillRecordsService) {
		this.fillRecordsService = fillRecordsService;
	}
	
	
	
}
