package com.hyq.lpg.web;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.common.collect.Maps;
import com.hyq.lpg.service.FillRecordService;
import com.hyq.lpg.service.GasTankService;
@Controller
@RequestMapping(value="/test")
public class TestController {
	private FillRecordService fillRecordService;
	private GasTankService gasTankService;
	@RequestMapping(value="test01" ,method = RequestMethod.GET)
	public String index(){
		return "test01";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="bar" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Map> bar(){
		Map<String, String> params = Maps.newHashMap();
		params.put("tenantcode","37018");
		params.put("officecode","3701801");
		params.put("type","1");
		return new ResponseEntity(fillRecordService.queryFillRecordRankingChart(params),org.springframework.http.HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/line" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> line(){
		return new ResponseEntity(fillRecordService.queryFillRecordTrendChart(null),org.springframework.http.HttpStatus.OK);
	}
	/**
	 * 批次回流率
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/queryGasTankBatchReturnRate" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> queryGasTankBatchReturnRate(){
		return new ResponseEntity(gasTankService.queryGasTankBatchReturnRate(null),org.springframework.http.HttpStatus.OK);
	}
	
	/**
	 * 钢瓶年龄柱形图
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/queryGasTankYearKPI" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> 
	queryGasTankYearKPI(){
		return new ResponseEntity(gasTankService.queryGasTankYearKPI(null),org.springframework.http.HttpStatus.OK);
	}


	
	@Autowired
	public void setFillRecordService(FillRecordService fillRecordService) {
		this.fillRecordService = fillRecordService;
	}

	@Autowired
	public void setGasTankServiceService(GasTankService gasTankService) {
		this.gasTankService = gasTankService;
	}
	
	

}
