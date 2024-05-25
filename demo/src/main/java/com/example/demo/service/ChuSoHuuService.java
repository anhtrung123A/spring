package com.example.demo.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ChuSoHuu;

public interface ChuSoHuuService {
	public ChuSoHuu findByPublicKey(String publicKey);
	public ChuSoHuu save(ChuSoHuu chuSoHuu);
}
