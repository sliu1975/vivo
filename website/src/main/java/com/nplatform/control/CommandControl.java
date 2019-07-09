package com.nplatform.control;

import com.alibaba.fastjson.JSONObject;
import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.utils.HttpRequestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/command")
public class CommandControl {

    @Value("${forward.service.url}")
    private String forwardServiceUrl;

    @RequestMapping("/device")
    @ResponseBody
    public ApiResult message(HttpServletRequest request){
        String device = request.getParameter("device");
        String command = request.getParameter("command");

        if (StringUtils.isBlank(device) ||
            StringUtils.isBlank(command)) {
            return ApiResult.error(ErrorCode.INTERNAL_ERROR, "参数缺失");
        }

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        String response;
        try {
            JSONObject body = new JSONObject();
            body.put("device", device);
            body.put("command", command);

            response = HttpRequestUtils.post(forwardServiceUrl + "command", body.toJSONString(), headers);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR, "指令发送异常");
        }

        return ApiResult.success(response);
    }

}
