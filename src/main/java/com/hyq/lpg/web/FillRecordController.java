package com.hyq.lpg.web;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
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
import com.hyq.lpg.entity.FillRecords;
import com.hyq.lpg.service.FillRecordService;
@Controller
@RequestMapping(value="/fillrecord")
public class FillRecordController {
	private FillRecordService fillRecordService;
	Logger log = LoggerFactory.getLogger(FillRecordController.class);
	@SuppressWarnings("rawtypes")
	@ModelAttribute("fillRecords")
	public FillRecords get(@RequestParam(required=false) Long id) {
		return new FillRecords(CasUtils.getCurrentPrincipal().getTenantcode());
	}
	
	@ModelAttribute
	public void init(ModelMap model) {
		model.put("principal", CasUtils.getCurrentPrincipal());
		JsonMapper jm = new JsonMapper(Include.NON_NULL);
		model.put("jsonprincipal",jm.toJson( CasUtils.getCurrentPrincipal()));
	}
	@RequestMapping(value="detail" ,method = RequestMethod.GET)
	public String detail( HttpServletRequest request, HttpServletResponse httpServletResponse){
		return "fillrecord/detail";
	}
	@RequestMapping(value="analysis" ,method = RequestMethod.GET)
	public String analysis( HttpServletRequest request, HttpServletResponse httpServletResponse){
		return "fillrecord/analysis";
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
	@RequestMapping(value="/queryFillRecordPageData",method = RequestMethod.GET)
	public org.springframework.http.ResponseEntity<Map> queryFillRecordPageData(
			FillRecords fillRecords,
			@RequestParam(value = "iDisplayStart",  defaultValue  = "0"       ) int start,
			@RequestParam(value = "iDisplayLength", defaultValue  = "4"       ) int pageSize,
			@RequestParam(value = "sEcho",          defaultValue  = "1"       ) String sEcho,
			@RequestParam(value = "sortType",       defaultValue  = "auto"    ) String sortType,
			@RequestParam(value = "czsj" ,          defaultValue  = ""        ) String czsj,
			Model model,
			HttpServletRequest request
			){
		com.hyq.lpg.common.Page p = new com.hyq.lpg.common.Page(request);
		p.setPageNo(start/pageSize+1);
		p.setPageSize(pageSize);
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		searchParams.put("tenantcode", CasUtils.getCurrentPrincipal().getTenantcode());
			if(!StringUtils.isBlank(czsj)){
				String[] czsjs = czsj.split("~");
				searchParams.put("GTE_czkssj", czsjs[0]);
				searchParams.put("LTE_czkssj", czsjs[1]);
				}
		
		com.hyq.lpg.common.Page<FillRecords> page = fillRecordService.queryFillRecordByMybatisPage(p,searchParams);
		Map m = Maps.newHashMap();
		m.put("iTotalRecords", page.getCount());
		m.put("iTotalDisplayRecords", page.getCount());
		m.put("sEcho", sEcho);
		m.put("aaData", page.getList());
		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return new ResponseEntity(m,org.springframework.http.HttpStatus.OK);
	}
	
	
	/**
	 * 单租户充装走势
	 * @param officecode
	 * @param year
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/queryFillRecordTrendChart" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> queryFillRecordTrendChart(
			@RequestParam(value = "officecode") String officecode,
			@RequestParam(value = "year",defaultValue = "") String year,
			Model model,
			ServletRequest request){
		Map params = Maps.newHashMap();
		params.put("tenantcode", CasUtils.getCurrentPrincipal().getTenantcode());
		params.put("officecode", officecode);
		params.put("year", year);
		return new ResponseEntity(fillRecordService.queryFillRecordTrendChart(params),org.springframework.http.HttpStatus.OK);
	}
	
	
	/**
	 * 各站点充装量占有比重
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@RequestMapping(value="/queryFillRecordDonutChart" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> queryFillRecordDonutChart(
			Model model,
			ServletRequest request){
		Map params = Maps.newHashMap();
		Map<String,String> Offices = CasUtils.getCurrentPrincipal().getOffices();
		
		
		;
		List<Map> ls =fillRecordService.queryFillRecordDonutChart(CasUtils.getCurrentPrincipal().getTenantcode(),null);
		for(Map m : ls){
			String key = (String)m.get("label");
			if(StringUtils.isNotBlank(Offices.get(key))){
				m.remove("label");
				m.put("label", Offices.get(key));
			}
		}
		return new ResponseEntity(ls,org.springframework.http.HttpStatus.OK);
	}
	
	
	/**
	 * 各站点充装工位排行分析
	 * @param officecode
	 * @param type
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/queryFillRecordRankingChart" ,method = RequestMethod.GET)
	@ResponseBody
	public org.springframework.http.ResponseEntity<Object> queryFillRecordRankingChart(
			@RequestParam(value = "officecode") String officecode,
			@RequestParam(value = "type", defaultValue = "1") String type,
			Model model,
			ServletRequest request){
		Map params = Maps.newHashMap();
		params.put("tenantcode", CasUtils.getCurrentPrincipal().getTenantcode());
		params.put("officecode",officecode);
		params.put("type",type);    //1 ： 当天  2：本月;
		return new ResponseEntity(fillRecordService.queryFillRecordRankingChart(params),org.springframework.http.HttpStatus.OK);
	}
	
	
	
	
	@Autowired
	public void setFillRecordService(FillRecordService fillRecordService) {
		this.fillRecordService = fillRecordService;
	}
	
	
}
