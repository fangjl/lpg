package com.hyq.lpg.repository.mybatis;
import java.util.List;
import java.util.Map;

import com.hyq.lpg.annotations.MyBatisRepository;
import com.hyq.lpg.entity.FillRecords;
@MyBatisRepository
public interface MyBatisFillRecordVoDao {

	@SuppressWarnings("rawtypes")
	public List<Map> queryFillRecordTrendChart(Map params);
	@SuppressWarnings("rawtypes")
	public List<Map> queryFillRecordDonutChart(Map params);
	@SuppressWarnings("rawtypes")
	public List<Map> queryFillRecordRankingChart(Map params);
	
	/**
	 * 统计当天充装总量
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int queryFillRecordCount(Map params);
	
	/**
	 * 各个加气站今日充装总量排行
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryFillRecordByOfficeCount(Map params);
	
	
	
	@SuppressWarnings("rawtypes")
	public void saveTenantOriginalFillRecords(FillRecords fillRecords);
	@SuppressWarnings("rawtypes")
	public void saveTenantFillRecords(FillRecords fillRecords);
	
	
	@SuppressWarnings("rawtypes")
	public List<FillRecords> queryTenantFillRecords(Map params);
	
	
	/**
	 * 修改今天充装过的瓶的充装时间  ，防止一个瓶一天充装两次
	 * @param params
	 */
	@SuppressWarnings("rawtypes")
	public void updateCZSJ(Map params);
	
	/**
	 * 验证该瓶今天是否充装过，如果充装过  返回 >0
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int checkDayAdd(Map params);
	
	
	/**
	 * 修改钢瓶的充装次数和充装时间
	 * @param params
	 */
	@SuppressWarnings("rawtypes")
	public void updateGasTank(Map params);
	
}
