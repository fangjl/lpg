package com.hyq.lpg.repository.mybatis;
import com.hyq.lpg.annotations.MyBatisRepository;
@MyBatisRepository
public interface AutoSchemaDao {

	/**
	 * 是否存在租户的充装记录表
	 * @param tenantcode
	 * @return
	 */
	public int exsitTenantFillRecordTable(String tenantcode);
	
	
	/**
	 * 创建租户的充装记录表
	 * @param tableNamePre
	 * @return
	 */
	public void createTenantFillRecordTable(int tableNamePre);
}
