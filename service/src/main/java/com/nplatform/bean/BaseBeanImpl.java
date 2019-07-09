package com.nplatform.bean;

import javax.persistence.Transient;

/**
 * 
 * @author lmq
 *
 */
public abstract class BaseBeanImpl implements BaseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7805110867344388840L;

	@Transient
	public String getPrefix() {
		// TODO Auto-generated method stub
		return null;
	}

}
