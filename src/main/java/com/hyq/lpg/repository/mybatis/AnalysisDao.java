package com.hyq.lpg.repository.mybatis;
import java.util.List;
import java.util.Map;

import com.hyq.lpg.annotations.MyBatisRepository;
@MyBatisRepository
public interface AnalysisDao {

	@SuppressWarnings("rawtypes")
	public List<Map> queryGasTankTotalReturnRate(Map params);
	
	
	/**
	 * 年龄注销图     必须  tenantcode
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryGasTankYearKPI(Map params);
	
	/**
	 * 各个站点钢瓶占有量
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List  queryGasTankDonutChart(Map params);
	
	/**
	 * 单个租户钢瓶注册趋势
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List  queryGasTankBatchReturnRate(Map params);

	
}
