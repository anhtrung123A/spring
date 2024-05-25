package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ChuSoHuuRepository;
import com.example.demo.entity.ChuSoHuu;

import jakarta.transaction.Transactional;

@Service
public class ChuSoHuuServiceImpl implements ChuSoHuuService {
	
	
	private ChuSoHuuRepository chuSoHuuRepository;
	
	@Autowired
	public ChuSoHuuServiceImpl(ChuSoHuuRepository chuSoHuuRepository) {
		this.chuSoHuuRepository = chuSoHuuRepository;
	}
	
	@Override
	public ChuSoHuu findByPublicKey(String publicKey){
		
		ChuSoHuu theChuSoHuu = chuSoHuuRepository.findByPublicKey(publicKey);
        return theChuSoHuu;
		
	}

	@Override
	@Transactional 
	public ChuSoHuu save(ChuSoHuu chuSoHuu) {
		return chuSoHuuRepository.save(chuSoHuu);
	}

	
}
