package com.example.demo.entity;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.stereotype.Component;

@Component
public class RSAKey {
	private RSAPublicKey publickey;
	private RSAPrivateKey privateKey;

	// khởi tạo khóa
	public RSAKey() throws NoSuchAlgorithmException {
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(2048);
		KeyPair kp = kpg.generateKeyPair();

		this.privateKey = (RSAPrivateKey) kp.getPrivate();
		this.publickey = (RSAPublicKey) kp.getPublic();

	}

	// khởi tạo với public key
	public RSAKey(String publicKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
		byte[] publicBytes = Base64.getDecoder().decode(publicKeyBase64);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey k = keyFactory.generatePublic(keySpec);
		this.publickey = (RSAPublicKey) k;
	}
// mã hóa key
	public String getPublicKeyBase64() {
		if (publickey == null)
			return "null";
		return Base64.getEncoder().encodeToString(publickey.getEncoded());
	}

	public String getPrivateKeyBase64() {
		if (privateKey == null)
			return "null";
		return Base64.getEncoder().encodeToString(privateKey.getEncoded());
	}

	// lấy ra n;
	public String getModulus() {
		if (publickey == null)
			if (privateKey == null)
				return "null";
			else
				return privateKey.getModulus().toString();
		return publickey.getModulus().toString();
	}
// lấy ra e
	public String getPublicExponent() {
		if (publickey == null)
			return "null";
		return publickey.getPublicExponent().toString();
	}
	//lấy ra d

	public String getPrivateExponent() {
		if (privateKey == null)
			return "null";
		return privateKey.getPrivateExponent().toString();
	}

	
	
	public RSAPublicKey getPublickey() {
		return publickey;
	}

	public void setPublickey(RSAPublicKey publickey) {
		this.publickey = publickey;
	}

	public RSAPrivateKey getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(RSAPrivateKey privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public String toString() {

		return "Modulus: " + getModulus() + "\n" + "Public Exponent: " + getPublicExponent() + "\n"
				+ "Private Exponent: " + getPrivateExponent();
	}
}
