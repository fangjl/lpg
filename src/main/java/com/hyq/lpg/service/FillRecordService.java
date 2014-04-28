package com.hyq.lpg.service;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Maps;
import com.hyq.lpg.entity.FillRecords;
import com.hyq.lpg.entity.OfficeRecordVo;
import com.hyq.lpg.repository.mybatis.GasTankVoDao;
import com.hyq.lpg.repository.mybatis.MyBatisFillRecordVoDao;
@Service
@Transactional
public class FillRecordService {
	private MyBatisFillRecordVoDao myBatisFillRecordVoDao;
	@SuppressWarnings("unused")
	private GasTankVoDao gasTankVoDao;
	
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional(readOnly = true)
	public com.hyq.lpg.common.Page<FillRecords> queryFillRecordByMybatisPage(com.hyq.lpg.common.Page page,Map params){
		
		params.put("page", page);
		page.setList(myBatisFillRecordVoDao.queryTenantFillRecords(params));
		return page;
		
	}
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List<Map> queryFillRecordTrendChart(Map params){
		return myBatisFillRecordVoDao.queryFillRecordTrendChart(params);
	}
	@SuppressWarnings("rawtypes")
	@Transactional(readOnly = true)
	public List<Map> queryFillRecordRankingChart(Map params){
		return myBatisFillRecordVoDao.queryFillRecordRankingChart(params);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public List<Map> queryFillRecordDonutChart(String tenantcode,List<String> officecodes){
		if(!StringUtils.isNotBlank(tenantcode)){
			return null;
		}
		Map params = Maps.newHashMap();
		params.put("tenantcode", tenantcode);
		return myBatisFillRecordVoDao.queryFillRecordDonutChart(params);
	}
	
	
	
	
	/**
	 * 保存充装记录原始
	 * @param fillRecords
	 */
	private void saveTenantOriginalFillRecords(FillRecords fillRecords){
		myBatisFillRecordVoDao.saveTenantOriginalFillRecords(fillRecords);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void saveTenantFillRecords(FillRecords fillRecords){
		
		//1 :现在今天该钢瓶是否充装装过  , 
		//2 :如果充装过 修改充装时间 ，  修改钢瓶档案充装时间
		//3 :如果没有   插入充装记录，修改钢瓶档案充装时间 和充装次数+1
		//
		
		
		
		
		
		
		saveTenantOriginalFillRecords(fillRecords);
		
		
		
		if(!StringUtils.isNotBlank(fillRecords.getGpbm())){
		return ;
		}
		if(!StringUtils.isNotBlank(fillRecords.getRfidcode())){
			return ;
		}
		
		Map params = Maps.newHashMap();
		params.put("tenantcode",fillRecords.getTenantcode());
		params.put("czkssj",fillRecords.getCzkssj());
		params.put("gpbm",fillRecords.getGpbm());
		int c = myBatisFillRecordVoDao.checkDayAdd(params);
		if(c>0){  //今天充装过
			myBatisFillRecordVoDao.updateCZSJ(params);  //修改 今天充装过的瓶的充装时间
		}else{
			myBatisFillRecordVoDao.saveTenantFillRecords(fillRecords);
			myBatisFillRecordVoDao.updateGasTank(params);   //修改 钢瓶的充装次数 和充装时间
		}
		
		
		
		
	}
	
	
	/**
	 * 今日充装统计
	 * @param tenantcode
	 * @param officecodes
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public int queryFillRecordCount(String tenantcode,List<String> officecodes){
		
		if(!StringUtils.isNotBlank(tenantcode)){
			return 0;
		}
		Map params = Maps.newHashMap();
		params.put("tenantcode", tenantcode);
		return myBatisFillRecordVoDao.queryFillRecordCount(params);
	}
	
	
	/**
	 * 统计各个站点的今日见到多少瓶总量
	 * @param tenantcode
	 * @param officecodes
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Transactional(readOnly = true)
	public List<OfficeRecordVo> queryFillRecordByOfficeCount(String tenantcode,List<String> officecodes){
		if(!StringUtils.isNotBlank(tenantcode)){
			return null;
		}
		Map params = Maps.newHashMap();
		params.put("tenantcode", tenantcode);
		return myBatisFillRecordVoDao.queryFillRecordByOfficeCount(params);
	}
	
	

	@Autowired
	public void setMyBatisFillRecordVoDao(
			MyBatisFillRecordVoDao myBatisFillRecordVoDao) {
		this.myBatisFillRecordVoDao = myBatisFillRecordVoDao;
	}

	@Autowired
	public void setGasTankVoDao(
			GasTankVoDao gasTankVoDao) {
		this.gasTankVoDao = gasTankVoDao;
	}
	
	
	
}
