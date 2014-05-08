package com.hyq.lpg.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.hyq.lpg.entity.Tanktransfer;
import com.hyq.lpg.repository.mybatis.TanktransferDao;

@Service
@Transactional
public class TanktransferService {
	
	private TanktransferDao tanktransferDao;

	public void add(Tanktransfer tanktransfer){
		tanktransferDao.add(tanktransfer);
	}
	
	
	
	
	
	@Autowired
	public void setTanktransferDao(TanktransferDao tanktransferDao) {
		this.tanktransferDao = tanktransferDao;
	}

	
	
	
}
