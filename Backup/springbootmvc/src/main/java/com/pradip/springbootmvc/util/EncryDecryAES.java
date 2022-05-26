package com.pradip.springbootmvc.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.spec.KeySpec;
import java.util.Base64;
public class EncryDecryAES {

	private static final String SECRET_KEY = "my_super_secret_key";
	private static final String SALT = "ssshhhhhhhhhhh!!!!";
	
	public static String encDecryAESAlgo(String strForConvert, String mode) {
		if(strForConvert != null) {
			try {
				byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
				IvParameterSpec ivspec = new IvParameterSpec(iv);

				SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
				KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
				SecretKey tmp = factory.generateSecret(spec);
				SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
				if(mode.equals("encrypt")) {
					cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
					return Base64.getEncoder().encodeToString(cipher.doFinal(strForConvert.getBytes(StandardCharsets.UTF_8)));
				}
				else {
					cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
					return new String(cipher.doFinal(Base64.getDecoder().decode(strForConvert)));
				}
				
			} 
			catch (Exception e) {
				System.out.println("Error while decrypting: " + e.toString());
			}
		}
		else {
			return null;
		}
		return null;
	}
	


}