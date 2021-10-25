package com.multiventas.app.captcha;

import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.multiventas.app.Constants;

@Service
public class CaptchaValidator {
	 private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";


	    private String recaptchaSecret = Constants.captchaKey;

	    public boolean validateCaptcha(String captchaResponse){
	        RestTemplate restTemplate = new RestTemplate();

	        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
	        requestMap.add("secret", recaptchaSecret);
	        requestMap.add("response", captchaResponse);

	        CaptchaResponse apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, CaptchaResponse.class);
	        if(apiResponse == null){
	            return false;
	        }else {
	        	return Boolean.TRUE.equals(apiResponse.getSuccess());
	        }
	    }
}
