package com.nplatform.control.info;

import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.dao.CompanyInfoRepository;
import com.nplatform.entity.CompanyInfo;
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
@RequestMapping("/companyInfo")
public class CompanyInfoControl {

    @Resource
    private CompanyInfoRepository companyInfoRepository;

    @RequestMapping("/findAll")
    @ResponseBody
    public ApiResult findAll(){
        List<CompanyInfo> result;
        try{
            result = companyInfoRepository.findAll();
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success(result);
    }

    @RequestMapping("/list")
    @ResponseBody
    public ApiResult list(HttpServletRequest request){
        Map<String,Object> results=new HashMap<String,Object>();
        String pageindex = request.getParameter("pi");
        String pagetotal = request.getParameter("ps");

        int page = 0;
        int pagecount = 10;

        try{
            if(pageindex == null){
                page = 0;
            }else{
                page = Integer.parseInt(pageindex) - 1;
            }

            if(pagetotal != null){
                pagecount = Integer.parseInt(pagetotal);
            }

            if(page == 0) {
                String count = companyInfoRepository.findAll().size() + "";
                results.put("count", count);
            }

            PageRequest pageRequest = new PageRequest(page,pagecount);

            Page<CompanyInfo> result = companyInfoRepository.findDataByPage(pageRequest);
            results.put("results",result.getContent());
        }catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success(results);
    }

    @RequestMapping("/getCompanyInfo")
    @ResponseBody
    public ApiResult get(HttpServletRequest request){
        String id = request.getParameter("id");

        if(StringUtils.isBlank(id)){
            return ApiResult.error(ErrorCode.INVALID_PARAMETER,"ID错误，请重新输入");
        }

        CompanyInfo companyInfo = companyInfoRepository.findById(id);
        return ApiResult.success(companyInfo);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ApiResult add(HttpServletRequest request,CompanyInfo companyInfo) {
        try {
            if (StringUtils.isBlank(companyInfo.getName()) ||
                StringUtils.isBlank(companyInfo.getShort_name()) ||
                StringUtils.isBlank(companyInfo.getLocation())) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
            }

            companyInfo.setCreated_time(new Date());
            companyInfo.setUpdate_time(new Date());

            companyInfoRepository.save(companyInfo);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ApiResult update(HttpServletRequest request,CompanyInfo companyInfo) {
        try {
            CompanyInfo info = companyInfoRepository.findById(companyInfo.getId());
            if(null == info){
                return ApiResult.error(ErrorCode.INVALID_PARAMETER,"公司不存在");
            }
            if(StringUtils.isBlank(companyInfo.getName())){
                companyInfo.setName(info.getName());
            }
            if(StringUtils.isBlank(companyInfo.getShort_name())){
                companyInfo.setShort_name(info.getShort_name());
            }
            if(StringUtils.isBlank(companyInfo.getLocation())){
                companyInfo.setLocation(info.getLocation());
            }
            if(StringUtils.isBlank(companyInfo.getStreet())){
                companyInfo.setStreet(info.getStreet());
            }
            if(StringUtils.isBlank(companyInfo.getCountry())){
                companyInfo.setCountry(info.getCountry());
            }
            if(StringUtils.isBlank(companyInfo.getContact())){
                companyInfo.setContact(info.getContact());
            }
            if(StringUtils.isBlank(companyInfo.getTelephone())){
                companyInfo.setTelephone(info.getTelephone());
            }
            if(StringUtils.isBlank(companyInfo.getLogo())){
                companyInfo.setLogo(info.getLogo());
            }
            if(StringUtils.isBlank(companyInfo.getWebsite())){
                companyInfo.setWebsite(info.getWebsite());
            }
            if(StringUtils.isBlank(companyInfo.getEmail())){
                companyInfo.setEmail(info.getEmail());
            }

            companyInfo.setCreated_time(info.getCreated_time());
            companyInfo.setUpdate_time(new Date());
            companyInfoRepository.save(companyInfo);
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
            companyInfoRepository.delete(id);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }
        return ApiResult.success();
    }

}
