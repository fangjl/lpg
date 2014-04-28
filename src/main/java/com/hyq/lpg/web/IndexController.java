package com.hyq.lpg.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyq.lpg.cas.CasUtils;
import com.hyq.lpg.entity.OfficeRecordVo;
import com.hyq.lpg.service.FillRecordService;
import com.hyq.lpg.service.GasTankService;


@Controller
public class IndexController {

	
	private FillRecordService fillRecordService;
	private GasTankService gasTankService;

	
	@SuppressWarnings("rawtypes")
	@ModelAttribute
	public void init(ModelMap model) {

		
		model.put("principal",CasUtils.getCurrentPrincipal());
		model.put("today_count_fill_record", fillRecordService.queryFillRecordCount( CasUtils.getCurrentPrincipal().getTenantcode(), null));

		
		List<OfficeRecordVo> vos = fillRecordService.queryFillRecordByOfficeCount( CasUtils.getCurrentPrincipal().getTenantcode(), null);
		Map m = CasUtils.getCurrentPrincipal().getOffices();
		
		for(OfficeRecordVo vo:vos){
			
			if(!m.containsKey(vo.getOfficecode())){
				vos.remove(vo);
				continue;
			}
			vo.setOfficename((String)m.get(vo.getOfficecode()));
		}
		model.put("today_count_fill_record_offices", vos);
		
		model.put("today_count_gas_tank", gasTankService.queryGasTankCount( CasUtils.getCurrentPrincipal().getTenantcode(), null));
	}
	
	@RequestMapping(value="/index" ,method = RequestMethod.GET)
	public String index(){
		return "index";
	}

	@RequestMapping(value="/dashboard" ,method = RequestMethod.GET)
	public String dashboard(){
		return "dashboard";
	}
	
	public String content(@PathVariable java.lang.String path){
		return path;
	}

	
	@Autowired
	public void setFillRecordService(FillRecordService fillRecordService) {
		this.fillRecordService = fillRecordService;
	}
	@Autowired
	public void setGasTankService(GasTankService gasTankService) {
		this.gasTankService = gasTankService;
	}
		
		
		
  
		
}
