package com.nplatform.filter.bean;

import com.mixsmart.utils.StringUtils;
import com.nplatform.filter.base.FilterParam;

/**
 * 流程实例搜索bean
 * @author lmq
 * @create 2015年7月15日
 * @version 1.0 
 * @since 
 *
 */
public class CommonSearchParam extends FilterParam {

	private String startDate;
	
	private String endDate;

	private String username;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getParamToString() {
		StringBuilder paramBuff = new StringBuilder();
		String param = super.getParamToString();
		if(StringUtils.isNotEmpty(startDate)) {
			paramBuff.append("startDate="+startDate+"&");
		}
		if(StringUtils.isNotEmpty(endDate)) {
			paramBuff.append("endDate="+endDate+"&");
		}
		if(StringUtils.isNotEmpty(paramBuff.toString())) {
			param = StringUtils.isEmpty(param)?paramBuff.toString():"&"+paramBuff.toString();
			param = param.substring(0, param.length()-1);
		}
		return param;
	}
	
}
