package com.nplatform.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.Claim;
import com.mixsmart.utils.ArrayUtils;
import com.mixsmart.utils.CollectionUtils;
import com.mixsmart.utils.StringUtils;
import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.redis.RedisCacheConfig;
import com.nplatform.redis.RedisUtils;
import com.nplatform.utils.JwtToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ACLInterceptor implements HandlerInterceptor {

    @Resource
    private RedisCacheConfig redisCacheConfig;

    private List<String> excludeMaps;
    private List<String> authUriList;
    private String resSuffix;
    private List<String> excludeAccessLogUrls;

    private final static Logger log = LoggerFactory.getLogger(ACLInterceptor.class);

    public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object obj, Exception arg3) throws Exception {
        String currentUri = HttpRequestHelper.getCurrentUri(request);
        long startTime = (Long)request.getAttribute("startTime");
        Date responseTime = new Date();
        long endTime = responseTime.getTime();
        long useTime = endTime - startTime;
        log.info("currentUri = {}, useTime = {}", currentUri, useTime);
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response,Object obj, ModelAndView modelAndView) throws Exception {
        String currentUri = HttpRequestHelper.getCurrentUri(request);
        if(isRes(currentUri) || null == modelAndView) {
            return;
        }
    }

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object obj) throws Exception {
        String currentUri = HttpRequestHelper.getCurrentUri(request);
        boolean isRes = isRes(currentUri);
        boolean is = false;

        Date currentTime = new Date();
        long startTime = currentTime.getTime();
        request.setAttribute("startTime", startTime);

        if(isLogin(request)) {
            is = true;

        } else {
            if(!isRes(currentUri)) {
                if(!isExclude(currentUri)) {
                    response.setHeader("Content-Type", "application/json");
                    response.getWriter().write(JSON.toJSONString(ApiResult.error(ErrorCode.NOT_LOGIN)));

                    is = false;
                } else {
                    is = true;
                }
            } else {
                is = true;
            }
        }

        return is;
    }

    /**
     *  处理调整URI
     * @param request
     * @param uri
     * @return
     */
    private String handleRedirectUri(HttpServletRequest request, String uri) {
        String contextPath = request.getContextPath();
        if(StringUtils.isNotEmpty(contextPath) && !"/".equals(contextPath)) {
            uri = contextPath+uri;
        }
        return uri;
    }

    /**
     * 检测请求的资源样式,js,图片等文件(css,js,img)
     * @param currentUri
     * @return
     */
    private boolean isRes(String currentUri) {
        boolean isRes = false;
        if(StringUtils.isNotEmpty(resSuffix)) {
            String suffix = StringUtils.getFileSuffix(currentUri);
            if(StringUtils.isNotEmpty(suffix)) {
                if(ArrayUtils.isArrayContainsIgnoreCase(resSuffix, suffix, ",")) {
                    isRes = true;
                }
            }
        }
        return isRes;
    }

    /**
     * 是否排除
     * @param currentUri
     * @return
     */
    private boolean isExclude(String currentUri) {
        boolean is = false;
        is = currentUri.startsWith("#");
        is = is ? is : isUriContains(excludeMaps, currentUri);
        return is;
    }

    /**
     * 判断URI列表中是否包含URI；
     * 注：当列表为空或 <code>uri</code> 参数值为空时，返回：true
     * @param uriList
     * @param uri
     * @return 如果包含返回：true；否则返回false
     */
    private boolean isUriContains(List<String> uriList, String uri) {
        boolean is = false;
        if(CollectionUtils.isEmpty(uriList) || StringUtils.isEmpty(uri)) {
            return true;
        }
        for (String uriTmp : uriList) {
            if(StringUtils.isNotEmpty(uriTmp) && uriTmp.indexOf("*")>-1) {
                uriTmp = uriTmp.replace("*", "");
                if(uri.startsWith(uriTmp) || uri.endsWith(uriTmp) || uri.contains(uriTmp)) {
                    is = true;
                    break;
                }
            } else {
                if(uri.equals(uriTmp)) {
                    is = true;
                    break;
                }
            }
        }
        return is;
    }

    /**
     * 判断用户是否登录
     * @param request
     * @return
     */
    private boolean isLogin(HttpServletRequest request) {
        //String token = request.getHeader("token");
        String token = HttpRequestHelper.getToken(request);

        if(token!=null)
            System.out.println("Got token is "+token);

        Map<String, Claim> map = JwtToken.verifyToken(token);
        if (map == null || System.currentTimeMillis() > map.get("exp").asDate().getTime()) {
            System.out.println("Got token --- return false 1");
            return false;
        }

        Integer uid = Integer.valueOf(map.get("user_id").asString());
        if (uid == null) {
            System.out.println("Got token --- return false 2");
            return false;
        }

        String tokenRedis = redisCacheConfig.getRedisTemplate().opsForValue().get(getRedisLoginKey(token));

        if (uid.toString().equals(tokenRedis)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 处理判断授权URI是否有访问权限
     * @param uri 没有参数的URI
     * @param request
     * @return 有访问权限；则返回：true；否则返回：false
     */
    private boolean handleAuthUri(String uri, HttpServletRequest request) {
        boolean is = true;
        if(StringUtils.isEmpty(uri) || CollectionUtils.isEmpty(authUriList)) {
            return is;
        }

        return is;
    }

    private String getRedisLoginKey(String token) {
        return RedisUtils.keyBuilder("user", "login", token);
    }

    /********getter and setter*******/
    public List<String> getExcludeMaps() {
        return excludeMaps;
    }

    public void setExcludeMaps(List<String> excludeMaps) {
        this.excludeMaps = excludeMaps;
    }

    public String getResSuffix() {
        return resSuffix;
    }

    public void setResSuffix(String resSuffix) {
        this.resSuffix = resSuffix;
    }

    public List<String> getAuthUriList() {
        return authUriList;
    }

    public void setAuthUriList(List<String> authUriList) {
        this.authUriList = authUriList;
    }

    public List<String> getExcludeAccessLogUrls() {
        return excludeAccessLogUrls;
    }

    public void setExcludeAccessLogUrls(List<String> excludeAccessLogUrls) {
        this.excludeAccessLogUrls = excludeAccessLogUrls;
    }
}
