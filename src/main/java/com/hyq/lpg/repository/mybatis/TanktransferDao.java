package com.hyq.lpg.repository.mybatis;
import com.hyq.lpg.annotations.MyBatisRepository;
import com.hyq.lpg.entity.Tanktransfer;
@MyBatisRepository
public interface TanktransferDao {
	public  void add(Tanktransfer t);
}
