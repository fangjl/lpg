package com.hyq.lpg.repository.mybatis;
import java.util.List;
import java.util.Map;
import com.hyq.lpg.annotations.MyBatisRepository;
import com.hyq.lpg.entity.GasTank;
@MyBatisRepository
public interface GasTankVoDao {
	
	
	 
	
	
	
	/**
	 * 统计今日建档多少瓶
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int  queryGasTankCount(Map params);
	
	
	
	@SuppressWarnings("rawtypes")
	public void saveTenantGasTank(GasTank gastank);
	
	@SuppressWarnings("rawtypes")
	public List<GasTank> queryTenantGasTank(Map seachParams);
	
}
