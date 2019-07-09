package com.nplatform.control.info;

import com.alibaba.fastjson.JSON;
import com.nplatform.bean.ApiResult;
import com.nplatform.utils.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 转发请求到客户的服务
 */
@Controller
public class ForwardControl {
  private final static Logger log = LoggerFactory.getLogger(ForwardControl.class);

  @RequestMapping("/forward/{path1}")
  @ResponseBody
  public ApiResult forward(HttpServletRequest request, @PathVariable("path1") String path1){
    String currentUri =path1;

    return ApiResult.success(get(request, currentUri));
  }

  @RequestMapping("/forward/{path1}/{path2}")
  @ResponseBody
  public ApiResult forward(HttpServletRequest request, @PathVariable("path1") String path1,
      @PathVariable("path2") String path2){
    String currentUri = path1 + "/" + path2;

    return ApiResult.success(get(request, currentUri));
  }

  @RequestMapping("/forward/{path1}/{path2}/{path3}")
  @ResponseBody
  public ApiResult forward(HttpServletRequest request, @PathVariable("path1") String path1,
      @PathVariable("path2") String path2,
      @PathVariable("path3") String path3){
    String currentUri = path1 + "/" + path2 + "/" + path3;

    return ApiResult.success(get(request, currentUri));
  }

  //@Value("${forward.service.url}")
  @Value("${forward.service.url}")
  private String forwardServiceUrl;

  private Object get(HttpServletRequest request, String currentUri) {
    StringBuilder getUrl = new StringBuilder(forwardServiceUrl);
    Map map = request.getParameterMap();
    if (!forwardServiceUrl.endsWith("/")) {
      getUrl.append("/");
    }
    getUrl.append(currentUri).append("?");
    Set<String> entrySet = map.keySet();
    Iterator<String> iterator = entrySet.iterator();
    while (iterator.hasNext()) {
      String key = iterator.next();
      getUrl.append(key).append("=").append(request.getParameter(key)).append("&");
    }

    long start = System.currentTimeMillis();
    String response = null;
    try {
      response = HttpRequestUtils.get(getUrl.toString());
    } catch (IOException e) {
      e.printStackTrace();
    }
    long end = System.currentTimeMillis();
    log.info("---------------------- url = {}, useTime = {}", getUrl.toString(), (end - start));

    return JSON.parse(response);
  }

}
