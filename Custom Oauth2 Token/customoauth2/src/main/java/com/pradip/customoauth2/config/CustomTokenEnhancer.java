package com.pradip.customoauth2.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
	 @Override
	    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
	        Map<String, Object> additionalInfo = new HashMap<>();
	        additionalInfo.put("Additional1", "This is addtional information 1");
	        additionalInfo.put("Additional2", "This is addtional information 2");
	        additionalInfo.put("Additional3", "This is addtional information 3");
	        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
	        return accessToken;
	    }
}
