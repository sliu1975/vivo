package com.nplatform.control.info;

import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.dao.PolicyDetailInfoRepository;
import com.nplatform.entity.PolicyDetailInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/policyDetailInfo")
public class PolicyDetailInfoControl {
    @Resource
    private PolicyDetailInfoRepository policyDetailInfoRepository;

    @RequestMapping("/findAll")
    @ResponseBody
    public ApiResult findAll(){
        List<PolicyDetailInfo> result = policyDetailInfoRepository.findAll();
        return ApiResult.success(result);
    }

    @RequestMapping("/list")
    @ResponseBody
    public ApiResult ListPolicyDetailInfo(HttpServletRequest request){
        Map<String,Object> results=new HashMap<String,Object>();
        String pageindex=request.getParameter("pi");
        String pagetotal=request.getParameter("ps");

        int page=0;
        int pagecount=10;

        if(pageindex==null || pageindex.equals("0"))
            page=0;
        else
            page=Integer.parseInt(pageindex) - 1;

        if(pagetotal!=null)
            pagecount=Integer.parseInt(pagetotal);

        if(page==0) {
            String count = policyDetailInfoRepository.findAll().size() + "";
            results.put("count", count);
        }

        PageRequest pageRequest=new PageRequest(page,pagecount);

        Page<PolicyDetailInfo> result = policyDetailInfoRepository.findDataByPage(pageRequest);
        results.put("results",result.getContent());

        return ApiResult.success(results);
    }

    @RequestMapping("/getPolicyDetailInfo")
    @ResponseBody
    public ApiResult GetPolicyDetailInfo(HttpServletRequest request){
        String id=request.getParameter("id");

        PolicyDetailInfo policyDetailInfo=policyDetailInfoRepository.findOne(Integer.valueOf(id));
        return ApiResult.success(policyDetailInfo);
    }

    @RequestMapping("/getPolicyDetailInfoByPolicy")
    @ResponseBody
    public ApiResult GetPolicyDetailInfoByPolicy(HttpServletRequest request){
        String id=request.getParameter("policyid");

        PolicyDetailInfo policyDetailInfo=policyDetailInfoRepository.findByPolicy_id(id);
        return ApiResult.success(policyDetailInfo);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ApiResult add(PolicyDetailInfo policyDetailInfo) {
        try {
            if (policyDetailInfo.getPolicy_id() == null ||
                StringUtils.isBlank(policyDetailInfo.getDescription()) ||
                StringUtils.isBlank(policyDetailInfo.getLocation()) ||
                StringUtils.isBlank(policyDetailInfo.getRadius())) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
            }

            policyDetailInfo.setCreated_time(new Date());
            policyDetailInfo.setUpdate_time(new Date());

            policyDetailInfoRepository.save(policyDetailInfo);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ApiResult update(PolicyDetailInfo policyDetailInfo) {
        try {
            if (policyDetailInfo.getId() == null) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
            }

            PolicyDetailInfo info = policyDetailInfoRepository.findById(policyDetailInfo.getId());
            if (info == null) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "id不存在");
            }

            if (StringUtils.isBlank(policyDetailInfo.getDescription())) {
                policyDetailInfo.setDescription(info.getDescription());
            }
            if (StringUtils.isBlank(policyDetailInfo.getLocation())) {
                policyDetailInfo.setDescription(info.getLocation());
            }
            if (StringUtils.isBlank(policyDetailInfo.getRadius())) {
                policyDetailInfo.setDescription(info.getRadius());
            }

            policyDetailInfo.setCreated_time(info.getCreated_time());
            policyDetailInfo.setUpdate_time(new Date());
            policyDetailInfo.setPolicy_id(info.getPolicy_id());
            policyDetailInfoRepository.save(policyDetailInfo);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ApiResult delete(HttpServletRequest request) {
        String id=request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
        }
        try {
            policyDetailInfoRepository.delete(Integer.valueOf(id));
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

}
