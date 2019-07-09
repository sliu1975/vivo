package com.nplatform.control.info;

import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.dao.PolicyInfoRepository;
import com.nplatform.entity.PolicyInfo;
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
@RequestMapping("/policyInfo")
public class PolicyInfoControl {
    @Resource
    private PolicyInfoRepository policyInfoRepository;

    @RequestMapping("/findAll")
    @ResponseBody
    public ApiResult findAll(){
        List<PolicyInfo> result = policyInfoRepository.findAll();

        return ApiResult.success(result);
    }

    @RequestMapping("/list")
    @ResponseBody
    public ApiResult ListPolicyInfo(HttpServletRequest request){
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
            String count = policyInfoRepository.findAll().size() + "";
            results.put("count", count);
        }

        PageRequest pageRequest=new PageRequest(page,pagecount);

        Page<PolicyInfo> result = policyInfoRepository.findDataByPage(pageRequest);
        results.put("results",result.getContent());

        return ApiResult.success(results);
    }

    @RequestMapping("/getPolicyInfo")
    @ResponseBody
    public ApiResult GetPolicyInfo(HttpServletRequest request){
        String id=request.getParameter("id");

        PolicyInfo policyInfo=policyInfoRepository.findById(id);
        return ApiResult.success(policyInfo);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ApiResult add(PolicyInfo policyInfo) {
        try {
            if (StringUtils.isBlank(policyInfo.getPolicy_name()) ||
                    StringUtils.isBlank(policyInfo.getDescription()) ||
                    StringUtils.isBlank(policyInfo.getType()) ||
                    StringUtils.isBlank(policyInfo.getOwner()) ||
                    policyInfo.getChannel_id() ==  null
            ) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
            }

            policyInfo.setCreated_time(new Date());
            policyInfo.setUpdate_time(new Date());
            policyInfo.setId(null);

            policyInfoRepository.save(policyInfo);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ApiResult update(PolicyInfo policyInfo) {
        try {
            if (policyInfo.getId() == null) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
            }

            PolicyInfo info = policyInfoRepository.findById(policyInfo.getId());
            if (info == null) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "id不存在");
            }

            if (StringUtils.isBlank(policyInfo.getDescription())) {
                policyInfo.setDescription(info.getDescription());
            }
            if (StringUtils.isBlank(policyInfo.getType())) {
                policyInfo.setType(info.getType());
            }
            if (StringUtils.isBlank(policyInfo.getOwner())) {
                policyInfo.setOwner(info.getOwner());
            }
            if (policyInfo.getChannel_id() == null) {
                policyInfo.setChannel_id(info.getChannel_id());
            }

            policyInfo.setPolicy_name(info.getPolicy_name());
            policyInfo.setCreated_time(info.getCreated_time());
            policyInfo.setUpdate_time(new Date());

            policyInfoRepository.save(policyInfo);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public ApiResult delete(HttpServletRequest request) {

        try {
            String id = request.getParameter("id");
            if(StringUtils.isBlank(id)){
                return ApiResult.error(ErrorCode.INVALID_PARAMETER,"参数错误");
            }

            policyInfoRepository.delete(id);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

}
