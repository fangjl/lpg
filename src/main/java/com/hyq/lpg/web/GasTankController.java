package com.hyq.lpg.web;

import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.mapper.JsonMapper;
import org.springside.modules.web.Servlets;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.google.common.collect.Maps;
import com.hyq.lpg.cas.CasUtils;
import com.hyq.lpg.entity.GasTank;
import com.hyq.lpg.service.GasTankService;
@Controller
@RequestMapping(value="/gastank")
public class GasTankController {
 
	private GasTankService gasTankService;
	
	
	@ModelAttribute
	public void init(ModelMap model) {
		model.put("principal", CasUtils.getCurrentPrincipal());
		JsonMapper jm = new JsonMapper(Include.NON_NULL);
		
		model.put("jsonprincipal",jm.toJson( CasUtils.getCurrentPrincipal()));
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@ModelAttribute("gastank")
	public GasTank get(@RequestParam(required=false) Long id) {
		return new GasTank(CasUtils.getCurrentPrincipal().getTenantcode());
	}
	
	@RequestMapping(value="refluxRatio" ,method = RequestMethod.GET)
	public String refluxRatio( HttpServletRequest request, HttpServletResponse httpServletResponse){
		return "gastank/refluxRatio";
	}
	@RequestMapping(value="analysis" ,method = RequestMethod.GET)
	public String analysis( HttpServletRequest request, HttpServletResponse httpServletResponse){
		return "gastank/analysis";
	}
	@RequestMapping(value="detail" ,method = RequestMethod.GET)
	public String detail( HttpServletRequest request, HttpServletResponse httpServletResponse){
		return "gastank/detail";
	}
	
	
	/** binder用于bean属性的设置 */
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	     //   binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM"), true));  
	}

	
	
	
	/**
	 * mybatis 分页查询
	 * @param start
	 * @param pageSize
	 * @param sEcho
	 * @param sortType
	 * @param czsj
	 * @param model
	 * @param request
	 * @return
	 */ 
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@ResponseBody
	@RequestMapping(value="/queryGasTankPageData",method = RequestMethod.GET)
	public org.springframework.http.ResponseEntity<Object> queryGasTankPageData(
			@RequestParam(value = "iDisplayStart" ,          defaultValue = "0")    int start,
			@RequestParam(value = "iDisplayLength",          defaultValue = "20")   int pageSize,
			@RequestParam(value = "sEcho"         ,          defaultValue = "1")    String sEcho,
			@RequestParam(value = "sortType"      ,          defaultValue = "auto") String sortType,
			@RequestParam(value = "czsj"          ,          defaultValue = "")     String czsj,
			@RequestParam(value = "scrq"          ,          defaultValue = "")    String scrq,
			@RequestParam(value = "jdsj"          ,          defaultValue = "")     String jdsj,
			Model model,
			HttpServletRequest request
			){
		
		com.hyq.lpg.common.Page p = new com.hyq.lpg.common.Page(request);
		p.setPageNo(start/pageSize+1);
		p.setPageSize(pageSize);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		searchParams.put("tenantcode", CasUtils.getCurrentPrincipal().getTenantcode());

		
	
			if(!StringUtils.isBlank(scrq)){
				String[] scrqs = scrq.split("~");
				searchParams.put("GTE_scrq",scrqs[0]);
				searchParams.put("LTE_scrq",scrqs[1] );
			}
			if(!StringUtils.isBlank(czsj)){
				String[] czsjs = czsj.split("~");
				searchParams.put("GTE_czsj", czsjs[0] );
				searchParams.put("LTE_czsj", czsjs[1] );
				}
			if(!StringUtils.isBlank(jdsj)){
				String[] jdsjs = jdsj.split("~");
				searchParams.put("GTE_jdsj", jdsjs[0] );
				searchParams.put("LTE_jdsj", jdsjs[1] );
				}
		
		com.hyq.lpg.common.Page<GasTank> page = gasTankService.queryTenantPageGasTank(p,searchParams);
		Map m = Maps.newHashMap();
		m.put("iTotalRecords", page.getCount());
		m.put("iTotalDisplayRecords", page.getCount());
		m.put("sEcho", sEcho);
		m.put("aaData", page.getList());
		return new ResponseEntity(m,org.springframework.http.HttpStatus.OK);
	}
	
	
	
	
	
	
	
	/**
	 * 总回流率
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/queryGasTankTotalReturnRate" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> 
	queryGasTankTotalReturnRate(
			@RequestParam(value = "ctime", defaultValue = "") String ctime,
			@RequestParam(value = "type", defaultValue = "0") Integer type,
			@RequestParam(value = "monthNumber", defaultValue = "6" ) Integer monthNumber,
			Model model,
			ServletRequest request){
		String start_time="";
		String end_time ="";
		if(!StringUtils.isBlank(ctime)){
			start_time = ctime.split("~")[0];
			end_time   = ctime.split("~")[1];
		}
		return new ResponseEntity(gasTankService.queryGasTankTotalReturnRate(
				CasUtils.getCurrentPrincipal().getTenantcode(),
				monthNumber,
				start_time,
				end_time,
				 type
				),org.springframework.http.HttpStatus.OK);

	}
	
	/**
	 * 批次回流率
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/queryGasTankBatchReturnRate" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> queryGasTankBatchReturnRate(){
		return new ResponseEntity(gasTankService.queryGasTankBatchReturnRate( CasUtils.getCurrentPrincipal().getTenantcode()),org.springframework.http.HttpStatus.OK);

	}
	
	
	
	/**
	 * 钢瓶年龄柱形图
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/queryGasTankYearKPI" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> 
	queryGasTankYearKPI(){
		return new ResponseEntity(gasTankService.queryGasTankYearKPI(CasUtils.getCurrentPrincipal().getTenantcode()),org.springframework.http.HttpStatus.OK);
	}
	
	/**
	 * 统计各个站点的钢瓶占有量
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="/queryGasTankDonutChart" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> 
	queryGasTankDonutChart(){
		Map<String,String> Offices = CasUtils.getCurrentPrincipal().getOffices();
		List<Map> ls = gasTankService.queryGasTankDonutChart(CasUtils.getCurrentPrincipal().getTenantcode());
		for(Map m : ls){
			String key = (String)m.get("label");
			if(StringUtils.isNotBlank(Offices.get(key))){
				m.remove("label");
				m.put("label", Offices.get(key));
			}
		}
		return new ResponseEntity(ls,org.springframework.http.HttpStatus.OK);
	}
	@Autowired
	public void setGasTankService(GasTankService gasTankService) {
		this.gasTankService = gasTankService;
	}
}
