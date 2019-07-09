package com.nplatform.validate;

import java.lang.annotation.*;

/**
 * 验证注释
 * @author lmq
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Validate {

	/**
	 * 是否为空
	 * @return 返回true或false，默认返回true
	 */
	public boolean nullable() default true;
	
	/**
	 * 数据长度(只能验证字符数据类型的数据)
	 * @return 返回设置结果，默认返回“”
	 */
	public String length() default "";
	
	/**
	 * 数据格式类型
	 * @return 返回设置结果，默认返回“”
	 */
	public String dataFormatType() default "";
	
	/**
	 * 正则表达式
	 * @return 返回设置结果，默认返回“”
	 */
	public String regexExpr() default "";
	
	/**
	 * 自定义验证类
	 * @return 返回设置结果，默认返回“”
	 */
	public String className() default "";
	
	/**
	 * 值范围
	 * @return 返回设置结果，默认返回“”
	 */
	public String valueArea() default "";
	
}
