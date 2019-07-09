package com.nplatform.helper;

//import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author lmq
 *
 */
public class ObjectHelper {
	
	//protected static final Logger log = Logger.getLogger(ObjectHelper.class);

	/**
	 * 处理时间对象
	 * @param lists
	 * @return List<Object>
	 */
	public static List<Object> handleObjDate(List<Object> lists) {
		if(null != lists && lists.size()>0) {
			for (Object obj : lists) {
				if (obj instanceof Object[]) {
					Object[] objs = (Object[])obj;
					int count=0;
					for (Object obj2 : objs) {
						if(obj2 instanceof Date) {
							//log.info("时间格式:"+obj2.toString());
							if(null != obj2 && isNotEmpty(obj2.toString())) {
								objs[count] = new String(obj2.toString().substring(0,obj2.toString().length()-2));
							}
						}
						count++;
					}
					//log.info("数组对象");
				} else {
				//	log.info("非数组对象");
				}
			}
		}
		return lists;
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static boolean isEmpty(String value) {
		return (null==value || value.trim().length()==0);
	}
}
