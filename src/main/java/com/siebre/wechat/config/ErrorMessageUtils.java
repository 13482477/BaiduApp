package com.siebre.wechat.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author lizhiqiang
 *
 */
public class ErrorMessageUtils {
	
	public static String resloveErrorMessage(BindingResult bindingResult) {
		String errorMessage = "";
		
		if (bindingResult.hasErrors()) {
			List<ObjectError> objectErrors = bindingResult.getAllErrors();
			
			Set<String> errorMessages = new HashSet<String>(); 
			for (ObjectError objectError : objectErrors) {
				errorMessages.add(objectError.getDefaultMessage());
			}
			return StringUtils.join(errorMessages, "\n");
		}
			
		return errorMessage;
	}

}
