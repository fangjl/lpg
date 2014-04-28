package com.hyq.lpg.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Maps;
import com.hyq.lpg.entity.GasTank;
import com.hyq.lpg.repository.mybatis.AnalysisDao;
import com.hyq.lpg.repository.mybatis.GasTankVoDao;
@Service
@Transactional
public class GasTankService {
	private GasTankVoDao gasTankVoDao;
	
	private AnalysisDao analysisDao;
	@SuppressWarnings("rawtypes")
	public void saveTenantGasTank(GasTank gastank){
		gasTankVoDao.saveTenantGasTank(gastank);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public com.hyq.lpg.common.Page<GasTank> queryTenantPageGasTank(com.hyq.lpg.common.Page page,Map params){
		params.put("page", page);
		page.setList(gasTankVoDao.queryTenantGasTank(params));
		return page;
	}
	

	
	
	
	/**
	 * 统计今日建档多少瓶
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public int  queryGasTankCount(String tenantcode,List<String> officecodes){
		if(!StringUtils.isNotBlank(tenantcode)){
			return 0;
		}
		Map params = Maps.newHashMap();
		params.put("tenantcode", tenantcode);
		return gasTankVoDao.queryGasTankCount(params);
	};
	
	
	
	////////////////////////////统计分析////////////////////////////////////////////
	
	/**
	 * 单个租户钢瓶回流率
	 * @param tenantcode
	 * @param monthNumber
	 * @param start_time
	 * @param end_time
	 * @param type
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List  queryGasTankTotalReturnRate(
			String tenantcode,
			Integer monthNumber,
			String start_time,
			String end_time,
			Integer type
			){
		
		Map<String, Object> params= Maps.newHashMap();
		params.put("tenantcode",tenantcode);
		params.put("monthNumber", monthNumber);
		if(StringUtils.isNotBlank(start_time) ){
			params.put("start_time", start_time);
			params.put("end_time",end_time);
			params.put("type", type);
		}else{
			params.put("type", 0);
		}
		
		return analysisDao.queryGasTankTotalReturnRate(params);
	};
	/**
	 * 单个租户钢瓶注册走势图
	 * @param tenantcode
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public List  queryGasTankBatchReturnRate(String tenantcode){
		if(!StringUtils.isNotBlank(tenantcode)){
			return null;
		}
		Map params = Maps.newHashMap();
		params.put("tenantcode", tenantcode);
		return analysisDao.queryGasTankBatchReturnRate( params);
	};
	/**
	 * 各个站点钢瓶占有量
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public List  queryGasTankDonutChart(String tenantCode){
		
		if(!StringUtils.isNotBlank(tenantCode)){
			return null;
		}
		Map params = Maps.newHashMap();
		params.put("tenantcode", tenantCode);
		return analysisDao.queryGasTankDonutChart(params);
	};
	
	/**
	 * 钢瓶年龄图
	 * @param params
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public List  queryGasTankYearKPI(String tenantCode){
		if(!StringUtils.isNotBlank(tenantCode)){
			return null;
		}
		Map params = Maps.newHashMap();
		params.put("tenantcode", tenantCode);
		return analysisDao.queryGasTankYearKPI(params);
	};
	
	
	
	
	@Autowired
	public void setGasTankVoDao(GasTankVoDao gasTankVoDao) {
		this.gasTankVoDao = gasTankVoDao;
	}
	@Autowired
	public void setAnalysisDao(AnalysisDao analysisDao) {
		this.analysisDao = analysisDao;
	}
	
	
}
