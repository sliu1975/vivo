package com.nplatform.filter.extra;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.EnumUtils;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;

public class DynamicSpecifications {
	private static final Logger logger = Logger.getLogger(DynamicSpecifications.class);
	
	// 用于存储每个线程的request请求
//	private static final ThreadLocal<HttpServletRequest> LOCAL_REQUEST = new ThreadLocal<HttpServletRequest>();
	
	private static final String SHORT_DATE = "yyyy-MM-dd";
	private static final String LONG_DATE = "yyyy-MM-dd mm:HH:ss";
	private static final String TIME = "mm:HH:ss";
	
//	public static void putRequest(HttpServletRequest request) {
//		LOCAL_REQUEST.set(request);
//	}
//	
//	public static HttpServletRequest getRequest() {
//		return LOCAL_REQUEST.get();
//	}
//	
//	public static void removeRequest() {
//		LOCAL_REQUEST.remove();
//	}
	
	public static Collection<SearchFilter> genSearchFilter(Map<String, Object> searchParams) {
		Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
		return filters.values();
	}
	
	public static <T> Specification<T> bySearchFilter(Map<String, Object> searchParams, final Class<T> entityClazz, final Collection<SearchFilter> searchFilters) {
		return bySearchFilter(searchParams, entityClazz, searchFilters.toArray(new SearchFilter[]{}));
	}
	
	public static <T> Specification<T> bySearchFilter(Map<String, Object> searchParams, final Class<T> entityClazz, final SearchFilter...searchFilters) {
		Collection<SearchFilter> filters = genSearchFilter(searchParams);
		Set<SearchFilter> set = new HashSet<SearchFilter>(filters);
		for (SearchFilter searchFilter : searchFilters) {
			set.add(searchFilter);
		}
		return bySearchFilter(entityClazz, set);
	}

	@SuppressWarnings("unchecked")
	public static <T> Specification<T> bySearchFilter(final Class<T> entityClazz, final Collection<SearchFilter> searchFilters) {
		final Set<SearchFilter> filterSet = new HashSet<SearchFilter>();
//		ServletRequest request = getRequest();
//		if (request != null) {
			// 数据权限中的filter
//			Collection<SearchFilter> nestFilters = 
//					(Collection<SearchFilter>)request.getAttribute(SecurityConstants.NEST_DYNAMIC_SEARCH);
//			if (nestFilters != null && !nestFilters.isEmpty()) {
//				for (SearchFilter searchFilter : nestFilters) {
//					filterSet.add(searchFilter);
//				}
//			}
//		}
		
		// 自定义
		for (SearchFilter searchFilter : searchFilters) {
			filterSet.add(searchFilter);
		}
		
		return new Specification<T>() {
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return toPredicate(root, query, builder, filterSet, false);
			}
			
			@SuppressWarnings("rawtypes")
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder, Set<SearchFilter> filterSet, boolean isOr) {
				if (filterSet != null && !filterSet.isEmpty()) {
					List<Predicate> predicates = new ArrayList<Predicate>();
					for (SearchFilter filter : filterSet) {
						Path expression = null;
						if(filter.getOperator() != SearchFilter.Operator.OR ) {
							// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
							String[] names = filter.getFieldName().split("\\.");
							expression = root.get(names[0]);
							for (int i = 1; i < names.length; i++) {
								expression = expression.get(names[i]);
							}
							
							// 自动进行enum和date的转换。
							Class clazz = expression.getJavaType();
							if (Date.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
								filter.setValue(convert2Date((String)filter.getValue()));
							} else if (Enum.class.isAssignableFrom(clazz) && !filter.getValue().getClass().equals(clazz)) {
								filter.setValue(convert2Enum(clazz, (String)filter.getValue()));
							}
						}
						
						// logic operator
						switch (filter.getOperator()) {
						case EQ:
							predicates.add(builder.equal(expression, filter.getValue()));
							break;
						case NEQ:
							predicates.add(builder.notEqual(expression, filter.getValue()));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.getValue() + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.getValue()));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.getValue()));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.getValue()));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.getValue()));
							break;
						case IN:
							predicates.add(builder.and(expression.in((Object[])filter.getValue())));
							break;
						case OR:
							Set<SearchFilter> eachSet = new HashSet<SearchFilter>();
							for (SearchFilter searchFilter : (List<SearchFilter>) filter.getValue()) {
								eachSet.add(searchFilter);
							}
							predicates.add(builder.and(toPredicate(root, query, builder, eachSet, true)));
							break;
						}
					}

					// 将所有条件用 and 联合起来
					if (predicates.size() > 0) {
						if(isOr) {
							return builder.or(predicates.toArray(new Predicate[predicates.size()]));
						} else {
							return builder.and(predicates.toArray(new Predicate[predicates.size()]));
						}
					}
				}

				return builder.conjunction();
			}
		};
	}
	
	private static Date convert2Date(String dateString) {
		SimpleDateFormat sFormat = new SimpleDateFormat(SHORT_DATE);
		try {
			return sFormat.parse(dateString);
		} catch (ParseException e) {
			try {
				return sFormat.parse(LONG_DATE);
			} catch (ParseException e1) {
				try {
					return sFormat.parse(TIME);
				} catch (ParseException e2) {
					logger.error("Convert time is error! The dateString is " + dateString, e1);
				}
			}
		}

		return null;
	}
		
	
	private static <E extends Enum<E>> E convert2Enum(Class<E> enumClass, String enumString) {
		return EnumUtils.getEnum(enumClass, enumString);
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString("test".split("\\.")));
	}
}
