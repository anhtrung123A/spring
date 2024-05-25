package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.ChuSoHuu;

public interface ChuSoHuuRepository extends JpaRepository<ChuSoHuu, Integer> {

	@Query("SELECT c FROM ChuSoHuu c WHERE c.publicKey = :publicKey")
    ChuSoHuu findByPublicKey(String publicKey);

}
