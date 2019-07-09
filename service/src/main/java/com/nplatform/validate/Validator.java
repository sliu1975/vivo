package com.nplatform.validate;

/**
 * 验证器
 * @author lmq
 *
 */
public interface Validator {

	public boolean validate() throws ValidateException;
	
}
