package com.nplatform.control;

import com.nplatform.dao.ActivityRepository;
import com.nplatform.dao.UserRepository;
import com.nplatform.entity.Activity;
import com.nplatform.entity.User;
import com.nplatform.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class Home {
    @Autowired
    private UserRepository userRepository;

    @Resource
    private ActivityRepository activityRepository;

    @RequestMapping("/login")
    public String pageLogin(){
        System.out.println("into login...");
        return "login";
    }

    @RequestMapping("/dologin")
    public String doLogin(HttpServletRequest request,RedirectAttributes redirectAttributes){

        String userid,password,roles;

        userid=request.getParameter("userid");
        password=request.getParameter("password");

        //IdentityService userCheck = engine.getIdentityService();
        //boolean check = userCheck.checkPassword(userid, password);

        String depassword= MD5.getResult(password);

        User user = userRepository.findByUserName(userid);

        boolean check =false;

        if(user!=null) {
            if(user.getPassword().equals(depassword))
                check = true;
        }

        if (check)
        {

            request.setAttribute("user", user);
            request.setAttribute("userid", userid);
            request.setAttribute("displayname", user.getDisplayname());

            request.getSession().setAttribute("userid", userid);

            request.getSession().setAttribute("username", user.getUserName());
            request.getSession().setAttribute("displayname", user.getDisplayname());


            if(user.getCategory().equals("admin")||user.getCategory().equals("expert")) {
                return "redirect:/home";
            }

            return "redirect:/home";
        }
        else
        {
            redirectAttributes.addFlashAttribute("message", "登录失败，请检查用户名和密码");
            return "login";

        }
    }

    @RequestMapping("/")
    public String index(HttpServletRequest request){
        System.out.println("into /...");

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

        return "index";
    }

    @RequestMapping("/home")
    public String pageHome(HttpServletRequest request){
        System.out.println("into /...");

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

        return "index";
    }

    @RequestMapping("/404")
    public String page404(){
        return "/404";
    }

    @RequestMapping("/500")
    public String page500(){
        return "/500";
    }

    @RequestMapping("/dologout")
    public String doLogout(HttpServletRequest request){

        request.getSession().setAttribute("userid", null);
        request.getSession().setAttribute("username", null);

        return "login";

    }

    public static void main(String[] args)
    {
        String depassword= MD5.getResult("123456");

        System.out.println(depassword);
    }

}
