package com.nplatform.control.activity;

import com.nplatform.bean.ApiResult;
import com.nplatform.constant.ErrorCode;
import com.nplatform.dao.ActivityRepository;
import com.nplatform.entity.Activity;
import com.nplatform.entity.PolicyInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/activity")
public class ActivityControl {
    @Resource
    private ActivityRepository activityRepository;

    @Value("${upload.path}")
    private String uploadPath;

    @RequestMapping("/index")
    public String pageIndex(HttpServletRequest request){
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
            String count = activityRepository.findAll().size() + "";
            results.put("count", count);
        }

        PageRequest pageRequest=new PageRequest(page,pagecount);

        Page<Activity> result = activityRepository.findDataByPage(pageRequest);
        results.put("results",result.getContent());

        request.setAttribute("data",results);

        return "/jsp/activity/index";
    }

    @RequestMapping("/toadd")
    public String toadd(HttpServletRequest request){

        return "/jsp/activity/add";
    }

    @RequestMapping("/addActivity")
    public String addActivity(HttpServletRequest request){

        try {
            if (StringUtils.isBlank(request.getParameter("name")) ||
                    StringUtils.isBlank(request.getParameter("type"))
                    )
            {
                request.setAttribute("message","请输入必要的内容!");
                return "/jsp/activity/add";
            }

            Activity activity=new Activity();

            activity.setName(request.getParameter("name"));
            activity.setType(request.getParameter("type"));
            activity.setDescription(request.getParameter("description"));
            activity.setTitle(request.getParameter("title"));

            if(request.getParameter("x")!=null&&request.getParameter("x")!="") {
                activity.setCountx(Integer.parseInt(request.getParameter("x")));
            }
            if(request.getParameter("y")!=null&&request.getParameter("y")!="") {
                activity.setCounty(Integer.parseInt(request.getParameter("y")));
            }

            if(request.getParameter("color")!=null&&request.getParameter("color")!="") {
                activity.setColor(request.getParameter("color"));
            }
            else
            {
                activity.setColor(request.getParameter("#000"));
            }

            activity.setParticipant(0);
            activity.setStatus("1");

            if(request.getParameter("startDate")!=null&&request.getParameter("startDate")!="")
            {
                activity.setStartDate(request.getParameter("startDate"));
            }

            if(request.getParameter("endDate")!=null&&request.getParameter("endDate")!="")
            {
                activity.setEndDate(request.getParameter("endDate"));
            }

            int i;
            String key="",path="";

            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request))
            {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;

                //获取multiRequest 中所有的文件名
                Iterator iter= null; //multiRequest.getFileNames();

                Map<String,MultipartFile> map=multiRequest.getFileMap();
                List<String> keyList=new ArrayList<String>();

                keyList.add("picture");

                if(map!=null)
                {
                    MultipartFile file=null;

                    for(i=0;i<keyList.size();i++)
                    {
                        key=keyList.get(i);
                        if(map.containsKey(key))
                        {
                            file=map.get(key);
                        }

                        if(file!=null&&file.getSize()>0)
                        {
                            System.out.println(" addActivity file is " + file);

                            File imgFile=new File(uploadPath+File.separator);
                            if(!imgFile.exists())
                                imgFile.mkdirs();

                            String rand=UUID.randomUUID().toString().substring(1,16)+ file.getOriginalFilename();
                            path=uploadPath+File.separator+rand;

                            System.out.println(" addActivity path is " + path);

                            //上传
                            try {
                                file.transferTo(new File(path));

                                if(key.equals("picture"))
                                    activity.setPicture(rand);
                            } catch (IllegalStateException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }

            activity.setCreated_time(new Date());
            activity.setUpdate_time(new Date());
            activity.setId(null);

            activityRepository.save(activity);
        } catch(Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message","请输入必要的内容!");
            return "/jsp/activity/add";
        }

        return "redirect:/activity/index";
    }

    @RequestMapping("/editActivity")
    public String editActivity(HttpServletRequest request){

        String id=request.getParameter(("id"));

        Activity activity = activityRepository.findById(id);

        try {

            if (StringUtils.isBlank(request.getParameter("name")) ||
                    StringUtils.isBlank(request.getParameter("type"))
                    )
            {
                request.setAttribute("activity",activity);
                request.setAttribute("message","请输入必要的内容!");
                return "/jsp/activity/edit";
            }

            activity.setName(request.getParameter("name"));
            activity.setType(request.getParameter("type"));
            activity.setDescription(request.getParameter("description"));
            activity.setTitle(request.getParameter("title"));

            if(request.getParameter("x")!=null&&request.getParameter("x")!="") {
                activity.setCountx(Integer.parseInt(request.getParameter("x")));
            }
            if(request.getParameter("y")!=null&&request.getParameter("y")!="") {
                activity.setCounty(Integer.parseInt(request.getParameter("y")));
            }

            if(request.getParameter("color")!=null&&request.getParameter("color")!="") {
                activity.setColor(request.getParameter("color"));
            }
            else
            {
                activity.setColor(request.getParameter("#000"));
            }

            activity.setParticipant(0);
            activity.setStatus("1");

            if(request.getParameter("startDate")!=null&&request.getParameter("startDate")!="")
            {
                activity.setStartDate(request.getParameter("startDate"));
            }

            if(request.getParameter("endDate")!=null&&request.getParameter("endDate")!="")
            {
                activity.setEndDate(request.getParameter("endDate"));
            }

            int i;
            String key="",path="";

            //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
            CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(request.getSession().getServletContext());
            //检查form中是否有enctype="multipart/form-data"
            if(multipartResolver.isMultipart(request))
            {
                //将request变成多部分request
                MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;

                //获取multiRequest 中所有的文件名
                Iterator iter= null; //multiRequest.getFileNames();

                Map<String,MultipartFile> map=multiRequest.getFileMap();
                List<String> keyList=new ArrayList<String>();

                keyList.add("picture");

                if(map!=null)
                {
                    MultipartFile file=null;

                    for(i=0;i<keyList.size();i++)
                    {
                        key=keyList.get(i);
                        if(map.containsKey(key))
                        {
                            file=map.get(key);
                        }

                        if(file!=null&&file.getSize()>0)
                        {
                            System.out.println(" addActivity file is " + file);

                            File imgFile=new File(uploadPath+File.separator);
                            if(!imgFile.exists())
                                imgFile.mkdirs();

                            String rand=UUID.randomUUID().toString().substring(1,16)+ file.getOriginalFilename();
                            path=uploadPath+File.separator+rand;

                            System.out.println(" addActivity path is " + path);

                            //上传
                            try {
                                file.transferTo(new File(path));

                                if(key.equals("picture"))
                                    activity.setPicture(rand);
                            } catch (IllegalStateException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            } catch (IOException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }

            //activity.setCreated_time(new Date());
            activity.setUpdate_time(new Date());
            //activity.setId(null);

            activityRepository.save(activity);
        } catch(Exception ex) {
            ex.printStackTrace();
            request.setAttribute("activity",activity);
            request.setAttribute("message","请输入必要的内容!");
            return "/jsp/activity/edit";
        }

        return "redirect:/activity/index";
    }

    @RequestMapping("/toedit")
    public String toedit(HttpServletRequest request){
        String id=request.getParameter(("id"));

        Activity activity = activityRepository.findById(id);

        request.setAttribute("activity",activity);

        return "/jsp/activity/edit";
    }

    @RequestMapping("/tostop")
    public String tostop(HttpServletRequest request){
        String id=request.getParameter(("id"));

        Activity activity = activityRepository.findById(id);

        if(activity!=null) {
            activity.setStatus("4");
            activity.setUpdate_time(new Date());

            activityRepository.save(activity);
        }

        return "redirect:/activity/index";
    }

    @RequestMapping("/history")
    public String history(HttpServletRequest request){
        String id=request.getParameter(("id"));

        Activity activity = activityRepository.findById(id);

        request.setAttribute("activity",activity);

        return "/jsp/activity/history";
    }

    @RequestMapping("/summary")
    public String summary(HttpServletRequest request){
        String id=request.getParameter(("id"));

        Activity activity = activityRepository.findById(id);

        request.setAttribute("activity",activity);

        return "/jsp/activity/summary";
    }

    @RequestMapping("/browse")
    public String browse(HttpServletRequest request){

        String id=request.getParameter(("id"));

        Activity activity = activityRepository.findById(id);

        request.setAttribute("activity",activity);

        return "/jsp/activity/browse";
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public ApiResult findAll(){
        List<Activity> result = activityRepository.findAll();

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
            String count = activityRepository.findAll().size() + "";
            results.put("count", count);
        }

        PageRequest pageRequest=new PageRequest(page,pagecount);

        Page<Activity> result = activityRepository.findDataByPage(pageRequest);
        results.put("results",result.getContent());

        return ApiResult.success(results);
    }

    @RequestMapping("/getActivity")
    @ResponseBody
    public ApiResult getActivity(HttpServletRequest request){
        String id=request.getParameter("id");

        Activity activity=activityRepository.findById(id);
        return ApiResult.success(activity);
    }

    @RequestMapping("/add")
    @ResponseBody
    public ApiResult add(Activity activity) {
        try {
            if (StringUtils.isBlank(activity.getName()) ||
                    StringUtils.isBlank(activity.getDescription()) ||
                    StringUtils.isBlank(activity.getType())
            ) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
            }

            activity.setCreated_time(new Date());
            activity.setUpdate_time(new Date());
            activity.setId(null);

            activityRepository.save(activity);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

    @RequestMapping("/update")
    @ResponseBody
    public ApiResult update(Activity activity) {
        try {
            if (activity.getId() == null) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "参数缺失");
            }

            Activity info = activityRepository.findById(activity.getId());
            if (info == null) {
                return ApiResult.error(ErrorCode.BUSINESS_ERROR, "id不存在");
            }

            if (StringUtils.isBlank(activity.getDescription())) {
                activity.setDescription(info.getDescription());
            }
            if (StringUtils.isBlank(activity.getType())) {
                activity.setType(info.getType());
            }
            if (StringUtils.isBlank(activity.getOwner())) {
                activity.setOwner(info.getOwner());
            }
            if (StringUtils.isBlank(activity.getStartDate())) {
                activity.setStartDate(info.getStartDate());
            }
            if (StringUtils.isBlank(activity.getEndDate())) {
                activity.setEndDate(info.getEndDate());
            }

            activity.setStartDate(info.getStartDate());
            activity.setEndDate(info.getEndDate());
            activity.setCreated_time(info.getCreated_time());
            activity.setUpdate_time(new Date());

            activityRepository.save(activity);
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

            activityRepository.delete(id);
        } catch(Exception ex) {
            ex.printStackTrace();
            return ApiResult.error(ErrorCode.INTERNAL_ERROR);
        }

        return ApiResult.success();
    }

}
