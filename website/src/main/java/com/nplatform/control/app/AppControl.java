package com.nplatform.control.app;

import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.dao.ActivityRepository;
import com.nplatform.entity.Activity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/app")
public class AppControl {
    @Resource
    private ActivityRepository activityRepository;

    @RequestMapping("/{app}")
    public String pageIndex(HttpServletRequest request,@PathVariable("app") String app){

        Activity activity=activityRepository.findById(app);

        if(activity!=null)
        {
            request.setAttribute("activity",activity);

            request.setAttribute("app",activity.getId());
            request.setAttribute("count",activity.getParticipant()+1);
            request.setAttribute("title",activity.getTitle());

            activity.setParticipant(activity.getParticipant()+1);

            activityRepository.save(activity);

            return "/jsp/app/index";
        }

        return "/jsp/app/index";
    }

    @RequestMapping("/demo/{app}")
    public String demo(HttpServletRequest request,@PathVariable("app") String app){

        Activity activity=activityRepository.findById(app);

        if(activity!=null)
        {
            request.setAttribute("activity",activity);

            request.setAttribute("picture",activity.getPicture());
            request.setAttribute("count",activity.getParticipant()+1);
            request.setAttribute("title",activity.getTitle());

            return "/jsp/app/index";
        }

        return "/jsp/app/index";
    }

    @RequestMapping("/share/{app}")
    @ResponseBody
    public ApiResult share(HttpServletRequest request,@PathVariable("app") String app){

        try {
            //Activity activity = activityRepository.findById(app);

            //activity.setParticipant(activity.getParticipant()+1);

            //activityRepository.save(activity);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

}
